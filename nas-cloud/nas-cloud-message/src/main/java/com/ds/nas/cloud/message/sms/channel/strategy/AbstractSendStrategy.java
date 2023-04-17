package com.ds.nas.cloud.message.sms.channel.strategy;

import com.ds.nas.lib.common.util.StringUtils;

/**
 * @author ds
 * @date 2023/4/7 价格优先策略
 * @description 抽象策略类
 */
public abstract class AbstractSendStrategy implements SendStrategy {

    /**
     * 策略注册方法
     */
    public void register() {
        StrategyContext.registerStrategy(StringUtils.lowerFirst(getClass().getSimpleName()), this);
    }

}
