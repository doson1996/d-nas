package com.ds.nas.nat.app.controller;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.entity.RecentNucleicAcid;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.io.request.DetectionPersonalInfoEntryRequest;
import com.ds.nas.nat.api.io.request.RecentNucleicAcidRecordsQueryRequest;
import com.ds.nas.nat.service.NatDetectionPersonalInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ds
 * @date 2022/12/11
 * @description
 */
@RestController
@RequestMapping("detection-personal")
public class DetectionPersonalInfoController {

    @Resource
    private NatDetectionPersonalInfoService detectionPersonalInfoService;

    @PostMapping("entry")
    public Result<StringResponse> entry(@RequestBody DetectionPersonalInfoEntryRequest request) {
        return detectionPersonalInfoService.entry(request);
    }

    @PostMapping("recentNucleicAcidRecordsQuery")
    public Result<List<RecentNucleicAcid>> recentNucleicAcidRecordsQuery(RecentNucleicAcidRecordsQueryRequest request) {
        return detectionPersonalInfoService.recentNucleicAcidRecordsQuery(request);
    }

}
