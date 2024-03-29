package com.ds.nas.lib.common.base.request;

import com.ds.nas.lib.common.util.DateUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ds
 * @date 2023/4/15
 * @description request私有通讯区
 */
@Data
public class RequestPrivate implements Serializable {

    private static final long serialVersionUID = 1470687257992062475L;

    /**
     * token
     */
    private String token;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 请求应用
     */
    private String requestApp;

    /**
     * 请求时间
     */
    private String requestTime = DateUtils.now();

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * 请求mac地址
     */
    private String mac;

}
