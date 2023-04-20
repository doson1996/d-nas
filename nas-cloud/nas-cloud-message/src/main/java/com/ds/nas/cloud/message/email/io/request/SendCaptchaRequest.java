package com.ds.nas.cloud.message.email.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2023/4/20
 * @description 发送邮件验证码入参
 */
@ApiModel("发送邮件验证码入参")
@EqualsAndHashCode(callSuper = true)
@Data
public class SendCaptchaRequest extends BaseRequest {

    /**
     * 收件人
     */
    @ApiModelProperty("收件人")
    private String to;

    /**
     * 邮件标题
     */
    @ApiModelProperty("邮件标题")
    private String subject;

    /**
     * 本次验证码进行的操作
     */
    @ApiModelProperty("本次验证码进行的操作")
    private String operate;

    /**
     * 验证码有效期(分钟)
     */
    @ApiModelProperty("验证码有效期(分钟)")
    private Integer expire;

}
