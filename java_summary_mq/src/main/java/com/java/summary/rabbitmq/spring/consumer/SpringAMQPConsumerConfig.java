package com.java.summary.rabbitmq.spring.consumer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
public class SpringAMQPConsumerConfig {

    private final static String HOST = "47.101.32.50";

    private final static String USER_NAME = "root";

    private final static String PASSWORD = "rabbitmq@2018";

    public final static String EXCHANGE_NAME = "holmes-direct-exchange";

    public final static String ROUTING_KEY = "holmes-routing-key";

    public final static String QUEUE_NAME = "holmes-queue-name";

    @Bean
    public ConnectionFactory connectionFactory() {

        com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();

        // 配置连接信息
        connectionFactory.setHost(HOST);
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(PASSWORD);

        // 网络异常自动连接恢复
        connectionFactory.setAutomaticRecoveryEnabled(true);
        // 每10秒尝试重试连接一次
        connectionFactory.setNetworkRecoveryInterval(10000);

        // 设置ConnectionFactory属性信息
        Map<String, Object> clientProperties = new HashMap();
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
    public Exchange exchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false, new HashMap<>());
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true, false, false, new HashMap<>());
    }

    @Bean
    public Binding binding() {
        return new Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE_NAME, ROUTING_KEY, new HashMap<>());
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.setQueueNames(QUEUE_NAME);

        // 设置消费者线程数
        messageListenerContainer.setConcurrentConsumers(10);
        // 设置最大消费者线程数
        messageListenerContainer.setMaxConcurrentConsumers(100);

        // 设置消费者属性信息
        Map<String, Object> argumentMap = new HashMap();
        messageListenerContainer.setConsumerArguments(argumentMap);

        // 设置消费者标签
        messageListenerContainer.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String s) {
                return "rabbitmq消费者测试";
            }
        });

        // 使用setAutoStartup方法可以手动设置消息消费时机
        messageListenerContainer.setAutoStartup(true);

        // 使用setAfterReceivePostProcessors方法可以增加消息后置处理器
        messageListenerContainer.setAfterReceivePostProcessors(new MessagePostProcessor() {
            @Override
            public org.springframework.amqp.core.Message postProcessMessage(org.springframework.amqp.core.Message message)
                    throws AmqpException {
                try {
                    System.out.println("postProcessMessage<<<<<<<<<<<<<<<");
                    System.out.println("content : " + new String(message.getBody(), "UTF-8"));
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return message;
            }
        });

        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageHandler());
        messageListenerAdapter.setDefaultListenerMethod("handleMessage");

        Map<String, String> queueOrTagToMethodName = new HashMap<>();
        // 将队列的消息 使用handle方法进行处理
        queueOrTagToMethodName.put(QUEUE_NAME, "handle");
        messageListenerAdapter.setQueueOrTagToMethodName(queueOrTagToMethodName);

        messageListenerContainer.setMessageListener(messageListenerAdapter);

        return messageListenerContainer;
    }
}