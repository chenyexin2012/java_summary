package com.holmes.zookeeper.clients;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES, Integer.MAX_VALUE);
        curatorFramework = CuratorFrameworkFactory.newClient(CONNECTION_STRING, SESSION_TIMEOUT, CONNECTION_TIMEOUT, retryPolicy);
        curatorFramework.start();
    }

    @Test
    public void test() {
//        log.info("zookeeper client: {}", client);
        log.info("curatorFramework: {}", curatorFramework);
    }

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

    @Test
    public void getData() {
        try {
            byte[] data = curatorFramework.getData().forPath("/curator/child001");
            log.info("data: {}", new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete() {

    }
}
