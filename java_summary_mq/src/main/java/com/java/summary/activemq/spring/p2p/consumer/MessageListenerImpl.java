package com.java.summary.activemq.spring.p2p.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MessageListenerImpl implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            System.out.println(Thread.currentThread().getName() + " receive message from "
                    + message.getJMSDestination().toString() + ", message:" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
