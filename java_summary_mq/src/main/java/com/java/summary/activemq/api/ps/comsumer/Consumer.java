package com.java.summary.activemq.api.ps.comsumer;

import com.java.summary.activemq.api.ActiveMqUtil;

import javax.jms.*;

public class Consumer {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {

                    final String name = Thread.currentThread().getName();
                    Connection connection = ActiveMqUtil.getActiveMqUtil().getConnection();
                    Session session = null;
                    try {
                        // activemq区分消费者，是通过clientID和订户名称来区分的。
                        // 此处指定clientID
                        connection.setClientID(name);
                        connection.start();
                        System.out.println(name + " start。。。");
                        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                        Topic topic = session.createTopic("testMqPS");

                        //非持久订阅者
                        MessageConsumer subscriber = session.createConsumer(topic);
                        //持久订阅者
//                        MessageConsumer subscriber = session.createDurableSubscriber(topic, name);

                        subscriber.setMessageListener(new MessageListener() {
                            public void onMessage(Message message) {
                                try {
                                    TextMessage msg = (TextMessage) message;
                                    System.out.println(name + " received a message：" + msg.getText());
                                } catch (JMSException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

//                        while (true) {
//                            TextMessage message = (TextMessage) subscriber.receive();
//                            System.out.println(name + " received a message：" + message.getText());
//                        }

                    } catch (JMSException e) {
                        e.printStackTrace();
                    } finally {
//                        try {
//                            if (null != session) {
//                                session.close();
//                            }
//                            if(null != connection) {
//                                connection.close();
//                            }
//                        } catch (JMSException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }, "subscriber" + i).start();
        }
    }
}
