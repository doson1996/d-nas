package com.ds.nas.hc.api.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2022/12/4
 * @description 申领健康码入参
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class HealthCodeApplyRequest extends BaseRequest {

    /**
     * 姓名
     */
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
