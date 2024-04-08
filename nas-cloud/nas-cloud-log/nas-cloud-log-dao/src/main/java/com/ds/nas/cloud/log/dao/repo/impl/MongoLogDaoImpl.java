package com.ds.nas.cloud.log.dao.repo.impl;


import com.ds.nas.cloud.log.dao.entity.LogInfo;
import com.ds.nas.cloud.log.dao.repo.LogDao;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ds
 * @date 2024/3/27
 * @description
 */
@Slf4j
@Repository("mongoLogDao")
public class MongoLogDaoImpl implements LogDao {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public boolean insertOne(LogInfo logInfo) {
        boolean result = true;
//        Query query = new Query(Criteria.where("age").gt("20"));
//        mongoTemplate.find(query, Log.class);
        try {
            String app = logInfo.getApp();
//            boolean collectionExists = mongoTemplate.collectionExists(app);
//            if (!collectionExists) {
//                mongoTemplate.createCollection(app);
//            }

            mongoTemplate.insert(logInfo, app);
        } catch (Exception e) {
            log.error("日志存储mongo异常，ex: ", e);
            result = false;
        }

        return result;
    }

    @Override
    public List<LogInfo> findAll(String app) {
        return mongoTemplate.findAll(LogInfo.class, app);
    }

}
