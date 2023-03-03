package com.ds.nas.cloud.mq.consumer;

import com.ds.nas.lib.common.constant.MqTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2023/2/28
 * @description 订阅canal发送的消息
 */
@Slf4j
@Component
public class CanalConsumer {

    @KafkaListener(topics = {MqTopic.CANAL_MYSQL_TOPIC}, groupId = "gid-cloud-canal")
    public void listenCanalMsg(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        log.info("消费到canal日志消息: {}", value);
        if (true) {
            //手动提交offset，一般是提交一个batch，幂等性防止重复消息
            // todo 每条消费完确认性能不好！
            ack.acknowledge();
        }
    }

}
