package com.ds.nas.lib.common.base.response;

import com.ds.nas.lib.common.base.request.BaseRequest;
import com.ds.nas.lib.common.util.DateUtils;
import com.ds.nas.lib.common.util.UUID;
import lombok.SneakyThrows;

import java.net.InetAddress;

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
    @SneakyThrows
    public static void onReturn(BaseRequest request, BaseResponse response) {
        ResponsePrivate responsePrivate = new ResponsePrivate();
        responsePrivate.setResponseApp("nas");
        responsePrivate.setResponseIp(InetAddress.getLocalHost().getHostAddress());
        responsePrivate.setResponseTime(DateUtils.now());
        responsePrivate.setResponseId(UUID.fastUUID().toString(true));
        responsePrivate.setRequestId(request.getRequestPrivate().getRequestId());
        response.setResponsePrivate(responsePrivate);
    }

}
