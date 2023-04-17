package com.ds.nas.cloud.message.sms.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2023/4/15
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SetStrategyRequest extends BaseRequest {

    /**
     * 策略名
     */
    private String strategy;

}
