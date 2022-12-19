package com.ds.nas.hc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.hc.common.base.db.DBUtils;
import com.ds.nas.hc.common.constant.HealthCodeState;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.domain.HcPersonalInfo;
import com.ds.nas.hc.dao.mapper.HcPersonalInfoMapper;
import com.ds.nas.hc.dao.request.HealthCodeApplyRequest;
import com.ds.nas.hc.dao.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.dao.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.dao.response.HealthCodeQueryResponse;
import com.ds.nas.hc.dao.response.PersonalInfoRegisterResponse;
import com.ds.nas.hc.dao.response.PersonalInfoUpdateResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ds
 * @description 针对表【hc_personal_info】的数据库操作Service实现
 * @createDate 2022-12-04 13:28:57
 */
@Service
public class HcPersonalInfoServiceImpl extends ServiceImpl<HcPersonalInfoMapper, HcPersonalInfo>
        implements HcPersonalInfoService {

    @Resource
    private HcPersonalInfoMapper hcPersonalInfoMapper;

    @Override
    public Result<String> apply(HealthCodeApplyRequest request) {
        if (hcPersonalInfoMapper.updateByIdCard(request.getIdCard()) == 1) {
            return Result.ok("申领健康码成功!");
        }

        return Result.fail("申领健康码失败!");
    }

    @Override
    public Result<HealthCodeQueryResponse> queryByIdCard(String idCard) {
        HealthCodeQueryResponse response = new HealthCodeQueryResponse();
        BeanUtil.copyProperties(hcPersonalInfoMapper.queryByIdCard(idCard), response);

        return Result.ok("查询成功", response);
    }

    @Override
    public Result<PersonalInfoRegisterResponse> register(PersonalInfoRegisterRequest request) {
        HcPersonalInfo hcPersonalInfo = new HcPersonalInfo();
        BeanUtil.copyProperties(request, hcPersonalInfo);
        hcPersonalInfo = (HcPersonalInfo) DBUtils.getCurrentDBUtils().commonFieldAssignments(hcPersonalInfo);
        hcPersonalInfo.setHealth(HealthCodeState.GREEN);

        if (!save(hcPersonalInfo)) {
            return Result.fail("注册失败!");
        }
        PersonalInfoRegisterResponse response = new PersonalInfoRegisterResponse();
        response.setHealth(HealthCodeState.GREEN);
        response.setName(hcPersonalInfo.getName());
        response.setPhone(hcPersonalInfo.getPhone());
        response.setIdCard(hcPersonalInfo.getIdCard());
        response.setAddress(hcPersonalInfo.getAddress());
        return Result.ok("注册成功!", response);
    }

    @Override
    public Result<PersonalInfoUpdateResponse> updateByIdCard(PersonalInfoUpdateRequest request) {
        HcPersonalInfo hcPersonalInfo = new HcPersonalInfo();
        BeanUtil.copyProperties(request, hcPersonalInfo);

        LambdaUpdateWrapper<HcPersonalInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(HcPersonalInfo::getIdCard, request.getIdCard());
        if (update(hcPersonalInfo, wrapper)) {
            PersonalInfoUpdateResponse response = new PersonalInfoUpdateResponse();
            BeanUtil.copyProperties(hcPersonalInfoMapper.queryByIdCard(hcPersonalInfo.getIdCard()), response);
            return Result.ok("更新成功!", response);
        }
        return Result.fail("更新失败!");
    }

}




