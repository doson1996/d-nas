package com.ds.nas.hc.api.io.response;

import com.ds.nas.lib.common.base.response.BaseResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ds
 * @date 2022/12/5
 * @description 更新出参
 */
@Data
public class PersonalInfoBatchUpdateResponse extends BaseResponse {

    /**
     * 身份证号码
     */
    private List<String> idCards;

    /**
     * 0.未申领 1.绿码 2.黄码 3.红码 4.弹窗
     */
    private Integer health;

    /**
     * 最后一次核酸时间
     */
    private Date lastNucleicAcidTime;

}
