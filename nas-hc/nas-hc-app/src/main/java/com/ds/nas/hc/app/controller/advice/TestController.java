package com.ds.nas.hc.app.controller.advice;

import com.ds.nas.nat.api.dubbo.EchoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ds
 * @date 2023/1/20
 * @description
 */
@RestController
@RequestMapping("test")
public class TestController {

    @DubboReference(version = "1.0")
    private EchoService echoService;

    @RequestMapping("dubbo")
    public String dubbo(String msg) {
        return echoService.echo(msg);
    }

}
