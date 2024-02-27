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
public class NumCtrlCreateRequest extends BaseRequest {

    /**
     * 场景
     */
    @Check(require = true)
    private String scenario;

    /**
     * 号段增长步长
     */
    @Check(require = true)
    private Integer step;

    /**
     * 号段最大值
     */
    @Check(require = true)
    private Integer maxNum;

    /**
     * 是否启用缓存 0.否 1.启用
     */
    @Check(require = true)
    private Integer enableCache;

    /**
     * 一次缓存生成数量
     */
    private Integer cacheQuantity;

    /**
     * 描述
     */
    private String description;

}
