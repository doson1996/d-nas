package com.ds.nas.lib.mq.consumer.kafka;

import com.ds.nas.lib.mq.consumer.Consumer;
import com.ds.nas.lib.mq.consumer.Visitor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.Properties;

/**
 * @author ds
 * @date 2023/1/28
 * @description kafka消费者实现
 */
@Component("dsKafkaConsumer")
public class DsKafkaConsumer implements Consumer {

    private final KafkaConsumer<String, String> consumer;

    private final String bootstrapServers;

    private final String groupId;

    public DsKafkaConsumer(String bootstrapServers, String groupId) {
        this.bootstrapServers = bootstrapServers;
        this.groupId = groupId;
        consumer = new KafkaConsumer<>(defineDefaultConfiguration());
    }

    @Override
    public ConsumerRecords<String, String> receive(String topic) {
        return receive(topic, null);
    }

    @Override
    public ConsumerRecords<String, String> receive(String topic, Visitor visitor) {
        consumer.subscribe(Collections.singleton(topic));
        ConsumerRecords<String, String> records = consumer.poll(3000);
        Optional.ofNullable(visitor).ifPresent(v -> visitor.onReceive(records));
        return records;
    }

    private Properties defineDefaultConfiguration() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, this.groupId);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return properties;
    }

}
