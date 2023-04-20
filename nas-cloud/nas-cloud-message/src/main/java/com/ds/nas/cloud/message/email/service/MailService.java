package com.ds.nas.cloud.message.email.service;

import com.ds.nas.cloud.message.email.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.message.email.io.request.SendMailRequest;
import com.ds.nas.cloud.message.email.io.request.VerifyCaptchaRequest;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;

/**
 * @author ds
 */
public interface MailService {

    /**
     * 发送简单邮件
     * @param request
     * @return
     */
    boolean sendSimpleMail(SendMailRequest request);


    /**
     * 发送html邮件
     *
     * @param request
     * @return
     */
    boolean sendHtmlMail(SendMailRequest request);

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
