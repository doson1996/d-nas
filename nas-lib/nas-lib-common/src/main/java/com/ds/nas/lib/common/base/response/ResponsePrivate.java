package com.ds.nas.lib.common.base.response;

import com.ds.nas.lib.common.util.DateUtils;
import lombok.Data;

/**
 * @author ds
 * @date 2023/4/15
 * @description response私有通讯区
 */
@Data
public class ResponsePrivate {

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 返回id
     */
    private String responseId;


    /**
     * 请求响应时间
     */
    private String responseTime = DateUtils.now();

}
