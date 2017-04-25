
-- ----------------------------
-- Table structure for authorities
-- ----------------------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `id` varchar(50) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `authority` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authorities
-- ----------------------------
INSERT INTO `authorities` VALUES ('admin', 'admin', null);

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` varchar(50) NOT NULL COMMENT '主键uuid',
  `param` varchar(200) DEFAULT NULL COMMENT '参数名称',
  `value` varchar(200) DEFAULT NULL COMMENT '参数值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置';

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES ('systemName', 'systemName', 'SOURCE');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `parentId` varchar(50) NOT NULL DEFAULT '0' COMMENT '父菜单id',
  `parentName` varchar(45) DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL COMMENT '名称',
  `uri` varchar(200) DEFAULT NULL COMMENT '地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `leaf` varchar(45) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('menu', 'root', '根目录', '菜单管理', 'app.menu.MenuTreeGrid', null, null, null);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(50) NOT NULL COMMENT 'uuid',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `enabled` varchar(45) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('admin', 'admin', 'b594510740d2ac4261c1b2fe87850d08', 'true');
