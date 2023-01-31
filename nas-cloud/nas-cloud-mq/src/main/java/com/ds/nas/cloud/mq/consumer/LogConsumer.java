package com.ds.nas.cloud.mq.consumer;

import com.alibaba.fastjson2.JSON;
import com.ds.nas.hc.api.dubbo.HcRequestLogProvider;
import com.ds.nas.hc.dao.domain.HcRequestLog;
import com.ds.nas.lib.common.constant.MqTopic;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2023/1/30
 * @description
 */
@Slf4j
@Component
public class LogConsumer {

    @DubboReference(version = "1.0")
    private HcRequestLogProvider hcRequestLogProvider;

    /**
     * 监听日志消息
     *
     * @param record
     * @param ack
     */
    @KafkaListener(topics = {MqTopic.HC_REQUEST_LOG_TOPIC}, groupId = "gid-hc-log")
    public void listenLogMsg(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        log.info("消费到request日志消息: {}", value);
        HcRequestLog log = JSON.parseObject(value, HcRequestLog.class);
        boolean save = hcRequestLogProvider.save(log);
        if (save) {
            //手动提交offset，一般是提交一个batch，幂等性防止重复消息
            // todo 每条消费完确认性能不好！
            ack.acknowledge();
        }
    }

}
