package com.holmes.zookeeper.distributelock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZookeeperDistributeLock extends AbstractZookeeperDistributeLock {

    private final static Logger log = LoggerFactory.getLogger(ZookeeperDistributeLock.class);

    private final static String LOCK_PRE = "/distribute_lock_";

    private final static String LOCK_PATH = "/distribute_lock";

    private ZooKeeper zooKeeper;

    private String connectionString;

    private int sessionTimeout;

    private String path;

    private long timeout;

    private long expireTime;

    private String currentPath = null;

    private CountDownLatch countDownLatch = null;

    public ZookeeperDistributeLock(String connectionString, int sessionTimeout, String name, long timeout, long expireTime) {
        this.connectionString = connectionString;
        this.sessionTimeout = sessionTimeout;
        this.path = LOCK_PRE + name;
        this.timeout = timeout;
        this.expireTime = expireTime;
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        try {
            this.zooKeeper = new ZooKeeper(connectionString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                }
            });
            Stat stat = this.zooKeeper.exists(this.path, false);
            if (stat == null) {
                try {
                    // 创建一个容器节点
                    this.zooKeeper.create(path, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.CONTAINER);
                } catch (KeeperException | InterruptedException e) {
                    // do nothing
                }
            }
        } catch (IOException | KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected boolean getLock() {
        try {
            if (null == currentPath) {
                // 创建一个自增序列的子节点
                String childPath = this.path + LOCK_PATH;
                this.currentPath = this.zooKeeper.create(childPath, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
                log.info("{}创建了节点: {}", Thread.currentThread().getName(), this.currentPath);
            }
            // 获取所有子节点
            List<String> children = this.zooKeeper.getChildren(this.path, false);
            if (null != children && children.size() > 0) {
                // 对子节点进行排序
                children.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
                // 获取排序后的第一个节点
                String childPath = children.get(0);
                if (this.currentPath.equals(this.path + "/" + childPath)) {
                    // 当第一个节点为当前节点则获取锁成功
                    return true;
                } else {
                    // 如果当前节点不是第一个节点，则监听前一个节点
                    int index = Collections.binarySearch(children, currentPath.substring(this.path.length() + 1));
                    String prePath = this.path + "/" + children.get(index - 1);
                    countDownLatch = new CountDownLatch(1);
                    Stat stat = zooKeeper.exists(prePath, new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            if (event.getType() == Event.EventType.NodeDeleted) {
                                log.info("{} 节点被删除", prePath);
                                countDownLatch.countDown();
                            }
                        }
                    });
                    if(stat == null) {
                        // 前一个节点已被删除
                        return true;
                    }
                    return false;
                }
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void waitLock() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void deleteNode() {
        try {
            zooKeeper.delete(currentPath, 0);
            this.currentPath = null;
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
