package com.java.summary.rabbitmq.api;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Administrator
 */
public class Publisher {

    private final static String EXCHANGE_NAME = "holmes-exchange";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Channel channel = ChannelUtil.createChannel("rabbitmq发布/订阅模式订阅方");

        // 使用扇形交换机，实现发布/订阅模式
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

//        while(true) {
            channel.basicPublish(EXCHANGE_NAME, "", null, "Hello rabbitmq".getBytes());
            Thread.sleep(1000);
//        }

        channel.close();
    }

}
