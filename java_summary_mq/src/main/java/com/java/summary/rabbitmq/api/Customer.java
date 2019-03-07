package com.java.summary.rabbitmq.api;


import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 一个简单的消费者
 *
 * @author Administrator
 */
public class Customer {

    public final static String EXCHANGE_NAME = "exchange-test";

    public final static String ROUTING_KEY = "test-producer";

    public final static String QUEUE_NAME = "holmes-queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = ChannelUtil.createChannel("rabbitmq消费者测试");

        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        System.out.println(declareOk.getQueue());

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);

        channel.queueBind(declareOk.getQueue(), EXCHANGE_NAME, ROUTING_KEY, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("consumerTag:" + consumerTag);
                System.out.println("exchange:" + envelope.getExchange());
                System.out.println("routing key:" + envelope.getRoutingKey());
                System.out.println("delivery tag:" + envelope.getDeliveryTag());
                System.out.println("content:" + new String(body));
            }
        };

        channel.basicConsume(declareOk.getQueue(), true, "rabbitmq消费者处理测试", consumer);
    }

}