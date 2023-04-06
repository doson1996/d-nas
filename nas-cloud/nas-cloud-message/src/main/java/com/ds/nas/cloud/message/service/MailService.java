package com.ds.nas.cloud.message.service;

import com.ds.nas.cloud.message.dto.SendMailParam;

/**
 * @author ds
 */
public interface MailService {

    /**
     * 发送简单邮件
     * @param param
     * @return
     */
    boolean sendSimpleMail(SendMailParam param);


    /**
     * 发送html邮件
     *
     * @param param
     * @return
     */
    boolean sendHtmlMail(SendMailParam param);
}
