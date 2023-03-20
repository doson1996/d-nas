-- 查看binlog 是否打开
show variables like 'log_%';
--  查看binlog 大小
show binary logs;
--  查看binlog 过期时间
show variables like '%expire%';
-- 将指定时间之前的binlog清掉
PURGE MASTER LOGS BEFORE DATE_SUB(CURRENT_DATE, INTERVAL 1 DAY);