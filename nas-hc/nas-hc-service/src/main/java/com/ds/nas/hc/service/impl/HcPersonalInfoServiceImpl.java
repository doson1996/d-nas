package com.ds.nas.hc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.hc.common.constant.CacheKey;
import com.ds.nas.hc.dao.domain.HcPersonalInfo;
import com.ds.nas.hc.dao.mapper.HcPersonalInfoMapper;
import com.ds.nas.hc.dao.request.HealthCodeQueryRequest;
import com.ds.nas.hc.dao.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.dao.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.dao.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.dao.response.HealthCodeQueryResponse;
import com.ds.nas.hc.dao.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.hc.dao.response.PersonalInfoRegisterResponse;
import com.ds.nas.hc.dao.response.PersonalInfoUpdateResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.constant.HealthCodeState;
import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.util.ResponseUtils;
import com.ds.nas.lib.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ds
 * @description 针对表【hc_personal_info】的数据库操作Service实现
 * @createDate 2022-12-04 13:28:57
 */
@Slf4j
@Service
public class HcPersonalInfoServiceImpl extends ServiceImpl<HcPersonalInfoMapper, HcPersonalInfo>
        implements HcPersonalInfoService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private HcPersonalInfoMapper hcPersonalInfoMapper;


    @Override
    public Result<HealthCodeQueryResponse> queryByIdCard(HealthCodeQueryRequest request) {
        if (StringUtils.isBlank(request.getIdCard())) {
            throw new BusinessException("身份证号不能为空!");
        }
        HealthCodeQueryResponse response = new HealthCodeQueryResponse();
        BeanUtil.copyProperties(qryHcPersonalInfo(request.getIdCard()), response);
        ResponseUtils.onReturn(request, response);
        return Result.ok("查询成功", response);
    }

    @Override
    public Result<PersonalInfoRegisterResponse> register(PersonalInfoRegisterRequest request) {
        HcPersonalInfo hcPersonalInfo = new HcPersonalInfo();
        checkRegisterRequest(request);
        BeanUtil.copyProperties(request, hcPersonalInfo);
        hcPersonalInfo = (HcPersonalInfo) DBUtils.getCurrentDBUtils().onCreate(hcPersonalInfo);
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
        hcPersonalInfo = (HcPersonalInfo) DBUtils.getCurrentDBUtils().onUpdate(hcPersonalInfo);
        LambdaUpdateWrapper<HcPersonalInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(HcPersonalInfo::getIdCard, request.getIdCard());
        if (update(hcPersonalInfo, wrapper)) {
            PersonalInfoUpdateResponse response = new PersonalInfoUpdateResponse();
            BeanUtil.copyProperties(hcPersonalInfoMapper.queryByIdCard(hcPersonalInfo.getIdCard()), response);
            return Result.ok("更新成功!", response);
        }
        return Result.fail("更新失败!");
    }

    /**
     * 根据身份证号查询健康信息
     *
     * @param idCard
     * @return
     */
    private HcPersonalInfo qryHcPersonalInfo(String idCard) {
        if (!IdcardUtil.isValidCard(idCard)) {
            throw new BusinessException("身份证号码无效!");
        }
        String key = CacheKey.HEALTH_CODE_KEY + idCard;
        HcPersonalInfo hcPersonalInfo;
        String hcPersonalInfoJson = redisUtil.get(key);
        if (StringUtils.isBlank(hcPersonalInfoJson)) {
            hcPersonalInfo = hcPersonalInfoMapper.queryByIdCard(idCard);
            redisUtil.set(key, JSON.toJSONString(hcPersonalInfo), 2 * 60 * 60);
        } else {
            hcPersonalInfo = JSON.parseObject(hcPersonalInfoJson, HcPersonalInfo.class);
        }
        return hcPersonalInfo;
    }

    @Override
    public Result<PersonalInfoBatchUpdateResponse> updateByIdCards(PersonalInfoBatchUpdateRequest request) {
        if (request.getIdCards().isEmpty()) {
            return Result.fail("未执行批量更新!");
        }
        int res = hcPersonalInfoMapper.updateByIdCards(request);
        if (res > 0) {
            return Result.ok("批量更新成功!");
        }
        return Result.fail("批量更新失败!");
    }

    /**
     * 注册参数校验
     *
     * @param request
     */
    private void checkRegisterRequest(PersonalInfoRegisterRequest request) {
        String idCard = request.getIdCard();
        if (StringUtils.isBlank(idCard, request.getName(), request.getPhone())) {
            throw new BusinessException("身份证号、姓名、手机不能为空!");
        }
        HcPersonalInfo hcPersonalInfo = hcPersonalInfoMapper.queryByIdCard(idCard);
        if (hcPersonalInfo != null) {
            throw new BusinessException("此身份证已注册!");
        }
    }

}




