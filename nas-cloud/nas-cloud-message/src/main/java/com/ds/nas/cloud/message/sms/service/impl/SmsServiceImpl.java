package com.ds.nas.cloud.message.sms.service.impl;

import com.ds.nas.cloud.message.constant.MessageConstant;
import com.ds.nas.cloud.message.sms.channel.strategy.SendStrategy;
import com.ds.nas.cloud.message.sms.channel.strategy.StrategyContext;
import com.ds.nas.cloud.message.sms.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.message.sms.io.request.SendSMSRequest;
import com.ds.nas.cloud.message.sms.io.request.VerifyCaptchaRequest;
import com.ds.nas.cloud.message.sms.io.response.AllStrategyResponse;
import com.ds.nas.cloud.message.sms.service.SmsService;
import com.ds.nas.lib.cache.key.RedisSMSKey;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.base.response.ResponseBuild;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.DateUnit;
import com.ds.nas.lib.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ds
 * @date 2023/4/6
 * @description
 */
@Service
public class SmsServiceImpl implements SmsService, MessageConstant {

    @Resource
    RedisUtil redisUtil;

    @Override
    public Result<StringResponse> send(SendSMSRequest request) {
        String phone = request.getPhone();
        // 判断是否限制发送频率
        String limitValue = redisUtil.get(getSmsLimitKey(phone));
        if (LIMIT_VALUE.equals(limitValue)) {
            return Result.fail("短信发送失败", StringResponse.builder().withData("请勿频繁请求发送短信验证码").build());
        }

        List<String> params = new ArrayList<>();
        params.add(request.getCaptcha());
        params.add(request.getExpire());
        SendStrategy sendStrategy = StrategyContext.getStrategy(getCurrentStrategy());
        boolean sendResult = sendStrategy.getClient()
                .send(phone, params);
        StringResponse response = StringResponse.builder().withData(phone).build();
        ResponseBuild.onReturn(request, response);
        if (sendResult) {
            // todo 发送成功，异步处理
            String key = getCaptchaKey(phone);
            long keyExpire = Long.parseLong(request.getExpire()) * DateUnit.MINUTE.getSecond();
            // 存放手机号和验证码到redis并设置过期时间
            redisUtil.set(key, request.getCaptcha(), keyExpire);
            // 存放手机号到发送频率限制key中（发送成功后，一分钟内不可再次发送）
            redisUtil.set(getSmsLimitKey(phone), LIMIT_VALUE, LIMIT_VALUE_EXPIRE * DateUnit.MINUTE.getSecond());
            return Result.ok("短信发送成功", response);
        }
        return Result.fail("短信发送失败", response);
    }

    @Override
    public Result<StringResponse> sendCaptcha(SendCaptchaRequest request) {
        SendSMSRequest sendSMSRequest = new SendSMSRequest();
        sendSMSRequest.setRequestPrivate(request.getRequestPrivate());
        sendSMSRequest.setPhone(request.getPhone());
        // 验证码
        sendSMSRequest.setCaptcha(getCaptcha());
        // 验证码有效期
        sendSMSRequest.setExpire(getExpire(request.getExpire()));
        return send(sendSMSRequest);
    }

    @Override
    public Result<StringResponse> verifyCaptcha(VerifyCaptchaRequest request) {
        if (StringUtils.isBlank(request.getCaptcha())) {
            return Result.fail("验证失败", StringResponse.builder().withData("验证失败").build());
        }
        String key = getCaptchaKey(request.getPhone());
        // 获取存在redis中的验证码
        String captcha = redisUtil.get(key);
        if (!request.getCaptcha().equals(captcha)) {
            return Result.fail("验证失败", StringResponse.builder().withData("验证失败").build());
        }
        // 验证成功，删除redis中的验证码（只能使用一次）
        redisUtil.delete(key);
        return Result.ok("验证成功", StringResponse.builder().withData("验证成功").build());
    }

    @Override
    public Result<StringResponse> setStrategy(String strategy) {
        redisUtil.set(RedisSMSKey.SMS_CURRENT_STRATEGY_KEY, strategy);
        String msg = "设置短信发送策略成功[" + strategy + "]";
        return Result.ok(msg, StringResponse.builder().withData(msg).build());
    }

    @Override
    public Result<StringResponse> currentStrategy() {
        String currentStrategy = getCurrentStrategy();
        String msg = "当前短信发送策略[" + currentStrategy + "]";
        return Result.ok(msg, StringResponse.builder().withData(currentStrategy).build());
    }

    @Override
    public Result<AllStrategyResponse> allStrategy() {
        Map<String, SendStrategy> strategyMap = StrategyContext.allStrategy();
        AllStrategyResponse response = new AllStrategyResponse();
        response.setData(strategyMap.keySet());
        return Result.ok("获取全部策略成功", response);
    }

    /**
     * 获取当前执行策略
     *
     * @return
     */
    private String getCurrentStrategy() {
        String strategy;
        // todo 不止从redis获取
        strategy = redisUtil.get(RedisSMSKey.SMS_CURRENT_STRATEGY_KEY);
        return strategy != null ? strategy : "";
    }

    /**
     * 生成验证码
     *
     * @return
     */
    private String getCaptcha() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));
    }

    /**
     * redis存验证码 key (key 前缀:手机号 value 验证码)
     *
     * @param phone
     * @return
     */
    private String getCaptchaKey(String phone) {
        return RedisSMSKey.SMS_CAPTCHA_KEY + phone;
    }

    /**
     * 短信发送频率 key (key 前缀:手机号 value 验证码)
     *
     * @param phone
     * @return
     */
    private String getSmsLimitKey(String phone) {
        return RedisSMSKey.SMS_LIMIT_KEY + phone;
    }

    /**
     * 获取短信有效期,没设置则返回默认有效期(3分钟)
     *
     * @param expire
     * @return
     */
    private String getExpire(String expire) {
        return StringUtils.isBlank(expire) ? DEFAULT_EXPIRE : expire;
    }

}
