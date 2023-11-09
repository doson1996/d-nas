package com.ds.nas.nat.api.dubbo;

import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.io.request.RecentNucleicAcidRecordsQueryRequest;

import java.util.Date;
import java.util.Set;

public interface DetectionPersonalInfoProvider {

    /**
     *
     * @param request
     * @return
     */
    Result<Set<Date>> recentNucleicAcidRecordsQuery(RecentNucleicAcidRecordsQueryRequest request);

}
