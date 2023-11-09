package com.ds.nas.hc.api.io.response;

import com.ds.nas.lib.common.entity.RecentNucleicAcid;
import com.ds.nas.lib.common.base.response.BaseResponse;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @author ds
 * @date 2022/12/4
 * @description 查询返回
 */
@EqualsAndHashCode(callSuper = true)
@Data
//@ApiModel(description = "查询返回")
public class HealthCodeQueryResponse extends BaseResponse {

    /**
     * 姓名
     */
    //@ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 身份证号码
     */
   // @ApiModelProperty(value = "身份证号码")
    private String idCard;

    /**
     * 住址
     */
   // @ApiModelProperty(value = "住址")
    private String address;

    /**
     * 手机号
     */
    //@ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 0.未申领 1.绿码 2.黄码 3.红码 4.弹窗
     */
    //@ApiModelProperty(value = "0.未申领 1.绿码 2.黄码 3.红码 4.弹窗")
    private Integer health;

    /**
     * 最后一次核酸时间
     */
    //@ApiModelProperty(value = "最后一次核酸时间")
    private Date lastNucleicAcidTime;

    /**
     * 最近检测记录
     */
    private List<RecentNucleicAcid> recentNucleicAcidRecords;

}
