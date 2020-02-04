package com.holmes.zookeeper.clients;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.I0Itec.zkclient.serialize.ZkSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /**
     *
     */
    @Test
    public void subscribe () {

        // 使用自定义序列化方式
        zkClient.setZkSerializer(new ZkSerializer() {
            @Override
            public byte[] serialize(Object data) throws ZkMarshallingError {
                return String.valueOf(data).getBytes();
            }

            @Override
            public Object deserialize(byte[] bytes) throws ZkMarshallingError {
                return new String(bytes);
            }
        });
        // 监听节点变化
        zkClient.subscribeDataChanges("/zkClient", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                log.info("{} 节点发生变化, 变更的内容为： {}", dataPath, data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                log.info("{} 节点被删除", dataPath);
            }
        });

        // 监听节点的子节点
        zkClient.subscribeChildChanges("/zkClient", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChildren) throws Exception {
                log.info("{} 的子节点发生变化, 子节点为: {}", parentPath, currentChildren);
            }
        });

        // 监听服务状态
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {

                switch (state) {
                    case SyncConnected:
                        log.info("连接成功");
                        break;
                    case Disconnected:
                        log.info("连接断开");
                        break;
                    default:
                        log.info("state: {}", state);
                }
            }

            @Override
            public void handleNewSession() throws Exception {
                log.info("重建session");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable error) throws Exception {
                log.info("重建session发生错误");
            }
        });

        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
