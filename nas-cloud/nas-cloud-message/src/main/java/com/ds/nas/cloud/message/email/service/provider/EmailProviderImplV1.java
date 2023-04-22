package com.ds.nas.cloud.message.email.service.provider;

import com.ds.nas.cloud.api.message.email.dubbo.EmailProvider;
import com.ds.nas.cloud.api.message.email.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.api.message.email.io.request.VerifyCaptchaRequest;
import com.ds.nas.cloud.message.email.service.EmailService;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/4/20
 * @description
 */
@DubboService(version = "1.0")
public class EmailProviderImplV1 implements EmailProvider {

    @Resource
    private EmailService emailService;

    @Override
    public Result<StringResponse> sendVerifyCode(SendCaptchaRequest request) {
        return emailService.sendCaptcha(request);
    }

    @Override
    public Result<StringResponse> verifyCaptcha(VerifyCaptchaRequest request) {
        return emailService.verifyCaptcha(request);
    }

}
