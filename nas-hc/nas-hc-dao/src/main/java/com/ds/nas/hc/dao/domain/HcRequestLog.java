package com.ds.nas.hc.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.ds.nas.lib.common.base.domain.BaseDomain;
import lombok.Data;

/**
 * 
 * @author ds
 * @TableName hc_request_log
 */
@TableName(value ="hc_request_log")
@Data
public class HcRequestLog extends BaseDomain {
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
     * 返回码
     */
    private String returnCode;

    /**
     * 请求参数
     */
    private String requestData;

    /**
     * 响应参数
     */
    private String responseData;

    /**
     * 执行时间(ms)
     */
    private Long executionTime;

}