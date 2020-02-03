package com.holmes.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkClientDemo {

    private final static Logger log = LoggerFactory.getLogger(ZkClientDemo.class);

    private final static String ZK_SERVER = "127.0.0.1:2181";

    private final static int CONNECTION_TIMEOUT = 30000;

    private final static int SESSION_TIMEOUT = 30000;

    private final static int OPERATION_RETRY_TIMEOUT = 30000;

    private ZkClient zkClient;

    @Before
    public void init() {
        zkClient = new ZkClient(ZK_SERVER, SESSION_TIMEOUT, CONNECTION_TIMEOUT,
                new SerializableSerializer(), OPERATION_RETRY_TIMEOUT);
    }

    @Test
    public void test() {
        log.info("zookeeper client: {}", zkClient);
    }
}
