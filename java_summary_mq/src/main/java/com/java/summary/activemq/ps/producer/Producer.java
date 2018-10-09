package com.java.summary.activemq.ps.producer;

import com.java.summary.activemq.ActiveMqUtil;

import javax.jms.*;

public class Producer {

    public static void main(String[] args) {

        Connection connection = ActiveMqUtil.getActiveMqUtil().getConnection();
        Session session = null;

        try {
            connection.setClientID("publisher001");
            connection.start();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);

            Topic topic = session.createTopic("testMqPS");
            MessageProducer publisher = session.createProducer(topic);
            // 不进行消息持久化
//                publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 消息持久化（默认）
//                publisher.setDeliveryMode(DeliveryMode.PERSISTENT);

            Message message = session.createTextMessage("I love Barcelona!");

            publisher.send(message);

            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != session) {
                    session.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
