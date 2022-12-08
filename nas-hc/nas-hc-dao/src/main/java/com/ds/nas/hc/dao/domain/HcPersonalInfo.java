package com.ds.nas.hc.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.ds.nas.hc.common.base.domain.BaseDomain;
import lombok.Data;

/**
 * 人员信息
 *
 * @author ds
 * @TableName hc_personal_info
 */
@TableName(value = "hc_personal_info")
@Data
public class HcPersonalInfo extends BaseDomain implements Serializable {

    private static final long serialVersionUID = -7781476491561838266L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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