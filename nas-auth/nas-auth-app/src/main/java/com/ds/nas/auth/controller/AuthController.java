package com.ds.nas.auth.controller;

import cn.hutool.crypto.digest.MD5;
import com.ds.nas.auth.dao.request.LoginRequest;
import com.ds.nas.lib.cache.key.RedisAuthKey;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/4/15
 * @description 认证
 */
@Api(tags = "认证中心")
@RestController
@RequestMapping("auth")
public class AuthController {

    @Resource
    private RedisUtil redisUtil;

    @ApiOperation("登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginRequest request) {
        String name = request.getName();
        String password = request.getPassword();
        if (StringUtils.isBlank(name, password)) {
            return Result.fail("登录失败");
        }
        String token = MD5.create().digestHex16(name + password);
        redisUtil.set(RedisAuthKey.LOGIN_TOKEN_KEY + token, name, 60);
        return Result.ok("登录成功!", token);
    }

}
