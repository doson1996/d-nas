package com.ds.nas.hc.api.dubbo;

import com.ds.nas.hc.dao.domain.HcRequestLog;

/**
 * @author ds
 * @date 2023/1/31
 * @description
 */
public interface HcRequestLogProvider {

    /**
     * 保存日志
     * @param log
     * @return
     */
    boolean save(HcRequestLog log);

}
