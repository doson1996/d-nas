package com.ds.nas.cloud.message.email.controller;

import com.ds.nas.cloud.api.message.email.io.request.SendCaptchaRequest;
import com.ds.nas.cloud.api.message.email.io.request.SendMailRequest;
import com.ds.nas.cloud.api.message.email.io.request.VerifyCaptchaRequest;
import com.ds.nas.cloud.message.email.constant.MailTemplate;
import com.ds.nas.cloud.message.email.service.MailService;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/4/6
 * @description 邮件服务
 */
@Api(tags = "邮件服务")
@RestController
@RequestMapping("mail")
public class MailController {

    @Resource
    private MailService mailService;

    @ApiOperation("发送简单邮件")
    @PostMapping("send-simple")
    public Result<StringResponse> sendSimple(@RequestBody SendMailRequest request) {
        if (mailService.sendSimpleMail(request)) {
            return Result.ok("发送邮件成功!");
        }
        return Result.fail("发送邮件失败!");
    }

    @ApiOperation("发送html邮件")
    @PostMapping("send-html")
    public Result<StringResponse> sendHtml(@RequestBody SendMailRequest request) {
        if (mailService.sendHtmlMail(request)) {
            return Result.ok("发送邮件成功!");
        }
        return Result.fail("发送邮件失败!");
    }

    @ApiOperation("发送验证码邮件")
    @PostMapping("send-verifyCode")
    public Result<StringResponse> sendVerifyCode(@RequestBody SendCaptchaRequest request) {
        return mailService.sendVerifyCode(request);
    }

    @ApiOperation("验证短信验证码")
    @PostMapping("verify-captcha")
    public Result<StringResponse> verifyCaptcha(@RequestBody VerifyCaptchaRequest request) {
        return mailService.verifyCaptcha(request);
    }

    @ApiOperation("发送激活邮件")
    @PostMapping("send-activate")
    public Result<StringResponse> sendActivate(@RequestBody SendMailRequest request) {
        request.setTemplate(MailTemplate.ACTIVATE_ACCOUNT);
        return sendHtml(request);
    }

    @GetMapping("activate/{id}")
    public Result<StringResponse> sendHtml(@PathVariable String id) {
        return Result.ok("账号" + id + "激活成功!", StringResponse.builder().withData(id).build());
    }

}
