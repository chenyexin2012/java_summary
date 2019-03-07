package com.java.summary.rabbitmq.spring.consumer;

import com.java.summary.rabbitmq.spring.producer.MessageHandle;
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
        return new DirectExchange("roberto.order", true, false, new HashMap<>());
    }

    @Bean
    public Queue queue() {
        return new Queue("roberto.order.add", true, false, false, new HashMap<>());
    }

    @Bean
    public Binding binding() {
        return new Binding("roberto.order.add", Binding.DestinationType.QUEUE, "roberto.order", "add", new HashMap<>());
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);
        messageListenerContainer.setQueueNames("roberto.order.add");

        // 设置消费者线程数
        messageListenerContainer.setConcurrentConsumers(50);
        // 设置最大消费者线程数
        messageListenerContainer.setMaxConcurrentConsumers(100);

        // 设置消费者属性信息
        Map<String, Object> argumentMap = new HashMap();
        messageListenerContainer.setConsumerArguments(argumentMap);

        // 设置消费者标签
        messageListenerContainer.setConsumerTagStrategy(new ConsumerTagStrategy() {
            @Override
            public String createConsumerTag(String s) {
                return "RGP订单系统ADD处理逻辑消费者";
            }
        });

        // 使用setAutoStartup方法可以手动设置消息消费时机
        messageListenerContainer.setAutoStartup(true);

        // 使用setAfterReceivePostProcessors方法可以增加消息后置处理器
        // messageListenerContainer.setAfterReceivePostProcessors();

//        messageListenerContainer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                try {
//                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//                    System.out.println(Thread.currentThread().getName());
//                    System.out.println("content:" + new String(message.getBody(), "UTF-8"));
//                    System.out.println(message.getMessageProperties());
//                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new MessageHandle());
        messageListenerAdapter.setDefaultListenerMethod("handleMessage");

        Map<String, String> queueOrTagToMethodName = new HashMap<>();
        // 将roberto.order.add队列的消息 使用add方法进行处理
        queueOrTagToMethodName.put("roberto.order.add","add");
        messageListenerAdapter.setQueueOrTagToMethodName(queueOrTagToMethodName);

        messageListenerContainer.setMessageListener(messageListenerAdapter);

        return messageListenerContainer;
    }
}