package com.ds.nas.cloud.log.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author ds
 * @date 2024/3/25 23:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LogRequest extends BaseRequest {

    /**
     * 日志json
     */
    private String logJson;

    /**
     * 日志
     */
    private Map<String, Object> log;

}
