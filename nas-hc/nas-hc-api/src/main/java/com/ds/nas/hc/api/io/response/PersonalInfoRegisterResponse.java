package com.ds.nas.hc.api.io.response;

import com.ds.nas.lib.common.base.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author ds
 * @date 2022/12/5
 * @description 注册出参
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PersonalInfoRegisterResponse extends BaseResponse {

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

    /**
     * 0.未申领 1.绿码 2.黄码 3.红码 4.弹窗
     */
    private Integer health;

    /**
     * 最后一次核酸时间
     */
    private Date lastNucleicAcidTime;

}
