package com.ds.nas.cloud.message.controller;

import com.ds.nas.cloud.message.service.SmsService;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

/**
 * @author ds
 * @date 2023/4/6
 * @description 短信服务
 */
@Api("短信服务")
@RestController
@AllArgsConstructor
@RequestMapping("sms")
public class SmsController {

    private final SmsService smsService;

    @ApiOperation("发送短信")
    @PostMapping("send")
    public Result<StringResponse> send(@RequestBody Map<String, Object> params) {
        return smsService.send(params);
    }

    @ApiOperation("设置短信发送策略")
    @PostMapping("set-strategy")
    public Result<StringResponse> setStrategy(String strategy) {
        return smsService.setStrategy(strategy);
    }

    @ApiOperation("当前短信发送策略")
    @PostMapping("current-strategy")
    public Result<StringResponse> currentStrategy() {
        return smsService.currentStrategy();
    }

    @ApiOperation("获取所有短信发送策略")
    @PostMapping("all-strategy")
    public Result<Set<String>> allStrategy() {
        return smsService.allStrategy();
    }


}
