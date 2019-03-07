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

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.101.32.50");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("rabbitmq@2018");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

//        while(true) {
            channel.basicPublish(EXCHANGE_NAME, "", null, "Hello rabbitmq".getBytes());
            Thread.sleep(1000);
//        }

        channel.close();
        connection.close();
    }

}
