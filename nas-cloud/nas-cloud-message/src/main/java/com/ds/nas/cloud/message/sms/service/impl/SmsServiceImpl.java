package com.ds.nas.cloud.message.sms.service.impl;

import com.ds.nas.cloud.message.shared.captcha.CaptchaService;
import com.ds.nas.cloud.message.shared.constant.MessageConstant;
import com.ds.nas.cloud.message.shared.limit.LimitService;
import com.ds.nas.cloud.message.sms.channel.strategy.SendStrategy;
import com.ds.nas.cloud.message.sms.channel.strategy.StrategyContext;
import com.ds.nas.cloud.api.message.sms.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.api.message.sms.io.request.SendSMSRequest;
import com.ds.nas.cloud.api.message.sms.io.request.VerifyCaptchaRequest;
import com.ds.nas.cloud.api.message.sms.io.response.AllStrategyResponse;
import com.ds.nas.cloud.message.sms.service.SmsService;
import com.ds.nas.lib.cache.key.RedisSMSKey;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.base.response.ResponseBuild;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.result.ResultCode;
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
public class SmsServiceImpl implements SmsService, RedisSMSKey {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private LimitService limitService;

    @Resource
    private CaptchaService captchaService;

    @Override
    public Result<StringResponse> send(SendSMSRequest request) {
        String phone = request.getPhone();
        if (StringUtils.isBlank(phone)) {
            return Result.fail("短信发送失败", StringResponse.builder().withData("手机号不能为空!").build());
        }

        String limitKey = limitService.getLimitKey(phone, SMS_LIMIT_KEY);
        // 判断是否限制发送频率
        if (limitService.getLimit(limitKey)) {
            return Result.fail("短信发送失败", StringResponse.builder().withData("请勿频繁请求发送短信").build());
        }

        List<String> params = new ArrayList<>();
        params.add(request.getCaptcha());
        params.add(String.valueOf(request.getExpire()));
        SendStrategy sendStrategy = StrategyContext.getStrategy(getCurrentStrategy());
        boolean sendResult = sendStrategy.getClient()
                .send(phone, params);
        StringResponse response = StringResponse.builder().withData(phone).build();
        ResponseBuild.onReturn(request, response);
        if (sendResult) {
            return Result.ok("短信发送成功", response);
        }
        return Result.fail("短信发送失败", response);
    }

    @Override
    public Result<StringResponse> sendCaptcha(SendCaptchaRequest request) {
        String phone = request.getPhone();
        if (StringUtils.isBlank(phone)) {
            return Result.fail("短信发送失败", StringResponse.builder().withData("手机号不能为空!").build());
        }
        SendSMSRequest sendSMSRequest = new SendSMSRequest();
        sendSMSRequest.setRequestPrivate(request.getRequestPrivate());
        sendSMSRequest.setPhone(phone);
        // 验证码
        String captcha = captchaService.generate();
        sendSMSRequest.setCaptcha(captcha);
        // 验证码有效期
        Long expire = captchaService.getExpire(request.getExpire());
        sendSMSRequest.setExpire(expire);

        Result<StringResponse> sendResult = send(sendSMSRequest);
        if (ResultCode.SUCCESS == sendResult.getCode())
            onSendCaptchaSuccess(request, captcha, limitService.getLimitKey(phone, SMS_LIMIT_KEY));
        return sendResult;
    }

    /**
     * 发送验证码成功后
     *  todo 发送成功，异步处理
     *
     * @param request
     */
    private void onSendCaptchaSuccess(SendCaptchaRequest request, String captcha, String limitKey) {
        String key = captchaService.getCaptchaKey(request.getPhone(), SMS_CAPTCHA_KEY);
        long keyExpire = request.getExpire() * DateUnit.MINUTE.getSecond();
        // 存放手机号和验证码到redis并设置过期时间
        captchaService.save(key, captcha, keyExpire);
        // 存放手机号到发送频率限制key中（发送成功后，一分钟内不可再次发送）
        limitService.setLimit(limitKey);
    }

    @Override
    public Result<StringResponse> verifyCaptcha(VerifyCaptchaRequest request) {
        if (StringUtils.isBlank(request.getCaptcha(), request.getPhone())) {
            return Result.fail("验证失败", StringResponse.builder().withData("验证失败").build());
        }
        String key = captchaService.getCaptchaKey(request.getPhone(), SMS_CAPTCHA_KEY);
        boolean verifySuccess = captchaService.verify(request.getCaptcha(), key);
        if (verifySuccess) {
            return Result.ok("验证成功", StringResponse.builder().withData("验证成功").build());
        }
        return Result.fail("验证失败", StringResponse.builder().withData("验证失败").build());
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
     * 获取短信验证码有效期,没设置则返回默认有效期(3分钟)
     *
     * @param expire
     * @return
     */
    private Long getExpire(Integer expire) {
        return expire == null ? MessageConstant.DEFAULT_EXPIRE : expire;
    }

}
