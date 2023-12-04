package com.ds.nas.nat.api.io.request;

import com.ds.nas.lib.common.base.annotation.Check;
import com.ds.nas.lib.common.base.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ds
 * @date 2023/12/04
 * @description 新增一级控制表场景号段入参
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NumCtrlCreateRequest extends BaseRequest {

    /**
     * 号段增长步长
     */
    private Integer step;

    /**
     * 场景
     */
    @Check(require = true)
    private String scenario;

    /**
     * 是否启用缓存 0.否 1.启用
     */
    @Check(require = true)
    private Integer enableCache;

    /**
     * 一次缓存生成数量
     */
    private Integer cacheQuantity;

}
