package com.holmes.zookeeper.clients;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
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

    /**
     * 创建节点
     */
    @Test
    public void create() {

        String result = zkClient.create("/zkClient", "Hello World", ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
        log.info("result: {}", result);

        zkClient.createPersistent("/zkClient/node", "Hello World");

        result = zkClient.createPersistentSequential("/zkClient/node", "Hello World");
        log.info("result: {}", result);

        result = zkClient.createPersistentSequential("/zkClient/node", "Hello World");
        log.info("result: {}", result);
    }

    /**
     * 删除节点
     */
    @Test
    public void delete() {
        boolean result = zkClient.delete("/zkClient/node");
        log.info("result: {}", String.valueOf(result));
    }

    /**
     * 递归删除
     */
    @Test
    public void deleteAll() {
        boolean result = zkClient.deleteRecursive("/zkClient");
        log.info("result: {}", String.valueOf(result));
    }

    /**
     * 修改节点值
     */
    @Test
    public void setData() {
        zkClient.writeData("/zkClient", "new data");
    }

    /**
     * 获取节点值
     */
    @Test
    public void getData() {
        String data = zkClient.readData("/zkClient");
        log.info("data: {}", data);
    }
}
