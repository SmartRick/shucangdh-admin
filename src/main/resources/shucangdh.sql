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

 Date: 30/07/2022 10:38:20
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
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blockchain
-- ----------------------------
INSERT INTO `t_blockchain` VALUES (16, '速藏', NULL, 'https://scan.jxrchain.pro', NULL, '2022-07-28 16:08:40', '2022-07-28 16:08:40');
INSERT INTO `t_blockchain` VALUES (17, '秘宝(Nervos)', NULL, 'https://explorer.mibao.net', NULL, '2022-07-28 16:08:40', '2022-07-28 16:08:40');
INSERT INTO `t_blockchain` VALUES (18, '百度超级链(XuperChain)', NULL, 'https://xuper.baidu.com', NULL, '2022-07-28 16:08:40', '2022-07-28 16:08:40');
INSERT INTO `t_blockchain` VALUES (19, '腾讯至信链', NULL, 'https://zxscan.qq.com', NULL, '2022-07-28 16:08:40', '2022-07-28 16:08:40');
INSERT INTO `t_blockchain` VALUES (20, 'BSN文昌链', NULL, 'https://wenchang.bianjie.ai', NULL, '2022-07-28 16:08:40', '2022-07-28 16:08:40');
INSERT INTO `t_blockchain` VALUES (21, '秦储数藏', NULL, 'https://scan.qcsc.vip', NULL, '2022-07-28 16:08:40', '2022-07-28 16:08:40');
INSERT INTO `t_blockchain` VALUES (22, 'NFTSSCAN', NULL, 'https://www.nfts-scan.com', NULL, '2022-07-28 16:08:40', '2022-07-28 16:08:40');
INSERT INTO `t_blockchain` VALUES (23, '京东智臻链', NULL, 'https://openchain.jd.com', NULL, '2022-07-28 16:08:40', '2022-07-28 16:08:40');
INSERT INTO `t_blockchain` VALUES (24, '智臻链浏览器', NULL, 'http://jdd-nft.jd.com', NULL, '2022-07-28 16:08:40', '2022-07-28 16:08:40');

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
