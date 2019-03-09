package com.java.summary.kafka.api;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Administrator
 */
public class Consumer {

    static Logger log = Logger.getLogger(Producer.class);

    private static final String TOPIC_001 = "test-kafka-001";

    private static final String TOPIC_002 = "test-kafka-002";

    private static final String TOPIC_003 = "test-kafka-003";

    private static final String BROKER_LIST = "127.0.0.1:9092";

    private static KafkaConsumer<String, String> consumer = null;

    static {
        Properties configs = initConfig();
        consumer = new KafkaConsumer<String, String>(configs);
        consumer.subscribe(Arrays.asList(new String[]{TOPIC_001, TOPIC_002, TOPIC_003}));
    }

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", BROKER_LIST);
        properties.put("group.id", "0");
        // 设置不自动提交
        properties.put("enable.auto.commit", "false");
//        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        return properties;
    }

    public static void main(String[] args) {

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(10);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                System.out.println("topic : " + record.topic());
                System.out.println("headers : " + record.headers());
                System.out.println("offset : " + record.offset());
                System.out.println("partition : " + record.partition());
                System.out.println("key : " + record.key());
                System.out.println("value : " + record.value());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                // 同步提交
//                consumer.commitSync();
                // 异步提交
//                consumer.commitAsync();

                consumer.commitAsync(new OffsetCommitCallback() {
                    @Override
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {

                        Set<Map.Entry<TopicPartition, OffsetAndMetadata>> set = offsets.entrySet();
                        for (Map.Entry<TopicPartition, OffsetAndMetadata> entry : set) {
                            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                            TopicPartition topicPartition = entry.getKey();
                            OffsetAndMetadata metadata = entry.getValue();
                            System.out.println("topic:" + topicPartition.topic());
                            System.out.println("partition:" + topicPartition.partition());
                            System.out.println("offset:" + metadata.offset());
                            System.out.println("metadata:" + metadata.metadata());
                            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                        }
                    }
                });
            }
        }
    }
}
