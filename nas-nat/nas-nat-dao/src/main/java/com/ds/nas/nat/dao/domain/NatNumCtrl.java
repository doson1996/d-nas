package com.ds.nas.nat.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ds.nas.lib.common.base.domain.BaseDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 号码生成控制一级表
 *
 * @TableName nat_num_ctrl
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "nat_num_ctrl")
@Data
public class NatNumCtrl extends BaseDomain {

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
     * 一级表号段起始
     */
    private Integer firstStart;

    /**
     * 一级表号段结束
     */
    private Integer firstEnd;

    /**
     * 号段最大值
     */
    private Integer maxNum;

    /**
     * 号段增长间隔
     */
    private Integer step;

    /**
     * 是否启用缓存 0.否 1.启用
     */
    private Integer enableCache;

    /**
     * 一次缓存生成数量
     */
    private Integer cacheQuantity;

    /**
     * 描述
     */
    private String description;

}