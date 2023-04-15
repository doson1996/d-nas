package com.ds.nas.lib.common.base.response;

import cn.hutool.core.date.DateUtil;
import com.ds.nas.lib.common.base.request.BaseRequest;
import com.ds.nas.lib.common.util.DateUtils;

/**
 * @author ds
 * @description
 * @date 2023/2/6
 */
public class ResponseBuild {

    /**
     * 请求返回时
     *
     * @param request
     * @param response
     */
    public static void onReturn(BaseRequest request, BaseResponse response) {
        ResponsePrivate responsePrivate = new ResponsePrivate();
        responsePrivate.setResponseTime(DateUtils.now());
        responsePrivate.setResponseId("");
        responsePrivate.setRequestId(request.getRequestPrivate().getRequestId());
        response.setResponsePrivate(responsePrivate);

    }

}
