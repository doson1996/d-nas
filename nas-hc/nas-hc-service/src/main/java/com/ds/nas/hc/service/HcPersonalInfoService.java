package com.ds.nas.hc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.nas.hc.dao.request.*;
import com.ds.nas.hc.dao.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.hc.dao.domain.HcPersonalInfo;
import com.ds.nas.hc.dao.response.HealthCodeQueryResponse;
import com.ds.nas.hc.dao.response.PersonalInfoRegisterResponse;
import com.ds.nas.hc.dao.response.PersonalInfoUpdateResponse;

/**
 * @author ds
 * @description 针对表【hc_personal_info】的数据库操作Service
 * @createDate 2022-12-04 13:28:57
 */
public interface HcPersonalInfoService extends IService<HcPersonalInfo> {

    /**
     * 根据身份证号申领健康码
     *
     * @param request
     * @return
     */
    Result<StringResponse> apply(HealthCodeApplyRequest request);

    /**
     * 根据身份证号查询健康码
     *
     * @param request
     * @return
     */
    Result<HealthCodeQueryResponse> queryByIdCard(HealthCodeQueryRequest request);

    /**
     * 用户注册
     * @param request
     * @return
     */
    Result<PersonalInfoRegisterResponse> register(PersonalInfoRegisterRequest request);

    /**
     * 根据idCard更新
     * @param request
     * @return
     */
    Result<PersonalInfoUpdateResponse> updateByIdCard(PersonalInfoUpdateRequest request);

    /**
     * 根据idCard批量更新
     * @param request
     * @return
     */
    Result<PersonalInfoBatchUpdateResponse> updateByIdCards(PersonalInfoBatchUpdateRequest request);

}
