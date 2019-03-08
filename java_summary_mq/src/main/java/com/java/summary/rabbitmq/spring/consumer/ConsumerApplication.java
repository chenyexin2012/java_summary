package com.java.summary.rabbitmq.spring.consumer;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;

/**
 * @author Administrator
 */
@ComponentScan(basePackages = "com.java.summary.rabbitmq.spring.consumer")
public class ConsumerApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerApplication.class);

//        RabbitAdmin rabbitAdmin = context.getBean(RabbitAdmin.class);
//
//        MessageListenerContainer messageListenerContainer = context.getBean(MessageListenerContainer.class);
//
//        // 声明队列 (队列名", 是否持久化, 是否排他, 是否自动删除, 队列属性);
//        rabbitAdmin.declareQueue(new Queue(SpringAMQPConsumerConfig.QUEUE_NAME, true, false, false, new HashMap<>()));
//
//        // 声明Direct Exchange (交换机名, 是否持久化, 是否自动删除, 交换机属性);
//        rabbitAdmin.declareExchange(new DirectExchange(SpringAMQPConsumerConfig.EXCHANGE_NAME, true, false, new HashMap<>()));
//
//        // 将队列Binding到交换机上 Routing key为add
//        rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue(SpringAMQPConsumerConfig.QUEUE_NAME))
//                .to(new DirectExchange(SpringAMQPConsumerConfig.EXCHANGE_NAME)).with(SpringAMQPConsumerConfig.ROUTING_KEY));
//
//        // 开始监听队列
//        messageListenerContainer.start();
    }
}