package com.ds.nas.hc.api.io.request;

import com.ds.nas.lib.common.base.annotation.Check;
import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/5
 * @description 注册入参
 */
@Data
public class PersonalInfoRegisterRequest extends BaseRequest {

    /**
     * 姓名
     */
    @Check(require = true)
    private String name;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 住址
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

}
