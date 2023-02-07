package com.ds.nas.lib.common.util;

import cn.hutool.core.date.DateUtil;
import com.ds.nas.lib.common.base.request.BaseRequest;
import com.ds.nas.lib.common.base.response.BaseResponse;

/**
 * @author ds
 * @description
 * @date 2023/2/6
 */
public class ResponseUtils {

    /**
     * 请求返回时
     *
     * @param request
     * @param response
     */
    public static void onReturn(BaseRequest request, BaseResponse response) {
        response.setRequestId(request.getRequestId());
        response.setResponseTime(DateUtil.now());
    }

}
