package com.ds.nas.nat.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ds.nas.lib.common.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 批次号段控制表
 * @TableName nat_num_ctrl_batch
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "nat_num_ctrl_batch")
@Data
public class NatNumCtrlBatch extends BaseDomain {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 场景
     */
    private String scenario;

    /**
     * 地区号
     */
    private String areaCode;

    /**
     * 号段起始
     */
    private Integer start;

    /**
     * 号段结束
     */
    private Integer end;

    /**
     * 号段结束
     */
    private Integer current;

}