package com.java.summary.activemq.spring.p2p.provider;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class AmqSenderService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "queueDestination")
    private Destination destination;

    public void sendMsg(String msg) {

        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {

                System.out.println(Thread.currentThread().getName() + " send message to " + destination.toString() + ", message:"
                        + msg);
                return session.createTextMessage(msg);
            }
        });
    }
}
