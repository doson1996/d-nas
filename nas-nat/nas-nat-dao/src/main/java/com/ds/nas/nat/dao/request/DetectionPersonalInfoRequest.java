package com.ds.nas.nat.dao.request;

import com.ds.nas.nat.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/11
 * @description
 */
@Data
public class DetectionPersonalInfoRequest extends BaseRequest {

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 身份证号
     */
    private String idCard;

}
