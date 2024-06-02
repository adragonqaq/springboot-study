CREATE TABLE `classroom` (
                             `id` int NOT NULL auto_increment COMMENT 'ID',
                             `roomname` varchar(32) DEFAULT NULL COMMENT '教室容纳人数',
                             `accptnum` int(11) DEFAULT NULL COMMENT '教室容纳人数',
                             `category` varchar(1000) DEFAULT NULL COMMENT '教室类别',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教室表';

CREATE TABLE `clazz` (
                         `id` int NOT NULL auto_increment COMMENT 'id',
                         `classname` varchar(32) DEFAULT NULL COMMENT '班级名称',
                         `classpreson` int(11) DEFAULT NULL COMMENT '班级人数',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='班级表';


CREATE TABLE `lesson` (
                          `id` int NOT NULL auto_increment COMMENT 'id',
                          `lessonname` varchar(32) DEFAULT NULL COMMENT '课程名称',
                          `xf` int(11) DEFAULT NULL COMMENT '学分',
                          `ks` decimal(18,2) DEFAULT NULL COMMENT '课时',
                          `category` varchar(32) DEFAULT NULL COMMENT '课程类型',
                          `start` int(11) DEFAULT NULL COMMENT '开始周',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';



CREATE TABLE `teacher` (
                           `id` int NOT NULL auto_increment COMMENT 'id',
                           `teachername` varchar(32) DEFAULT NULL COMMENT '姓名',
                           `sex`  varchar(32) DEFAULT NULL COMMENT '教师性别',
                           `major` varchar(32) DEFAULT NULL COMMENT '教师授课专业',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师表';



CREATE TABLE `teachplan` (
                             `id` int NOT NULL auto_increment COMMENT 'id',
                             `lesson_name` varchar(32) DEFAULT NULL COMMENT '课程名称',
                             `teacher_name`  varchar(32) DEFAULT NULL COMMENT '老师名称',
                             `clazz_name` varchar(32) DEFAULT NULL COMMENT '班纳名称',
                             `zks` int(11) DEFAULT NULL COMMENT '总课时',
                             `classroom_name` varchar(32) DEFAULT NULL COMMENT '教室名称',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教师计划表';



CREATE TABLE `tpcontent` (
                             `clazz_name` varchar(32) DEFAULT NULL COMMENT '班级名称',
                             `teachplan_content`  varchar(32) DEFAULT NULL COMMENT '排课内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课表信息表';



CREATE TABLE `user` (
                        `id` int NOT NULL auto_increment COMMENT 'id',
                        `name` varchar(32) DEFAULT NULL COMMENT '用户名',
                        `password`  varchar(32) DEFAULT NULL COMMENT '密码',
                        `role`  varchar(32) DEFAULT NULL COMMENT '角色',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';