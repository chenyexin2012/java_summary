package com.holmes.zookeeper.clients.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class CuratorDemo {

    private final static Logger log = LoggerFactory.getLogger(CuratorDemo.class);

    private final static String CONNECTION_STRING = "127.0.0.1:2181";

    private final static int CONNECTION_TIMEOUT = 30000;

    private final static int SESSION_TIMEOUT = 30000;

    private final static int BASE_SLEEP_TIME = 10000;

    private final static int MAX_RETRIES = 3;

    private CuratorFramework curatorFramework = null;

    @Before
    public void init() {
        // 重试策略
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES, Integer.MAX_VALUE);
        curatorFramework = CuratorFrameworkFactory.newClient(CONNECTION_STRING, SESSION_TIMEOUT, CONNECTION_TIMEOUT, retryPolicy);
        // 需要调用start开启客户端
        curatorFramework.start();
    }

    @Test
    public void test() {
        log.info("curatorFramework: {}", curatorFramework);
    }

    /**
     * 创建节点
     */
    @Test
    public void create() {
        try {
            String result = curatorFramework.create()
                    .creatingParentsIfNeeded() // 递归创建，如果父节点不存在，自动创建父节点
                    .withMode(CreateMode.PERSISTENT) // 创建持久节点
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE) // /设置ACL,和原生API相同
                    .forPath("/curator/child001", "123456".getBytes());
            log.info("result: {}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取节点数据
     */
    @Test
    public void getData() {
        try {
            byte[] data = curatorFramework.getData().forPath("/curator/child001");
            log.info("data: {}", new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除节点
     */
    @Test
    public void delete() {
        try {
            curatorFramework.delete()
                    .deletingChildrenIfNeeded() // 存在子节点则一并删除
                    .forPath("/curator");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断节点是否存在
     */
    @Test
    public void exists() {
        try {
            Stat stat = curatorFramework.checkExists().forPath("/curator");
            log.info("stat: {}", stat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void watcher() {
        try {
            curatorFramework.checkExists()
                    // 添加监视器，只作用一次
                    .usingWatcher(new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            log.info(event.toString());
                        }
                    })
                    .forPath("/curator");

            curatorFramework.checkExists()
                    // 添加监视器，只作用一次
                    .usingWatcher(new CuratorWatcher() {
                        @Override
                        public void process(WatchedEvent event) throws Exception {
                            log.info(event.toString());
                        }
                    })
                    .forPath("/curator");
            TimeUnit.SECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listener() {

        try {
            // 监听一个特定节点
            NodeCache nodeCache = new NodeCache(curatorFramework, "/curator");
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    log.info("节点/curator发生改变");
                }
            });
            nodeCache.start();

            // 监听子节点
            PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/curator", false);
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    log.info("节点/curator的子节点发生改变：{}", event);
                }
            });
            pathChildrenCache.start();

            // 同时监听节点和子节点
            TreeCache treeCache = new TreeCache(curatorFramework, "/curator");
            treeCache.getListenable().addListener(new TreeCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                    log.info("节点/curator或其子节点发生改变：{}", event);
                }
            });
            treeCache.start();

            TimeUnit.SECONDS.sleep(1000);
            nodeCache.close();
            pathChildrenCache.close();
            treeCache.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void destroy() {
        curatorFramework.close();
    }
}
