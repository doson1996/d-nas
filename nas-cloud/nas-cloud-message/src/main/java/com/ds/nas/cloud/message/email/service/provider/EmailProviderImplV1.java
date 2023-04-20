package com.ds.nas.cloud.message.email.service.provider;

import com.ds.nas.cloud.api.message.email.dubbo.EmailProvider;
import com.ds.nas.cloud.api.message.email.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.api.message.email.io.request.SendMailRequest;
import com.ds.nas.cloud.api.message.email.io.request.VerifyCaptchaRequest;
import com.ds.nas.cloud.message.email.service.MailService;
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
    private MailService mailService;

    @Override
    public Result<StringResponse> sendVerifyCode(SendCaptchaRequest request) {
        return mailService.sendVerifyCode(request);
    }

    @Override
    public Result<StringResponse> verifyCaptcha(VerifyCaptchaRequest request) {
        return mailService.verifyCaptcha(request);
    }

}
