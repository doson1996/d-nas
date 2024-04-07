package com.ds.nas.cloud.log.dao.repo.impl;


import com.ds.nas.cloud.log.dao.repo.LogDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/3/27
 * @description
 */
@Repository("mongoLogDao")
public class MongoLogDaoImpl implements LogDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public String insertOne(LogRequest request) {
        mongoTemplate.insert(request);
        return "ok";
    }

}
