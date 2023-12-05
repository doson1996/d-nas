package com.ds.nas.nat.app.controller;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.io.request.GetNoRequest;
import com.ds.nas.nat.api.io.request.NumCtrlCreateRequest;
import com.ds.nas.nat.service.NatNumCtrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/12/4
 * @description 号码生成控制
 */
@Slf4j
@RequestMapping("num-ctrl")
@RestController
public class NumCtrlController {

    @Resource
    private NatNumCtrlService numCtrlService;

    @PostMapping("create")
    public Result<StringResponse> create(@RequestBody NumCtrlCreateRequest request) {
        return numCtrlService.create(request);
    }

    @PostMapping("get-no")
    public Result<StringResponse> getNo(@RequestBody GetNoRequest request) {
        return numCtrlService.getNo(request);
    }

}
