package com.ds.nas.nat.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ds.nas.nat.dao.domain.NatDetectionPersonalInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ds
 * @description 针对表【nat_detection_personal_info】的数据库操作Mapper
 * @createDate 2022-12-10 23:58:31
 * @Entity com.ds.nas.nat.dao.domain.NatDetectionPersonalInfo
 */
@Repository
public interface NatDetectionPersonalInfoMapper extends BaseMapper<NatDetectionPersonalInfo> {

    /**
     * 录入
     *
     * @param tableName
     * @param personalInfo
     * @return
     */
    int entry(String tableName, NatDetectionPersonalInfo personalInfo);

    /**
     * 根据idCard查询
     * @param tableName
     * @param idCard
     * @return
     */
    NatDetectionPersonalInfo selectByIdCard(String tableName, String idCard);

    /**
     * 根据批次号获取身份证号
     *
     * @param tableName
     * @param batchNo
     * @return
     */
    List<String> getIdCards(String tableName, String batchNo);

    /**
     * 根据批次号和身份证号查询
     *
     * @param batchNo
     * @param idCard
     * @return
     */
    int countByBatchNo(String tableName, String batchNo, String idCard);

}




