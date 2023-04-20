package com.ds.nas.cloud.api.message.email.io.request;

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
@ApiModel("验证验证码入参")
@Data
@EqualsAndHashCode(callSuper = true)
public class VerifyCaptchaRequest extends BaseRequest {

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String captcha;

}
