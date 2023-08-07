package com.ds.nas.hc.service.impl;
import com.ds.nas.lib.common.base.request.RequestPrivate;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.cloud.api.message.sms.dubbo.SmsProvider;
import com.ds.nas.cloud.api.message.sms.io.request.SendCaptchaRequest;
import com.ds.nas.hc.dao.domain.HcPersonalInfo;
import com.ds.nas.hc.dao.mapper.HcPersonalInfoMapper;
import com.ds.nas.hc.api.io.request.HealthCodeQueryRequest;
import com.ds.nas.hc.api.io.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.api.io.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.api.io.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.api.io.response.HealthCodeQueryResponse;
import com.ds.nas.hc.api.io.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.hc.api.io.response.PersonalInfoRegisterResponse;
import com.ds.nas.hc.api.io.response.PersonalInfoUpdateResponse;
import com.ds.nas.hc.service.HcPersonalInfoService;
import com.ds.nas.lib.cache.key.RedisHcKey;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.base.request.RequestCheck;
import com.ds.nas.lib.common.constant.HealthCodeState;
import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.lib.common.base.response.ResponseBuild;
import com.ds.nas.lib.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    @DubboReference(version = "1.0")
    private SmsProvider smsProvider;


    @Override
    public Result<HealthCodeQueryResponse> queryByIdCard(HealthCodeQueryRequest request) {
        // 基本参数校验
        RequestCheck.check(request);
        HealthCodeQueryResponse response = new HealthCodeQueryResponse();
        BeanUtil.copyProperties(qryHcPersonalInfo(request.getIdCard()), response);
        ResponseBuild.onReturn(request, response);
        return Result.ok("查询成功", response);
    }

    @Override
    public Result<PersonalInfoRegisterResponse> register(PersonalInfoRegisterRequest request) {
        HcPersonalInfo hcPersonalInfo = new HcPersonalInfo();
        checkRegisterRequest(request);
        BeanUtil.copyProperties(request, hcPersonalInfo);
        DBUtils.onCreate(hcPersonalInfo);
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
        DBUtils.onUpdate(hcPersonalInfo);
        LambdaUpdateWrapper<HcPersonalInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(HcPersonalInfo::getIdCard, request.getIdCard());
        if (update(hcPersonalInfo, wrapper)) {
            deleteHcCache(Collections.singletonList(hcPersonalInfo.getIdCard()));
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
        String key = RedisHcKey.HEALTH_CODE_KEY + idCard;
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
        List<String> idCards = request.getIdCards();
        Integer health = request.getHealth();
        Date lastNucleicAcidTime = request.getLastNucleicAcidTime();
        int res = hcPersonalInfoMapper.updateByIdCards(health, idCards, lastNucleicAcidTime);
        if (res > 0) {
            deleteHcCache(request.getIdCards());
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

    /**
     * 删除hc缓存
     *
     * @param idCards
     */
    private void deleteHcCache(List<String> idCards) {
        List<String> keys = new ArrayList<>();
        for (String idCard : idCards) {
            keys.add(RedisHcKey.HEALTH_CODE_KEY + idCard);
        }
        //删除缓存
        redisUtil.delete(keys);
    }

}




