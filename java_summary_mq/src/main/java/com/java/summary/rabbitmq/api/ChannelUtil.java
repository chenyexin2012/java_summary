package com.java.summary.rabbitmq.api;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author Administrator
 */
public class ChannelUtil {

    private final static String HOST = "47.101.32.50";

    private final static String USER_NAME = "root";

    private final static String PASSWORD = "rabbitmq@2018";

    public static Channel createChannel(String connectionName) {

        ConnectionFactory connectionFactory = getConnectionFactory();
        try {
            Connection connection = connectionFactory.newConnection(connectionName);
            return connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ConnectionFactory getConnectionFactory() {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setUsername(USER_NAME);
        connectionFactory.setPassword(PASSWORD);

        // 网络异常，自动重连
        connectionFactory.setAutomaticRecoveryEnabled(true);
        // 重连尝试时间为10s
        connectionFactory.setNetworkRecoveryInterval(10000);

        Map<String, Object> clientProperties = new HashMap(3);
        clientProperties.put("name", "Holmes");
        clientProperties.put("description", "rabbitmq生产者消费者测试");
        clientProperties.put("address", "holmes2019@126.com");
        connectionFactory.setClientProperties(clientProperties);

        return connectionFactory;
    }
}
