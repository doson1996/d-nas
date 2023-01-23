package com.ds.nas.lib.common.base.request;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ds
 * @date 2022/12/5
 * @description 通用请求
 */
@Data
public class BaseRequest  implements Serializable {

    private static final long serialVersionUID = 737016089980834810L;

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
    private String requestTime = DateUtil.now();

    /**
     * 请求ip
     */
    private String requestIp;

    /**
     * mac地址
     */
    private String mac;

}
