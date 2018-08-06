CREATE TABLE pe_user (
  id int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  username varchar(100) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
	show_name VARCHAR(100) CHARACTER SET utf8mb4 DEFAULT null COMMENT '显示名',
	password VARCHAR(200) not null comment '密码',
  phone varchar(19) DEFAULT NULL COMMENT '手机号',
  sex tinyint(2) unsigned DEFAULT NULL COMMENT '性别',
  avatar varchar(255) DEFAULT '' COMMENT '头像',
  email varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '电子邮箱',
  create_time datetime NOT NULL COMMENT '创建时间',
  update_time timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  is_delete tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;