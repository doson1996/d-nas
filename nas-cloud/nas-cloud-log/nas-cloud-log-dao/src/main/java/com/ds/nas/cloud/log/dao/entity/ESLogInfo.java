package com.ds.nas.cloud.log.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author ds
 * @date 2024/4/19
 * @description
 */
@Data
@Document(indexName = "nas-cloud-log",createIndex = true)
public class ESLogInfo {
    @Id
    @Field(type = FieldType.Text)
    private String id;

//    @Field(analyzer="ik_max_word")
//    private String title;
//
//    @Field(analyzer="ik_max_word")
//    private String author;

//    @Field(type = FieldType.Double)
//    private Double price;

    @Field(type = FieldType.Text)
    private String app;

    @Field(type = FieldType.Text)
    private String log;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date createTime;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date updateTime;

}
