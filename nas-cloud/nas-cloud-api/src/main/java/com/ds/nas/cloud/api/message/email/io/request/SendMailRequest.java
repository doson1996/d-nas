package com.ds.nas.cloud.api.message.email.io.request;

import lombok.Data;

import java.util.Map;

/**
 * @author ds
 */
@Data
public class SendMailRequest {

    /**
     * 收件人
     */
    private String to;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    /**
     * 邮件模板
     */
    private String template;

    /**
     * 发送html邮件时模板引擎参数
     */
    private Map<String, Object> variables;
}
