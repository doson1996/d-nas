package com.ds.nas.hc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.hc.common.base.db.DBUtils;
import com.ds.nas.hc.common.result.Result;
import com.ds.nas.hc.dao.domain.HcPersonalInfo;
import com.ds.nas.hc.dao.mapper.HcPersonalInfoMapper;
import com.ds.nas.hc.dao.request.ApplyHealthCodeRequest;
import com.ds.nas.hc.dao.response.ApplyHealthCodeResponse;
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
    public Result<ApplyHealthCodeResponse> apply(ApplyHealthCodeRequest request) {
        HcPersonalInfo hcPersonalInfo = new HcPersonalInfo();
        BeanUtil.copyProperties(request, hcPersonalInfo);
        hcPersonalInfo = (HcPersonalInfo) DBUtils.getCurrentDBUtils().commonFieldAssignments(hcPersonalInfo);
        hcPersonalInfo.setHealth(1);
        boolean save = save(hcPersonalInfo);
        if (!save) {
            return Result.fail("申领健康码失败!");
        }
        return Result.ok("申领健康码成功!");
    }

    @Override
    public Result<ApplyHealthCodeResponse> queryByIdCard(String idCard) {
        LambdaQueryWrapper<HcPersonalInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(HcPersonalInfo::getIdCard, idCard);
        HcPersonalInfo hcPersonalInfo = hcPersonalInfoMapper.selectOne(wrapper);
        ApplyHealthCodeResponse response = new ApplyHealthCodeResponse();
        BeanUtil.copyProperties(hcPersonalInfo, response);

        return Result.ok("", response);
    }
}




