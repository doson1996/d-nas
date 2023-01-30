package com.ds.nas.hc.app.config.mq;

import com.ds.nas.lib.mq.producer.KafkaProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2023/1/28
 * @description
 */
@Configuration
public class MqConfig {

    @Bean
    @ConditionalOnClass(value = {com.ds.nas.lib.mq.producer.KafkaProducer.class})
    public KafkaProducer kafkaProducer() {
        return new KafkaProducer();
    }

}
