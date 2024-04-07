package com.ds.nas.cloud.log.dao.repo.impl;


import com.ds.nas.cloud.log.dao.repo.LogDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
//        Query query = new Query(Criteria.where("age").gt("20"));
//        mongoTemplate.find(query, Log.class);

        mongoTemplate.insert(request);
        return "ok";
    }

}
