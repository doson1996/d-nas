package com.ds.nas.cloud.log.dao.impl;

import cn.hutool.json.JSONUtil;
import com.ds.nas.cloud.log.dao.LogDao;
import com.ds.nas.cloud.log.io.request.LogRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/3/27
 * @description
 */
@Repository("mySqlLogDao")
public class MySqlLogDaoImpl implements LogDao {

    @Override
    public String insertOne(LogRequest request) {
        return "数据库方式暂不支持";
    }

}
