/*
 Navicat Premium Data Transfer

 Source Server         : 郑如缘本地Mysql
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : 192.168.128.61:3306
 Source Schema         : shucangdh

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 27/07/2022 16:40:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blockchain
-- ----------------------------
DROP TABLE IF EXISTS `t_blockchain`;
CREATE TABLE `t_blockchain`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blockchain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `background_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blockchain
-- ----------------------------
INSERT INTO `t_blockchain` VALUES (1, '', '', '', '#FFFFFF', '2022-07-27 11:15:08', '2022-07-27 14:02:55');
INSERT INTO `t_blockchain` VALUES (2, '', '', '', '#FFFFFF', '2022-07-27 11:16:16', '2022-07-27 14:03:04');
INSERT INTO `t_blockchain` VALUES (4, '', '', '', '#FFFFFF', '2022-07-27 13:51:01', '2022-07-27 14:07:28');
INSERT INTO `t_blockchain` VALUES (5, '', '', 'http://123', '#FFFFFF', '2022-07-27 13:52:21', '2022-07-27 14:14:51');
INSERT INTO `t_blockchain` VALUES (6, '蚂蚁链3', '蚂蚁旗下', 'http://123', '#FFFFFA', '2022-07-27 14:16:14', '2022-07-27 14:17:36');

-- ----------------------------
-- Table structure for t_sc_bc
-- ----------------------------
DROP TABLE IF EXISTS `t_sc_bc`;
CREATE TABLE `t_sc_bc`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sc_id` int(11) NULL DEFAULT NULL,
  `bc_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sc_bc
-- ----------------------------
INSERT INTO `t_sc_bc` VALUES (1, 1, 6);

-- ----------------------------
-- Table structure for t_sc_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_sc_tag`;
CREATE TABLE `t_sc_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sc_id` int(11) NULL DEFAULT NULL,
  `tag_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sc_tag
-- ----------------------------

-- ----------------------------
-- Table structure for t_shucang_platform
-- ----------------------------
DROP TABLE IF EXISTS `t_shucang_platform`;
CREATE TABLE `t_shucang_platform`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `cover_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `market_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `browse_num` int(11) NULL DEFAULT 0,
  `lick_num` int(11) NULL DEFAULT 0,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_shucang_platform
-- ----------------------------
INSERT INTO `t_shucang_platform` VALUES (1, 'iBox2', 'https://avatars.githubusercontent.com/u/55905576?v=4', '很火，上了央视', '二级市场', 0, 0, '2022-07-27 14:19:13', '2022-07-27 14:21:01');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `tag_type` int(255) NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `background_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `created_at` datetime NULL DEFAULT NULL,
  `updated_at` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (1, 'H5', 0, 'https://avatars.githubusercontent.com/u/55905576?v=4', '#FFFFFF', '2022-07-27 14:21:46', '2022-07-27 14:21:46');

SET FOREIGN_KEY_CHECKS = 1;
