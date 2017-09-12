/*
SQLyog Community v12.4.1 (64 bit)
MySQL - 5.7.17-log : Database - pmsys_20170711
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pmsys_20170711` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pmsys_20170711`;

/*Table structure for table `act_evt_log` */

DROP TABLE IF EXISTS `act_evt_log`;

CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TIME_STAMP_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp NULL DEFAULT NULL,
  `IS_PROCESSED_` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`LOG_NR_`)
) ENGINE=MyISAM AUTO_INCREMENT=7291 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ge_bytearray` */

DROP TABLE IF EXISTS `act_ge_bytearray`;

CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ge_property` */

DROP TABLE IF EXISTS `act_ge_property`;

CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_actinst` */

DROP TABLE IF EXISTS `act_hi_actinst`;

CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) NOT NULL,
  `PROC_DEF_ID_` varchar(64) NOT NULL,
  `PROC_INST_ID_` varchar(64) NOT NULL,
  `EXECUTION_ID_` varchar(64) NOT NULL,
  `ACT_ID_` varchar(255) NOT NULL,
  `TASK_ID_` varchar(64) DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) DEFAULT NULL,
  `ACT_NAME_` varchar(255) DEFAULT NULL,
  `ACT_TYPE_` varchar(255) NOT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `act_hi_attachment` */

DROP TABLE IF EXISTS `act_hi_attachment`;

CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) DEFAULT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL,
  `TASK_ID_` varchar(64) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) DEFAULT NULL,
  `URL_` varchar(4000) DEFAULT NULL,
  `CONTENT_ID_` varchar(64) DEFAULT NULL,
  `TIME_` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `act_hi_comment` */

DROP TABLE IF EXISTS `act_hi_comment`;

CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_detail` */

DROP TABLE IF EXISTS `act_hi_detail`;

CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) NOT NULL,
  `TYPE_` varchar(255) NOT NULL,
  `PROC_INST_ID_` varchar(64) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) DEFAULT NULL,
  `TASK_ID_` varchar(64) DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) DEFAULT NULL,
  `NAME_` varchar(255) NOT NULL,
  `VAR_TYPE_` varchar(255) DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime NOT NULL,
  `BYTEARRAY_ID_` varchar(64) DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` decimal(19,0) DEFAULT NULL,
  `TEXT_` varchar(4000) DEFAULT NULL,
  `TEXT2_` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `act_hi_identitylink` */

DROP TABLE IF EXISTS `act_hi_identitylink`;

CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(255) DEFAULT NULL,
  `TYPE_` varchar(255) DEFAULT NULL,
  `USER_ID_` varchar(255) DEFAULT NULL,
  `TASK_ID_` varchar(64) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `act_hi_procinst` */

DROP TABLE IF EXISTS `act_hi_procinst`;

CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) NOT NULL,
  `PROC_INST_ID_` varchar(64) NOT NULL,
  `BUSINESS_KEY_` varchar(255) DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) NOT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `START_USER_ID_` varchar(255) DEFAULT NULL,
  `START_ACT_ID_` varchar(255) DEFAULT NULL,
  `END_ACT_ID_` varchar(255) DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  `NAME_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `act_hi_taskinst` */

DROP TABLE IF EXISTS `act_hi_taskinst`;

CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) NOT NULL,
  `PROC_DEF_ID_` varchar(64) DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) DEFAULT NULL,
  `NAME_` varchar(255) DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) DEFAULT NULL,
  `OWNER_` varchar(255) DEFAULT NULL,
  `ASSIGNEE_` varchar(255) DEFAULT NULL,
  `START_TIME_` datetime NOT NULL,
  `CLAIM_TIME_` datetime DEFAULT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` decimal(19,0) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime DEFAULT NULL,
  `FORM_KEY_` varchar(255) DEFAULT NULL,
  `CATEGORY_` varchar(255) DEFAULT NULL,
  `TENANT_ID_` varchar(255) DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `act_hi_varinst` */

DROP TABLE IF EXISTS `act_hi_varinst`;

CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) NOT NULL,
  `PROC_INST_ID_` varchar(64) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) DEFAULT NULL,
  `TASK_ID_` varchar(64) DEFAULT NULL,
  `NAME_` varchar(255) NOT NULL,
  `VAR_TYPE_` varchar(100) DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` decimal(19,0) DEFAULT NULL,
  `TEXT_` varchar(4000) DEFAULT NULL,
  `TEXT2_` varchar(4000) DEFAULT NULL,
  `CREATE_TIME_` datetime DEFAULT NULL,
  `LAST_UPDATED_TIME_` datetime DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `act_id_info` */

DROP TABLE IF EXISTS `act_id_info`;

CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_re_deployment` */

DROP TABLE IF EXISTS `act_re_deployment`;

CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `DEPLOY_TIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_re_model` */

DROP TABLE IF EXISTS `act_re_model`;

CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_re_procdef` */

DROP TABLE IF EXISTS `act_re_procdef`;

CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_event_subscr` */

DROP TABLE IF EXISTS `act_ru_event_subscr`;

CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_execution` */

DROP TABLE IF EXISTS `act_ru_execution`;

CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCK_TIME_` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_identitylink` */

DROP TABLE IF EXISTS `act_ru_identitylink`;

CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_job` */

DROP TABLE IF EXISTS `act_ru_job`;

CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_task` */

DROP TABLE IF EXISTS `act_ru_task`;

CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DUE_DATE_` datetime DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `TENANT_ID_` varchar(255) COLLATE utf8_bin DEFAULT '',
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_ru_variable` */

DROP TABLE IF EXISTS `act_ru_variable`;

CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `asset` */

DROP TABLE IF EXISTS `asset`;

CREATE TABLE `asset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `asset_num` varchar(50) NOT NULL COMMENT '资产编号',
  `secret_num` varchar(50) DEFAULT NULL COMMENT '涉密编号',
  `brand` varchar(50) DEFAULT NULL COMMENT '品牌',
  `model` varchar(50) DEFAULT NULL COMMENT '设备型号',
  `sn_num` varchar(50) DEFAULT NULL COMMENT 'SN号',
  `equip_type` int(11) DEFAULT NULL COMMENT '设备类型',
  `secret_level` int(11) DEFAULT NULL COMMENT '密级',
  `purpose` varchar(100) DEFAULT NULL COMMENT '用途',
  `Production_date` date DEFAULT NULL COMMENT '生产日期',
  `Purchase_time` datetime DEFAULT NULL COMMENT '购置时间',
  `record_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `manufa_id` int(11) NOT NULL COMMENT '厂商',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Table structure for table `attachment` */

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `thumbnailFilename` varchar(255) DEFAULT NULL,
  `newFilename` varchar(255) DEFAULT NULL,
  `contentType` varchar(255) DEFAULT NULL,
  `size_` int(11) DEFAULT NULL,
  `thumbnailSize` int(11) DEFAULT NULL,
  `dateCreated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastUpdated` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `type_` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `path_` varchar(255) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `new_filename` varchar(255) DEFAULT NULL,
  `thumbnail_filename` varchar(255) DEFAULT NULL,
  `thumbnail_size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_abpc1wrf8notq4f3nclwvn0nb` (`type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=412 DEFAULT CHARSET=utf8;

/*Table structure for table `cabinet` */

DROP TABLE IF EXISTS `cabinet`;

CREATE TABLE `cabinet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(50) NOT NULL COMMENT '机柜编号',
  `Capacity` varchar(50) NOT NULL COMMENT '容量',
  `desc` varchar(100) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='机柜';

/*Table structure for table `cabinet_position` */

DROP TABLE IF EXISTS `cabinet_position`;

CREATE TABLE `cabinet_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(50) NOT NULL COMMENT '位置编号',
  `cabinet_id` int(11) NOT NULL COMMENT '所属机柜',
  `desc` varchar(100) DEFAULT NULL COMMENT '说明',
  `server_id` int(11) DEFAULT NULL COMMENT '服务器',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='机柜位置';

/*Table structure for table `check_item` */

DROP TABLE IF EXISTS `check_item`;

CREATE TABLE `check_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `name_` varchar(255) DEFAULT NULL,
  `demand` text COMMENT '评测要求',
  `technique` text COMMENT '测评方法',
  `base_` tinyint(1) DEFAULT NULL COMMENT '是否是基本测评项',
  `record_` text COMMENT '相关记录',
  PRIMARY KEY (`id`),
  KEY `FK_ma8hmkf09c8ofvs8x9nk5dry3` (`item_id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Table structure for table `cms_category` */

DROP TABLE IF EXISTS `cms_category`;

CREATE TABLE `cms_category` (
  `category_code` varchar(50) DEFAULT NULL COMMENT '分类编码',
  `category_name` varchar(255) DEFAULT NULL COMMENT '分类名'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `cms_category_property` */

DROP TABLE IF EXISTS `cms_category_property`;

CREATE TABLE `cms_category_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_code` varchar(50) DEFAULT NULL,
  `property_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kii5jo26ykp3b07itvsaisagq` (`property_id`),
  KEY `FK_rfpfujkdfjyvwfer8oqn29ttd` (`category_code`)
) ENGINE=MyISAM AUTO_INCREMENT=148 DEFAULT CHARSET=utf8;

/*Table structure for table `cms_category_relation` */

DROP TABLE IF EXISTS `cms_category_relation`;

CREATE TABLE `cms_category_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_code_primary` varchar(50) DEFAULT NULL,
  `category_code_secondary` varchar(50) DEFAULT NULL,
  `relation_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t4lt84iu2agbblv42hkpac7hq` (`category_code_primary`),
  KEY `FK_2alhqru4pqsf0gof1ruo185ki` (`category_code_secondary`),
  KEY `FK_cc0nhoc83dtdisrnm9wuos6v0` (`relation_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Table structure for table `cms_ci` */

DROP TABLE IF EXISTS `cms_ci`;

CREATE TABLE `cms_ci` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(255) DEFAULT NULL COMMENT '名称',
  `model` varchar(255) DEFAULT NULL COMMENT '型号',
  `is_use` varchar(10) DEFAULT NULL COMMENT '是否启用',
  `security_no` varchar(255) DEFAULT NULL COMMENT '保密编号',
  `security_level` varchar(50) DEFAULT NULL COMMENT '密级',
  `system` varchar(50) DEFAULT NULL COMMENT '所属系统',
  `category_code` varchar(50) DEFAULT NULL COMMENT '分类代码',
  `importance` varchar(50) DEFAULT NULL COMMENT '重要度',
  `incidence` varchar(255) DEFAULT NULL COMMENT '影响范围',
  `location` varchar(255) DEFAULT NULL COMMENT '物理位置',
  `department_in_use` varchar(255) DEFAULT NULL COMMENT '使用部门',
  `department_in_maintenance` varchar(255) DEFAULT NULL COMMENT '维护部门',
  `user_in_maintenance` varchar(255) DEFAULT NULL COMMENT '维护人员',
  `service_start_time` datetime DEFAULT NULL COMMENT '服务开始日期',
  `service_end_time` datetime DEFAULT NULL COMMENT '服务结束日期',
  `service_level` varchar(255) DEFAULT NULL COMMENT '服务级别',
  `service_provider` varchar(255) DEFAULT NULL COMMENT '服务提供商',
  `service_provider_contact` varchar(255) DEFAULT NULL COMMENT '服务联系方式',
  `review_status` varchar(50) DEFAULT '02' COMMENT '审核状态',
  `last_review_time` datetime DEFAULT NULL COMMENT '最近审核时间',
  `delete_status` varchar(50) DEFAULT '01' COMMENT '删除状态',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `producer` varchar(255) DEFAULT NULL COMMENT '厂商',
  `status_` varchar(50) DEFAULT '03' COMMENT '状态',
  `purpose` varchar(255) DEFAULT NULL COMMENT '用途',
  `expiration_time` datetime DEFAULT NULL COMMENT '硬件报废日期/软件许可过期日期',
  `ci_manager` varchar(50) DEFAULT NULL COMMENT 'ci管理员',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_update_user` varchar(50) DEFAULT NULL COMMENT '最后更新人',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `properties_data` text COMMENT '属性数据',
  `desc_` text COMMENT '描述',
  `num_` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `serial_` varchar(100) DEFAULT NULL COMMENT '出厂编号/序列号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Table structure for table `cms_ci_relation` */

DROP TABLE IF EXISTS `cms_ci_relation`;

CREATE TABLE `cms_ci_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `primary_id` bigint(20) DEFAULT NULL,
  `secondary_id` bigint(20) DEFAULT NULL,
  `relation_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `cms_property` */

DROP TABLE IF EXISTS `cms_property`;

CREATE TABLE `cms_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `property_id` varchar(255) DEFAULT NULL COMMENT '属性id',
  `property_name` varchar(255) DEFAULT NULL COMMENT '属性名',
  `property_type` varchar(255) DEFAULT NULL COMMENT '属性类型',
  `property_constraint` varchar(255) DEFAULT NULL COMMENT '属性约束条件',
  `is_required` tinyint(1) DEFAULT NULL COMMENT '是否是必填属性',
  `default_value` varchar(255) DEFAULT NULL COMMENT '属性的预设值',
  `html_code` varchar(512) DEFAULT NULL COMMENT '生成的html代码',
  `max_length` int(11) DEFAULT NULL COMMENT '最大字数',
  `min_length` int(11) DEFAULT '0' COMMENT '最少字数',
  `max_value` double DEFAULT NULL COMMENT '最大值',
  `min_value` double DEFAULT NULL COMMENT '最小数值',
  `date_format` varchar(64) DEFAULT NULL COMMENT '时间格式',
  `in_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

/*Table structure for table `cms_relation` */

DROP TABLE IF EXISTS `cms_relation`;

CREATE TABLE `cms_relation` (
  `relation_id` varchar(50) NOT NULL,
  `relation_name` varchar(255) DEFAULT NULL,
  `relation_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`relation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `contract` */

DROP TABLE IF EXISTS `contract`;

CREATE TABLE `contract` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(255) DEFAULT NULL COMMENT '合同名称',
  `abstract_` varchar(255) DEFAULT NULL COMMENT '合同摘要',
  `type_` varchar(50) DEFAULT NULL COMMENT '类型',
  `sn_` varchar(255) DEFAULT NULL COMMENT '服务代码',
  `contact_person` varchar(255) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `sign_time` datetime DEFAULT NULL COMMENT '签订时间',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件',
  `created_user` varchar(255) DEFAULT NULL COMMENT '创建者',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status_` varchar(50) DEFAULT NULL,
  `is_locked` tinyint(1) DEFAULT NULL,
  `process_instance_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '上级部门id',
  `telephone` varchar(64) DEFAULT NULL COMMENT '部门电话',
  PRIMARY KEY (`id`),
  KEY `FK_65cvny6rlr2o9o7pw0af5oxxk` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `doc_style` */

DROP TABLE IF EXISTS `doc_style`;

CREATE TABLE `doc_style` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `style_id` int(11) NOT NULL,
  KEY `id` (`id`),
  KEY `FK_1nhvd4yirmwonmvk0044qfe6d` (`doc_id`),
  KEY `FK_3yqtp6adkwncm40ay8tddanrr` (`style_id`)
) ENGINE=MyISAM AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='文档类别表';

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` varchar(64) DEFAULT NULL COMMENT '文档id，用于多版本时标识文档的唯一标识',
  `name` varchar(255) NOT NULL,
  `keywords` varchar(255) NOT NULL COMMENT '关键字？是否单独成表？',
  `versions` int(8) DEFAULT NULL COMMENT '版本号',
  `in_while` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `auth_` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `issue_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
  `last_version` tinyint(4) DEFAULT '1' COMMENT '是否是最新版本',
  `deposit` varchar(255) DEFAULT NULL COMMENT '纸质文档存放位置',
  `secret_level` int(11) DEFAULT NULL COMMENT '密级',
  `doc_num` varchar(255) DEFAULT NULL COMMENT '文档编号',
  `style_id` int(11) NOT NULL COMMENT '所属类别',
  `link_` varchar(64) DEFAULT NULL COMMENT '文档所在链接',
  `enabled` tinyint(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `FK_9ygxt6fvlc1fbrpsxmwduvdcw` (`style_id`),
  KEY `FK_esb799ihho7ek44mndbsvmyit` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=114 DEFAULT CHARSET=utf8;

/*Table structure for table `document_sec_audit` */

DROP TABLE IF EXISTS `document_sec_audit`;

CREATE TABLE `document_sec_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `update_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `version_num` varchar(30) NOT NULL COMMENT '版本号',
  `user_id` int(11) NOT NULL COMMENT '用户',
  `update_info` varchar(200) DEFAULT NULL COMMENT '备注',
  `doc_id` int(11) NOT NULL COMMENT '对应的文档',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='文档安全审计表';

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `telephone` varchar(64) DEFAULT NULL COMMENT '电话',
  `duty` varchar(64) DEFAULT NULL COMMENT '职务',
  `department_id` int(11) NOT NULL COMMENT '部门',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='员工表';

/*Table structure for table `hard_disk` */

DROP TABLE IF EXISTS `hard_disk`;

CREATE TABLE `hard_disk` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_num` varchar(50) NOT NULL COMMENT '硬盘序列号',
  `disk_interface` varchar(20) NOT NULL COMMENT '硬盘接口',
  `disk_type` varchar(20) NOT NULL COMMENT '硬盘类型',
  `Capacity` varchar(20) NOT NULL COMMENT '容量',
  `server_id` int(11) NOT NULL COMMENT '服务器',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='硬盘';

/*Table structure for table `ip_add` */

DROP TABLE IF EXISTS `ip_add`;

CREATE TABLE `ip_add` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `mac_add` varchar(50) DEFAULT NULL COMMENT 'mac地址',
  `mask_code` varchar(50) DEFAULT NULL COMMENT '掩码',
  `desc` varchar(100) DEFAULT NULL COMMENT '说明',
  `network_id` int(11) DEFAULT NULL COMMENT '网卡',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='IP地址';

/*Table structure for table `kg_knowledge` */

DROP TABLE IF EXISTS `kg_knowledge`;

CREATE TABLE `kg_knowledge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `desc_` varchar(255) DEFAULT NULL,
  `solution` text,
  `keyword` varchar(255) DEFAULT NULL,
  `apply_user` varchar(255) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `status_` tinyint(1) DEFAULT NULL,
  `is_locked` tinyint(1) DEFAULT NULL,
  `process_instance_id` varchar(255) DEFAULT NULL,
  `current_activity_id` varchar(255) DEFAULT NULL,
  `current_activity_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Table structure for table `leave_jpa` */

DROP TABLE IF EXISTS `leave_jpa`;

CREATE TABLE `leave_jpa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apply_time` datetime DEFAULT NULL,
  `dept_leader_approved` varchar(255) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `hr_approved` varchar(255) DEFAULT NULL,
  `leave_type` varchar(255) DEFAULT NULL,
  `process_instance_id` varchar(255) DEFAULT NULL,
  `reality_end_time` datetime DEFAULT NULL,
  `reality_start_time` datetime DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `report_back_date` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `maintain_record` */

DROP TABLE IF EXISTS `maintain_record`;

CREATE TABLE `maintain_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `equip_name` varchar(50) DEFAULT NULL COMMENT '设备名称',
  `equip_num` varchar(50) DEFAULT NULL COMMENT '设备编号',
  `maintain_time` datetime NOT NULL COMMENT '维护时间',
  `add_in` varchar(1000) DEFAULT NULL COMMENT '外接设备情况',
  `performance_circs` varchar(2000) DEFAULT NULL COMMENT '执行情况',
  `executor` varchar(50) NOT NULL COMMENT '执行人',
  `escort` varchar(50) DEFAULT NULL COMMENT '陪同者',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注',
  `type_` int(11) NOT NULL COMMENT '类型',
  `role_` int(11) NOT NULL COMMENT '管理员角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Table structure for table `manage_action` */

DROP TABLE IF EXISTS `manage_action`;

CREATE TABLE `manage_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_in5798c0hcjorv9jjfntqeujt` (`action_name`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Table structure for table `manage_form` */

DROP TABLE IF EXISTS `manage_form`;

CREATE TABLE `manage_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `in_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '填写时间',
  `username` varchar(255) NOT NULL COMMENT '执行人',
  `value_` text NOT NULL COMMENT '记录内容',
  `process_instance_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `manage_item` */

DROP TABLE IF EXISTS `manage_item`;

CREATE TABLE `manage_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_` varchar(50) NOT NULL,
  `order_` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4dqn9ifys1jwebh4uxgevgplo` (`name_`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `manage_position` */

DROP TABLE IF EXISTS `manage_position`;

CREATE TABLE `manage_position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `system_id` int(11) NOT NULL COMMENT '系统id',
  `character_` varchar(20) NOT NULL COMMENT '角色',
  `user_id` int(11) NOT NULL COMMENT '三员用户',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `in_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `del_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_elpi7dklotslgghc7dowhphyb` (`user_id`),
  CONSTRAINT `FK_elpi7dklotslgghc7dowhphyb` FOREIGN KEY (`user_id`) REFERENCES `sys_users` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='人、系统、角色定位表';

/*Table structure for table `manage_records` */

DROP TABLE IF EXISTS `manage_records`;

CREATE TABLE `manage_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `in_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '填写时间',
  `relation_id` int(11) NOT NULL COMMENT '项目与操作的关系id',
  `details_` text COMMENT '详细情况',
  `basis_` varchar(512) DEFAULT NULL COMMENT '依据',
  `record_time` datetime DEFAULT NULL COMMENT '记录发生的日期',
  `auth_id` int(11) NOT NULL COMMENT '权限表id',
  PRIMARY KEY (`id`),
  KEY `FK_n9plsw2hte3vh6n3sot3mwk26` (`auth_id`),
  CONSTRAINT `FK_n9plsw2hte3vh6n3sot3mwk26` FOREIGN KEY (`auth_id`) REFERENCES `manage_position` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Table structure for table `manage_relation` */

DROP TABLE IF EXISTS `manage_relation`;

CREATE TABLE `manage_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `action_id` int(11) NOT NULL,
  `type_` varchar(255) NOT NULL,
  `order_` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_item` (`item_id`,`action_id`,`type_`),
  KEY `FK_2iqd57xr4bxywjduieur2warc` (`action_id`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

/*Table structure for table `manufacturer` */

DROP TABLE IF EXISTS `manufacturer`;

CREATE TABLE `manufacturer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL COMMENT '厂商名称',
  `linkman` varchar(128) DEFAULT NULL COMMENT '联系人',
  `telephone` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `qualifications` varchar(255) DEFAULT NULL COMMENT '资质',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` varchar(255) DEFAULT NULL,
  `to_user` varchar(255) DEFAULT NULL,
  `content` text,
  `href` varchar(255) DEFAULT NULL,
  `is_read` tinyint(1) DEFAULT '0',
  `created_time` datetime DEFAULT NULL,
  `read_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1259 DEFAULT CHARSET=utf8;

/*Table structure for table `network_card` */

DROP TABLE IF EXISTS `network_card`;

CREATE TABLE `network_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_type` int(11) NOT NULL COMMENT '网卡类型',
  `mac_add` varchar(50) NOT NULL COMMENT 'mac地址',
  `remark` varchar(100) DEFAULT NULL COMMENT '说明',
  `server_id` int(11) DEFAULT NULL COMMENT '服务器',
  `internet_protocol` varchar(30) NOT NULL COMMENT 'IP',
  `subnet_mask` varchar(30) NOT NULL COMMENT '子网掩码',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='网卡';

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `created_time` datetime DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  `status_` varchar(50) DEFAULT '01',
  `process_instance_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `operation_records` */

DROP TABLE IF EXISTS `operation_records`;

CREATE TABLE `operation_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `operation_type` int(11) NOT NULL,
  `desc_` text NOT NULL COMMENT '操作描述',
  `in_while` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  `ip_address` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4095 DEFAULT CHARSET=utf8;

/*Table structure for table `persistent_logins` */

DROP TABLE IF EXISTS `persistent_logins`;

CREATE TABLE `persistent_logins` (
  `USERNAME` varchar(64) DEFAULT NULL,
  `SERIES` varchar(64) NOT NULL,
  `TOKEN` varchar(64) DEFAULT NULL,
  `LAST_USED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SERIES`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Spring Remember me';

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Table structure for table `person_revinfo` */

DROP TABLE IF EXISTS `person_revinfo`;

CREATE TABLE `person_revinfo` (
  `id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK_qlm61yhfbpr4svs7f5st5hhh3` (`REV`),
  CONSTRAINT `FK_qlm61yhfbpr4svs7f5st5hhh3` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `revinfo` */

DROP TABLE IF EXISTS `revinfo`;

CREATE TABLE `revinfo` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `server_software` */

DROP TABLE IF EXISTS `server_software`;

CREATE TABLE `server_software` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `server_id` int(11) NOT NULL,
  `software_id` int(11) NOT NULL,
  `install_date` datetime NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `servicer` */

DROP TABLE IF EXISTS `servicer`;

CREATE TABLE `servicer` (
  `num` varchar(50) NOT NULL DEFAULT '11111' COMMENT '服务器编号',
  `cpu` varchar(50) DEFAULT NULL COMMENT 'CPU',
  `memory` varchar(50) DEFAULT NULL COMMENT '内存',
  `cdrom` varchar(50) DEFAULT NULL COMMENT '光驱',
  `raid_card` varchar(50) DEFAULT NULL COMMENT 'raid卡',
  `raid_model` varchar(50) DEFAULT NULL COMMENT 'raid模式',
  `power_num` int(11) DEFAULT NULL COMMENT '电源数量',
  `id` int(11) DEFAULT NULL COMMENT '主键'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `software` */

DROP TABLE IF EXISTS `software`;

CREATE TABLE `software` (
  `id` int(11) NOT NULL,
  `num` varchar(50) NOT NULL COMMENT '软件编号',
  `name` varchar(50) NOT NULL COMMENT '软件名称',
  `versions` varchar(50) DEFAULT NULL COMMENT '版本',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Table structure for table `style_list` */

DROP TABLE IF EXISTS `style_list`;

CREATE TABLE `style_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `style_id` int(11) NOT NULL COMMENT 'styles表',
  `desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `styles` */

DROP TABLE IF EXISTS `styles`;

CREATE TABLE `styles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_` varchar(255) NOT NULL COMMENT '类型名',
  `desc_` varchar(255) DEFAULT NULL COMMENT '概述',
  `parent_id` int(11) DEFAULT NULL,
  `code_` varchar(10) DEFAULT NULL COMMENT '代号',
  `order_` int(11) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`),
  KEY `FK_rm7322xf8x6prpc56gbhxx0po` (`parent_id`)
) ENGINE=MyISAM AUTO_INCREMENT=278 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_authorities` */

DROP TABLE IF EXISTS `sys_authorities`;

CREATE TABLE `sys_authorities` (
  `AUTHORITY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUTHORITY_MARK` varchar(100) DEFAULT NULL,
  `AUTHORITY_NAME` varchar(100) NOT NULL,
  `AUTHORITY_DESC` varchar(200) DEFAULT NULL,
  `MESSAGE` varchar(100) DEFAULT NULL,
  `ENABLE` int(11) DEFAULT '1',
  `ISSYS` int(11) DEFAULT '1',
  `MODULE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`AUTHORITY_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Table structure for table `sys_authorities_resources` */

DROP TABLE IF EXISTS `sys_authorities_resources`;

CREATE TABLE `sys_authorities_resources` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RESOURCE_ID` int(11) NOT NULL,
  `AUTHORITY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_7n0wf51c7kbsfvtr19qy5p7nx` (`AUTHORITY_ID`),
  KEY `FK_c70f2qag35ebmw5nhs3qr3xbx` (`RESOURCE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=495 DEFAULT CHARSET=utf8 COMMENT='权限资源表';

/*Table structure for table `sys_code` */

DROP TABLE IF EXISTS `sys_code`;

CREATE TABLE `sys_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_` varchar(50) DEFAULT NULL,
  `code_name` varchar(50) DEFAULT NULL,
  `type_` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=245 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_group` */

DROP TABLE IF EXISTS `sys_group`;

CREATE TABLE `sys_group` (
  `id` int(11) NOT NULL,
  `group_name` varchar(255) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `remark_` varchar(255) DEFAULT NULL,
  `order_` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cpe22l4nto9hpqhl9qh3drnbf` (`parent_id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='用户组表';

/*Table structure for table `sys_modules` */

DROP TABLE IF EXISTS `sys_modules`;

CREATE TABLE `sys_modules` (
  `MODULE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MODULE_NAME` varchar(100) NOT NULL,
  `MODULE_DESC` varchar(200) DEFAULT NULL,
  `MODULE_TYPE` int(11) DEFAULT NULL,
  `PARENT` int(11) DEFAULT NULL,
  `MODULE_URL` varchar(100) DEFAULT NULL,
  `I_LEVEL` int(4) DEFAULT NULL,
  `LEAF` int(4) DEFAULT '1',
  `APPLICATION` varchar(100) DEFAULT NULL,
  `CONTROLLER` varchar(100) DEFAULT NULL,
  `ENABLE` int(1) DEFAULT '1',
  `PRIORITY` int(4) DEFAULT '1',
  PRIMARY KEY (`MODULE_ID`),
  UNIQUE KEY `UK_39h60w9j08ogr7jr7kyb6natj` (`MODULE_NAME`,`PARENT`),
  KEY `FK_ohfu43hjekojyb0icqyn113eg` (`PARENT`)
) ENGINE=MyISAM AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COMMENT='模块表，与菜单有关系';

/*Table structure for table `sys_resources` */

DROP TABLE IF EXISTS `sys_resources`;

CREATE TABLE `sys_resources` (
  `RESOURCE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `RESOURCE_TYPE` varchar(100) DEFAULT 'URL' COMMENT 'url, method',
  `RESOURCE_NAME` varchar(100) DEFAULT NULL,
  `RESOURCE_DESC` varchar(200) DEFAULT NULL,
  `RESOURCE_PATH` varchar(200) DEFAULT NULL,
  `PRIORITY` varchar(100) DEFAULT NULL,
  `ENABLE` int(4) DEFAULT '1',
  `ISSYS` int(4) DEFAULT '1',
  `MODULE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`),
  KEY `FK_b03c5vty62u32sd76foycw10b` (`MODULE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Table structure for table `sys_roles` */

DROP TABLE IF EXISTS `sys_roles`;

CREATE TABLE `sys_roles` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(100) NOT NULL,
  `ROLE_DESC` varchar(200) DEFAULT NULL,
  `ISENABLE` int(1) DEFAULT NULL,
  `ISSYS` int(1) DEFAULT NULL,
  `MODULE_ID` int(11) DEFAULT NULL,
  `REV_` int(11) DEFAULT '1',
  `TYPE_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `unique_role_name` (`ROLE_NAME`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Table structure for table `sys_roles_authorities` */

DROP TABLE IF EXISTS `sys_roles_authorities`;

CREATE TABLE `sys_roles_authorities` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUTHORITY_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_dkh9l7rjxrfg486vytc4wbx4x` (`AUTHORITY_ID`),
  KEY `FK_ef555l3uejwmxhxt0fjbl4bdu` (`ROLE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

/*Table structure for table `sys_roles_moudles` */

DROP TABLE IF EXISTS `sys_roles_moudles`;

CREATE TABLE `sys_roles_moudles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MODULE_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_7o7dhbi2e0j1wqo1chv6x0t1o` (`MODULE_ID`),
  KEY `FK_n989by7yrj3374ypuen52tivu` (`ROLE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1498 DEFAULT CHARSET=utf8 COMMENT='角色模块表——控制角色对模块的访问权，主要用于生成菜单';

/*Table structure for table `sys_users` */

DROP TABLE IF EXISTS `sys_users`;

CREATE TABLE `sys_users` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `DT_CREATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_LOGIN` timestamp NULL DEFAULT NULL,
  `DEADLINE` date DEFAULT NULL,
  `LOGIN_IP` varchar(100) DEFAULT NULL,
  `MECH_ID` int(11) DEFAULT NULL,
  `MECH_NAME` varchar(100) DEFAULT NULL,
  `DEP_ID` int(11) DEFAULT NULL,
  `DEP_NAME` varchar(100) DEFAULT NULL,
  `ENABLED` tinyint(1) DEFAULT NULL,
  `ACCOUNT_NON_EXPIRED` tinyint(1) DEFAULT NULL,
  `ACCOUNT_NON_LOCKED` tinyint(1) DEFAULT NULL,
  `CREDENTIALS_NON_EXPIRED` tinyint(1) DEFAULT NULL,
  `init_pwd` tinyint(1) DEFAULT '0',
  `GROUP_ID` int(11) NOT NULL DEFAULT '24',
  `REV_` int(11) DEFAULT '1',
  `LAST_` varchar(255) DEFAULT NULL,
  `EMAIL_` varchar(255) DEFAULT NULL,
  `PWD_` varchar(255) DEFAULT NULL,
  `PICTURE_ID_` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UK_lgllb14jd9kcm09b9enkkbh0q` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Table structure for table `sys_users_roles` */

DROP TABLE IF EXISTS `sys_users_roles`;

CREATE TABLE `sys_users_roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_laitchy0659so1fu7j7841p3s` (`ROLE_ID`),
  KEY `FK_2hdponpg7247h0udx3fmyg0p2` (`USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Table structure for table `wk_account` */

DROP TABLE IF EXISTS `wk_account`;

CREATE TABLE `wk_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(255) DEFAULT NULL COMMENT '台账名称',
  `category` varchar(50) DEFAULT NULL COMMENT '配置项类别',
  `fields` varchar(500) DEFAULT NULL COMMENT '字段',
  `properties` varchar(500) DEFAULT NULL COMMENT '属性',
  `views_` varchar(2000) DEFAULT NULL COMMENT '页面要显示的属性',
  `created_user` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_change` */

DROP TABLE IF EXISTS `wk_change`;

CREATE TABLE `wk_change` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL COMMENT '变更描述',
  `apply_user` varchar(255) DEFAULT NULL COMMENT '申请人',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `change_type` varchar(50) DEFAULT NULL COMMENT '变更分类',
  `solution` varchar(500) DEFAULT NULL COMMENT '变更方案',
  `fallback` varchar(500) DEFAULT NULL COMMENT '回退方案',
  `plan_start_time` datetime DEFAULT NULL COMMENT '变更开始时间',
  `plan_end_time` datetime DEFAULT NULL COMMENT '变更结束时间',
  `template` varchar(255) DEFAULT NULL,
  `template_data` varchar(500) DEFAULT NULL,
  `influence` varchar(50) DEFAULT NULL COMMENT '影响度',
  `critical` varchar(50) DEFAULT NULL COMMENT '紧急性',
  `priority` varchar(50) DEFAULT NULL COMMENT '优先级',
  `risk` varchar(50) DEFAULT NULL COMMENT '风险等级',
  `status_` varchar(50) DEFAULT NULL COMMENT '状态',
  `attachment` varchar(255) DEFAULT NULL,
  `delegate_user` varchar(255) DEFAULT NULL COMMENT '变更实施人',
  `approve` varchar(255) DEFAULT NULL COMMENT '变更审批记录',
  `result` varchar(255) DEFAULT NULL COMMENT '变更结果',
  `process_instance_id` varchar(255) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL COMMENT '变更结束时间',
  `endbyuser` tinyint(1) DEFAULT '0' COMMENT '是否正常关闭',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_change_item` */

DROP TABLE IF EXISTS `wk_change_item`;

CREATE TABLE `wk_change_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `change_id` bigint(20) DEFAULT NULL,
  `ci_id` bigint(20) DEFAULT NULL COMMENT '配置项id',
  `properties_name` varchar(500) DEFAULT NULL COMMENT '属性名称',
  `properties` varchar(500) DEFAULT NULL COMMENT '配置项属性列表',
  `old_value` text COMMENT '属性原值',
  `new_value` text COMMENT '属性新值',
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2s0mnwv50t384gdi425li2vlm` (`change_id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_event` */

DROP TABLE IF EXISTS `wk_event`;

CREATE TABLE `wk_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abstract` varchar(255) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `apply_user` varchar(255) DEFAULT NULL,
  `attachment` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `critical` varchar(255) DEFAULT NULL,
  `current_delegate_group` varchar(255) DEFAULT NULL,
  `current_delegate_user` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `influence` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `process_instance_id` varchar(255) DEFAULT NULL,
  `recover_time` datetime DEFAULT NULL,
  `solution` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `status_` varchar(255) DEFAULT NULL,
  `template` varchar(255) DEFAULT NULL,
  `template_data` varchar(255) DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `wk_feedback` */

DROP TABLE IF EXISTS `wk_feedback`;

CREATE TABLE `wk_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_user` varchar(255) DEFAULT NULL COMMENT '事件请求人',
  `detail_` varchar(2000) NOT NULL COMMENT '反馈意见',
  `priority_` varchar(20) DEFAULT NULL COMMENT '优先级',
  `state_` varchar(20) DEFAULT NULL COMMENT '状态',
  `type_` varchar(20) DEFAULT NULL COMMENT '分类',
  `completion` varchar(2000) DEFAULT NULL COMMENT '完成情况',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `execution_time` datetime DEFAULT NULL COMMENT '完成时间',
  `process_instance_id` varchar(255) DEFAULT NULL,
  `status_` varchar(255) DEFAULT NULL,
  `endbyuser` tinyint(1) DEFAULT '0' COMMENT '是否正常关闭',
  `current_delegate_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_incident` */

DROP TABLE IF EXISTS `wk_incident`;

CREATE TABLE `wk_incident` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apply_user` varchar(255) DEFAULT NULL COMMENT '事件请求人',
  `phone_number` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `abstract` varchar(255) DEFAULT NULL COMMENT '摘要',
  `detail` varchar(500) DEFAULT NULL COMMENT '详细描述',
  `template` varchar(255) DEFAULT NULL COMMENT '事件模板',
  `template_data` varchar(500) DEFAULT NULL COMMENT '事件模板数据',
  `apply_time` datetime DEFAULT NULL COMMENT '报告时间',
  `source` varchar(50) DEFAULT NULL COMMENT '事件来源',
  `influence` varchar(50) DEFAULT NULL COMMENT '影响度',
  `support_type` varchar(10) DEFAULT NULL COMMENT '支持类型',
  `critical` varchar(50) DEFAULT NULL COMMENT '紧急度',
  `priority` varchar(50) DEFAULT NULL COMMENT '优先级',
  `finish_time` datetime DEFAULT NULL COMMENT '要求完成时间',
  `type_` varchar(50) DEFAULT NULL COMMENT '事件类型',
  `category` varchar(50) DEFAULT NULL COMMENT '事件分类',
  `current_delegate_group` varchar(255) DEFAULT NULL COMMENT '受派组',
  `current_delegate_user` varchar(255) DEFAULT NULL COMMENT '受派人',
  `status_` varchar(50) DEFAULT NULL COMMENT '状态',
  `solution` varchar(255) DEFAULT NULL COMMENT '解决方案',
  `recover_time` datetime DEFAULT NULL COMMENT '恢复时间',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件',
  `process_instance_id` varchar(255) DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  `satisfaction` varchar(50) DEFAULT '00' COMMENT '满意度',
  `feedback` varchar(255) DEFAULT NULL COMMENT '反馈意见',
  `endbyuser` tinyint(1) DEFAULT '0' COMMENT '是否正常关闭',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_income` */

DROP TABLE IF EXISTS `wk_income`;

CREATE TABLE `wk_income` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `person_count` int(11) DEFAULT NULL COMMENT '人数',
  `person_name` varchar(255) DEFAULT NULL COMMENT '人名',
  `person_of_company` varchar(255) DEFAULT NULL COMMENT '单位',
  `accompany` varchar(255) DEFAULT NULL COMMENT '陪同人员',
  `in_time` datetime DEFAULT NULL COMMENT '进入时间',
  `out_time` datetime DEFAULT NULL COMMENT '离开时间',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_user` varchar(255) DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_inspection` */

DROP TABLE IF EXISTS `wk_inspection`;

CREATE TABLE `wk_inspection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `template` varchar(255) DEFAULT NULL COMMENT '模板',
  `template_data` text COMMENT '模板数据',
  `execution_user` varchar(255) DEFAULT NULL COMMENT '执行人',
  `execution_time` datetime DEFAULT NULL COMMENT '执行时间',
  `status_` varchar(50) DEFAULT NULL,
  `process_instance_id` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL COMMENT '发起时间',
  `incident_id` bigint(20) DEFAULT NULL COMMENT '事件单号',
  `endbyuser` tinyint(1) DEFAULT '0' COMMENT '是否正常关闭',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_itil_relation` */

DROP TABLE IF EXISTS `wk_itil_relation`;

CREATE TABLE `wk_itil_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `primary_id` bigint(20) DEFAULT NULL,
  `secondary_id` bigint(20) DEFAULT NULL,
  `relation_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_knowledge` */

DROP TABLE IF EXISTS `wk_knowledge`;

CREATE TABLE `wk_knowledge` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `desc_` varchar(255) DEFAULT NULL,
  `solution` text,
  `keyword` varchar(255) DEFAULT NULL,
  `apply_user` varchar(255) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `status_` varchar(50) DEFAULT NULL,
  `is_locked` tinyint(1) DEFAULT NULL,
  `process_instance_id` varchar(255) DEFAULT NULL,
  `hits` bigint(20) DEFAULT NULL COMMENT '点击数',
  `last_read_time` datetime DEFAULT NULL COMMENT '最后阅读时间',
  `update_time` datetime DEFAULT NULL,
  `endbyuser` tinyint(1) DEFAULT '0' COMMENT '是否正常关闭',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_sec_job` */

DROP TABLE IF EXISTS `wk_sec_job`;

CREATE TABLE `wk_sec_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_` varchar(50) DEFAULT NULL COMMENT '三员工作类别',
  `user_id` varchar(255) DEFAULT NULL COMMENT '分配用户',
  `attachment` varchar(255) DEFAULT NULL COMMENT '附件',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `execution_time` datetime DEFAULT NULL COMMENT '完成时间',
  `process_instance_id` varchar(255) DEFAULT NULL,
  `endbyuser` tinyint(1) DEFAULT '0' COMMENT '是否正常关闭',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_task` */

DROP TABLE IF EXISTS `wk_task`;

CREATE TABLE `wk_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user` varchar(255) DEFAULT NULL COMMENT '交办领导',
  `to_user` varchar(255) DEFAULT NULL COMMENT '交办对象',
  `task_title` varchar(255) DEFAULT NULL COMMENT '任务标题',
  `task_content` varchar(4000) DEFAULT NULL COMMENT '任务内容',
  `task_result` varchar(4000) DEFAULT NULL COMMENT '工作结果',
  `process_instance_id` varchar(255) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `due_time` datetime DEFAULT NULL COMMENT '到期时间',
  `user_id` varchar(255) DEFAULT NULL,
  `execution_time` datetime DEFAULT NULL COMMENT '执行完成时间',
  `current_activity_id` varchar(255) DEFAULT NULL,
  `current_activity_name` varchar(255) DEFAULT NULL,
  `task_desc` varchar(255) DEFAULT NULL,
  `endbyuser` tinyint(1) DEFAULT '0' COMMENT '是否正常关闭',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_training` */

DROP TABLE IF EXISTS `wk_training`;

CREATE TABLE `wk_training` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户',
  `company_` varchar(255) DEFAULT NULL COMMENT '培训单位',
  `certification` varchar(255) DEFAULT NULL COMMENT '资格证书',
  `certification_no` varchar(255) DEFAULT NULL COMMENT '证书编号',
  `expiry_date` datetime DEFAULT NULL COMMENT '有效期',
  `created_time` datetime DEFAULT NULL,
  `created_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_update` */

DROP TABLE IF EXISTS `wk_update`;

CREATE TABLE `wk_update` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_` varchar(255) DEFAULT NULL,
  `version_` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `process_instance_id` varchar(255) DEFAULT NULL,
  `execution_time` datetime DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `name_` varchar(255) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `endbyuser` tinyint(1) DEFAULT '0' COMMENT '是否正常关闭',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Table structure for table `wk_update_record` */

DROP TABLE IF EXISTS `wk_update_record`;

CREATE TABLE `wk_update_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(255) DEFAULT NULL,
  `process_instance_id` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `version_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `act_id_group` */

DROP TABLE IF EXISTS `act_id_group`;

/*!50001 DROP VIEW IF EXISTS `act_id_group` */;
/*!50001 DROP TABLE IF EXISTS `act_id_group` */;

/*!50001 CREATE TABLE  `act_id_group`(
 `ID_` varchar(100) ,
 `REV_` int(11) ,
 `NAME_` varchar(200) ,
 `TYPE_` varchar(255) 
)*/;

/*Table structure for table `act_id_membership` */

DROP TABLE IF EXISTS `act_id_membership`;

/*!50001 DROP VIEW IF EXISTS `act_id_membership` */;
/*!50001 DROP TABLE IF EXISTS `act_id_membership` */;

/*!50001 CREATE TABLE  `act_id_membership`(
 `USER_ID_` varchar(100) ,
 `GROUP_ID_` varchar(100) 
)*/;

/*Table structure for table `act_id_user` */

DROP TABLE IF EXISTS `act_id_user`;

/*!50001 DROP VIEW IF EXISTS `act_id_user` */;
/*!50001 DROP TABLE IF EXISTS `act_id_user` */;

/*!50001 CREATE TABLE  `act_id_user`(
 `ID_` varchar(100) ,
 `REV_` int(11) ,
 `FIRST_` varchar(100) ,
 `LAST_` varchar(255) ,
 `EMAIL_` varchar(255) ,
 `PWD_` varchar(255) ,
 `PICTURE_ID_` varchar(64) 
)*/;

/*View structure for view act_id_group */

/*!50001 DROP TABLE IF EXISTS `act_id_group` */;
/*!50001 DROP VIEW IF EXISTS `act_id_group` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `act_id_group` AS (select `sys_roles`.`ROLE_NAME` AS `ID_`,`sys_roles`.`REV_` AS `REV_`,`sys_roles`.`ROLE_DESC` AS `NAME_`,`sys_roles`.`TYPE_` AS `TYPE_` from `sys_roles`) */;

/*View structure for view act_id_membership */

/*!50001 DROP TABLE IF EXISTS `act_id_membership` */;
/*!50001 DROP VIEW IF EXISTS `act_id_membership` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `act_id_membership` AS (select `sys_users`.`USERNAME` AS `USER_ID_`,`sys_roles`.`ROLE_NAME` AS `GROUP_ID_` from ((`sys_users_roles` left join `sys_roles` on((`sys_users_roles`.`ROLE_ID` = `sys_roles`.`ROLE_ID`))) left join `sys_users` on((`sys_users_roles`.`USER_ID` = `sys_users`.`USER_ID`)))) */;

/*View structure for view act_id_user */

/*!50001 DROP TABLE IF EXISTS `act_id_user` */;
/*!50001 DROP VIEW IF EXISTS `act_id_user` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `act_id_user` AS (select `sys_users`.`USERNAME` AS `ID_`,`sys_users`.`REV_` AS `REV_`,`sys_users`.`NAME` AS `FIRST_`,`sys_users`.`LAST_` AS `LAST_`,`sys_users`.`EMAIL_` AS `EMAIL_`,`sys_users`.`PWD_` AS `PWD_`,`sys_users`.`PICTURE_ID_` AS `PICTURE_ID_` from `sys_users`) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
