package com.ds.nas.hc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.domain.HcPersonalInfo;
import com.ds.nas.hc.dao.request.ApplyHealthCodeRequest;
import com.ds.nas.hc.dao.response.ApplyHealthCodeResponse;

/**
 * @author ds
 * @description 针对表【hc_personal_info】的数据库操作Service
 * @createDate 2022-12-04 13:28:57
 */
public interface HcPersonalInfoService extends IService<HcPersonalInfo> {

    /**
     * 健康码申领
     *
     * @param request
     * @return
     */
    Result<ApplyHealthCodeResponse> apply(ApplyHealthCodeRequest request);

}
