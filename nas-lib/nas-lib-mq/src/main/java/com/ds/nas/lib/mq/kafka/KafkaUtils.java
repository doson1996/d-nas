package com.ds.nas.lib.mq.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/1/30
 * @description kafka工具类
 */
@Component
public class KafkaUtils {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
    }

}
