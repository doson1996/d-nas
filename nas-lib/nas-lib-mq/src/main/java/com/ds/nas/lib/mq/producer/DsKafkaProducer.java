package com.ds.nas.lib.mq.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ds
 * @date 2023/1/28
 * @description kafka生产者实现
 */
public class DsKafkaProducer implements Producer {

    public static final String RES_KEY = "result";

    private static KafkaProducer<String, String> producer = new KafkaProducer<>(defineDefaultConfiguration());

    @Override
    public boolean send(String topic, String msg) {
        Map<String, Boolean> res = new HashMap<>(4);
        res.put(RES_KEY, Boolean.FALSE);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
        producer.send(record, (recordMetadata, e) -> {
            if (e == null) {
                res.put(RES_KEY, Boolean.TRUE);
            }
        });
        return res.get(RES_KEY);
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
