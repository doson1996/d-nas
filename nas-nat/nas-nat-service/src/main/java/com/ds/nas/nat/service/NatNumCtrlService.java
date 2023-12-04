package com.ds.nas.nat.service;

import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.io.request.NumCtrlCreateRequest;
import com.ds.nas.nat.dao.domain.NatNumCtrl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author ds
* @description 针对表【nat_num_ctrl】的数据库操作Service
* @createDate 2023-12-04 23:16:00
*/
public interface NatNumCtrlService extends IService<NatNumCtrl> {

    /**
     * 新增一级控制表场景号段
     * @param request
     * @return
     */
    Result<StringResponse> create(NumCtrlCreateRequest request);

}
