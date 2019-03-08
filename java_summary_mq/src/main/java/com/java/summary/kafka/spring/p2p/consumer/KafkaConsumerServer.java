package com.java.summary.kafka.spring.p2p.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author Administrator
 */
public class KafkaConsumerServer implements MessageListener<String, String> {

    @Override
    public void onMessage(ConsumerRecord<String, String> record) {

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
