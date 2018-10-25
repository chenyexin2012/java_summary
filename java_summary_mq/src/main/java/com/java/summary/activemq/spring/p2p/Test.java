package com.java.summary.activemq.spring.p2p;

import com.java.summary.activemq.spring.p2p.provider.AmqSenderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    @org.junit.Test
    public void providerTest() {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/spring-amq-provider.xml");

        applicationContext.start();

        AmqSenderService amqSenderService = applicationContext.getBean("amqSenderService", AmqSenderService.class);

        for(int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        amqSenderService.sendMsg(new SimpleDateFormat("yyyy-MM-dd hh:mm:dd").format(new Date()) + " hello world");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        while(true){
        }
//        applicationContext.stop();
//        applicationContext.close();
    }

    @org.junit.Test
    public void consumerTest() {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/spring-amq-consumer.xml");

        applicationContext.start();

        while (true) {
        }
    }
}
