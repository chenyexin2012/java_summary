package com.java.summary.rabbitmq.spring.producer;

import java.io.UnsupportedEncodingException;

public class MessageHandle {

    public void add(byte[] message) {
        try {
            System.out.println(new String(message, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}