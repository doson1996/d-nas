package com.ds.nas.cloud.message.shared.captcha;

import com.ds.nas.cloud.message.shared.constant.MessageConstant;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/4/22
 * @description
 */
@Service
public class CaptchaServiceImpl implements CaptchaService, MessageConstant {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public String getCaptchaKey(String key, String prefix) {
        return prefix + key;
    }

    @Override
    public String generate() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));
    }

    @Override
    public String fetch(String key) {
        return redisUtil.get(key);
    }

    @Override
    public void save(String key, String captcha) {
        save(key, captcha, DEFAULT_EXPIRE);
    }

    @Override
    public void save(String key, String captcha, Long seconds) {
        redisUtil.set(key, captcha, seconds);
    }

    @Override
    public boolean verify(String captcha, String key) {
        return verify(captcha, key, false);
    }

    @Override
    public boolean verify(String captcha, String key, boolean repeat) {
        // redis中存放的验证码
        String redisCaptcha = fetch(key);
        if (StringUtils.isBlank(captcha) || !captcha.equals(redisCaptcha)) {
            return false;
        }

        // 如不支持重复成功验证,验证成功后删除redis中的验证码（只能使用一次）
        if (!repeat)
            redisUtil.delete(key);
        return true;
    }

}
