package com.java.summary.rabbitmq.spring.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author Administrator
 */
@ComponentScan(basePackages = "com.java.summary.rabbitmq.spring.producer")
public class Producer {

    public static void main(String[] args) throws InterruptedException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Producer.class);

        RabbitAdmin rabbitAdmin = context.getBean(RabbitAdmin.class);
        RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);

        // 声明交换机
        rabbitAdmin.declareExchange(new DirectExchange(SpringAMQPProducerConfig.EXCHANGE_NAME,
                true, false, new HashMap<>()));

        // 声明消息 (消息体, 消息属性)
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType("UTF-8");
        Message message = new Message("rabbitmq生产者消费者测试".getBytes(), messageProperties);

        // 发布消息 (交换机名, Routing key, 消息);
        // 发布消息还可以使用rabbitTemplate.convertAndSend(); 其支持消息后置处理
        while (true) {
            rabbitTemplate.send(SpringAMQPProducerConfig.EXCHANGE_NAME, SpringAMQPProducerConfig.ROUTING_KEY, message);
            System.out.println("消息发送成功 > > > > > > > ");
            Thread.sleep(1000);
        }
    }
}
