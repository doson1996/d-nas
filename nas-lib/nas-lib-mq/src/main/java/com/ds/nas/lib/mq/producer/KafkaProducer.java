package com.ds.nas.lib.mq.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author ds
 * @date 2023/1/28
 * @description kafka生产者实现
 */
public class KafkaProducer implements Producer {

    private static org.apache.kafka.clients.producer.KafkaProducer<String, String> producer
            = new org.apache.kafka.clients.producer.KafkaProducer<>(defineDefaultConfiguration());

    @Override
    public boolean send(String topic, String msg) {

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
        producer.send(record, (recordMetadata, e) -> {
            if (e == null) {

            } else {

            }
        });

        return true;
    }

    private static Properties defineDefaultConfiguration() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "dy.com:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "default_client");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

}
