package com.ds.nas.lib.mq.config;

import com.ds.nas.lib.mq.consumer.Consumer;
import com.ds.nas.lib.mq.consumer.kafka.DsKafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Value("${ds.mq.bootstrapServers : ds.com:9092}")
    private String bootstrapServers;

    @Value("${ds.mq.groupId : GID_NAS_DEFAULT}")
    private String groupId;

    @Bean
    public Consumer dsKafkaConsumer() {
        return new DsKafkaConsumer(bootstrapServers, groupId);
    }

}
