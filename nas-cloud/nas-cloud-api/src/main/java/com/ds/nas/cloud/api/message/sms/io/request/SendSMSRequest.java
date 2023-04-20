package com.ds.nas.cloud.api.message.sms.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2023/4/15
     * @description 发送短信参数（只支持验证码）
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SendSMSRequest extends BaseRequest {

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 有效期(分钟)
     */
    private String expire;

}
