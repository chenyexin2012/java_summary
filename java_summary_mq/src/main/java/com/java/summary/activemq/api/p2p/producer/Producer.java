package com.java.summary.activemq.api.p2p.producer;

import com.java.summary.activemq.api.ActiveMqUtil;

import javax.jms.*;

public class Producer {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new Runnable() {
                public void run() {

                    Connection connection = ActiveMqUtil.getActiveMqUtil().getConnection();
                    Session session = null;
                    try {
                        //开启链接
                        connection.start();

                        System.out.println(Thread.currentThread().getName() + "启动");
                        //创建一个事务，并设置事务级别
                        session = connection.createSession(true, Session.SESSION_TRANSACTED);
                        //创建一个消息队列
                        Queue queue = session.createQueue("testMqP2P");
                        //创建一个生产者
                        MessageProducer producer = session.createProducer(queue);
//                        //不进行消息持久化
//                        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//                        //消息持久化（默认）
//                        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

                        while (true) {
                            //创建一条消息
                            Message message = session.createTextMessage(Thread.currentThread().getName());
                            //发送消息
                            producer.send(message);
                            //提交事务
                            session.commit();

                            Thread.sleep(3000);
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //close session
                        //close connection
                    }

                }
            }, "生产者" + i);

            thread.start();
        }
    }

}
