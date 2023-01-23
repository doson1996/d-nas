package com.ds.nas.nat.dao.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.ds.nas.lib.common.base.domain.BaseDomain;
import lombok.Data;

/**
 * 
 * @author ds
 * @TableName nat_detection_batch_info
 */
@TableName(value ="nat_detection_batch_info")
@Data
public class NatDetectionBatchInfo extends BaseDomain {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 检测类型: 0.混检 1.单检
     */
    private Integer type;

    /**
     * 录入时间
     */
    private Date entryTime;

    /**
     * 录入机构
     */
    private String entryMechanism;

    /**
     * 检测时间
     */
    private Date detectionTime;

    /**
     * 检测结果: 0.未检测 1.正常 2.异常
     */
    private Integer detectionResult;

    /**
     * 检测机构
     */
    private String detectionMechanism;

}