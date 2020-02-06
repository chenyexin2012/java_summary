package com.holmes.zookeeper.clients.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorFrameworkCreate {

    private final static String CONNECTION_STRING = "127.0.0.1:2181";

    private final static int CONNECTION_TIMEOUT = 30000;

    private final static int SESSION_TIMEOUT = 30000;

    private final static int BASE_SLEEP_TIME = 10000;

    private final static int MAX_RETRIES = 3;

    private final static CuratorFramework curatorFramework;

    static  {
        // 重试策略
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES, Integer.MAX_VALUE);
        curatorFramework = CuratorFrameworkFactory.newClient(CONNECTION_STRING, SESSION_TIMEOUT, CONNECTION_TIMEOUT, retryPolicy);
        // 需要调用start开启客户端
        curatorFramework.start();
    }

    public static CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

}
