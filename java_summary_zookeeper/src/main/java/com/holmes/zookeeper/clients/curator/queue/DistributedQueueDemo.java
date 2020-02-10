package com.holmes.zookeeper.clients.curator.queue;

import com.holmes.zookeeper.clients.curator.CuratorFrameworkCreate;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.queue.DistributedQueue;
import org.apache.curator.framework.recipes.queue.QueueBuilder;
import org.apache.curator.framework.recipes.queue.QueueConsumer;
import org.apache.curator.framework.recipes.queue.QueueSerializer;
import org.apache.curator.framework.state.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class DistributedQueueDemo {

    private final static Logger log = LoggerFactory.getLogger(DistributedQueueDemo.class);

    public static void main(String[] args) {

        QueueConsumer queueConsumer = new QueueConsumer() {
            @Override
            public void consumeMessage(Object message) throws Exception {
                log.info("consumer message: {}", message);
            }
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                log.info("connection new state: {}", newState.name());
            }
        };

        QueueSerializer<String> stringQueueSerializer = new QueueSerializer<String>() {
            @Override
            public byte[] serialize(String item) {
                return item.getBytes();
            }

            @Override
            public String deserialize(byte[] bytes) {
                return new String(bytes);
            }
        };

        DistributedQueue distributedQueue = QueueBuilder.builder(CuratorFrameworkCreate.getCuratorFramework(), queueConsumer,
                stringQueueSerializer, "/queue").buildQueue();
        try {
            distributedQueue.start();
            for(int i = 0; i < 100; i++) {
                distributedQueue.put(UUID.randomUUID().toString());
            }
            distributedQueue.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
