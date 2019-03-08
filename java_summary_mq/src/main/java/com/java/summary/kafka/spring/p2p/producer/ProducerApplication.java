package com.java.summary.kafka.spring.p2p.producer;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
public class ProducerApplication {

    private static final String TOPIC_001 = "test-kafka-001";

    private static final String TOPIC_002 = "test-kafka-002";

    private static final String TOPIC_003 = "test-kafka-003";

    private static final String BROKER_LIST = "127.0.0.1:9092";

    @Bean
    public Map<String, String> getProperties() {

        Map properties = new HashMap<String, String>();
        properties.put("bootstrap.servers", BROKER_LIST);
        properties.put("acks", "all");
        properties.put("retries", "0");
        properties.put("batch.size", "1024");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        return properties;
    }

    @Bean
    public DefaultKafkaProducerFactory getProducerFactory(Map<String, String> properties) {

        DefaultKafkaProducerFactory producerFactory = new DefaultKafkaProducerFactory(properties);
        return producerFactory;
    }

    @Bean
    public ProducerListener getProducerListener() {
        return new KafkaProducerListener();
    }

    @Bean
    public KafkaTemplate getKafkaTemplate(ProducerFactory producerFactory, ProducerListener producerListener) {

        KafkaTemplate kafkaTemplate = new KafkaTemplate(producerFactory);
        kafkaTemplate.setDefaultTopic(TOPIC_001);
        kafkaTemplate.setProducerListener(producerListener);
        return kafkaTemplate;
    }
}
