package com.ds.nas.hc.dao.request;

import lombok.Data;

/**
 * @author ds
 * @date 2022/12/4
 * @description 申领健康码入参
 */
@Data
public class ApplyHealthCodeRequest {

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
