package com.java.summary.kafka.api;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;

import java.util.Properties;

/**
 * @author Administrator
 */
public class Producer {

    static Logger log = Logger.getLogger(Producer.class);

    private static final String TOPIC_001 = "test-kafka-001";

    private static final String TOPIC_002 = "test-kafka-002";

    private static final String TOPIC_003 = "test-kafka-003";

    private static final String BROKER_LIST = "127.0.0.1:9092";

    private static KafkaProducer<String, String> producer = null;

    /**
     * 初始化生产者
     */
    static {
        Properties configs = initConfig();
        producer = new KafkaProducer<String, String>(configs);
    }

    /**
     * 初始化配置
     * <p>
     * bootstrap.servers： kafka的地址。
     * acks:消息的确认机制，默认值是0。
     * acks=0：如果设置为0，生产者不会等待kafka的响应。
     * acks=1：这个配置意味着kafka会把这条消息写到本地日志文件中，但是不会等待集群中其他机器的成功响应。
     * acks=all：这个配置意味着leader会等待所有的follower同步完成。这个确保消息不会丢失，除非kafka集群中所有机器挂掉。这是最强的可用性保证。
     * retries：配置为大于0的值的话，客户端会在消息发送失败时重新发送。
     * batch.size:当多条消息需要发送到同一个分区时，生产者会尝试合并网络请求。这会提高client和生产者的效率。
     * key.serializer: 键序列化，默认org.apache.kafka.common.serialization.StringDeserializer。
     * value.deserializer:值序列化，默认org.apache.kafka.common.serialization.StringDeserializer。
     */
    private static Properties initConfig() {
        Properties properties = new Properties();
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
        ProducerRecord<String, String> record1 = new ProducerRecord<String, String>(TOPIC_001, "kafka生产者消费者测试-001");
        ProducerRecord<String, String> record2 = new ProducerRecord<String, String>(TOPIC_002, "kafka生产者消费者测试-002");
        ProducerRecord<String, String> record3 = new ProducerRecord<String, String>(TOPIC_003, "kafka生产者消费者测试-003");

        for (int i = 0; i < 1000; i++) {
            sendMessage(record1);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");
            sendMessage(record2);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");
            sendMessage(record3);
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");
            Thread.sleep(1000);
        }
        producer.close();
    }
}
