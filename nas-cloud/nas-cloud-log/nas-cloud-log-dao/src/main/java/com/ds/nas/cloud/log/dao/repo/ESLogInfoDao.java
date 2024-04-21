package com.ds.nas.cloud.log.dao.repo;

import com.ds.nas.cloud.log.dao.entity.ESLogInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ds
 * @date 2024/4/19
 * @description
 */
@Repository("esLogInfoDao")
public interface ESLogInfoDao extends ElasticsearchRepository<ESLogInfo, String> {
}
