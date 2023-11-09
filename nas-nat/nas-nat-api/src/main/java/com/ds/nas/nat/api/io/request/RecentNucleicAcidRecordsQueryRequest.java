package com.ds.nas.nat.api.io.request;

import com.ds.nas.lib.common.base.annotation.Check;
import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2022/12/4
 * @description 查询入参
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RecentNucleicAcidRecordsQueryRequest extends BaseRequest {

    /**
     * 身份证号码
     */
    @Check(fieldName = "身份证号码", require = true, idCard = true)
    private String idCard;

    /**
     * 查询天数
     */
    @Check(fieldName = "查询天数", require = true)
    private Integer days;

}
