/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : zgcenv

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 03/04/2020 14:28:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_columns
-- ----------------------------
DROP TABLE IF EXISTS `tb_columns`;
CREATE TABLE `tb_columns`  (
  `id` bigint(20) NOT NULL,
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父类ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '栏目标题',
  `description` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '栏目描述',
  `sorting` int(11) NOT NULL COMMENT '栏目排序',
  `insert_time` datetime(0) NOT NULL COMMENT '创建日期',
  `update_time` datetime(0) NOT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_columns
-- ----------------------------
INSERT INTO `tb_columns` VALUES (1244425021292544000, 0, '栏目1', '栏目1', 0, '2020-03-30 00:43:40', '2020-03-30 00:43:40');
INSERT INTO `tb_columns` VALUES (1245901631854215168, 1244425021292544000, 'dddddd', 'dddd', 4, '2020-04-03 02:31:11', '2020-04-03 02:32:31');

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news`  (
  `id` bigint(20) NOT NULL,
  `column_id` bigint(20) NOT NULL COMMENT '栏目id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `head_pic` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头图',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `context` varchar(8000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `sorting` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `look_sum` int(11) NOT NULL DEFAULT 0 COMMENT '查看数',
  `follow_sum` int(11) NOT NULL DEFAULT 0 COMMENT '关注数',
  `insert_time` datetime(0) NOT NULL COMMENT '创建日期',
  `update_time` datetime(0) NOT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_news
-- ----------------------------
INSERT INTO `tb_news` VALUES (1245920083394428928, 1245901631854215168, 'ccccc', 'cccc', 'cccc', 'cccc', 3, 3, 3, '2020-04-03 04:18:36', '2020-04-03 04:18:36');

-- ----------------------------
-- Table structure for tu_users
-- ----------------------------
DROP TABLE IF EXISTS `tu_users`;
CREATE TABLE `tu_users`  (
  `id` bigint(20) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `insert_time` datetime(0) NOT NULL COMMENT '创建日期',
  `update_time` datetime(0) NOT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tu_users
-- ----------------------------
INSERT INTO `tu_users` VALUES (1244292742176047105, 'zhangsan', '$2a$10$/CGzEJC/sNkBG7e8Rbzz3eHN2tggDB9Pi2mXwJll99LKcYfp//nw2', '18899992222', '999@163.com', '2020-03-30 00:40:47', '2020-03-30 00:40:47');

SET FOREIGN_KEY_CHECKS = 1;
