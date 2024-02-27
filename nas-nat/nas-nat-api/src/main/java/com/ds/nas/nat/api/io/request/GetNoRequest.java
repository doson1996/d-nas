package com.ds.nas.nat.api.io.request;

import com.ds.nas.lib.common.base.request.BaseRequest;
import com.ds.nas.lib.core.request.check.annotion.Check;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2023/12/04
 * @description 新增一级控制表场景号段入参
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetNoRequest extends BaseRequest {

    /**
     * 场景
     */
    @Check(require = true)
    private String scenario;

    /**
     * 地区号
     */
    @Check(require = true)
    private String areaCode;

    /**
     * 获取类型
     */
    private Integer type;

}
