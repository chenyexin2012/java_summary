package com.java.summary.kafka.spring.p2p.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * @author Administrator
 */
@ComponentScan(basePackages = "com.java.summary.kafka.spring.p2p.producer")
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static final String TOPIC_001 = "test-kafka-001";

    public void sendMessage() {
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(TOPIC_001, 0, "test-key", "kafka生产者消费者测试-001");
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(KafkaProducer.class);
        KafkaProducer kafkaProducerServer = applicationContext.getBean(KafkaProducer.class);
//        while (true) {
            kafkaProducerServer.sendMessage();
//            Thread.sleep(1000);
//        }
    }

}
