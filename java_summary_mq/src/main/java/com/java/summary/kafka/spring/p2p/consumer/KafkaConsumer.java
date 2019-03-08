package com.java.summary.kafka.spring.p2p.consumer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Administrator
 */
@ComponentScan("com.java.summary.kafka.spring.p2p.consumer")
public class KafkaConsumer {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(KafkaConsumer.class);
    }
}
