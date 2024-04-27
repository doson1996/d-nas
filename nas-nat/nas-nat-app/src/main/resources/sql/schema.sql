-- 创建数据库
CREATE DATABASE d_nas_nat CHARACTER SET utf8 COLLATE utf8_general_ci;

-- 号码生成控制一级表
CREATE TABLE `nat_num_ctrl`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `scenario`       varchar(255) NOT NULL COMMENT '场景',
    `first_start`    int(11) NOT NULL COMMENT '一级表号段起始',
    `first_end`      int(11) NOT NULL COMMENT '一级表号段结束',
    `max_num`        int(11) NOT NULL COMMENT '号段最大值',
    `step`           int(6) NOT NULL COMMENT '号段增长步长',
    `enable_cache`   tinyint(1) NOT NULL COMMENT '是否启用缓存 0.否 1.启用',
    `cache_quantity` int(5) DEFAULT NULL COMMENT '一次缓存生成数量',
    `description`    varchar(255) DEFAULT NULL COMMENT '描述',
    `delete_flag`    tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志: 0.未删除 1.已删除',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    `create_by`      varchar(50)  NOT NULL COMMENT '创建人',
    `update_by`      varchar(50)  NOT NULL COMMENT '更新人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_scenario` (`scenario`) USING BTREE COMMENT '场景唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 批次号段控制表
CREATE TABLE `nat_num_ctrl_batch`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `scenario`    varchar(50) NOT NULL COMMENT '场景',
    `area_code`   varchar(10) NOT NULL COMMENT '地区号，如 00200',
    `start`       int(11) NOT NULL COMMENT '号段起始',
    `end`         int(11) NOT NULL COMMENT '号段结束',
    `current`     int(11) NOT NULL COMMENT '当前号码',
    `delete_flag` tinyint(1) default 0 not null comment '逻辑删除标志: 0.未删除 1.已删除',
    `create_time` datetime null comment '创建时间',
    `update_time` datetime null comment '更新时间',
    `create_by`   varchar(50) not null comment '创建人',
    `update_by`   varchar(50) not null comment '更新人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_scenario_ areacode` (`scenario`, `area_code`) USING BTREE COMMENT '场景地区号唯一索引'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;