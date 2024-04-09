package com.ds.nas.cloud.batch.mock;

import com.ds.nas.cloud.log.api.dubbo.LogProvider;
import com.ds.nas.cloud.log.api.io.request.LogRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2023/3/13
 * @description
 */
@Slf4j
@Component
public class LogMock {

    @DubboReference(version = "1.0")
    private LogProvider logProvider;

    @Scheduled(fixedDelay = 1000)
    public void regMock() {
        for (int i = 0; i < 10; i++) {
            LogRequest logRequest = LogRequest.builder()
                    .logJson("{\"idCard\":\"411103197002196435\",\"requestId\":\"" + i + "\",\"requestTime\":\"2023-03-21 16:10:12\"}")
                    .build();
            logProvider.save(logRequest);
        }

    }

}
