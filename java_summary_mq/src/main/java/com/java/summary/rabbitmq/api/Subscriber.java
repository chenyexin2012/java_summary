package com.java.summary.rabbitmq.api;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Administrator
 */
public class Subscriber {

    private final static String EXCHANGE_NAME = "holmes-exchange";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = ChannelUtil.createChannel("rabbitmq发布/订阅模式发布方");

        // 扇形交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        String queueName = channel.queueDeclare().getQueue();

        System.out.println("queue name : " + queueName);

        // 将队列绑定至扇形交换机中
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received '" + message + "'");
            }
        };

        channel.basicConsume(queueName, true, consumer);
    }
}
