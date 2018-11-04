package com.java.summary.activemq.spring.ps;

import com.java.summary.activemq.spring.ps.provider.AmqSenderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    @org.junit.Test
    public void providerTest() {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/amq/spring-amq-provider.xml");

        applicationContext.start();

        AmqSenderService amqSenderService = applicationContext.getBean("amqSenderService2", AmqSenderService.class);

        amqSenderService.sendMsg("hello world");
    }

    @org.junit.Test
    public void consumerTest() {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/amq/spring-amq-consumer.xml");

        applicationContext.start();

        while (true) {
        }
    }
}
