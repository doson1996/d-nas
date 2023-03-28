package com.ds.nas.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ds.nas.lib.cache.key.RedisGatewayKey;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author ds
 * @date 2021/12/29 21:54
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered, AuthGlobalConstant, RedisGatewayKey {

    @Resource
    private RedisUtil redisUtil;

    @PostConstruct
    private void init() {
        redisUtil.sAdd(GATEWAY_IGNORE_PATH_SET_KEY, ADMIN_LOGIN_PATH, USER_LOGIN_PATH);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String path = request.getURI().getPath();
        // 忽略鉴权路径判断
        boolean ignorePath = ignorePath(path);
        if (!ignorePath) {
            List<String> list = headers.get("token");
            if (list == null) {
                return intercept(exchange);
            }
            String token = list.get(0);
            // 用户信息json
            String userJson = redisUtil.get(token);
            //token无效或已过期
            if (StrUtil.isBlank(userJson)) {
                return intercept(exchange);
            }
            //如果是管理员用户
            if (token.startsWith("ADMIN-TOKEN")) {
                JSONObject jsonObject = JSONUtil.parseObj(userJson);
            }
        }

        return chain.filter(exchange);
    }

    /**
     * 是否忽略认证路径
     *
     * @param path
     * @return
     */
    private boolean ignorePath(String path) {
        // 当配置有 /*时，所有请求均不认证
        if (redisUtil.sIsMember(GATEWAY_IGNORE_PATH_SET_KEY, "/*")) {
            return true;
        }

        return redisUtil.sIsMember(GATEWAY_IGNORE_PATH_SET_KEY, path);
    }

    /**
     * 拦截请求，直接返回
     *
     * @param exchange
     * @return
     */
    private Mono<Void> intercept(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin", "*");
        response.getHeaders().set("Cache-Control", "no-cache");
        String body = JSONUtil.toJsonStr(Result.fail("无效Token"));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
