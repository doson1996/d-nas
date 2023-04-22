package com.ds.nas.cloud.message.email.service.impl;

import com.ds.nas.cloud.api.message.email.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.api.message.email.io.request.SendMailRequest;
import com.ds.nas.cloud.api.message.email.io.request.VerifyCaptchaRequest;
import com.ds.nas.cloud.message.email.constant.MailTemplate;
import com.ds.nas.cloud.message.email.service.EmailService;
import com.ds.nas.cloud.message.shared.captcha.CaptchaService;
import com.ds.nas.cloud.message.shared.limit.LimitService;
import com.ds.nas.lib.cache.key.RedisEmailKey;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.DateUnit;
import com.ds.nas.lib.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService, RedisEmailKey {

    @Resource
    private LimitService limitService;

    @Resource
    private CaptchaService captchaService;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${config.mail.from}")
    private String from;

    @Override
    public boolean sendSimpleMail(SendMailRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getTo());
        mailMessage.setSubject(request.getSubject());
        mailMessage.setText(request.getContent());
        mailMessage.setFrom(from);

        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            log.info("发送simple邮件失败! request: {}, ex: {}", request, e.getMessage());
            return false;
        }

        log.info("发送simple邮件成功!");
        return true;
    }

    @Override
    public boolean sendHtmlMail(SendMailRequest request) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //创建邮件正文
            Context context = new Context();
            context.setVariables(request.getVariables());
            String emailContent = templateEngine.process(request.getTemplate(), context);

            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());
            helper.setText(emailContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            log.info("发送html邮件失败! request: {}, ex: {}", request, e.getMessage());
            return false;
        }

        log.info("发送html邮件成功!");
        return true;
    }

    @Override
    public Result<StringResponse> sendCaptcha(SendCaptchaRequest request) {
        // 收件人
        String to = request.getTo();
        // 限制频率key
        String limitKey = limitService.getLimitKey(to, EMAIL_LIMIT_KEY);
        // 判断是否限制发送频率
        if (limitService.getLimit(limitKey)) {
            return Result.fail("邮件发送失败", StringResponse.builder().withData("请勿频繁请求发送邮箱验证码").build());
        }

        // 生成验证码
        String captcha = captchaService.generate();
        Map<String, Object> variables = new HashMap<>(16);
        variables.put("operate", request.getOperate());
        variables.put("expire", request.getExpire());
        variables.put("verifyCode", captcha);

        SendMailRequest sendMailRequest = new SendMailRequest();
        // 验证码模版
        sendMailRequest.setTemplate(MailTemplate.VERIFY_CODE);
        // 发送html邮件时模板引擎参数
        sendMailRequest.setVariables(variables);
        // 收件人
        sendMailRequest.setTo(to);
        // 邮件标题
        sendMailRequest.setSubject(request.getSubject());
        boolean sendSuccess = sendHtmlMail(sendMailRequest);
        if (sendSuccess) {
            onSendCaptcha(request, captcha, limitKey);
            return Result.ok("发送邮件验证码成功!");
        }
        return Result.fail("发送邮件验证码失败!");
    }

    @Override
    public Result<StringResponse> verifyCaptcha(VerifyCaptchaRequest request) {
        if (StringUtils.isBlank(request.getCaptcha(), request.getEmail())) {
            return Result.fail("验证失败", StringResponse.builder().withData("验证失败").build());
        }
        String key = captchaService.getCaptchaKey(request.getEmail(), EMAIL_CAPTCHA_KEY);
        boolean verifySuccess = captchaService.verify(request.getCaptcha(), key);
        if (verifySuccess) {
            return Result.ok("验证成功", StringResponse.builder().withData("验证成功").build());
        }
        return Result.fail("验证失败", StringResponse.builder().withData("验证失败").build());
    }

    /**
     * 发送验证码成功后
     *  todo 发送成功，异步处理
     *
     * @param request
     */
    private void onSendCaptcha(SendCaptchaRequest request, String captcha, String limitKey) {
        String key = captchaService.getCaptchaKey(request.getTo(), EMAIL_CAPTCHA_KEY);
        long keyExpire = request.getExpire() * DateUnit.MINUTE.getSecond();
        // 存放邮箱和验证码到redis并设置过期时间
        captchaService.save(key, captcha, keyExpire);
        // 存放邮箱到发送频率限制key中（发送成功后，一分钟内不可再次发送）
        limitService.setLimit(limitKey);
    }

}
