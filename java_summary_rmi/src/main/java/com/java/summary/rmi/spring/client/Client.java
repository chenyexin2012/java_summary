package com.java.summary.rmi.spring.client;

import com.java.summary.rmi.spring.Calculator;
import com.java.summary.rmi.Input;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-rmi-client.xml");
        Calculator calculator = (Calculator) context.getBean("rmiProxyFactoryBean");

        System.out.println(calculator.add(new Input(1, 2)));
        System.out.println(calculator.sub(new Input(1, 2)));
        System.out.println(calculator.mul(new Input(1, 2)));
        System.out.println(calculator.div(new Input(1, 2)));
    }
}
