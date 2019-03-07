package com.java.summary.activemq.api.p2p.consumer;

import com.java.summary.activemq.api.ActiveMqUtil;

import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 */
public class Consumer {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    final String name = Thread.currentThread().getName();
                    Connection connection = ActiveMqUtil.getActiveMqUtil().getConnection();
                    Session session = null;
                    try {
                        connection.start();

                        System.out.println(name + "启动");

//                        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                        session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

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
                            // 确认
                            message.acknowledge();
                        }

                    } catch (JMSException e) {
                        e.printStackTrace();
                    } finally {
                        //close session
                        //close connection
                    }
                }
            });
        }


    }

}
