package com.ds.nas.nat.dao.mapper;

import com.ds.nas.nat.dao.domain.NatDetectionBatchInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author ds
* @description 针对表【nat_detection_batch_info】的数据库操作Mapper
* @createDate 2022-12-10 23:55:13
* @Entity com.ds.nas.nat.dao.domain.NatDetectionBatchInfo
*/
@Repository
public interface NatDetectionBatchInfoMapper extends BaseMapper<NatDetectionBatchInfo> {

    /**
     * 创建
     *
     * @param detectionBatchInfo
     * @param tableName
     * @return
     */
    int create(NatDetectionBatchInfo detectionBatchInfo, String tableName);

}




