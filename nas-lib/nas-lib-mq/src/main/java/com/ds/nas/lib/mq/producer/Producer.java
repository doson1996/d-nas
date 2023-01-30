package com.ds.nas.lib.mq.producer;

/**
 * @author ds
 * @date 2023/1/28
 * @description 消息生产者接口
 */
public interface Producer {

    /**
     * 发送消息
     *
     * @param topic 主题
     * @param msg   消息
     * @return true 发送成功
     *        false 发送失败
     */
    boolean send(String topic, String msg);

}
