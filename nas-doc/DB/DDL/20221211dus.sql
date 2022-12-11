create table if not exists d_nas_hc.hc_personal_info
(
    id                     bigint(11) auto_increment comment 'id' primary key,
    name                   varchar(20)          not null comment '姓名',
    id_card                varchar(18)          not null comment '身份证号码',
    address                varchar(255)         null comment '住址',
    phone                  varchar(11)          not null comment '手机号',
    health                 tinyint(1) default 0 not null comment '0.未申领 1.绿码 2.黄码 3.红码 4.弹窗',
    last_nucleic_acid_time datetime             null comment '最后一次核酸时间',
    create_time            datetime             not null comment '创建时间',
    update_time            datetime             not null comment '更新时间',
    cretae_by              varchar(255)         not null comment '创建人',
    update_by              varchar(255)         not null comment '更新人',
    delete_flag            tinyint(1) default 0 not null comment '逻辑删除标志 0.未删除 1.已删除'
);

create table if not exists d_nas_nat.nat_detection_batch_info
(
    id                  bigint(11) auto_increment comment 'id' primary key,
    batch_no            varchar(20)          not null comment '批次号',
    type                int(1)               not null comment '检测类型: 0.混检 1.单检',
    entry_time          datetime             not null comment '录入时间',
    entry_mechanism     varchar(200)         not null comment '检测机构',
    detection_time      datetime             null comment '检测时间',
    detection_result    int(1)               not null comment '检测结果: 0.未检测 1.正常 2.异常',
    detection_mechanism varchar(200)         not null comment '检测机构',
    delete_flag         tinyint(1) default 0 not null comment '逻辑删除标志: 0.未删除 1.已删除'
);

create table if not exists d_nas_nat.nat_detection_personal_info
(
    id          bigint(11) auto_increment comment 'id' primary key,
    batch_no    varchar(20)          not null comment '批次号',
    id_card     varchar(18)          not null comment '身份证号码',
    delete_flag tinyint(1) default 0 not null comment '逻辑删除标志: 0.未删除 1.已删除'
);