package com.ds.nas.hc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.cloud.api.message.sms.dubbo.SmsProvider;
import com.ds.nas.hc.api.io.request.HealthCodeQueryRequest;
import com.ds.nas.hc.api.io.request.PersonalInfoBatchUpdateRequest;
import com.ds.nas.hc.api.io.request.PersonalInfoRegisterRequest;
import com.ds.nas.hc.api.io.request.PersonalInfoUpdateRequest;
import com.ds.nas.hc.api.io.response.HealthCodeQueryResponse;
import com.ds.nas.hc.api.io.response.PersonalInfoBatchUpdateResponse;
import com.ds.nas.hc.api.io.response.PersonalInfoRegisterResponse;
import com.ds.nas.hc.api.io.response.PersonalInfoUpdateResponse;
import com.ds.nas.hc.dao.domain.HcPersonalInfo;
import com.ds.nas.hc.dao.mapper.HcPersonalInfoMapper;
import com.ds.nas.hc.service.HcPersonalInfoService;
import com.ds.nas.lib.cache.key.RedisHcKey;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.base.annotation.CheckParam;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.base.response.ResponseBuild;
import com.ds.nas.lib.common.constant.HealthCodeState;
import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.Result;
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

    @CheckParam
    @Override
    public Result<HealthCodeQueryResponse> queryByIdCard(HealthCodeQueryRequest request) {
        HealthCodeQueryResponse response = new HealthCodeQueryResponse();
        BeanUtil.copyProperties(qryHcPersonalInfo(request.getIdCard()), response);
        ResponseBuild.onReturn(request, response);
        return Result.ok("查询成功", response);
    }

    @CheckParam
    @Override
    public Result<PersonalInfoRegisterResponse> register(PersonalInfoRegisterRequest request) {
        // 重复注册校验
        String idCard = request.getIdCard();
        // 截取身份证前12位，地区 + 出生年月
        String key = getRegCheckCacheKey(idCard);
        // 先判断redis中是否存在
        if (redisUtil.sIsMember(key, idCard)) {
            throw new BusinessException("此身份证已注册!");
        }

        HcPersonalInfo hcPersonalInfo = hcPersonalInfoMapper.queryByIdCard(idCard);
        if (hcPersonalInfo != null) {
            // 添加到redis set中
            redisUtil.sAdd(key, idCard);
            throw new BusinessException("此身份证已注册!");
        }

        hcPersonalInfo = new HcPersonalInfo();
        BeanUtil.copyProperties(request, hcPersonalInfo);
        DBUtils.onCreate(hcPersonalInfo);
        hcPersonalInfo.setHealth(HealthCodeState.GREEN);

        if (!save(hcPersonalInfo)) {
            return Result.fail("注册失败!");
        }

        // 添加到redis set中
        redisUtil.sAdd(key, idCard);

        PersonalInfoRegisterResponse response = new PersonalInfoRegisterResponse();
        response.setHealth(HealthCodeState.GREEN);
        response.setName(hcPersonalInfo.getName());
        response.setPhone(hcPersonalInfo.getPhone());
        response.setIdCard(hcPersonalInfo.getIdCard());
        response.setAddress(hcPersonalInfo.getAddress());
        return Result.ok("注册成功!", response);
    }

    @CheckParam
    @Override
    public Result<PersonalInfoUpdateResponse> updateByIdCard(PersonalInfoUpdateRequest request) {
        HcPersonalInfo hcPersonalInfo = new HcPersonalInfo();
        BeanUtil.copyProperties(request, hcPersonalInfo);
        DBUtils.onUpdate(hcPersonalInfo);
        LambdaUpdateWrapper<HcPersonalInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(HcPersonalInfo::getIdCard, request.getIdCard());
        if (update(hcPersonalInfo, wrapper)) {
            deleteHealthInfoCache(Collections.singletonList(hcPersonalInfo.getIdCard()));
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
        String key = getHealthInfoCacheKey(idCard);
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
            deleteHealthInfoCache(request.getIdCards());
            return Result.ok("批量更新成功!");
        }
        return Result.fail("批量更新失败!");
    }

    /**
     * 删除健康码信息缓存
     *
     * @param idCards
     */
    private void deleteHealthInfoCache(List<String> idCards) {
        List<String> keys = new ArrayList<>();
        for (String idCard : idCards) {
            keys.add(getHealthInfoCacheKey(idCard));
        }
        //删除缓存
        redisUtil.delete(keys);
    }

    /**
     * 生成健康信息缓存key
     *
     * @param idCard
     * @return
     */
    private String getHealthInfoCacheKey(String idCard) {
        return RedisHcKey.HEALTH_CODE_KEY
                + idCard.substring(0, 6) + RedisHcKey.SEPARATOR
                + idCard.substring(6, 12) + RedisHcKey.SEPARATOR
                + idCard;
    }

    /**
     * 生成重复注册校验缓存key
     *
     * @param idCard
     * @return
     */
    private String getRegCheckCacheKey(String idCard) {
        return RedisHcKey.REGISTRATION_CHECK_KEY.concat(idCard.substring(0, 12));
    }

}




