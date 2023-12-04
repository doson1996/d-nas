package com.ds.nas.nat.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ds.nas.lib.common.base.domain.BaseDomain;
import lombok.Data;

/**
 * 
 * @TableName nat_num_ctrl 号码生成控制一级表
 */
@TableName(value ="nat_num_ctrl")
@Data
public class NatNumCtrl extends BaseDomain {

    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 一级表号段起始
     */
    private Integer firstStart;

    /**
     * 一级表号段结束
     */
    private Integer firstEnd;

    /**
     * 号段增长间隔
     */
    private Integer interval;

    /**
     * 场景
     */
    private String scenario;

}