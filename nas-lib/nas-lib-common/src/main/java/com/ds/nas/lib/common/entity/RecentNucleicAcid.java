package com.ds.nas.lib.common.entity;

import lombok.Data;

import java.util.Date;

@Data
public class RecentNucleicAcid {

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
