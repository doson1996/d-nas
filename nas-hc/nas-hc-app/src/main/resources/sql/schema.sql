-- hc_personal_info
create table if not exists d_nas_hc.hc_personal_info
(
    id                     int(11) unsigned auto_increment comment 'id' primary key,
    name                   varchar(20)          not null comment '姓名',
    id_card                varchar(18)          not null comment '身份证号码',
    address                varchar(200)         null comment '住址',
    phone                  varchar(11)          not null comment '手机号',
    health                 tinyint(1) default 0 not null comment '0.未申领 1.绿码 2.黄码 3.红码 4.弹窗',
    last_nucleic_acid_time datetime             null comment '最后一次核酸时间',
    create_time            datetime             not null comment '创建时间',
    update_time            datetime             not null comment '更新时间',
    create_by              varchar(50)         not null comment '创建人',
    update_by              varchar(50)         not null comment '更新人',
    delete_flag            tinyint(1) default 0 not null comment '逻辑删除标志 0.未删除 1.已删除',
    constraint uk_id_card unique (id_card)
);

-- hc_request_log
create table if not exists d_nas_hc.hc_request_log
(
    id                     int(11) unsigned auto_increment comment 'id' primary key,
    path                   varchar(150)         not null comment '请求路径',
    request_ip             varchar(15)         not null comment '请求ip',
    response_ip            varchar(15)         null comment '响应ip',
    request_data           varchar(1000)         not null comment '请求参数',
    response_data          varchar(1000)         null comment '响应参数',
    return_code            int(3)              null comment '响应参数',
    execution_time         int(11) unsigned     null comment '执行时间(ms)',
    create_time            datetime             not null comment '创建时间',
    update_time            datetime             not null comment '更新时间',
    create_by              varchar(50)         not null comment '创建人',
    update_by              varchar(50)         not null comment '更新人',
    delete_flag            tinyint(1) default 0 not null comment '逻辑删除标志 0.未删除 1.已删除'
);

-- hc_system_log
create table if not exists d_nas_hc.hc_system_log
(
    id                     int(11) auto_increment comment 'id' primary key,
    path                   varchar(150)         not null comment '请求路径',
    request_ip             varchar(15)         not null comment '请求ip',
    request_data           varchar(500)         not null comment '请求参数',
    response_ip            varchar(15)         null comment '响应ip',
    response_data          varchar(500)         null comment '响应参数',
    return_code            int(3)         null comment '响应参数',
    execution_time         int(11)              null comment '执行时间(ms)',
    create_time            datetime             not null comment '创建时间',
    update_time            datetime             not null comment '更新时间',
    create_by              varchar(50)         not null comment '创建人',
    update_by              varchar(50)         not null comment '更新人',
    delete_flag            tinyint(1) default 0 not null comment '逻辑删除标志 0.未删除 1.已删除'
);
