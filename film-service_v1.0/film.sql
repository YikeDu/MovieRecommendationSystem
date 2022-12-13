/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : film

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2022-12-11 20:36:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT '',
  `email` varchar(64) DEFAULT '',
  `password` varchar(64) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test', 'asdfadsf@abc.com', 'asdfasdfdasf');
INSERT INTO `user` VALUES ('2', 'testadf', 'asdfadsf33@abc.com', 'adsfasdfasdf');
INSERT INTO `user` VALUES ('4', 'adsfadsf', 'asdfadadsfdsafsf@abc.com', 'asdasdfadsfasdfasdf');
INSERT INTO `user` VALUES ('5', 'test1', 'test1@test1.com', '11111111');
INSERT INTO `user` VALUES ('6', 'test2', 'test222@abc.com', '11111111');
INSERT INTO `user` VALUES ('7', 'test3', 'test3@test3.com', '11111111');
