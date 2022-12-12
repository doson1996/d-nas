package com.ds.nas.nat.dao.mapper;

import com.ds.nas.nat.dao.domain.NatDetectionPersonalInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author ds
* @description 针对表【nat_detection_personal_info】的数据库操作Mapper
* @createDate 2022-12-10 23:58:31
* @Entity com.ds.nas.nat.dao.domain.NatDetectionPersonalInfo
*/
public interface NatDetectionPersonalInfoMapper extends BaseMapper<NatDetectionPersonalInfo> {

    /**
     * 录入
     * @param tableName
     * @param personalInfo
     * @return
     */
    int detection(String tableName, NatDetectionPersonalInfo personalInfo);

}




