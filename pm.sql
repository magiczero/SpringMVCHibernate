/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.0.76-enterprise-gpl-nt-log : Database - pmsys
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pmsys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pmsys`;

/*Table structure for table `asset` */

DROP TABLE IF EXISTS `asset`;

CREATE TABLE `asset` (
  `id` int(11) NOT NULL auto_increment,
  `asset_num` varchar(50) NOT NULL COMMENT '资产编号',
  `secret_num` varchar(50) default NULL COMMENT '涉密编号',
  `brand` varchar(50) default NULL COMMENT '品牌',
  `model` varchar(50) default NULL COMMENT '设备型号',
  `sn_num` varchar(50) default NULL COMMENT 'SN号',
  `equip_type` int(11) default NULL COMMENT '设备类型',
  `secret_level` int(11) default NULL COMMENT '密级',
  `purpose` varchar(100) default NULL COMMENT '用途',
  `Production_date` date default NULL COMMENT '生产日期',
  `Purchase_time` datetime default NULL COMMENT '购置时间',
  `record_time` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `manufa_id` int(11) NOT NULL COMMENT '厂商',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `asset` */

insert  into `asset`(`id`,`asset_num`,`secret_num`,`brand`,`model`,`sn_num`,`equip_type`,`secret_level`,`purpose`,`Production_date`,`Purchase_time`,`record_time`,`manufa_id`) values (3,'11111111','22222222','蓝超','型号','SN好',2,0,'安装杀毒软件','2015-09-01','2015-09-05 00:00:00','2015-08-31 13:44:41',1),(4,'11111111','22222222','蓝超','型号','SN好',2,0,'用途',NULL,NULL,'2015-08-31 14:20:13',1),(12,'J4569','23451','瑞星1','型号','HK98700983',9,0,'杀毒','2015-09-01','2015-09-16 00:00:00','2015-09-24 16:46:54',1),(13,'44444','44455','555','555','555',2,0,'55','2015-10-08','2015-10-09 00:00:00','2015-10-09 14:07:51',2),(10,'1234567','7654321','浪潮','desc2_8_','arent_i4_8_',2,0,'Hibernate: select style0_.id as id1_8_, style0_.desc as desc2_8_, ','2015-08-01','2015-09-09 00:00:00','2015-09-09 09:43:08',1),(11,'12345679','6443333','蓝超','电饭锅','SN好',2,0,'','2015-09-01','2015-09-04 00:00:00','2015-09-10 14:16:38',1);

/*Table structure for table `cabinet` */

DROP TABLE IF EXISTS `cabinet`;

CREATE TABLE `cabinet` (
  `id` int(11) NOT NULL auto_increment,
  `num` varchar(50) NOT NULL COMMENT '机柜编号',
  `Capacity` varchar(50) NOT NULL COMMENT '容量',
  `desc` varchar(100) default NULL COMMENT '说明',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='机柜';

/*Data for the table `cabinet` */

/*Table structure for table `cabinet_position` */

DROP TABLE IF EXISTS `cabinet_position`;

CREATE TABLE `cabinet_position` (
  `id` int(11) NOT NULL auto_increment,
  `num` varchar(50) NOT NULL COMMENT '位置编号',
  `cabinet_id` int(11) NOT NULL COMMENT '所属机柜',
  `desc` varchar(100) default NULL COMMENT '说明',
  `server_id` int(11) default NULL COMMENT '服务器',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='机柜位置';

/*Data for the table `cabinet_position` */

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `parent_id` int(11) default NULL COMMENT '上级部门id',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `department` */

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) NOT NULL,
  `telephone` varchar(64) default NULL COMMENT '电话',
  `duty` varchar(64) default NULL COMMENT '职务',
  `department_id` int(11) NOT NULL COMMENT '部门',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='员工表';

/*Data for the table `employee` */

/*Table structure for table `hard_disk` */

DROP TABLE IF EXISTS `hard_disk`;

CREATE TABLE `hard_disk` (
  `id` int(11) NOT NULL auto_increment,
  `serial_num` varchar(50) NOT NULL COMMENT '硬盘序列号',
  `disk_interface` varchar(20) NOT NULL COMMENT '硬盘接口',
  `disk_type` varchar(20) NOT NULL COMMENT '硬盘类型',
  `Capacity` varchar(20) NOT NULL COMMENT '容量',
  `server_id` int(11) NOT NULL COMMENT '服务器',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='硬盘';

/*Data for the table `hard_disk` */

insert  into `hard_disk`(`id`,`serial_num`,`disk_interface`,`disk_type`,`Capacity`,`server_id`) values (1,'44DLVLYKS94B','SATA2.0','台式机','500G',10),(15,'fgh','fgh','fgh','fgh',10),(16,'89780','SATA3.0','笔记本','500G',10),(13,'dfgds','fghf','dsfgsd','afdgasdf',10),(14,'fdghfdg','dfghdfg','dfgh','dfghdfgh',10),(17,'234567','SATA3.0','笔记本硬盘','2T',10),(18,'66666','斤斤计较','坎坎坷坷','12365',3);

/*Table structure for table `ip_add` */

DROP TABLE IF EXISTS `ip_add`;

CREATE TABLE `ip_add` (
  `id` int(11) NOT NULL auto_increment,
  `address` varchar(50) default NULL COMMENT 'ip地址',
  `mac_add` varchar(50) default NULL COMMENT 'mac地址',
  `mask_code` varchar(50) default NULL COMMENT '掩码',
  `desc` varchar(100) default NULL COMMENT '说明',
  `network_id` int(11) default NULL COMMENT '网卡',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='IP地址';

/*Data for the table `ip_add` */

/*Table structure for table `manufacturer` */

DROP TABLE IF EXISTS `manufacturer`;

CREATE TABLE `manufacturer` (
  `id` int(11) NOT NULL auto_increment,
  `num` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '厂商名称',
  `linkman` varchar(128) default NULL COMMENT '联系人',
  `telephone` varchar(255) default NULL COMMENT '联系电话',
  `qualifications` varchar(255) default NULL COMMENT '资质',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `manufacturer` */

insert  into `manufacturer`(`id`,`num`,`name`,`linkman`,`telephone`,`qualifications`) values (1,'000011','联想','联系人','89286066-911','苦逼资质'),(2,'000023','味多美','米老鼠','68787220-911','代码缺陷'),(3,'343443','酷派','张经理','13366688548','没有任何资质'),(4,'000333','曙光','李经理','59682365','服务器全国优质奖章，哈哈');

/*Table structure for table `network_card` */

DROP TABLE IF EXISTS `network_card`;

CREATE TABLE `network_card` (
  `id` int(11) NOT NULL auto_increment,
  `card_type` int(11) NOT NULL COMMENT '网卡类型',
  `mac_add` varchar(50) NOT NULL COMMENT 'mac地址',
  `remark` varchar(100) default NULL COMMENT '说明',
  `server_id` int(11) default NULL COMMENT '服务器',
  `internet_protocol` varchar(30) NOT NULL COMMENT 'IP',
  `subnet_mask` varchar(30) NOT NULL COMMENT '子网掩码',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='网卡';

/*Data for the table `network_card` */

insert  into `network_card`(`id`,`card_type`,`mac_add`,`remark`,`server_id`,`internet_protocol`,`subnet_mask`) values (1,8,'28-D2-44-9C-FC-12','本机网卡',10,'192.168.0.1','255.255.255.0'),(2,6,'12321321','撒旦飞洒的',10,'192.168.0.2','255.255.255.0'),(7,6,'11-22-33-44--55-66','',10,'192.168.0.3','255.255.255.0');

/*Table structure for table `persistent_logins` */

DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `USERNAME` varchar(64) default NULL,
  `SERIES` varchar(64) NOT NULL,
  `TOKEN` varchar(64) default NULL,
  `LAST_USED` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`SERIES`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Spring Remember me';

/*Data for the table `persistent_logins` */

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `name` varchar(20) NOT NULL default '',
  `country` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `person` */

insert  into `person`(`id`,`name`,`country`) values (1,'admin','usa'),(2,'yang','chn'),(3,'杨海鹏','中国');

/*Table structure for table `server_software` */

DROP TABLE IF EXISTS `server_software`;

CREATE TABLE `server_software` (
  `id` int(11) NOT NULL auto_increment,
  `server_id` int(11) NOT NULL,
  `software_id` int(11) NOT NULL,
  `install_date` datetime NOT NULL,
  `remark` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `server_software` */

insert  into `server_software`(`id`,`server_id`,`software_id`,`install_date`,`remark`) values (1,11,12,'2015-10-10 00:00:00',''),(2,10,12,'2015-10-10 00:00:00','瑞星杀毒软件');

/*Table structure for table `servicer` */

DROP TABLE IF EXISTS `servicer`;

CREATE TABLE `servicer` (
  `num` varchar(50) NOT NULL default '11111' COMMENT '服务器编号',
  `cpu` varchar(50) default NULL COMMENT 'CPU',
  `memory` varchar(50) default NULL COMMENT '内存',
  `cdrom` varchar(50) default NULL COMMENT '光驱',
  `raid_card` varchar(50) default NULL COMMENT 'raid卡',
  `raid_model` varchar(50) default NULL COMMENT 'raid模式',
  `power_num` int(11) default NULL COMMENT '电源数量',
  `id` int(11) default NULL COMMENT '主键'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `servicer` */

insert  into `servicer`(`num`,`cpu`,`memory`,`cdrom`,`raid_card`,`raid_model`,`power_num`,`id`) values ('11111','122','33','666','444','555',1,13),('234556','CPU','内存','光驱','raid卡','raid模式',1,3),('234556','CPU','内存','光驱','raid卡','raid模式',1,4),('11111','zhiqiao-009','jinshidun-0987','LG','raid-098','raid=09786',4,10),('11111','CPU','内存','光驱','raid卡','raid模式',1,11);

/*Table structure for table `software` */

DROP TABLE IF EXISTS `software`;

CREATE TABLE `software` (
  `id` int(11) NOT NULL,
  `num` varchar(50) NOT NULL COMMENT '软件编号',
  `name` varchar(50) NOT NULL COMMENT '软件名称',
  `versions` varchar(50) default NULL COMMENT '版本',
  `remark` varchar(100) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `software` */

insert  into `software`(`id`,`num`,`name`,`versions`,`remark`) values (12,'3451','瑞星杀毒企业版1','3443','士大夫');

/*Table structure for table `style_list` */

DROP TABLE IF EXISTS `style_list`;

CREATE TABLE `style_list` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL,
  `style_id` int(11) NOT NULL COMMENT 'styles表',
  `desc` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `style_list` */

/*Table structure for table `styles` */

DROP TABLE IF EXISTS `styles`;

CREATE TABLE `styles` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) NOT NULL COMMENT '类型名',
  `desc` varchar(50) default NULL COMMENT '概述',
  `parent_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `styles` */

insert  into `styles`(`id`,`name`,`desc`,`parent_id`) values (1,'资产类型','服务器类型',NULL),(2,'服务器',NULL,1),(3,'机柜',NULL,1),(4,'涉密终端',NULL,1),(5,'网卡类型',NULL,NULL),(6,'类型1',NULL,5),(7,'类型2',NULL,5),(8,'类型3',NULL,5),(9,'软件',NULL,1);

/*Table structure for table `sys_authorities` */

DROP TABLE IF EXISTS `sys_authorities`;

CREATE TABLE `sys_authorities` (
  `AUTHORITY_ID` int(11) NOT NULL auto_increment,
  `AUTHORITY_MARK` varchar(100) default NULL,
  `AUTHORITY_NAME` varchar(100) NOT NULL,
  `AUTHORITY_DESC` varchar(200) default NULL,
  `MESSAGE` varchar(100) default NULL,
  `ENABLE` int(11) default NULL,
  `ISSYS` int(11) default NULL,
  `MODULE_ID` int(11) default NULL,
  PRIMARY KEY  (`AUTHORITY_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `sys_authorities` */

insert  into `sys_authorities`(`AUTHORITY_ID`,`AUTHORITY_MARK`,`AUTHORITY_NAME`,`AUTHORITY_DESC`,`MESSAGE`,`ENABLE`,`ISSYS`,`MODULE_ID`) values (1,'AUTH_ADMIN','AUTH_ADMIN','最高权限',NULL,1,1,NULL);

/*Table structure for table `sys_authorities_resources` */

DROP TABLE IF EXISTS `sys_authorities_resources`;

CREATE TABLE `sys_authorities_resources` (
  `ID` int(11) NOT NULL,
  `RESOURCE_ID` int(11) NOT NULL,
  `AUTHORITY_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='权限资源表';

/*Data for the table `sys_authorities_resources` */

/*Table structure for table `sys_modules` */

DROP TABLE IF EXISTS `sys_modules`;

CREATE TABLE `sys_modules` (
  `MODULE_ID` int(11) NOT NULL auto_increment,
  `MODULE_NAME` varchar(100) NOT NULL,
  `MODULE_DESC` varchar(200) default NULL,
  `MODULE_TYPE` varchar(100) default NULL,
  `PARENT` varchar(100) default NULL,
  `MODULE_URL` varchar(100) default NULL,
  `I_LEVEL` int(4) default NULL,
  `LEAF` int(4) default NULL,
  `APPLICATION` varchar(100) default NULL,
  `CONTROLLER` varchar(100) default NULL,
  `ENABLE` int(1) default NULL,
  `PRIORITY` int(4) default NULL,
  PRIMARY KEY  (`MODULE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='模块表，与菜单有关系';

/*Data for the table `sys_modules` */

/*Table structure for table `sys_resources` */

DROP TABLE IF EXISTS `sys_resources`;

CREATE TABLE `sys_resources` (
  `RESOURCE_ID` int(11) NOT NULL auto_increment,
  `RESOURCE_TYPE` varchar(100) default NULL COMMENT 'url, method',
  `RESOURCE_NAME` varchar(100) default NULL,
  `RESOURCE_DESC` varchar(200) default NULL,
  `RESOURCE_PATH` varchar(200) default NULL,
  `PRIORITY` varchar(100) default NULL,
  `ENABLE` int(4) default NULL,
  `ISSYS` int(4) default NULL,
  `MODULE_ID` int(11) default NULL,
  PRIMARY KEY  (`RESOURCE_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `sys_resources` */

/*Table structure for table `sys_roles` */

DROP TABLE IF EXISTS `sys_roles`;

CREATE TABLE `sys_roles` (
  `ROLE_ID` int(11) NOT NULL auto_increment,
  `ROLE_NAME` varchar(100) default NULL,
  `ROLE_DESC` varchar(200) default NULL,
  `ISENABLE` int(1) default NULL,
  `ISSYS` int(1) default NULL,
  `MODULE_ID` int(11) default NULL,
  PRIMARY KEY  (`ROLE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_roles` */

insert  into `sys_roles`(`ROLE_ID`,`ROLE_NAME`,`ROLE_DESC`,`ISENABLE`,`ISSYS`,`MODULE_ID`) values (1,'ROLE_ADMIN','系统管理员',1,1,NULL);

/*Table structure for table `sys_roles_authorities` */

DROP TABLE IF EXISTS `sys_roles_authorities`;

CREATE TABLE `sys_roles_authorities` (
  `ID` int(11) NOT NULL auto_increment,
  `AUTHORITY_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

/*Data for the table `sys_roles_authorities` */

insert  into `sys_roles_authorities`(`ID`,`AUTHORITY_ID`,`ROLE_ID`) values (1,1,1);

/*Table structure for table `sys_roles_moudles` */

DROP TABLE IF EXISTS `sys_roles_moudles`;

CREATE TABLE `sys_roles_moudles` (
  `ID` int(11) NOT NULL auto_increment,
  `MODULE_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色模块表——控制角色对模块的访问权，主要用于生成菜单';

/*Data for the table `sys_roles_moudles` */

/*Table structure for table `sys_users` */

DROP TABLE IF EXISTS `sys_users`;

CREATE TABLE `sys_users` (
  `USER_ID` int(11) NOT NULL auto_increment,
  `USERNAME` varchar(100) NOT NULL,
  `NAME` varchar(100) default NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `DT_CREATE` timestamp NULL default CURRENT_TIMESTAMP,
  `LAST_LOGIN` date default NULL,
  `DEADLINE` date default NULL,
  `LOGIN_IP` varchar(100) default NULL,
  `MECH_ID` varchar(100) default NULL,
  `MECH_NAME` varchar(100) default NULL,
  `DEP_ID` varchar(100) default NULL,
  `DEP_NAME` varchar(100) default NULL,
  `ENABLED` tinyint(1) default NULL,
  `ACCOUNT_NON_EXPIRED` tinyint(1) default NULL,
  `ACCOUNT_NON_LOCKED` tinyint(1) default NULL,
  `CREDENTIALS_NON_EXPIRED` tinyint(1) default NULL,
  PRIMARY KEY  (`USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Data for the table `sys_users` */

insert  into `sys_users`(`USER_ID`,`USERNAME`,`NAME`,`PASSWORD`,`DT_CREATE`,`LAST_LOGIN`,`DEADLINE`,`LOGIN_IP`,`MECH_ID`,`MECH_NAME`,`DEP_ID`,`DEP_NAME`,`ENABLED`,`ACCOUNT_NON_EXPIRED`,`ACCOUNT_NON_LOCKED`,`CREDENTIALS_NON_EXPIRED`) values (1,'admin','系统管理员','123456','2015-08-18 15:33:42',NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,0,0,0);

/*Table structure for table `sys_users_roles` */

DROP TABLE IF EXISTS `sys_users_roles`;

CREATE TABLE `sys_users_roles` (
  `ID` int(11) NOT NULL auto_increment,
  `ROLE_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `sys_users_roles` */

insert  into `sys_users_roles`(`ID`,`ROLE_ID`,`USER_ID`) values (1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
