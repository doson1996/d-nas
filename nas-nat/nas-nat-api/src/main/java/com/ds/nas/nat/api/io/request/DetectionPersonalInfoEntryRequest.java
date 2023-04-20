package com.ds.nas.nat.api.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/11
 * @description
 */
@Data
public class DetectionPersonalInfoEntryRequest extends BaseRequest {

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 身份证号
     */
    private String idCard;

}