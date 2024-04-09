package com.ds.nas.cloud.log.api.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author ds
 * @date 2024/3/25 23:52
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class LogRequest extends BaseRequest {

    /**
     * 存储类型 0.mongo  1.mysql  2.redis
     */
    private  Integer type;

    /**
     * 日志json
     */
    private String logJson;

    /**
     * 日志
     */
    private Map<String, Object> log;

}
