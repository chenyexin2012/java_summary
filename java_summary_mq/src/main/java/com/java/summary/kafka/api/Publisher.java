package com.java.summary.kafka.api;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * @author Administrator
 */
public class Publisher {

    static Logger log = Logger.getLogger(Producer.class);

    private static final String TOPIC = "test";

    private static final String BROKER_LIST = "127.0.0.1:9092";

    private static KafkaProducer<String, String> producer = null;

    /**
     * 初始化生产者
     */
    static {
        Properties configs = initConfig();
        producer = new KafkaProducer<String, String>(configs);
    }

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put("group.id", "group-001");
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, "0");
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, "1024");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    private static void sendMessage(ProducerRecord record) {

        //发送消息
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (null != e) {
                    log.info("send error" + e.getMessage());
                } else {
                    System.out.println(String.format("offset:%s,partition:%s", recordMetadata.offset(), recordMetadata.partition()));
                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {

        //消息实体
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(TOPIC, "kafka生产者消费者测试-001");
        sendMessage(record);
        producer.close();
    }
}
