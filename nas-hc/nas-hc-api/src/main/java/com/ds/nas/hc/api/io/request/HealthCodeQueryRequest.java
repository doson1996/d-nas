package com.ds.nas.hc.api.io.request;


import com.ds.nas.lib.common.base.request.BaseRequest;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import com.ds.nas.lib.core.request.check.annotion.Check;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2022/12/4
 * @description 查询入参
 */
@Data
@EqualsAndHashCode(callSuper = true)
//@ApiModel(description = "查询入参")
public class HealthCodeQueryRequest extends BaseRequest {

    /**
     * 身份证号码
     */
    @Check(fieldName = "身份证号码", require = true, idCard = true)
    // @ApiModelProperty(value = "身份证号码", required = true)
    private String idCard;

}
