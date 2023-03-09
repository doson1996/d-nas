package com.ds.nas.lib.mq.consumer;

/**
 * @author ds
 * @date 2023/1/28
 * @description 消息消费者接口
 */
public interface Consumer {

    /**
     * 接收消息
     * @param topic
     */
    void receive(String topic);

}
