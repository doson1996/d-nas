package com.ds.nas.nat.service;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.dao.domain.TableInfo;

import java.util.Map;

/**
 * @author ds
 * @date 2022/12/9
 * @description
 */
public interface PersistenceService {

    /**
     * 创建表
     *
     * @param tableInfo
     * @return
     */
    Result<StringResponse> createTable(TableInfo tableInfo);

    /**
     * 根据日期创建表 detection_personal_info_yyyyMMdd
     *
     * @return
     */
    Result<StringResponse> createTableDpi();

    /**
     * 创建detection_personal_info当前日期后多少天的表 detection_personal_info_yyyyMMdd
     * @param days
     * @return
     */
    Result<StringResponse> batchCreateTableDpi(int days);

}
