package com.ds.nas.lib.cache.key;

/**
 * @author ds
 * @date 2023/4/12
 * @description 短信相关redis key
 */
public interface RedisSMSKey extends RedisMessageKey {

    /**
     * message key
     */
    String SMS_KEY = "sms";

    /**
     * message key 前缀
     */
    String SMS_KEY_PREFIX = MESSAGE_KEY_PREFIX + SMS_KEY + SEPARATOR;

    /**
     * 短信客户端集合 key
     */
    String SMS_CLIENT_KEY = SMS_KEY_PREFIX + "client" + SEPARATOR;

    /**
     * 短信发送频率 key
     */
    String SMS_LIMIT_KEY = MESSAGE_KEY_PREFIX + "limit" + SEPARATOR;


    /**
     * 验证码集合 key 手机号:验证码
     */
    String SMS_CAPTCHA_KEY = SMS_KEY_PREFIX + "captcha" + SEPARATOR;

    /**
     * 发送短信当前策略
     */
    String SMS_CURRENT_STRATEGY_KEY = SMS_KEY_PREFIX + "current-strategy";

    /**
     * 客户端发送成功次数
     */
    String SMS_CLIENT_SEND_SUCCESS_KEY = SMS_KEY_PREFIX + "send-success" + SEPARATOR;

    /**
     * 客户端发送总次数
     */
    String SMS_CLIENT_SEND_TOTAL_KEY = SMS_KEY_PREFIX + "send-total" + SEPARATOR;

}
