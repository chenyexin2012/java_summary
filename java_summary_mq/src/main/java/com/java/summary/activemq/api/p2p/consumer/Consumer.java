package com.java.summary.activemq.api.p2p.consumer;

import com.java.summary.activemq.api.ActiveMqUtil;

import javax.jms.*;

public class Consumer {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new Runnable() {

                public void run() {
                    final String name = Thread.currentThread().getName();
                    Connection connection = ActiveMqUtil.getActiveMqUtil().getConnection();
                    Session session = null;
                    try {
                        connection.start();

                        System.out.println(name + "启动");

                        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                        Queue queue = session.createQueue("testMqP2P");

                        MessageConsumer consumer = session.createConsumer(queue);

//                        consumer.setMessageListener(new MessageListener() {
//                            public void onMessage(Message message) {
//                                TextMessage msg = (TextMessage) message;
//                                try {
//                                    System.out.println(name + "接收消息，内容为：" + msg.getText());
//                                } catch (JMSException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        });

                        while (true) {
                            TextMessage message = (TextMessage) consumer.receive();
                            System.out.println(name + "接收消息，内容为：" + message.getText());
                        }

                    } catch (JMSException e) {
                        e.printStackTrace();
                    } finally {
                        //close session
                        //close connection
                    }
                }
            }, "消费者" + i);

            thread.start();
        }


    }

}
