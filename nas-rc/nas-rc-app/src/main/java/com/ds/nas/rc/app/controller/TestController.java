package com.ds.nas.rc.app.controller;

import com.ds.nas.lib.common.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ds
 * @date 2024/4/26
 * @description
 */
@RestController
public class TestController {

    @GetMapping("test")
    public Result<String> test() {
        return Result.ok("ok");
    }

}
