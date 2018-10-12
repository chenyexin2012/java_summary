package com.java.summary.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;

public class ActiveMqUtil {

    private static ActiveMqUtil activeMqUtil = new ActiveMqUtil();

    private PooledConnectionFactory pooledConnectionFactory = null;

    private final static String URL = "tcp://localhost:61616";

    private final static String USER_NAME = "admin";

    private final static String PASSWORD = "";

    public static ActiveMqUtil getActiveMqUtil() {
        if (null != activeMqUtil) {
            return activeMqUtil;
        }
        return null;
    }

    private ActiveMqUtil() {

        // 真正用于产生Connection的ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(URL);
        connectionFactory.setUserName(USER_NAME);
        connectionFactory.setPassword(PASSWORD);

        //ActiveMQ为我们提供了一个PooledConnectionFactory，通过往里面注入一个ActiveMQConnectionFactory
        //可以用来将Connection、Session和MessageProducer池化，这样可以大大的减少我们的资源消耗。 要依赖于activemq-pool包
        pooledConnectionFactory = new PooledConnectionFactory(connectionFactory);
        //设置最大连接数,默认值为1
        pooledConnectionFactory.setMaxConnections(2);
        //每个Connection中可以活跃的Session数量
        //在某种程度上来说，Connection可以看做是一个用于通信的管道，而Session是实际的操作
        //所以一个Connection可以被多个线程重用，但是这个Connection中活跃的Session数量超过最大数量时，
        //再次createSession时会一直等待有Session被释放。
        pooledConnectionFactory.setMaximumActiveSessionPerConnection(20);

        //以最后一次使用Connection为开始时间，IdleTimeout时间后，Connection被回收
        pooledConnectionFactory.setIdleTimeout(1 * 1000);
        //以创建Connection为开始时间，ExpiryTimeout时间后，Connection被回收
        pooledConnectionFactory.setExpiryTimeout(1 * 1000);
    }

    public Connection getConnection() {
        if (null == pooledConnectionFactory) {
            return null;
        }
        try {
            return pooledConnectionFactory.createConnection();
        } catch (JMSException e) {
            return null;
        }
    }

    public static void main(String[] args) throws JMSException, InterruptedException {

        for (int i = 0; i < 20; i++) {
            Connection connection = ActiveMqUtil.getActiveMqUtil().getConnection();
            System.out.println(i + "-->" + connection);
            //测试IdleTimeout和ExpiryTimeout的值对connnect回收的影响
            Thread.sleep(2000);
            connection.start();
            connection.close();

            //休息2s，等待空闲连接被回收，之后会产生新的Connection
//            Thread.sleep(2000);

//            for (int j = 0; j < 10; j++) {
//                Session session = connection.createSession(Boolean.TRUE, Session.SESSION_TRANSACTED);
//                System.out.println(session);
//            }
        }
    }
}
