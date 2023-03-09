package com.ds.nas.lib.mq.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Properties;

/**
 * @author ds
 * @date 2023/1/28
 * @description kafka消费者实现
 */
@Component("kafkaConsumer")
public class DsKafkaConsumer implements Consumer {

    private static final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(defineDefaultConfiguration());

    public void receive(String topic) {
        consumer.subscribe(Collections.singleton(topic));
        ConsumerRecords<String, String> records = consumer.poll(3000);
    }

    private static Properties defineDefaultConfiguration() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "dy.com:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

}
