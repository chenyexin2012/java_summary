package com.java.summary.activemq.spring.p2p;

import com.java.summary.activemq.spring.p2p.provider.AmqSenderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring/spring-amq.xml");

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
//        applicationContext.stop();
//        applicationContext.close();
    }
}
