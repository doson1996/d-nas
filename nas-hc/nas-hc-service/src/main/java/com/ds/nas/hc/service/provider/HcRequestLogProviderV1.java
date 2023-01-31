package com.ds.nas.hc.service.provider;

import com.ds.nas.hc.api.dubbo.HcRequestLogProvider;
import com.ds.nas.hc.dao.domain.HcRequestLog;
import com.ds.nas.hc.service.HcRequestLogService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2023/1/31
 * @description
 */
@DubboService(version = "1.0")
public class HcRequestLogProviderV1 implements HcRequestLogProvider {

    @Resource
    private HcRequestLogService hcRequestLogService;

    @Override
    public boolean save(HcRequestLog log) {
        return hcRequestLogService.save(log);
    }

}
