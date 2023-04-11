package com.ds.nas.cloud.message.service;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;

import java.util.Map;
import java.util.Objects;

/**
 * @author ds
 * @date 2023/4/6
 * @description
 */
public interface SmsService {

    /**
     * 发送短信
     * @param params
     * @return
     */
    Result<StringResponse> send(Map<String, Objects> params);

}
