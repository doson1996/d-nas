package com.ds.nas.lib.cache.key;

/**
 * @author ds
 * @date 2023/4/12
 * @description 邮箱相关redis key
 */
public interface RedisEmailKey extends RedisMessageKey {

    /**
     * message key
     */
    String EMAIL_KEY = "email";

    /**
     * message key 前缀
     */
    String EMAIL_KEY_PREFIX = MESSAGE_KEY_PREFIX + EMAIL_KEY + SEPARATOR;


    /**
     * 邮箱发送频率 key
     */
    String EMAIL_LIMIT_KEY = MESSAGE_KEY_PREFIX + "limit" + SEPARATOR;


    /**
     * 验证码集合 key 邮箱:验证码
     */
    String EMAIL_CAPTCHA_KEY = EMAIL_KEY_PREFIX + "captcha" + SEPARATOR;

}
