package com.ds.nas.cloud.log.dao.repo.impl;

import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.ds.nas.cloud.log.dao.entity.LogInfo;
import com.ds.nas.cloud.log.dao.repo.LogDao;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.util.DateUnit;
import com.ds.nas.lib.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2024/3/27
 * @description
 */
@Slf4j
@Repository("redisLogDao")
public class RedisLogDaoImpl implements LogDao {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean insertOne(LogInfo logInfo) {
        boolean result = true;
        try {
            String key = generaKey(logInfo.getApp());
            redisUtil.lLeftPush(key, logInfo.getLogJson());
            // 当天第一次存日志时，设置过期时间
            if (Long.valueOf(1).equals(redisUtil.lLen(key))) {
                redisUtil.expire(key, 30, TimeUnit.DAYS);
            }
        } catch (Exception e) {
            log.error("日志存储redis异常，ex: ", e);
            result = false;
        }

        return result;
    }

    @Override
    public List<LogInfo> findAll(String app) {
        List<LogInfo> result = new ArrayList<>();
        String key = generaKey(app);
        List<String> redisData = redisUtil.lRange(key, 0, -1);
        for (String data : redisData) {
            log.info("data: {}", data);
        }
        return result;
    }

    /**
     * 生成key
     * @param app
     * @return
     */
    private String generaKey(String app) {
        return generaKey(app, true);
    }

    /**
     * 生成key
     * @param app
     * @param date 是否带日期
     * @return
     */
    private String generaKey(String app, boolean date) {
        String key = app == null ? "nas-cloud-log" : app;
        if (date) {
            key = key + ":" + DateUtils.today();
        }

        return key;
    }

}
