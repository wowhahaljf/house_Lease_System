CREATE DATABASE `house`;

USE `house`;
SET FOREIGN_KEY_CHECKS = 1;
SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS;
SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION;
SET NAMES utf8;
SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO';
SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`
(
    `id`       int(11)      NOT NULL AUTO_INCREMENT,
    `username` varchar(30)  NOT NULL,
    `userpwd`  varchar(100) NOT NULL,
    PRIMARY KEY (`id`)#主键
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
#AUTO_INCREMENT=2，自增键的起始序号为2
-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin`
VALUES ('1', 'liangzhou', '123456');
-- ----------------------------
-- Table structure for t_house
-- ----------------------------
DROP TABLE IF EXISTS `t_house`;
CREATE TABLE `t_house`
(
    `h_id`               int(11)       NOT NULL AUTO_INCREMENT,
    `house_desc`         varchar(255)  NOT NULL COMMENT '租房描述',#comment属性来添加注释
    `house_model`        varchar(20)   NOT NULL COMMENT '房屋类型，几室几厅',
    `house_area`         varchar(20)   NOT NULL COMMENT '房屋面积',
    `house_floor`        varchar(20)   NOT NULL COMMENT '房屋楼层',
    `house_type`         varchar(20)   NOT NULL COMMENT '出租方式',
    `house_price`        int(10)       NOT NULL COMMENT '出租价格',
    `house_address`      varchar(100)  NOT NULL COMMENT '出租地址',
    `house_image`        varchar(1000) NOT NULL COMMENT '房屋简介照片',
    `community_name`     varchar(100)  NOT NULL COMMENT '小区名字',
    `house_linkman`      varchar(11)   NOT NULL COMMENT '房屋联系电话',
    `house_oriented`     varchar(20)   NOT NULL COMMENT '房屋朝向',
    `house_detailes_img` varchar(1000) NOT NULL COMMENT '房屋详细页面展示图片',
    `publisher`          varchar(50)   NOT NULL DEFAULT '管理员' COMMENT '发布人',
    `contract`           varchar(1000) NOT NULL COMMENT '房屋合同',
    `publish_time`       datetime               DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',#CURRENT_TIMESTAMP当插入数据的时候，该字段默认值为当前时间
    PRIMARY KEY (`h_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 33
  DEFAULT CHARSET = utf8;



-- ----------------------------
-- Records of t_house
-- ----------------------------
INSERT INTO `t_house`
VALUES ('1', '华侨大学厦门校区梅苑六人间', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('2', '公寓', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('3', '别墅', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('4', '海景房', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('5', '华侨大学厦门校区梅苑六人间', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('6', '公寓', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('7', '别墅', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('8', '海景房', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('9', '华侨大学厦门校区梅苑六人间', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('10', '公寓', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('11', '别墅', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('12', '海景房', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('13', '华侨大学厦门校区梅苑六人间', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('14', '公寓', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('15', '别墅', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');
INSERT INTO `t_house`
VALUES ('16', '海景房', '1室0厅1卫', '20', '中层/9层', '整租', '600', '华侨大学厦门校区',
        'http://localhost:8080/images/997b2cc4-fb5c-46da-b8da-82a6ab0de843.jpg', '梅苑C515', '13813813800', '南北',
        'http://localhost:8080/images/b70a0eed-5182-4172-b9f3-d900f066097c.jpg~http://localhost:8080/images/5356fd0b-8ac9-4d39-adb4-c861b5522671.jpg~http://localhost:8080/images/1a6660f5-53b9-4498-aa50-f029af6bfd29.jpg~',
        'liangzhou', '2021-12-12 10:28:43');

-- ----------------------------
-- Table structure for t_order预定
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`
(
    `o_id`       int(11)     NOT NULL AUTO_INCREMENT,
    `h_id`       int(11)     NOT NULL COMMENT '房屋租赁id',
    `u_id`       int(11)     NOT NULL COMMENT '预定用户id',
    `order_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `order_user` varchar(20)    NOT NULL COMMENT '预定用户名',
    `start`      datetime NOT NULL COMMENT '租房开始时间',
    `end`        datetime NOT NULL COMMENT '租房结束时间',
    `money`      int(20)     NOT NULL COMMENT '订单金额',
    `status`      int(20)    NOT NULL DEFAULT '0' COMMENT '订单状态',
    PRIMARY KEY (`o_id`),
    KEY `fk1` (`h_id`),
    KEY `fk2` (`u_id`),

    CONSTRAINT `fk1` FOREIGN KEY (`h_id`) REFERENCES `t_house` (`h_id`),
    #添加外键约束，预定房子，若删掉所指向的主表记录但是主表记录存在，会失败
    #即要删掉已经预定的房子记录t_house，一定会失败

    CONSTRAINT `fk2` FOREIGN KEY (`u_id`) REFERENCES `t_users` (`u_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order`
VALUES ('11', '30', '3', '2021-12-13 21:46:14', 'liangzhou');


-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users`
(
    `u_id`           int(11)                                         NOT NULL AUTO_INCREMENT,
    `u_name`         varchar(20)                                     NOT NULL,
    `u_password`     varchar(100)                                    NOT NULL,
    `u_phone_number` varchar(11) DEFAULT NULL COMMENT '用户注册手机号码，用于找回密码',
    `u_nickname`     varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
    PRIMARY KEY (`u_id`),
    UNIQUE KEY `uniq` (`u_name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users`
VALUES ('1', 'wangz', '123456', '1357648878', '王清杉');
INSERT INTO `t_users`
VALUES ('2', 'wangy', '123456', '1357648878', '王清杉');
INSERT INTO `t_users`
VALUES ('3', 'yangx', '123456', '1567785468', '杨朕颜');
INSERT INTO `t_users`
VALUES ('4', 'yanga', '123456', '1567785468', '杨朕颜');
INSERT INTO `t_users`
VALUES ('5', 'yangb', '123456', '1567785468', '杨朕颜');
INSERT INTO `t_users`
VALUES ('6', 'wangb', '123456', '1357648878', '王清杉');
INSERT INTO `t_users`
VALUES ('7', 'yangc', '123456', '1567785468', '杨朕颜');
INSERT INTO `t_users`
VALUES ('8', 'yangd', '123456', '1567785468', '杨朕颜');
INSERT INTO `t_users`
VALUES ('9', 'yange', '123456', '1567785468', '杨朕颜');
INSERT INTO `t_users`
VALUES ('10', 'wangf', '123456', '1357648878', '王清杉');
INSERT INTO `t_users`
VALUES ('11', 'yangg', '123456', '1567785468', '杨朕颜');
INSERT INTO `t_users`
VALUES ('12', 'yangh', '123456', '1567785468', '杨朕颜');
INSERT INTO `t_users`
VALUES ('13', 'yangi', '123456', '1567785468', '杨朕颜');


-- ----------------------------
-- Records of t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment`
(
    `c_id`        int(11)     NOT NULL AUTO_INCREMENT COMMENT '评论的id',
    `h_id`        int(11)     NOT NULL COMMENT '评论房源的id',
    `u_id`        int(11)     NOT NULL COMMENT '评论用户id',
    `u_nickname`  varchar(11) NOT NULL COMMENT '评论用户昵称',
    `parent_id`   int(11)      DEFAULT '-1' COMMENT '父级评论',
    `content`     varchar(255) DEFAULT NULL COMMENT '评论的内容',
    `time`        datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    `comment_num` int(11)      DEFAULT '0' COMMENT '回复数量',
    `like`        int(11)      DEFAULT '0' COMMENT '点赞数',
    PRIMARY KEY (`c_id`),
    CONSTRAINT `fk3` FOREIGN KEY (`h_id`) REFERENCES `t_house` (`h_id`),
    CONSTRAINT `fk4` FOREIGN KEY (`u_id`) REFERENCES `t_users` (`u_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply`
(
    `r_id`        int(11)     NOT NULL AUTO_INCREMENT COMMENT '评论的id',
    `c_id`        int(11)     NOT NULL COMMENT '父级评论的id',
    `h_id`        int(11)     NOT NULL COMMENT '评论房源的id',
    `u_id`        int(11)     NOT NULL COMMENT '评论用户id',
    `from`        varchar(11) NOT NULL COMMENT '评论用户昵称',
    `fromId`      int(11)      DEFAULT '-1' COMMENT '子',
    `content`     varchar(255) DEFAULT NULL COMMENT '子id',
    `to`          varchar(11)  DEFAULT '-1' COMMENT '父级评论',
    `toId`        int(255)     DEFAULT '0' COMMENT '父id',
    `fromReplyId` int(255)     DEFAULT '0' COMMENT '父评论id',
    `time`        datetime     default current_timestamp COMMENT '发布时间',
    `comment_num` int(11)      DEFAULT '0' COMMENT '回复数量',
    `like`        int(11)      DEFAULT '0' COMMENT '点赞数',
    PRIMARY KEY (`r_id`),
    CONSTRAINT `fk5` FOREIGN KEY (`h_id`) REFERENCES `t_house` (`h_id`),
    CONSTRAINT `fk6` FOREIGN KEY (`u_id`) REFERENCES `t_users` (`u_id`),
    CONSTRAINT `fk7` FOREIGN KEY (`c_id`) REFERENCES `t_comment` (`c_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `t_comment_like`;
create table `t_comment_like`
(
    `cl_id`       int(11) auto_increment NOT NULL COMMENT '点赞id',
    `c_id`        int(11)                NOT NULL COMMENT '评论id',
    `u_id`        int(11)                NOT NULL COMMENT '评论点赞者id',
    `like_status` int(2) default '0' COMMENT '点赞状态',
    primary key (`cl_id`),
    CONSTRAINT `fk8` FOREIGN KEY (`u_id`) REFERENCES `t_users` (`u_id`),
    CONSTRAINT `fk9` FOREIGN KEY (`c_id`) REFERENCES `t_comment` (`c_id`)
) ENGINE = InnoDB
  auto_increment = 1
  DEFAULT CHARSET = utf8;
insert into `t_comment_like`
values (0, 13, 18, 1);

DROP TABLE IF EXISTS `t_reply_like`;

create table `t_reply_like`
(
    `rl_id`       int(11) auto_increment NOT NULL COMMENT '回复点赞id',
    `r_id`        int(11)                NOT NULL COMMENT '评论id',
    `u_id`        int(11)                NOT NULL COMMENT '评论点赞者id',
    `like_status` int(2) default '0' COMMENT '点赞状态',
    primary key (`rl_id`),
    CONSTRAINT `fk10` FOREIGN KEY (`u_id`) REFERENCES `t_users` (`u_id`),
    CONSTRAINT `fk11` FOREIGN KEY (`r_id`) REFERENCES `t_reply` (`r_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS;
SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION;
SET SQL_NOTES = @OLD_SQL_NOTES;
use house;



-- ----------------------------
-- Table structure for t_user_house_like
-- ----------------------------
drop table if exists `t_user_house_like`;
create table `t_user_house_like`
(
    `u_like_id` int(11) auto_increment not null comment '房屋点赞id' primary key,
    `u_id`      int(11)                not null comment '用户id',
    `h_id`      int(11)                not null comment '房屋id',
    `is_like`   int(11)                not null default '0' comment '点赞状态',
    constraint `u_uh_like` foreign key (`u_id`) references `t_users` (`u_id`),
    constraint `h_uh_like` foreign key (`h_id`) references `t_house` (`h_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_user_house_collection
-- ----------------------------
drop table if exists `t_user_house_collection`;
create table `t_user_house_collection`
(
    `u_col_id` int(11) auto_increment not null comment '房屋收藏id' primary key,
    `u_id`     int(11)                not null comment '用户id',
    `h_id`     int(11)                not null comment '房屋id',
    `is_col`   int(11)                not null comment '房屋id',
    constraint `u_uh_col` foreign key (`u_id`) references `t_users` (`u_id`),
    constraint `h_uh_col` foreign key (`h_id`) references `t_house` (`h_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_complain
-- ----------------------------
drop table if exists `t_complain`;
create table `t_complain`
(
    `cpl_id` int(11) auto_increment not null comment '房屋投诉id' primary key,
    `u_id`     int(11)                not null comment '用户id',
    `h_id`     int(11)                not null comment '房屋id',
    `a_id`     int(11)                not null comment '管理员id',
    `cpl_content`   varchar(200)      not null comment '投诉内容',
    `status`   int(11)                not null comment '处理状态',
    constraint `cpl_user` foreign key (`u_id`) references `t_users` (`u_id`),
    constraint `cpl_house` foreign key (`h_id`) references `t_house` (`h_id`),
    constraint `cpl_admin` foreign key (`a_id`) references `t_admin` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
-- ----------------------------
-- Table structure for t_complain
-- ----------------------------
drop table if exists `t_house_like_number`;
create table `t_house_like_number`
(
    `h_id`     int(11)                not null comment '房屋id',
    `number`   int(11)                not null comment '点赞数量',
    constraint `h_number` foreign key (`h_id`) references `t_house` (`h_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;