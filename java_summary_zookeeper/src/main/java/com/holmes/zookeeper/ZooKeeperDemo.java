package com.holmes.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class ZooKeeperDemo {

    private final static Logger log = LoggerFactory.getLogger(ZooKeeper.class);

    private final static String CONNECTION_STRING = "127.0.0.1:2181";

    private final static int SESSION_TIMEOUT = 30000;

    private ZooKeeper zooKeeper = null;

    @Before
    public void init() throws IOException {

        zooKeeper = new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数
                log.info(event.getType() + "---" + event.getPath());
            }
        });
    }

    @Test
    public void test() {
        log.info("zookeeper client: {}", zooKeeper);
    }

    /**
     * 创建节点
     */
    @Test
    public void create() {
        try {
            String result = zooKeeper.create("/node1", "Hello World".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            log.info("result: {}", result);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        zooKeeper.create("/node2", "Hello World".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//                CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
//                    @Override
//                    public void processResult(int rc, String path, Object ctx, String name) {
//                        log.info("rc: " + rc + ", path: " + path + ", ctx: " + ctx + ", name: " + name);
//                    }
//                }, null);
    }

    /**
     * 删除节点
     */
    @Test
    public void delete() {

        try {
            zooKeeper.delete("/node1", 0);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        zooKeeper.delete("/node1", 0, new AsyncCallback.VoidCallback() {
//            @Override
//            public void processResult(int rc, String path, Object ctx) {
//                log.info("rc: " + rc + ", path: " + path + ", ctx: " + ctx);
//            }
//        }, null);
    }

    @Test
    public void deleteAll() {
    }

    @Test
    public void getData() {
        try {
            Stat stat = new Stat();
            byte[] data = zooKeeper.getData("/node1", false, stat);
            if(data != null) {
                log.info("data: {}, stat: {}", new String(data), stat.toString());
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setData() {
        try {
            Stat stat = zooKeeper.setData("/node1", "new data".getBytes(), 0);
            log.info("stat: {}", stat);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void exists() {
        try {
            Stat stat = zooKeeper.exists("/node1", false);
            log.info("stat: {}", stat);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() throws InterruptedException {
        zooKeeper.close();
        TimeUnit.SECONDS.sleep(10);
    }
}
