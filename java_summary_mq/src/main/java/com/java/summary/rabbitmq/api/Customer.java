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
                try {
                    String content = new String(body);
                    if ("".equals(content)) {
                        throw new Exception("消息错误");
                    }
                    System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                    System.out.println("consumerTag:" + consumerTag);
                    System.out.println("exchange:" + envelope.getExchange());
                    System.out.println("routing key:" + envelope.getRoutingKey());
                    System.out.println("delivery tag:" + envelope.getDeliveryTag());
                    System.out.println("content:" + new String(body));
                    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    // 确认消息
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    // 拒绝消息，并将消息保留在队列中
                    channel.basicNack(envelope.getDeliveryTag(), false, true);
                    System.out.println(e.getMessage());
                }
            }
        };
        // 将自动确认置为false，通过basicAck确认
        channel.basicConsume(declareOk.getQueue(), false, "rabbitmq消费者处理测试", consumer);
    }
}