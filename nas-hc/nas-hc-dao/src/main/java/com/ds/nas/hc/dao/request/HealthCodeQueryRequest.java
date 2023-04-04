package com.ds.nas.hc.dao.request;

import com.ds.nas.lib.common.base.annotation.Check;
import com.ds.nas.lib.common.base.request.BaseRequest;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/4
 * @description 查询入参
 */
@Data
//@ApiModel(description = "查询入参")
public class HealthCodeQueryRequest extends BaseRequest {

    /**
     * 身份证号码
     */
    @Check(require = true)
  //  @ApiModelProperty(value = "身份证号码", required = true)
    private String idCard;

}
