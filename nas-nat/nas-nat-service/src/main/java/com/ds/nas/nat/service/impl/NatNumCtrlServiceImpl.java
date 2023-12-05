package com.ds.nas.nat.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ds.nas.lib.cache.redis.RedisUtil;
import com.ds.nas.lib.common.base.annotation.CheckParam;
import com.ds.nas.lib.common.base.db.DBUtils;
import com.ds.nas.lib.common.base.response.StringResponse;
import com.ds.nas.lib.common.exception.BusinessException;
import com.ds.nas.lib.common.result.Result;
import com.ds.nas.nat.api.io.request.GetNoRequest;
import com.ds.nas.nat.api.io.request.NumCtrlCreateRequest;
import com.ds.nas.nat.dao.domain.NatNumCtrl;

import com.ds.nas.nat.dao.domain.NatNumCtrlBatch;
import com.ds.nas.nat.dao.mapper.NatNumCtrlBatchMapper;
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

    @Resource
    private NatNumCtrlBatchMapper numCtrlBatchMapper;

    @Resource
    private RedisUtil redisUtil;

    @CheckParam
    @Override
    public Result<StringResponse> create(NumCtrlCreateRequest request) {
        NatNumCtrl natNumCtrl = getNatNumCtrl(request.getScenario());
        if (natNumCtrl != null) {
            throw new BusinessException("当前场景已添加一级控制表号段!");
        }
        natNumCtrl = BeanUtil.copyProperties(request, NatNumCtrl.class);
        natNumCtrl.setFirstStart(0);
        natNumCtrl.setFirstEnd(request.getStep() - 1);
        DBUtils.onCreate(natNumCtrl);
        int insert = numCtrlMapper.insert(natNumCtrl);
        if (insert == 0) {
            return Result.fail("添加一级控制表号段失败!");
        }
        return Result.ok("添加一级控制表号段成功!",
                StringResponse.builder().withData(request.getScenario()).build());
    }

    @Override
    public Result<StringResponse> getNo(GetNoRequest request) {
        Integer no = -1;
        NatNumCtrl natNumCtrl = getNatNumCtrl(request.getScenario());
        // 缓存开关 (实际开关表)
        Integer enableCache = natNumCtrl.getEnableCache();

        NatNumCtrlBatch natNumCtrlBatch = getNatNumCtrlBatch(request.getScenario(), request.getAreaCode());
        // 如果为空，二级表该地区没数据，新增
        if (natNumCtrlBatch == null) {
            natNumCtrlBatch = new NatNumCtrlBatch();
            natNumCtrlBatch.setScenario(request.getScenario());
            natNumCtrlBatch.setAreaCode(request.getAreaCode());
            natNumCtrlBatch.setStart(natNumCtrl.getFirstStart());
            natNumCtrlBatch.setEnd(natNumCtrl.getFirstEnd());
            natNumCtrlBatch.setCurrent(natNumCtrl.getFirstStart() + 1);
            DBUtils.onCreate(natNumCtrlBatch);
            numCtrlBatchMapper.insert(natNumCtrlBatch);
            // 第一次获取号码，返回起始号码
            no = natNumCtrl.getFirstStart();

            // 更新一级控制表
            natNumCtrl.setFirstStart(natNumCtrl.getFirstEnd() + 1);
            natNumCtrl.setFirstEnd(natNumCtrl.getFirstEnd() + natNumCtrl.getStep());
            DBUtils.onUpdate(natNumCtrl);
            numCtrlMapper.updateById(natNumCtrl);
        } else {
            if (enableCache == 1) {

            } else {
                no = natNumCtrlBatch.getCurrent();
                natNumCtrlBatch.setCurrent(no + 1);
                DBUtils.onUpdate(natNumCtrlBatch);
                numCtrlBatchMapper.updateById(natNumCtrlBatch);
            }
        }

        return Result.ok("获取号码成功",
                StringResponse.builder().withData(String.valueOf(no)).build());
    }

    /**
     * 根据场景获取一级表信息
     *
     * @param scenario
     * @return
     */
    private NatNumCtrl getNatNumCtrl(String scenario) {
        LambdaQueryWrapper<NatNumCtrl> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NatNumCtrl::getScenario, scenario);
        return numCtrlMapper.selectOne(queryWrapper);
    }

    /**
     * 根据场景获取二级表信息
     *
     * @param scenario
     * @return
     */
    private NatNumCtrlBatch getNatNumCtrlBatch(String scenario, String areaCode) {
        LambdaQueryWrapper<NatNumCtrlBatch> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NatNumCtrlBatch::getScenario, scenario)
                .eq(NatNumCtrlBatch::getAreaCode, areaCode);
        return numCtrlBatchMapper.selectOne(queryWrapper);
    }

}




