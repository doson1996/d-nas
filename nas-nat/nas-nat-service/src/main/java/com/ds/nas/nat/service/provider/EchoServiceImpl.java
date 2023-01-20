package com.ds.nas.nat.service.provider;

import com.ds.nas.nat.api.dubbo.EchoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author ds
 * @date 2023/1/20
 * @description
 */
@DubboService(version = "1.0")
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String msg) {
        return "dubbo: hello " + msg;
    }

}
