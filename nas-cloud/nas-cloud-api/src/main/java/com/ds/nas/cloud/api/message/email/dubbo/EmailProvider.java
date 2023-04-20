package com.ds.nas.cloud.api.message.email.dubbo;

import com.ds.nas.cloud.api.message.email.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.api.message.email.io.request.SendMailRequest;
import com.ds.nas.cloud.api.message.email.io.request.VerifyCaptchaRequest;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;

/**
 * @author ds
 * @date 2023/4/20
 * @description
 */
public interface EmailProvider {

    /**
     * 发送验证码
     * @param request
     * @return
     */
    Result<StringResponse> sendVerifyCode(SendCaptchaRequest request);

    /**
     * 验证验证码
     * @param request
     * @return
     */
    Result<StringResponse> verifyCaptcha(VerifyCaptchaRequest request);

}
