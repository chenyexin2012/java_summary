package com.java.summary.rabbitmq.spring.producer;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringAMQPProducerConfig {

    private final static String HOST = "127.0.0.1";

    private final static String USER_NAME = "root";

    private final static String PASSWORD = "rabbitmq@2018";

    public final static String EXCHANGE_NAME = "holmes-direct-exchange";

    public final static String ROUTING_KEY = "holmes-routing-key";

    public final static String QUEUE_NAME = "holmes-queue-name";
    
    @Bean
    public CachingConnectionFactory connectionFactory() {

        com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();

        // 配置连接信息
        connectionFactory.setHost(HOST);
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(PASSWORD);

        // 网络异常自动连接恢复
        connectionFactory.setAutomaticRecoveryEnabled(true);
        // 每10秒尝试重试连接一次
        connectionFactory.setNetworkRecoveryInterval(10000);

        Map<String, Object> clientProperties = new HashMap(3);
        clientProperties.put("name", "Holmes");
        clientProperties.put("description", "rabbitmq生产者消费者测试");
        clientProperties.put("address", "holmes2019@126.com");
        connectionFactory.setClientProperties(clientProperties);

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);

        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}