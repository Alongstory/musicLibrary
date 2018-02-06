/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : musicrecommendationsystem

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2016-12-03 07:40:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `singer` varchar(200) NOT NULL,
  `writer` varchar(200) NOT NULL,
  `time` varchar(200) DEFAULT NULL,
  `style` enum('Blues','Jazz','Rock','Pop','Classical','Country','Electronic','R&B') NOT NULL,
  `lyrics` varchar(20000) NOT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES ('1', '1', 'singer', 'writer', '05:02', 'Blues', 'Scjhgijhkg', '3');
INSERT INTO `music` VALUES ('2', '2', 'singer2', 'writer', '04:02', 'Blues', 'Scjhgjhkg', '2');
INSERT INTO `music` VALUES ('3', '3', 'singer2', 'writer', '03:52', 'Blues', 'Scg', '3');
INSERT INTO `music` VALUES ('4', 'Black Beatles', 'Rae Sremmurd/Gucci Mane', 'A.Brown,K.Brown,M.Williams,R.Davis', '03:52', 'Blues', 'Black beatles in the city be back immediately to confiscate the moneys', '2');
