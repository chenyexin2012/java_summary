package com.java.summary.rabbitmq.api;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生成者
 *
 * @author Administrator
 */
public class Producer {

    public final static String EXCHANGE_NAME = "exchange-test";

    public final static String ROUTING_KEY = "test-producer";

    public final static String QUEUE_NAME = "holmes-queue";

    public static void main(String[] args) throws IOException {

        Channel channel = ChannelUtil.createChannel("rabbitmq生产者测试");

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, false, null);

        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, false, null, "rabbitmq生产者测试".getBytes());
    }
}