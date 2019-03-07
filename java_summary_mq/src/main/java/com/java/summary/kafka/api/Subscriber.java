package com.java.summary.kafka.api;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author Administrator
 */
public class Subscriber {

    private static final String BROKER_LIST = "127.0.0.1:9092";

    private static final String TOPIC = "test";

    private static KafkaConsumer<String, String> consumer = null;

    static {
        consumer = new KafkaConsumer<String, String>(initConfig());
        consumer.subscribe(Arrays.asList(TOPIC));
    }

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", BROKER_LIST);
        properties.put("group.id", "group-001");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("auto.offset.reset", "earliest");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        return properties;
    }

    public static void main(String[] args) {

        ConsumerRecords<String, String> records = null;
        while (true) {
            records = consumer.poll(1000);
            Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<String, String> record = iterator.next();
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                System.out.println("topic : " + record.topic());
                System.out.println("headers : " + record.headers());
                System.out.println("offset : " + record.offset());
                System.out.println("partition : " + record.partition());
                System.out.println("key : " + record.key());
                System.out.println("value : " + record.value());
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
        }
    }
}
