package com.ds.nas.cloud.log.dao.repo;

import com.ds.nas.cloud.log.dao.entity.LogInfo;

/**
 * @author ds
 * @date 2024/3/25 23:50
 */
public interface LogDao {

    /**
     * 插入一条日志
     * @param logInfo
     * @return
     */
    boolean insertOne(LogInfo logInfo);

}
