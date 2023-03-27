# 创建 nat_dml用户
CREATE USER 'nat_dml'@'%' IDENTIFIED BY '123456';
# 授权dml 权限
GRANT SELECT,INSERT,UPDATE,DELETE ON d_nas_nat.* TO 'nat_dml'@'%';
