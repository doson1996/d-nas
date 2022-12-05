package com.ds.nas.hc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.domain.HcPersonalInfo;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import com.ds.nas.hc.dao.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.dao.response.ApplyHealthCodeResponse;
import com.ds.nas.hc.dao.response.PersonalInfoRegisterResponse;

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
    Result<ApplyHealthCodeResponse> apply(HealthCodeApplyRequest request);

    /**
     * 查询健康码
     *
     * @param idCard
     * @return
     */
    Result<ApplyHealthCodeResponse> queryByIdCard(String idCard);

    /**
     * 注册
     * @param request
     * @return
     */
    Result<PersonalInfoRegisterResponse> register(PersonalInfoRegisterRequest request);

}
