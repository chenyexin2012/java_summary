package com.java.summary.kafka.spring.p2p.consumer;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
public class ConsumerApplication {

    private static final String TOPIC_001 = "test-kafka-001";

    private static final String TOPIC_002 = "test-kafka-002";

    private static final String TOPIC_003 = "test-kafka-003";

    private static final String BROKER_LIST = "127.0.0.1:9092";

    @Bean
    public Map<String, String> getConfigs() {

        Map properties = new HashMap<String, String>();
        properties.put("bootstrap.servers", BROKER_LIST);
        properties.put("group.id", "group-002");
        properties.put("acks", "all");
        properties.put("retries", "0");
        properties.put("batch.size", "1024");
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        return properties;
    }

    @Bean
    public DefaultKafkaConsumerFactory getConsumerFactory(Map<String, String> configs) {

        DefaultKafkaConsumerFactory consumerFactory = new DefaultKafkaConsumerFactory(configs);
        return consumerFactory;
    }

    @Bean
    public KafkaConsumerServer getKafkaConsumerServer() {
        return new KafkaConsumerServer();
    }

    @Bean
    public ContainerProperties getContainerProperties(KafkaConsumerServer consumerServer) {

        ContainerProperties containerProperties = new ContainerProperties(TOPIC_001);
        containerProperties.setMessageListener(consumerServer);
        return containerProperties;
    }

    @Bean
    public KafkaMessageListenerContainer getListenerContainer(ConsumerFactory consumerFactory,
                                                              ContainerProperties containerProperties) {
        KafkaMessageListenerContainer listenerContainer =
                new KafkaMessageListenerContainer(consumerFactory, containerProperties);
        return listenerContainer;
    }
}
