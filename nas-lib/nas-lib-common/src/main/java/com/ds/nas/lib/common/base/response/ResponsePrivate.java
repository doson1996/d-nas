package com.ds.nas.lib.common.base.response;

import com.ds.nas.lib.common.util.DateUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ds
 * @date 2023/4/15
 * @description response私有通讯区
 */
@Data
public class ResponsePrivate implements Serializable {

    private static final long serialVersionUID = -5947779920373539089L;

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
