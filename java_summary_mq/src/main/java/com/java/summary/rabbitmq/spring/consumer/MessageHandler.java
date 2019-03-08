package com.java.summary.rabbitmq.spring.consumer;

import java.io.UnsupportedEncodingException;

/**
 * @author Administrator
 */
public class MessageHandler {

    public void handle(byte[] message) {
        try {
            System.out.println("接收到消息<<<<<<<<<<<<<<<<<<");
            System.out.println(new String(message, "UTF-8"));
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}