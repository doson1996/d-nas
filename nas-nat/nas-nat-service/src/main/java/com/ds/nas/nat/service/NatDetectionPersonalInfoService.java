package com.ds.nas.nat.service;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.dao.domain.NatDetectionPersonalInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.nas.nat.dao.request.DetectionPersonalInfoEntryRequest;

/**
* @author ds
* @description 针对表【nat_detection_personal_info】的数据库操作Service
* @createDate 2022-12-10 23:58:31
*/
public interface NatDetectionPersonalInfoService extends IService<NatDetectionPersonalInfo> {

    /**
     * 信息录入
     * @param request 入参
     * @return 录入结果
     */
    Result<StringResponse> entry(DetectionPersonalInfoEntryRequest request);

}
