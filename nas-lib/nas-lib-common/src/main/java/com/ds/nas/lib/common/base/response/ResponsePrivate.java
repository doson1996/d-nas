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
     * 响应id
     */
    private String responseId;

    /**
     * 响应应用
     */
    private String responseApp;

    /**
     * todo 外部调用时，返回到网关抹掉
     * 响应服务器ip
     */
    private String responseIp;

    /**
     * 响应时间
     */
    private String responseTime = DateUtils.now();

}
