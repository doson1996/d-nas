package com.ds.nas.cloud.log.dao.repo.impl;

import cn.hutool.core.util.IdUtil;
import com.ds.nas.cloud.log.dao.entity.ESLogInfo;
import com.ds.nas.cloud.log.dao.entity.LogInfo;
import com.ds.nas.cloud.log.dao.repo.ESLogInfoDao;
import com.ds.nas.cloud.log.dao.repo.LogDao;
import com.ds.nas.lib.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ds
 * @date 2024/4/19
 * @description
 */
@Slf4j
@Repository("esLogDao")
public class EsLogDaoImpl implements LogDao {

    @Resource
    private ESLogInfoDao esLogInfoDao;

    @Override
    public boolean insertOne(LogInfo logInfo) {
        ESLogInfo esLogInfo = new ESLogInfo();
        esLogInfo.setId(IdUtil.simpleUUID());
        esLogInfo.setApp(logInfo.getApp());
        esLogInfo.setLog(logInfo.getLogJson());
        esLogInfo.setCreateTime(new Date());
        esLogInfo.setUpdateTime(new Date());
        ESLogInfo save = esLogInfoDao.save(esLogInfo);
        return StringUtils.isNotBlank(save.getId());
    }

    @Override
    public List<LogInfo> findAll(String app) {
        return null;
    }
}
