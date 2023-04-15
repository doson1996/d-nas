package com.ds.nas.cloud.message.email.service;

import com.ds.nas.cloud.message.email.request.SendMailRequest;

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
}
