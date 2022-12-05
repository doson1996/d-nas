package com.ds.nas.hc.dao.request;

import com.ds.nas.hc.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/4
 * @description 申领健康码入参
 */
@Data
public class HealthCodeApplyRequest extends BaseRequest {

    /**
     * 身份证号码
     */
    private String idCard;

}
