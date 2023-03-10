package com.ds.nas.hc.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ds.nas.lib.common.base.domain.BaseDomain;
import lombok.Data;

/**
 * 
 * @TableName hc_system_log
 */
@TableName(value ="hc_system_log")
@Data
public class HcSystemLog extends BaseDomain {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * 请求参数
     */
    private String requestData;

    /**
     * 响应ip
     */
    private String responseIp;

    /**
     * 响应参数
     */
    private String responseData;

    /**
     * 执行时间(ms)
     */
    private Integer executionTime;

}