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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `person` */

insert  into `person`(`id`,`name`,`country`) values (1,'admin','usa'),(2,'yang','chn');

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
