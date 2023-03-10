package com.ds.nas.lib.mq.kafka;

import com.ds.nas.lib.mq.producer.Producer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/1/30
 * @description kafka工具类
 */
@Component("kafka")
public class KafkaUtils implements Producer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean send(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
        return true;
    }

}
