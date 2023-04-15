package com.ds.nas.auth.dao.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2023/4/15
 * @description
 */
@Data
public class LoginRequest extends BaseRequest {

    /**
     * 登录类型 0.密码 1.短信验证码 2.邮箱验证码
     */
    private Integer type;

    private String name;

    private String password;

    private String phone;

}
