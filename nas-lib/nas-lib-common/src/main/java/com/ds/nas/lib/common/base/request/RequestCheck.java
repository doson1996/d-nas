package com.ds.nas.lib.common.base.request;

import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.ResultMsg;

/**
 * @author ds
 * @date 2023/1/22
 * @description 入参校验
 */
public class RequestCheck {

    public static void check(Class<? extends BaseRequest> clazz, BaseRequest request) {
        if (clazz == null && request == null)
            throw new BusinessException(ResultMsg.PARAMETER_ERROR_MSG);

    }

}
