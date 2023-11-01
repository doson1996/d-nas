package com.ds.nas.lib.mq.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;

/**
 * kafka消费访问
 */
public interface Visitor {

    /**
     * 当接收到消息的时候
     *
     * @param records kafka消息
     */
    void onReceive(ConsumerRecords<String, String> records);

}
