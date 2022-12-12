package com.ds.nas.nat.dao.request;

import com.ds.nas.nat.common.base.request.BaseRequest;
import lombok.Data;

import java.util.Date;

/**
 * @author ds
 * @date 2022/12/12
 * @description 提交
 */
@Data
public class DetectionPersonalInfoCreateRequest extends BaseRequest {

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 检测机构
     */
    private String entryMechanism;

}
