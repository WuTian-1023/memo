/*
 Navicat Premium Data Transfer

 Source Server         : 本机mysql8
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : memodb

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 23/10/2024 18:23:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_borrow_record
-- ----------------------------
DROP TABLE IF EXISTS `t_borrow_record`;
CREATE TABLE `t_borrow_record`  (
  `archives_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '档案名',
  `files_borrow_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '档案外借人员姓名',
  `files_borrow_time` datetime NULL DEFAULT NULL COMMENT '外借时间',
  `return_time` datetime NULL DEFAULT NULL COMMENT '归还时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态：00-在库，01-外接',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'uuid'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Triggers structure for table t_borrow_record
-- ----------------------------
DROP TRIGGER IF EXISTS `before_insert_t_borrow_record`;
delimiter ;;
CREATE TRIGGER `before_insert_t_borrow_record` BEFORE INSERT ON `t_borrow_record` FOR EACH ROW BEGIN
    IF NEW.uuid IS NULL THEN
        SET NEW.uuid = UUID();
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
