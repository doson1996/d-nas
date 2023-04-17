package com.ds.nas.cloud.message.sms.service;

import com.ds.nas.cloud.message.sms.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.message.sms.io.request.SendSMSRequest;
import com.ds.nas.cloud.message.sms.io.request.VerifyCaptchaRequest;
import com.ds.nas.cloud.message.sms.io.response.AllStrategyResponse;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;

import java.util.Set;

/**
 * @author ds
 * @date 2023/4/6
 * @description
 */
public interface SmsService {

    /**
     * 限制发送频率存放的value
     */
    String LIMIT_VALUE = "1";

    /**
     * 限制发送频率（每分钟限制发送一次）
     */
    Integer LIMIT_VALUE_EXPIRE = 1;

    /**
     * 验证码默认有效期(3分钟)
     */
    String DEFAULT_EXPIRE = "3";

    /**
     * 发送短信
     * @param request
     * @return
     */
    Result<StringResponse> send(SendSMSRequest request);

    /**
     * 发送短信验证码
     * @param request
     * @return
     */
    Result<StringResponse> sendCaptcha(SendCaptchaRequest request);

    /**
     * 验证短信验证码
     * @param request
     * @return
     */
    Result<StringResponse> verifyCaptcha(VerifyCaptchaRequest request);

    /**
     * 设置短信发送策略
     * @param strategy
     * @return
     */
    Result<StringResponse> setStrategy(String strategy);

    /**
     * 当前短信发送策略
     * @return
     */
    Result<StringResponse> currentStrategy();

    /**
     * 获取所有短信发送策略
     * @return
     */
    Result<AllStrategyResponse> allStrategy();

}
