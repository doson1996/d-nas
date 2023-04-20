package com.ds.nas.cloud.message.email.service.impl;

import com.ds.nas.cloud.message.constant.MessageConstant;
import com.ds.nas.cloud.message.email.constant.MailTemplate;
import com.ds.nas.cloud.message.email.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.message.email.io.request.SendMailRequest;
import com.ds.nas.cloud.message.email.io.request.VerifyCaptchaRequest;
import com.ds.nas.cloud.message.email.service.MailService;
import com.ds.nas.lib.cache.key.RedisEmailKey;
import com.ds.nas.lib.cache.redis.RedisUtil;
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
public class MailServiceImpl implements MailService, MessageConstant, RedisEmailKey {

    @Resource
    RedisUtil redisUtil;

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
    public Result<StringResponse> sendVerifyCode(SendCaptchaRequest request) {
        // 收件人
        String to = request.getTo();
        // 判断是否限制发送频率
        String limitValue = redisUtil.get(getSmsLimitKey(to));
        if (LIMIT_VALUE.equals(limitValue)) {
            return Result.fail("邮件发送失败", StringResponse.builder().withData("请勿频繁请求发送邮箱验证码").build());
        }

        String captcha = getCaptcha();
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
            onSendSuccess(request, captcha);
            return Result.ok("发送邮件验证码成功!");
        }
        return Result.fail("发送邮件验证码失败!");
    }

    @Override
    public Result<StringResponse> verifyCaptcha(VerifyCaptchaRequest request) {
        if (StringUtils.isBlank(request.getCaptcha())) {
            return Result.fail("验证失败", StringResponse.builder().withData("验证失败").build());
        }
        String key = getCaptchaKey(request.getEmail());
        // 获取存在redis中的验证码
        String captcha = redisUtil.get(key);
        if (!request.getCaptcha().equals(captcha)) {
            return Result.fail("验证失败", StringResponse.builder().withData("验证失败").build());
        }
        // 验证成功，删除redis中的验证码（只能使用一次）
        redisUtil.delete(key);
        return Result.ok("验证成功", StringResponse.builder().withData("验证成功").build());
    }

    /**
     * 发送验证码成功后
     *  todo 发送成功，异步处理
     *
     * @param request
     */
    private void onSendSuccess(SendCaptchaRequest request, String captcha) {
        String key = getCaptchaKey(request.getTo());
        long keyExpire = request.getExpire() * DateUnit.MINUTE.getSecond();
        // 存放手机号和验证码到redis并设置过期时间
        redisUtil.set(key, captcha, keyExpire);
        // 存放手机号到发送频率限制key中（发送成功后，一分钟内不可再次发送）
        redisUtil.set(getSmsLimitKey(request.getTo()), LIMIT_VALUE, LIMIT_VALUE_EXPIRE * DateUnit.MINUTE.getSecond());
    }

    /**
     * 邮箱发送频率 key
     *
     * @param to
     * @return
     */
    private String getSmsLimitKey(String to) {
        return EMAIL_LIMIT_KEY + to;
    }

    /**
     * redis存验证码 key (key 前缀:收件人邮箱 value 验证码)
     *
     * @param to
     * @return
     */
    private String getCaptchaKey(String to) {
        return EMAIL_CAPTCHA_KEY + to;
    }

    /**
     * 生成验证码
     *
     * @return
     */
    private String getCaptcha() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));
    }

}
