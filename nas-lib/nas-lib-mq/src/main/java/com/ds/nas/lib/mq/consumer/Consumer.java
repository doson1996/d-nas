package com.ds.nas.lib.mq.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * @author ds
 * @date 2023/1/28
 * @description 消息消费者接口
 */
public interface Consumer {

    /**
     * 接收消息
     *
     * @param topic
     */
    ConsumerRecords<String, String> receive(String topic);

    /**
     * 接收消息
     *
     * @param topic
     */
    ConsumerRecords<String, String> receive(String topic, Visitor visitor);

}
