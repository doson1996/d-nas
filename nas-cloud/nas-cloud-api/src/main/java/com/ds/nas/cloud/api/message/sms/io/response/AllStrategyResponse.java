package com.ds.nas.cloud.api.message.sms.io.response;

import com.ds.nas.lib.common.base.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author ds
 * @date 2023/4/16
 * @description
 */
@ApiModel("获取所有策略响应")
@Data
@EqualsAndHashCode(callSuper = true)
public class AllStrategyResponse extends BaseResponse {

    @ApiModelProperty("所有策略")
    Set<String> data;

}
