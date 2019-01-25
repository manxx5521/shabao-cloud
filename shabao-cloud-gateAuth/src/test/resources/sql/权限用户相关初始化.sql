

-- ----------------------------
-- Table structure for upms_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_permission`;
CREATE TABLE `upms_permission` (
  `permission_id` int(6) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_name` varchar(40) NOT NULL COMMENT '权限名称',
  `type` int(1) NOT NULL COMMENT '权限类型1-菜单，2-按钮',
  `permission_value` varchar(40) NOT NULL COMMENT '权限值，做标识使用',
  `parent_id` int(6) DEFAULT NULL COMMENT '父级',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态,1-正常，0-禁止',
  `order_no` int(8) DEFAULT '10000' COMMENT '排序',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of upms_permission
-- ----------------------------

-- ----------------------------
-- Table structure for upms_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_role`;
CREATE TABLE `upms_role` (
  `role_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_value` varchar(40) DEFAULT NULL COMMENT '角色使用标识',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `order_no` int(8) DEFAULT '100000' COMMENT '排序',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of upms_role
-- ----------------------------
INSERT INTO `upms_role` VALUES ('1000', '后台人员', 'admin', null, '100000', null);

-- ----------------------------
-- Table structure for upms_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `upms_role_permission`;
CREATE TABLE `upms_role_permission` (
  `role_id` int(4) NOT NULL COMMENT '角色id',
  `permission_id` int(6) NOT NULL COMMENT '权限id',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限关联表';

-- ----------------------------
-- Records of upms_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for upms_user
-- ----------------------------
DROP TABLE IF EXISTS `upms_user`;
CREATE TABLE `upms_user` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录用户名',
  `nickname` varchar(40) DEFAULT NULL COMMENT '昵称',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态1-正常',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `u_user_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of upms_user
-- ----------------------------
INSERT INTO `upms_user` VALUES ('10000', 'admin', 'admin', '$2a$10$cz1meUmrrkw6Sz1XeB22POAOoUZT23dXw.QfLuscJRIIIq6WdHSSq', '1', null, '2019-01-18 15:46:40', '2019-01-18 15:46:43');

-- ----------------------------
-- Table structure for upms_user_role
-- ----------------------------
DROP TABLE IF EXISTS `upms_user_role`;
CREATE TABLE `upms_user_role` (
  `user_id` int(8) NOT NULL COMMENT '用户id',
  `role_id` int(4) NOT NULL COMMENT '角色id',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='人员角色关联表';

-- ----------------------------
-- Records of upms_user_role
-- ----------------------------
INSERT INTO `upms_user_role` VALUES ('10000', '1000', '2019-01-23 16:50:44');
