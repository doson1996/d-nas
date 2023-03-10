package com.ds.nas.cloud.canal.client;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.ds.nas.lib.common.constant.MqTopic;
import com.ds.nas.lib.mq.producer.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author ds
 * @date 2023/2/28
 * @description
 */
@Slf4j
@Component
public class Client implements ApplicationRunner {

    @Resource
    @Qualifier("kafka")
    private Producer producer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Thread thread = new Thread(() -> {
            // 创建链接
            CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("dy.com",
                    11111), "example", "canal", "canal");
            int batchSize = 1000;
            int emptyCount = 0;
            try {
                connector.connect();
                connector.subscribe(".*\\..*");
                connector.rollback();
                log.info("开始监听...");
                while (true) {
                    Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                        emptyCount++;
                        log.info("第{}次读取数据为空...", emptyCount);
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            Thread.interrupted();
                        }
                    } else {
                        emptyCount = 0;
                        printEntry(message.getEntries());
                    }
                    connector.ack(batchId); // 提交确认
                    // connector.rollback(batchId); // 处理失败, 回滚数据
                }
            } finally {
                connector.disconnect();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            CanalEntry.RowChange rowChage = null;
            try {
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChage.getEventType();
            log.info(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == CanalEntry.EventType.DELETE) {
                    //删除
                    sendMsg(entry.getHeader().getSchemaName(),
                            entry.getHeader().getTableName(),
                            eventType,
                            rowData.getBeforeColumnsList());
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    //新增
                    sendMsg(entry.getHeader().getSchemaName(),
                            entry.getHeader().getTableName(),
                            eventType,
                            rowData.getAfterColumnsList());
                } else {
                    //更新
                    sendMsg(entry.getHeader().getSchemaName(),
                            entry.getHeader().getTableName(),
                            eventType,
                            rowData.getAfterColumnsList());
                    log.info("-------更新之前数据-------");
                    printColumn(rowData.getBeforeColumnsList());
                    log.info("-------更新之后数据-------");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
    }

    /**
     * 打印一行数据
     *
     * @param columns
     */
    private void printColumn(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            log.info(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }

    /**
     * 发送kafka消息
     *
     * @param db      数据库名
     * @param table   表名
     * @param columns 数据
     */
    private void sendMsg(String db, String table, CanalEntry.EventType eventType, List<CanalEntry.Column> columns) {
        JSONObject data = new JSONObject();
        for (CanalEntry.Column column : columns) {
            data.put(column.getName(), column.getValue());
        }
        JSONObject msg = new JSONObject();
        msg.put("db", db);
        msg.put("table", table);
        msg.put("type", eventType);
        msg.put("data", data);
        producer.send(MqTopic.CANAL_MYSQL_TOPIC, msg.toJSONString());
    }

}
