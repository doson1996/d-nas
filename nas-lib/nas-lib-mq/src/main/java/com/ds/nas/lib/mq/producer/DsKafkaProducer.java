package com.ds.nas.lib.mq.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author ds
 * @date 2023/1/28
 * @description kafka生产者实现
 */
@Component("kafkaProducer")
public class DsKafkaProducer implements Producer {

    private static final KafkaProducer<String, String> producer = new KafkaProducer<>(defineDefaultConfiguration());

    @Override
    public boolean send(String topic, String msg) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
        producer.send(record);
        return Boolean.TRUE;
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
