package com.java.summary.kafka.spring.p2p.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * @author Administrator
 */
public class KafkaProducerListener implements ProducerListener {

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        System.out.println("发送成功");
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        System.out.println("发送失败");
    }

}
