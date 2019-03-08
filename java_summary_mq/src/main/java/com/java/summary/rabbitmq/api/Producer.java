package com.java.summary.rabbitmq.api;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * 消息生成者
 *
 * @author Administrator
 */
public class Producer {

    public final static String EXCHANGE_NAME = "holmes-direct-exchange";

    public final static String ROUTING_KEY = "holmes-routing-key";

    public static void main(String[] args) throws IOException {

        Channel channel = ChannelUtil.createChannel("rabbitmq生产者测试");

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, false, null);

        int i = 10;
        while (i-- > 0) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, false, null, "rabbitmq生产者测试".getBytes());
            System.out.println(">>>>>>>>>>>>>>>>>>>>发送成功");
        }
    }
}