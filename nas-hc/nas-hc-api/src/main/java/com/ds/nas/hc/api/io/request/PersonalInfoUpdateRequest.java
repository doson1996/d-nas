package com.ds.nas.hc.api.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import com.ds.nas.lib.core.request.check.annotion.Check;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author ds
 * @date 2022/12/5
 * @description 更新入参
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PersonalInfoUpdateRequest extends BaseRequest {

    /**
     * 身份证号码
     */
    @Check(fieldName = "身份证号码", require = true, idCard = true)
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
