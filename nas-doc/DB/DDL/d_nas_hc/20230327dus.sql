# 创建 hc_dml用户
CREATE USER 'hc_dml'@'%' IDENTIFIED BY '123456';
# 授权dml 权限
GRANT SELECT,INSERT,UPDATE,DELETE ON d_nas_hc.* TO 'hc_dml'@'%';
