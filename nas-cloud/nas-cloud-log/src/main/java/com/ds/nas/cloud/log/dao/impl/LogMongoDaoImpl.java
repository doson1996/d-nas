package com.ds.nas.cloud.log.dao.impl;

import cn.hutool.json.JSONUtil;
import com.ds.nas.cloud.log.dao.LogMongoDao;
import com.ds.nas.cloud.log.io.request.LogRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/3/27
 * @description
 */
@Repository
public class LogMongoDaoImpl implements LogMongoDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public String insertOne(LogRequest request) {
        LogRequest insert = mongoTemplate.insert(request);
        return JSONUtil.toJsonStr(insert);
    }

}
