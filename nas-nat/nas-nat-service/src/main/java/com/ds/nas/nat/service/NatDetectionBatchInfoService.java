package com.ds.nas.nat.service;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.dao.domain.NatDetectionBatchInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ds.nas.nat.api.io.request.DetectionBatchInfoCreateRequest;
import com.ds.nas.nat.api.io.request.DetectionBatchInfoDetectionRequest;
import com.ds.nas.nat.api.io.request.DetectionBatchInfoSubmitRequest;

/**
* @author ds
* @description 针对表【nat_detection_batch_info】的数据库操作Service
* @createDate 2022-12-10 23:55:13
*/
public interface NatDetectionBatchInfoService extends IService<NatDetectionBatchInfo> {

    /**
     * 获取batchNo
     * @return 批次号
     */
    Result<StringResponse> getBatchNo();

    /**
     * 新增批次
     * @param request
     * @return
     */
    Result<StringResponse> create(DetectionBatchInfoCreateRequest request);

    /**
     * 提交批次
     * @param request
     * @return
     */
    Result<StringResponse> submit(DetectionBatchInfoSubmitRequest request);

    /**
     * 检测
     * @param request
     * @return
     */
    Result<StringResponse> detection(DetectionBatchInfoDetectionRequest request);

}
