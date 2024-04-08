package com.ds.nas.cloud.log.dao.repo.impl;

import com.ds.nas.cloud.log.dao.entity.LogInfo;
import com.ds.nas.cloud.log.dao.repo.LogDao;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.util.DateUnit;
import com.ds.nas.lib.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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
            String key = logInfo.getApp() == null ? "nas-cloud-log" : logInfo.getApp();
            key = key + ":" + DateUtils.today();
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

}
