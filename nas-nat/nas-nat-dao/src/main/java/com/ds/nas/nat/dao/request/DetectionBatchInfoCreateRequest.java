package com.ds.nas.nat.dao.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;

/**
 * @author ds
 * @date 2022/12/12
 * @description 提交
 */
@Data
public class DetectionBatchInfoCreateRequest extends BaseRequest {

    /**
     * 检测机构
     */
    private String entryMechanism;

}
