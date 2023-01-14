package com.ds.nas.nat.common.base.request;

import com.sun.org.apache.xpath.internal.operations.String;
import lombok.Data;

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
     * token
     */
    private String token;

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
