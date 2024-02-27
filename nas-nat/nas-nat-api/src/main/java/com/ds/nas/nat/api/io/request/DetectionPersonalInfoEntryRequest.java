package com.ds.nas.nat.api.io.request;


import com.ds.nas.lib.common.base.request.BaseRequest;
import com.ds.nas.lib.core.request.check.annotion.Check;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2022/12/11
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DetectionPersonalInfoEntryRequest extends BaseRequest {

    /**
     * 批次号
     */
    @Check(fieldName = "批次号", require = true)
    private String batchNo;

    /**
     * 身份证号
     */
    @Check(fieldName = "身份证号码", require = true, idCard = true)
    private String idCard;

}
