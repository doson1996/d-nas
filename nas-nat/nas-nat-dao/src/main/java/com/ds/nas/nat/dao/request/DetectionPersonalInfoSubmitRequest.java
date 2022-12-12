package com.ds.nas.nat.dao.request;

import com.ds.nas.nat.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/12
 * @description 提交
 */
@Data
public class DetectionPersonalInfoSubmitRequest extends BaseRequest {

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 检测类型: 0.混检 1.单检
     */
    private Integer type;

}
