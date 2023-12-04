package com.ds.nas.nat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.lib.common.base.annotation.CheckParam;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.io.request.NumCtrlCreateRequest;
import com.ds.nas.nat.dao.domain.NatNumCtrl;

import com.ds.nas.nat.dao.mapper.NatNumCtrlMapper;
import com.ds.nas.nat.service.NatNumCtrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ds
 * @description 针对表【nat_num_ctrl】的数据库操作Service实现
 * @createDate 2023-12-04 23:16:00
 */
@Slf4j
@Service
public class NatNumCtrlServiceImpl extends ServiceImpl<NatNumCtrlMapper, NatNumCtrl> implements NatNumCtrlService {

    @Resource
    private NatNumCtrlMapper numCtrlMapper;

    @CheckParam
    @Override
    public Result<StringResponse> create(NumCtrlCreateRequest request) {
        LambdaQueryWrapper<NatNumCtrl> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NatNumCtrl::getScenario, request.getScenario());
        NatNumCtrl natNumCtrl = numCtrlMapper.selectOne(queryWrapper);
        if (natNumCtrl != null) {
            throw new BusinessException("当前场景已添加一级控制表号段!");
        }
        natNumCtrl = BeanUtil.copyProperties(request, NatNumCtrl.class);
        natNumCtrl.setFirstStart(0);
        natNumCtrl.setFirstEnd(request.getStep());
        DBUtils.onCreate(natNumCtrl);
        int insert = numCtrlMapper.insert(natNumCtrl);
        if (insert == 0) {
            return Result.fail("添加一级控制表号段失败!");
        }
        return Result.ok("添加一级控制表号段成功!",
                StringResponse.builder().withData(request.getScenario()).build());
    }

}




