package com.ds.nas.hc.dao.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/4
 * @description 查询健康码入参
 */
@Data
public class HealthCodeQueryRequest extends BaseRequest {

    /**
     * 身份证号码
     */
    private String idCard;

}
