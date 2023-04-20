package com.ds.nas.cloud.api.message.sms.dubbo;

import com.ds.nas.cloud.api.message.sms.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.api.message.sms.io.request.VerifyCaptchaRequest;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;

/**
 * @author ds
 * @date 2023/4/20
 * @description
 */
public interface SmsProvider {

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

}
