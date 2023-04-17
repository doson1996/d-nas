package com.ds.nas.cloud.message.sms.service;

import com.ds.nas.cloud.message.sms.io.request.SendSMSRequest;
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
     * 发送短信
     * @param request
     * @return
     */
    Result<StringResponse> send(SendSMSRequest request);

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
