package com.ds.nas.lib.common.base.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ds
 * @date 2022/12/5
 * @description 通用响应
 */
@Data
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = 5223590227149117304L;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 请求响应时间
     */
    private String responseTime;

}
