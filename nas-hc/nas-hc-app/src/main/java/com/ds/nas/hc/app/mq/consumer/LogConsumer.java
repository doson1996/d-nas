package com.ds.nas.hc.app.mq.consumer;

import com.alibaba.fastjson2.JSON;
import com.ds.nas.hc.dao.domain.HcRequestLog;
import com.ds.nas.hc.service.HcRequestLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/1/30
 * @description
 */
@Slf4j
@Component
public class LogConsumer {

    @Resource
    private HcRequestLogService requestLogService;

    /**
     *  监听日志消息
     * @param record
     * @param ack
     */
    @KafkaListener(topics = "log-test", groupId = "gid-hc-log")
    public void listenLogMsg(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        log.info("消费到日志消息: {}", value);
        HcRequestLog log = JSON.parseObject(value, HcRequestLog.class);
        boolean save = requestLogService.save(log);
        if (save) {
            //手动提交offset，一般是提交一个banch，幂等性防止重复消息
            // todo 每条消费完确认性能不好！
            ack.acknowledge();
        }
    }

}
