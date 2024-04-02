package com.ds.nas.cloud.log.dao;

import com.ds.nas.cloud.log.io.request.LogRequest;

/**
 * @author ds
 * @date 2024/3/25 23:50
 */
public interface LogDao {

    String insertOne(LogRequest request);

}
