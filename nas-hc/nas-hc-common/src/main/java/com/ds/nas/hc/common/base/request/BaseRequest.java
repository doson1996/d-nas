package com.ds.nas.hc.common.base.request;

import lombok.Data;

import java.util.Date;

/**
 * @author ds
 * @date 2022/12/5
 * @description
 */
@Data
public class BaseRequest {

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 请求时间
     */
    private String requestTime;

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * mac地址
     */
    private String mac;
}
