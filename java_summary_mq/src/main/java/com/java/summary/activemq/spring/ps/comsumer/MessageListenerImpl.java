package com.java.summary.activemq.spring.ps.comsumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @see com.java.summary.activemq.spring.p2p.consumer.MessageListenerImpl
 */
public class MessageListenerImpl implements MessageListener {
    @Override
    public void onMessage(Message message) {
    }
}