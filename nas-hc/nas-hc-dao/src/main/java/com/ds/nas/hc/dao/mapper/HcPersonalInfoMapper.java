package com.ds.nas.hc.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ds.nas.hc.dao.domain.HcPersonalInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author ds
 * @description 针对表【hc_personal_info】的数据库操作Mapper
 * @createDate 2022-12-04 13:28:57
 * @Entity com.ds.nas.hc.dao.domain.HcPersonalInfo
 */
@Repository
public interface HcPersonalInfoMapper extends BaseMapper<HcPersonalInfo> {

    /**
     * 根据身份证号更新
     * @param idCard
     * @return
     */
    @Deprecated
    int updateByIdCard(String idCard);

    /**
     * 根据身份证号查询
     * @param idCard
     * @return
     */
    HcPersonalInfo queryByIdCard(String idCard);

    /**
     * 根据批次号更新
     *
     * @param health
     * @param idCards
     * @param lastNucleicAcidTime
     * @return
     */
    int updateByIdCards(Integer health, List<String> idCards, Date lastNucleicAcidTime);
}




