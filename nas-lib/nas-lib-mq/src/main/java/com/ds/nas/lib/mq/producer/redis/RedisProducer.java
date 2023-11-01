package com.ds.nas.lib.mq.producer.redis;

import com.ds.nas.lib.mq.producer.Producer;
import org.springframework.stereotype.Component;

@Component("redisProducer")
public class RedisProducer implements Producer {

    @Override
    public boolean send(String topic, String msg) {
        return false;
    }

}
