package com.java.summary.rmi.spring.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-rmi-server.xml");
    }
}
