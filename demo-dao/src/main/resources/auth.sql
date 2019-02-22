CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) DEFAULT NULL COMMENT '登录名称',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '密码时间戳',
  `deleted` int(11) DEFAULT '1' COMMENT '是否删除:1.正常, 2.删除',
  `disabled` int(11) DEFAULT '1' COMMENT '是否禁用:1.正常, 2.禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT '用户信息表';


CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `deleted` int(11) DEFAULT '1' COMMENT '是否删除:1.正常, 2.删除',
  `disabled` int(11) DEFAULT '1' COMMENT '是否禁用:1.正常, 2.禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT '用户角色关系表';

CREATE TABLE `role_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `deleted` int(11) DEFAULT '1' COMMENT '是否删除:1.正常, 2.删除',
  `disabled` int(11) DEFAULT '1' COMMENT '是否禁用:1.正常, 2.禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT '角色信息表';

CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `deleted` int(11) DEFAULT '1' COMMENT '是否删除:1.正常, 2.删除',
  `disabled` int(11) DEFAULT '1' COMMENT '是否禁用:1.正常, 2.禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT '角色菜单关系表';

CREATE TABLE `menu_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(20) DEFAULT NULL COMMENT '菜单编码',
  `menu_path` varchar(100) DEFAULT NULL COMMENT '菜单访问路径',
  `menu_type` varchar(100) DEFAULT NULL COMMENT '菜单类型: 1.菜单, 2.按钮',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序序号',
  `level` int(11) DEFAULT NULL COMMENT '菜单层级',
  `deleted` int(11) DEFAULT '1' COMMENT '是否删除:1.正常, 2.删除',
  `disabled` int(11) DEFAULT '1' COMMENT '是否禁用:1.正常, 2.禁用',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT '菜单信息表';
