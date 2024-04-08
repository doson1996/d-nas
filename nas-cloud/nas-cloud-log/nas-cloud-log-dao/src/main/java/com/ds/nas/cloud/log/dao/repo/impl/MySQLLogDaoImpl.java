package com.ds.nas.cloud.log.dao.repo.impl;

import com.ds.nas.cloud.log.dao.entity.LogInfo;
import com.ds.nas.cloud.log.dao.repo.LogDao;
import com.ds.nas.lib.common.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2024/3/27
 * @description
 */
@Slf4j
@Repository("mysqlLogDao")
public class MySQLLogDaoImpl implements LogDao {

    @Override
    public boolean insertOne(LogInfo request) {
        boolean result = true;
        try {
            log.info("数据库方式暂不支持");
        } catch (Exception e) {
            log.error("日志存储mysql异常，ex: ", e);
            result = false;
        }

        return result;
    }

    @Override
    public List<LogInfo> findAll(String app) {
        return new ArrayList<>();
    }

}
