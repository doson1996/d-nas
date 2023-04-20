package com.ds.nas.cloud.message.sms.service.provider;

import com.ds.nas.cloud.api.message.sms.dubbo.SmsProvider;
import com.ds.nas.cloud.api.message.sms.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.api.message.sms.io.request.VerifyCaptchaRequest;
import com.ds.nas.cloud.message.sms.service.SmsService;
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
public class SmsProviderImplV1 implements SmsProvider {

    @Resource
    private SmsService smsService;

    @Override
    public Result<StringResponse> sendCaptcha(SendCaptchaRequest request) {
        return smsService.sendCaptcha(request);
    }

    @Override
    public Result<StringResponse> verifyCaptcha(VerifyCaptchaRequest request) {
        return smsService.verifyCaptcha(request);
    }

}
