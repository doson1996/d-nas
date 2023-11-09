package com.ds.nas.nat.service.proivder;

import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.dubbo.DetectionPersonalInfoProvider;
import com.ds.nas.nat.api.io.request.RecentNucleicAcidRecordsQueryRequest;
import com.ds.nas.nat.service.NatDetectionPersonalInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

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
    public Result<Set<Date>> recentNucleicAcidRecordsQuery(RecentNucleicAcidRecordsQueryRequest request) {
        return natDetectionPersonalInfoService.recentNucleicAcidRecordsQuery(request);
    }
}




