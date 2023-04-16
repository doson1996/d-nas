package com.ds.nas.cloud.message.sms.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2023/4/15
 * @description
 */
@Data
public class SetStrategyRequest extends BaseRequest {

    /**
     * 策略名
     */
    private String strategy;

}
