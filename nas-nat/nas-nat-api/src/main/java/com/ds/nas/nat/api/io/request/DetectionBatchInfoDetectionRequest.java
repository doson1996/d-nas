package com.ds.nas.nat.api.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/12
 * @description 提交
 */
@Data
public class DetectionBatchInfoDetectionRequest extends BaseRequest {

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 检测结果: 0.未检测 1.正常 2.异常
     */
    private Integer detectionResult;

    /**
     * 检测机构
     */
    private String detectionMechanism;

}
