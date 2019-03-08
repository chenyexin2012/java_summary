package com.java.summary.rabbitmq.api;

import com.rabbitmq.client.*;

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

        // 消息没有被正确路由至对应的队列中，回调
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey,
                                     AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                System.out.println("replyCode:" + replyCode);
                System.out.println("replyText:" + replyText);
                System.out.println("exchange:" + exchange);
                System.out.println("routingKey:" + routingKey);
                System.out.println("properties:" + properties);
                System.out.println("body:" + new String(body, "UTF-8"));
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
        });

        // 开启消息确认
        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("-------handleAck-------");
                System.out.println("deliveryTag:" + deliveryTag);
                System.out.println("multiple:" + multiple);
            }

            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("-------handleNack-------");
                System.out.println("deliveryTag:" + deliveryTag);
                System.out.println("multiple:" + multiple);
            }
        });

        // 将mandatory属性设置成true
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, true, null, "rabbitmq生产者测试".getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, true, null, "".getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, true, null, "rabbitmq生产者测试".getBytes());
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, true, null, "".getBytes());
    }
}