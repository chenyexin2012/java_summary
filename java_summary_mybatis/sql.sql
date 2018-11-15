/*
 Navicat Premium Data Transfer

 Source Server         : mySql
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : holmes

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 15/11/2018 10:39:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`  (
  `ADDRESS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PROVINCE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `CITY` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `STREET` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ADDRESS_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_address
-- ----------------------------
INSERT INTO `t_address` VALUES (1, '安徽省', '合肥市', '九龙路');
INSERT INTO `t_address` VALUES (2, '安徽省', '合肥市', '科学大道');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `ORDER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `COUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`) USING BTREE,
  INDEX `USER_ID`(`USER_ID`) USING BTREE,
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, 1, 'C++', 11);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_PASSWORD` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_EMAIL` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ADDRESS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE,
  INDEX `IDX_NAME`(`USER_NAME`) USING BTREE,
  INDEX `ADDRESS_ID`(`ADDRESS_ID`) USING BTREE,
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `t_address` (`ADDRESS_ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'sherlock', '123456', 'wwwsfsdf@123.com', 1);
INSERT INTO `t_user` VALUES (3, 'sherlock', '123456', 'xxx@xxx.com', 2);
INSERT INTO `t_user` VALUES (4, 'sherlock', '123456', 'xxx@xxx.com', 1);
INSERT INTO `t_user` VALUES (5, 'sherlock', '123456', 'xxx@xxx.com', 2);
INSERT INTO `t_user` VALUES (6, 'sherlock', '123456', 'xxx@xxx.com', NULL);
INSERT INTO `t_user` VALUES (7, 'sherlock', '123456', 'xxx@xxx.com', NULL);
INSERT INTO `t_user` VALUES (8, 'sherlock', '123456', 'xxx@xxx.com', NULL);
INSERT INTO `t_user` VALUES (9, 'sherlock', '123456', 'xxx@xxx.com', NULL);


SET FOREIGN_KEY_CHECKS = 1;
