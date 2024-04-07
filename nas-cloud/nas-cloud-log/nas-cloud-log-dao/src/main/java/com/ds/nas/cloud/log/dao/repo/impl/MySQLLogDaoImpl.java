package com.ds.nas.cloud.log.dao.repo.impl;

import com.ds.nas.cloud.log.app.dao.LogDao;
import com.ds.nas.cloud.log.app.io.request.LogRequest;
import org.springframework.stereotype.Repository;

/**
 * @author ds
 * @date 2024/3/27
 * @description
 */
@Repository("mySQLLogDao")
public class MySQLLogDaoImpl implements LogDao {

    @Override
    public String insertOne(LogRequest request) {
        return "数据库方式暂不支持";
    }

}
