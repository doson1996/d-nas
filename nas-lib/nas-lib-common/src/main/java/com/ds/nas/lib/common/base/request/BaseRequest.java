package com.ds.nas.lib.common.base.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ds
 * @date 2022/12/5
 * @description 请求入参基类
 */
@Data
public class BaseRequest implements Serializable {

    private static final long serialVersionUID = 737016089980834810L;

    /**
     * request私有通讯区
     */
    RequestPrivate requestPrivate;

    public RequestPrivate getRequestPrivate() {
        // 避免空指针 {@see ResponseUtils.onReturn}
        if (requestPrivate == null)
            requestPrivate = new RequestPrivate();
        return requestPrivate;
    }

}
