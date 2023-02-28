package com.ds.nas.lib.common.constant;

/**
 * @author ds
 * @date 2023/1/31
 * @description 消息队列主题
 */
public class MqTopic {

    /*--------------------- hc系统topic ---------------------*/

    /**
     * hc 请求日志
     */
    public static final String HC_REQUEST_LOG_TOPIC = "hc-request-log";


    /*--------------------- nat系统topic ---------------------*/

    /**
     * nat 请求日志
     */
    public static final String NAT_REQUEST_LOG_TOPIC = "nat-request-log";

    /*--------------------- cloud topic ---------------------*/

    /**
     * canal发送消息topic
     */
    public static final String CANAL_MYSQL_TOPIC = "mysql-canal-test";

}
