package com.ds.nas.nat.service.provider;

import com.ds.nas.lib.common.entity.RecentNucleicAcid;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.dubbo.DetectionPersonalInfoProvider;
import com.ds.nas.nat.api.io.request.RecentNucleicAcidRecordsQueryRequest;
import com.ds.nas.nat.service.NatDetectionPersonalInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ds
 * @date 2023/11/09
 * @description
 */
@DubboService(version = "1.0")
public class DetectionPersonalInfoProviderImplV1 implements DetectionPersonalInfoProvider {

    @Resource
    private NatDetectionPersonalInfoService natDetectionPersonalInfoService;

    @Override
    public Result<List<RecentNucleicAcid>> recentNucleicAcidRecordsQuery(RecentNucleicAcidRecordsQueryRequest request) {
        return natDetectionPersonalInfoService.recentNucleicAcidRecordsQuery(request);
    }

}




