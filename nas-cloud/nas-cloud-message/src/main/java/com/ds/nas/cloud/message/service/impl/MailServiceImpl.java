package com.ds.nas.cloud.message.service.impl;

import com.ds.nas.cloud.message.dto.SendMailParam;
import com.ds.nas.cloud.message.service.MailService;
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

/**
 * @author ds
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${config.mail.from}")
    private String from;

    @Override
    public boolean sendSimpleMail(SendMailParam param) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(param.getTo());
        mailMessage.setSubject(param.getSubject());
        mailMessage.setText(param.getContent());
        mailMessage.setFrom(from);

        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            log.info("发送simple邮件失败! param: {}, ex: {}", param, e.getMessage());
            return false;
        }

        log.info("发送simple邮件成功!");
        return true;
    }

    @Override
    public boolean sendHtmlMail(SendMailParam param) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //创建邮件正文
            Context context = new Context();
            context.setVariables(param.getVariables());
            String emailContent = templateEngine.process(param.getTemplate(), context);

            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(param.getTo());
            helper.setSubject(param.getSubject());
            helper.setText(emailContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            log.info("发送html邮件失败! param: {}, ex: {}", param, e.getMessage());
            return false;
        }

        log.info("发送html邮件成功!");
        return true;
    }

}
