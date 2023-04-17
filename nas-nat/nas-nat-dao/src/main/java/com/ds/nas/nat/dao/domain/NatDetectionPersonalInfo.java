package com.ds.nas.nat.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.ds.nas.lib.common.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @TableName nat_detection_personal_info
 */
@TableName(value = "nat_detection_personal_info")
@Data
@EqualsAndHashCode(callSuper = true)
public class NatDetectionPersonalInfo extends BaseDomain {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 身份证号码
     */
    private String idCard;

}