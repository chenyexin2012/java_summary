package com.java.summary.rabbitmq.api;


import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 一个简单的消费者
 *
 * @author Administrator
 */
public class Customer {

    public final static String EXCHANGE_NAME = "holmes-direct-exchange";

    public final static String ROUTING_KEY = "holmes-routing-key";

    public final static String QUEUE_NAME = "holmes-queue-name";

    public static void main(String[] args) throws IOException {

        Channel channel = ChannelUtil.createChannel("rabbitmq消费者测试");

        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        System.out.println(declareOk.getQueue());

        // 使用直连交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);

        channel.queueBind(declareOk.getQueue(), EXCHANGE_NAME, ROUTING_KEY, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                System.out.println("consumerTag:" + consumerTag);
                System.out.println("exchange:" + envelope.getExchange());
                System.out.println("routing key:" + envelope.getRoutingKey());
                System.out.println("delivery tag:" + envelope.getDeliveryTag());
                System.out.println("content:" + new String(body));
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
        };
        channel.basicConsume(declareOk.getQueue(), true, "rabbitmq消费者处理测试", consumer);
    }
}