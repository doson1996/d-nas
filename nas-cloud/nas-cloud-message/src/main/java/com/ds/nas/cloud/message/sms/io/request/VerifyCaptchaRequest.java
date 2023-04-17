package com.ds.nas.cloud.message.sms.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2023/4/17
 * @description
 */
@ApiModel("发送验证码入参")
@Data
@EqualsAndHashCode(callSuper = true)
public class VerifyCaptchaRequest extends BaseRequest {

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String captcha;

}
