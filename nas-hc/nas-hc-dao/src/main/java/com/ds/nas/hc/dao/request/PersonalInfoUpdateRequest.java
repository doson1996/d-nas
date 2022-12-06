package com.ds.nas.hc.dao.request;

import com.ds.nas.hc.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/5
 * @description 更新入参
 */
@Data
public class PersonalInfoUpdateRequest extends BaseRequest {

    /**
     * id
     */
    private Integer id;

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
