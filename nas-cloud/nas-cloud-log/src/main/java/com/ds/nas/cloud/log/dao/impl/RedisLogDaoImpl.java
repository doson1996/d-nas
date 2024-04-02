package com.ds.nas.cloud.log.dao.impl;

import cn.hutool.json.JSONUtil;
import com.ds.nas.cloud.log.dao.LogDao;
import com.ds.nas.cloud.log.io.request.LogRequest;
import com.ds.nas.lib.cache.redis.RedisUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/3/27
 * @description
 */
@Repository("redisLogDao")
public class RedisLogDaoImpl implements LogDao {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public String insertOne(LogRequest request) {
        String key = request.getRequestPrivate().getRequestApp() == null ? "nas-cloud-log" : request.getRequestPrivate().getRequestApp();
        redisUtil.lLeftPush(key, request.getLogJson());
        return "ok";
    }

}
