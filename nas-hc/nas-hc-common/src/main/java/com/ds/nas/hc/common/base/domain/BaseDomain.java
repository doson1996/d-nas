package com.ds.nas.hc.common.base.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author ds
 * @date 2022/12/4
 * @description
 */
@Data
public class BaseDomain {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String cretaeBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 逻辑删除标志 0.未删除 1.已删除
     */
    private Integer deleteFlag;

}
