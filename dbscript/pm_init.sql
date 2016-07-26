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
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pmsys_basic` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `pmsys_basic`;

/*Table structure for table `act_evt_log` */

DROP TABLE IF EXISTS `act_evt_log`;

CREATE TABLE `act_evt_log` (
  `LOG_NR_` bigint(20) NOT NULL auto_increment,
  `TYPE_` varchar(64) collate utf8_bin default NULL,
  `PROC_DEF_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_INST_ID_` varchar(64) collate utf8_bin default NULL,
  `EXECUTION_ID_` varchar(64) collate utf8_bin default NULL,
  `TASK_ID_` varchar(64) collate utf8_bin default NULL,
  `TIME_STAMP_` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `USER_ID_` varchar(255) collate utf8_bin default NULL,
  `DATA_` longblob,
  `LOCK_OWNER_` varchar(255) collate utf8_bin default NULL,
  `LOCK_TIME_` timestamp NULL default NULL,
  `IS_PROCESSED_` tinyint(4) default '0',
  PRIMARY KEY  (`LOG_NR_`)
) ENGINE=MyISAM AUTO_INCREMENT=4798 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


/*Table structure for table `act_ge_bytearray` */

DROP TABLE IF EXISTS `act_ge_bytearray`;

CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) collate utf8_bin NOT NULL default '',
  `REV_` int(11) default NULL,
  `NAME_` varchar(255) collate utf8_bin default NULL,
  `DEPLOYMENT_ID_` varchar(64) collate utf8_bin default NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) default NULL,
  PRIMARY KEY  (`ID_`),
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


/*Table structure for table `act_ge_property` */

DROP TABLE IF EXISTS `act_ge_property`;

CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) collate utf8_bin NOT NULL default '',
  `VALUE_` varchar(300) collate utf8_bin default NULL,
  `REV_` int(11) default NULL,
  PRIMARY KEY  (`NAME_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ge_property` */

insert  into `act_ge_property`(`NAME_`,`VALUE_`,`REV_`) values ('next.dbid','400001',161),('schema.history','create(5.16)',1),('schema.version','5.16',1);

/*Table structure for table `act_hi_actinst` */

DROP TABLE IF EXISTS `act_hi_actinst`;

CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) NOT NULL,
  `PROC_DEF_ID_` varchar(64) NOT NULL,
  `PROC_INST_ID_` varchar(64) NOT NULL,
  `EXECUTION_ID_` varchar(64) NOT NULL,
  `ACT_ID_` varchar(255) NOT NULL,
  `TASK_ID_` varchar(64) default NULL,
  `CALL_PROC_INST_ID_` varchar(64) default NULL,
  `ACT_NAME_` varchar(255) default NULL,
  `ACT_TYPE_` varchar(255) NOT NULL,
  `ASSIGNEE_` varchar(255) default NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime default NULL,
  `DURATION_` decimal(19,0) default NULL,
  `TENANT_ID_` varchar(255) default '',
  PRIMARY KEY  (`ID_`),
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`),
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `act_hi_attachment` */

DROP TABLE IF EXISTS `act_hi_attachment`;

CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) NOT NULL,
  `REV_` int(11) default NULL,
  `USER_ID_` varchar(255) default NULL,
  `NAME_` varchar(255) default NULL,
  `DESCRIPTION_` varchar(4000) default NULL,
  `TYPE_` varchar(255) default NULL,
  `TASK_ID_` varchar(64) default NULL,
  `PROC_INST_ID_` varchar(64) default NULL,
  `URL_` varchar(4000) default NULL,
  `CONTENT_ID_` varchar(64) default NULL,
  `TIME_` datetime default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `act_hi_attachment` */

/*Table structure for table `act_hi_comment` */

DROP TABLE IF EXISTS `act_hi_comment`;

CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) collate utf8_bin NOT NULL,
  `TYPE_` varchar(255) collate utf8_bin default NULL,
  `TIME_` datetime NOT NULL,
  `USER_ID_` varchar(255) collate utf8_bin default NULL,
  `TASK_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_INST_ID_` varchar(64) collate utf8_bin default NULL,
  `ACTION_` varchar(255) collate utf8_bin default NULL,
  `MESSAGE_` varchar(4000) collate utf8_bin default NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY  (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `act_hi_detail` */

DROP TABLE IF EXISTS `act_hi_detail`;

CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) NOT NULL,
  `TYPE_` varchar(255) NOT NULL,
  `PROC_INST_ID_` varchar(64) default NULL,
  `EXECUTION_ID_` varchar(64) default NULL,
  `TASK_ID_` varchar(64) default NULL,
  `ACT_INST_ID_` varchar(64) default NULL,
  `NAME_` varchar(255) NOT NULL,
  `VAR_TYPE_` varchar(255) default NULL,
  `REV_` int(11) default NULL,
  `TIME_` datetime NOT NULL,
  `BYTEARRAY_ID_` varchar(64) default NULL,
  `DOUBLE_` double default NULL,
  `LONG_` decimal(19,0) default NULL,
  `TEXT_` varchar(4000) default NULL,
  `TEXT2_` varchar(4000) default NULL,
  PRIMARY KEY  (`ID_`),
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`),
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`),
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`),
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `act_hi_detail` */

/*Table structure for table `act_hi_identitylink` */

DROP TABLE IF EXISTS `act_hi_identitylink`;

CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) NOT NULL default '',
  `GROUP_ID_` varchar(255) default NULL,
  `TYPE_` varchar(255) default NULL,
  `USER_ID_` varchar(255) default NULL,
  `TASK_ID_` varchar(64) default NULL,
  `PROC_INST_ID_` varchar(64) default NULL,
  PRIMARY KEY  (`ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`),
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `act_hi_identitylink` */

/*Table structure for table `act_hi_procinst` */

DROP TABLE IF EXISTS `act_hi_procinst`;

CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) NOT NULL,
  `PROC_INST_ID_` varchar(64) NOT NULL,
  `BUSINESS_KEY_` varchar(255) default NULL,
  `PROC_DEF_ID_` varchar(64) NOT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime default NULL,
  `DURATION_` decimal(19,0) default NULL,
  `START_USER_ID_` varchar(255) default NULL,
  `START_ACT_ID_` varchar(255) default NULL,
  `END_ACT_ID_` varchar(255) default NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) default NULL,
  `DELETE_REASON_` varchar(4000) default NULL,
  `TENANT_ID_` varchar(255) default '',
  `NAME_` varchar(255) default NULL,
  PRIMARY KEY  (`ID_`),
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`),
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `act_hi_procinst` */

/*Table structure for table `act_hi_taskinst` */

DROP TABLE IF EXISTS `act_hi_taskinst`;

CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) NOT NULL,
  `PROC_DEF_ID_` varchar(64) default NULL,
  `TASK_DEF_KEY_` varchar(255) default NULL,
  `PROC_INST_ID_` varchar(64) default NULL,
  `EXECUTION_ID_` varchar(64) default NULL,
  `NAME_` varchar(255) default NULL,
  `PARENT_TASK_ID_` varchar(64) default NULL,
  `DESCRIPTION_` varchar(4000) default NULL,
  `OWNER_` varchar(255) default NULL,
  `ASSIGNEE_` varchar(255) default NULL,
  `START_TIME_` datetime NOT NULL,
  `CLAIM_TIME_` datetime default NULL,
  `END_TIME_` datetime default NULL,
  `DURATION_` decimal(19,0) default NULL,
  `DELETE_REASON_` varchar(4000) default NULL,
  `PRIORITY_` int(11) default NULL,
  `DUE_DATE_` datetime default NULL,
  `FORM_KEY_` varchar(255) default NULL,
  `CATEGORY_` varchar(255) default NULL,
  `TENANT_ID_` varchar(255) default '',
  PRIMARY KEY  (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `act_hi_taskinst` */

/*Table structure for table `act_hi_varinst` */

DROP TABLE IF EXISTS `act_hi_varinst`;

CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) NOT NULL,
  `PROC_INST_ID_` varchar(64) default NULL,
  `EXECUTION_ID_` varchar(64) default NULL,
  `TASK_ID_` varchar(64) default NULL,
  `NAME_` varchar(255) NOT NULL,
  `VAR_TYPE_` varchar(100) default NULL,
  `REV_` int(11) default NULL,
  `BYTEARRAY_ID_` varchar(64) default NULL,
  `DOUBLE_` double default NULL,
  `LONG_` decimal(19,0) default NULL,
  `TEXT_` varchar(4000) default NULL,
  `TEXT2_` varchar(4000) default NULL,
  `CREATE_TIME_` datetime default NULL,
  `LAST_UPDATED_TIME_` datetime default NULL,
  PRIMARY KEY  (`ID_`),
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`),
  KEY `ACT_IDX_HI_PROCVAR_TASK_ID` (`TASK_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `act_hi_varinst` */

/*Table structure for table `act_id_info` */

DROP TABLE IF EXISTS `act_id_info`;

CREATE TABLE `act_id_info` (
  `ID_` varchar(64) collate utf8_bin NOT NULL default '',
  `REV_` int(11) default NULL,
  `USER_ID_` varchar(64) collate utf8_bin default NULL,
  `TYPE_` varchar(64) collate utf8_bin default NULL,
  `KEY_` varchar(255) collate utf8_bin default NULL,
  `VALUE_` varchar(255) collate utf8_bin default NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) collate utf8_bin default NULL,
  PRIMARY KEY  (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_id_info` */

/*Table structure for table `act_re_deployment` */

DROP TABLE IF EXISTS `act_re_deployment`;

CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) collate utf8_bin NOT NULL default '',
  `NAME_` varchar(255) collate utf8_bin default NULL,
  `CATEGORY_` varchar(255) collate utf8_bin default NULL,
  `TENANT_ID_` varchar(255) collate utf8_bin default '',
  `DEPLOY_TIME_` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_deployment` */

/*Table structure for table `act_re_model` */

DROP TABLE IF EXISTS `act_re_model`;

CREATE TABLE `act_re_model` (
  `ID_` varchar(64) collate utf8_bin NOT NULL,
  `REV_` int(11) default NULL,
  `NAME_` varchar(255) collate utf8_bin default NULL,
  `KEY_` varchar(255) collate utf8_bin default NULL,
  `CATEGORY_` varchar(255) collate utf8_bin default NULL,
  `CREATE_TIME_` timestamp NULL default NULL,
  `LAST_UPDATE_TIME_` timestamp NULL default NULL,
  `VERSION_` int(11) default NULL,
  `META_INFO_` varchar(4000) collate utf8_bin default NULL,
  `DEPLOYMENT_ID_` varchar(64) collate utf8_bin default NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) collate utf8_bin default NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) collate utf8_bin default NULL,
  `TENANT_ID_` varchar(255) collate utf8_bin default '',
  PRIMARY KEY  (`ID_`),
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`),
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`),
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_model` */

/*Table structure for table `act_re_procdef` */

DROP TABLE IF EXISTS `act_re_procdef`;

CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) collate utf8_bin NOT NULL,
  `REV_` int(11) default NULL,
  `CATEGORY_` varchar(255) collate utf8_bin default NULL,
  `NAME_` varchar(255) collate utf8_bin default NULL,
  `KEY_` varchar(255) collate utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) collate utf8_bin default NULL,
  `RESOURCE_NAME_` varchar(4000) collate utf8_bin default NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) collate utf8_bin default NULL,
  `DESCRIPTION_` varchar(4000) collate utf8_bin default NULL,
  `HAS_START_FORM_KEY_` tinyint(4) default NULL,
  `HAS_GRAPHICAL_NOTATION_` tinyint(4) default NULL,
  `SUSPENSION_STATE_` int(11) default NULL,
  `TENANT_ID_` varchar(255) collate utf8_bin default '',
  PRIMARY KEY  (`ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_re_procdef` */

/*Table structure for table `act_ru_event_subscr` */

DROP TABLE IF EXISTS `act_ru_event_subscr`;

CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) collate utf8_bin NOT NULL,
  `REV_` int(11) default NULL,
  `EVENT_TYPE_` varchar(255) collate utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) collate utf8_bin default NULL,
  `EXECUTION_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_INST_ID_` varchar(64) collate utf8_bin default NULL,
  `ACTIVITY_ID_` varchar(64) collate utf8_bin default NULL,
  `CONFIGURATION_` varchar(255) collate utf8_bin default NULL,
  `CREATED_` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `PROC_DEF_ID_` varchar(64) collate utf8_bin default NULL,
  `TENANT_ID_` varchar(255) collate utf8_bin default '',
  PRIMARY KEY  (`ID_`),
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`),
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_event_subscr` */

/*Table structure for table `act_ru_execution` */

DROP TABLE IF EXISTS `act_ru_execution`;

CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) collate utf8_bin NOT NULL default '',
  `REV_` int(11) default NULL,
  `PROC_INST_ID_` varchar(64) collate utf8_bin default NULL,
  `BUSINESS_KEY_` varchar(255) collate utf8_bin default NULL,
  `PARENT_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_DEF_ID_` varchar(64) collate utf8_bin default NULL,
  `SUPER_EXEC_` varchar(64) collate utf8_bin default NULL,
  `ACT_ID_` varchar(255) collate utf8_bin default NULL,
  `IS_ACTIVE_` tinyint(4) default NULL,
  `IS_CONCURRENT_` tinyint(4) default NULL,
  `IS_SCOPE_` tinyint(4) default NULL,
  `IS_EVENT_SCOPE_` tinyint(4) default NULL,
  `SUSPENSION_STATE_` int(11) default NULL,
  `CACHED_ENT_STATE_` int(11) default NULL,
  `TENANT_ID_` varchar(255) collate utf8_bin default '',
  `NAME_` varchar(255) collate utf8_bin default NULL,
  `LOCK_TIME_` timestamp NULL default NULL,
  PRIMARY KEY  (`ID_`),
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`),
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`),
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`),
  KEY `ACT_FK_EXE_PROCDEF` (`PROC_DEF_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_execution` */

/*Table structure for table `act_ru_identitylink` */

DROP TABLE IF EXISTS `act_ru_identitylink`;

CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) collate utf8_bin NOT NULL default '',
  `REV_` int(11) default NULL,
  `GROUP_ID_` varchar(255) collate utf8_bin default NULL,
  `TYPE_` varchar(255) collate utf8_bin default NULL,
  `USER_ID_` varchar(255) collate utf8_bin default NULL,
  `TASK_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_INST_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_DEF_ID_` varchar(64) collate utf8_bin default NULL,
  PRIMARY KEY  (`ID_`),
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`),
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`),
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`),
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`),
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_identitylink` */

/*Table structure for table `act_ru_job` */

DROP TABLE IF EXISTS `act_ru_job`;

CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) collate utf8_bin NOT NULL,
  `REV_` int(11) default NULL,
  `TYPE_` varchar(255) collate utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp NULL default NULL,
  `LOCK_OWNER_` varchar(255) collate utf8_bin default NULL,
  `EXCLUSIVE_` tinyint(1) default NULL,
  `EXECUTION_ID_` varchar(64) collate utf8_bin default NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_DEF_ID_` varchar(64) collate utf8_bin default NULL,
  `RETRIES_` int(11) default NULL,
  `EXCEPTION_STACK_ID_` varchar(64) collate utf8_bin default NULL,
  `EXCEPTION_MSG_` varchar(4000) collate utf8_bin default NULL,
  `DUEDATE_` timestamp NULL default NULL,
  `REPEAT_` varchar(255) collate utf8_bin default NULL,
  `HANDLER_TYPE_` varchar(255) collate utf8_bin default NULL,
  `HANDLER_CFG_` varchar(4000) collate utf8_bin default NULL,
  `TENANT_ID_` varchar(255) collate utf8_bin default '',
  PRIMARY KEY  (`ID_`),
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_job` */

/*Table structure for table `act_ru_task` */

DROP TABLE IF EXISTS `act_ru_task`;

CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) collate utf8_bin NOT NULL default '',
  `REV_` int(11) default NULL,
  `EXECUTION_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_INST_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_DEF_ID_` varchar(64) collate utf8_bin default NULL,
  `NAME_` varchar(255) collate utf8_bin default NULL,
  `PARENT_TASK_ID_` varchar(64) collate utf8_bin default NULL,
  `DESCRIPTION_` varchar(4000) collate utf8_bin default NULL,
  `TASK_DEF_KEY_` varchar(255) collate utf8_bin default NULL,
  `OWNER_` varchar(255) collate utf8_bin default NULL,
  `ASSIGNEE_` varchar(255) collate utf8_bin default NULL,
  `DELEGATION_` varchar(64) collate utf8_bin default NULL,
  `PRIORITY_` int(11) default NULL,
  `CREATE_TIME_` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `DUE_DATE_` datetime default NULL,
  `CATEGORY_` varchar(255) collate utf8_bin default NULL,
  `SUSPENSION_STATE_` int(11) default NULL,
  `TENANT_ID_` varchar(255) collate utf8_bin default '',
  `FORM_KEY_` varchar(255) collate utf8_bin default NULL,
  PRIMARY KEY  (`ID_`),
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`),
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_task` */

/*Table structure for table `act_ru_variable` */

DROP TABLE IF EXISTS `act_ru_variable`;

CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) collate utf8_bin NOT NULL,
  `REV_` int(11) default NULL,
  `TYPE_` varchar(255) collate utf8_bin NOT NULL,
  `NAME_` varchar(255) collate utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) collate utf8_bin default NULL,
  `PROC_INST_ID_` varchar(64) collate utf8_bin default NULL,
  `TASK_ID_` varchar(64) collate utf8_bin default NULL,
  `BYTEARRAY_ID_` varchar(64) collate utf8_bin default NULL,
  `DOUBLE_` double default NULL,
  `LONG_` bigint(20) default NULL,
  `TEXT_` varchar(4000) collate utf8_bin default NULL,
  `TEXT2_` varchar(4000) collate utf8_bin default NULL,
  PRIMARY KEY  (`ID_`),
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`),
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`),
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`),
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `act_ru_variable` */

/*Table structure for table `attachment` */

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `thumbnailFilename` varchar(255) default NULL,
  `newFilename` varchar(255) default NULL,
  `contentType` varchar(255) default NULL,
  `size_` int(11) default NULL,
  `thumbnailSize` int(11) default NULL,
  `dateCreated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `lastUpdated` timestamp NOT NULL default '0000-00-00 00:00:00',
  `type_` int(11) default NULL,
  `type_id` int(11) default NULL,
  `path_` varchar(255) default NULL,
  `content_type` varchar(255) default NULL,
  `date_created` datetime default NULL,
  `last_updated` datetime default NULL,
  `new_filename` varchar(255) default NULL,
  `thumbnail_filename` varchar(255) default NULL,
  `thumbnail_size` bigint(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_abpc1wrf8notq4f3nclwvn0nb` (`type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=384 DEFAULT CHARSET=utf8;

/*Data for the table `attachment` */

/*Table structure for table `check_item` */

DROP TABLE IF EXISTS `check_item`;

CREATE TABLE `check_item` (
  `id` int(11) NOT NULL auto_increment,
  `item_id` int(11) default NULL,
  `name_` varchar(255) default NULL,
  `demand` text COMMENT '评测要求',
  `technique` text COMMENT '测评方法',
  `base_` tinyint(1) default NULL COMMENT '是否是基本测评项',
  `record_` text COMMENT '相关记录',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Data for the table `check_item` */

/*Table structure for table `cms_category` */

DROP TABLE IF EXISTS `cms_category`;

CREATE TABLE `cms_category` (
  `category_code` varchar(50) default NULL COMMENT '分类编码',
  `category_name` varchar(255) default NULL COMMENT '分类名'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `cms_category` */

insert  into `cms_category`(`category_code`,`category_name`) values ('01','服务器'),('02','存储备份'),('03','网络'),('0101','小型机'),('0102','PC服务器'),('0201','磁带库'),('0202','磁盘阵列'),('0204','其他存储设备'),('04','终端'),('0401','台式机'),('040103','堡垒机'),('0301','网络设备'),('0302','网管软件'),('030101','交换机'),('030102','路由器'),('040106','试验机'),('040104','中间机'),('040105','互联网台式机'),('06','存储介质'),('0601','U盘'),('0602','光盘'),('0603','软盘'),('0604','录音带'),('0605','录像带'),('0606','移动硬盘'),('0607','存储卡'),('0608','数据磁带'),('07','移动光驱'),('08','办公自动化设备'),('0801','打印机'),('0802','复印机'),('0803','传真机'),('09','安全产品'),('0901','网络防护设备'),('0902','终端防护产品'),('0903','保密检查工具'),('0904','加密设备'),('090101','防火墙'),('090102','入侵检测系统'),('090103','漏洞扫描系统'),('090201','防水墙'),('090202','防病毒'),('090203','三合一'),('090204','补丁分发'),('090205','终端身份认证'),('090206','打印审计'),('090401','链路加密机'),('090402','网络加密机'),('10','身份认证设备'),('11','机房设备'),('1101','UPS电源'),('1102','空调系统'),('1103','门禁系统'),('1104','消防系统'),('1105','监控系统'),('1106','KVM'),('12','应用系统'),('1201','综合办公网'),('1202','保密管理系统'),('1203','运维管理系统'),('13','文档'),('1301','管理文档'),('1302','技术文档'),('1303','维护文档'),('1304','工程文档'),('1305','合同'),('130501','产品购买合同'),('130502','维护合同'),('0402','便携式计算机'),('05','软件'),('0501','应用软件'),('0502','系统软件'),('050101','办公软件'),('050102','专用软件'),('050103','其他'),('050201','操作系统'),('050202','数据库'),('050203','中间件'),('050204','其他'),('0203','备份系统'),('040101','涉密台式机'),('040102','非密台式机');

/*Table structure for table `cms_category_property` */

DROP TABLE IF EXISTS `cms_category_property`;

CREATE TABLE `cms_category_property` (
  `id` bigint(20) NOT NULL auto_increment,
  `category_code` varchar(50) default NULL,
  `property_id` bigint(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

/*Data for the table `cms_category_property` */

insert  into `cms_category_property`(`id`,`category_code`,`property_id`) values (4,'0103',10),(5,'0103',9),(10,'0102',6),(11,'0102',10),(12,'0102',5),(13,'0102',7),(14,'0102',11),(20,'0201',9),(21,'0201',7),(22,'020101',10),(23,'020101',13),(24,'02',11),(25,'02',5),(31,'01',16),(32,'01',13),(33,'01',12),(34,'03',9),(35,'0301',10),(36,'04',13),(37,'01',59),(38,'01',49),(39,'01',54),(40,'01',57),(41,'01',48),(42,'01',58),(43,'01',56),(44,'0102',46),(45,'0102',53),(46,'0102',51),(47,'0102',45),(48,'0102',47),(49,'0102',52),(50,'0102',50),(51,'0101',50),(52,'0101',46),(53,'0101',51),(54,'0101',52),(55,'02',59),(56,'03',48),(57,'03',60),(58,'03',49),(59,'03',59),(60,'0301',61),(61,'04',52),(62,'04',51),(63,'04',46),(64,'04',53),(65,'04',50),(66,'04',47),(67,'04',45),(68,'0401',48),(69,'0401',49),(70,'0401',56),(71,'0401',57),(72,'06',63),(73,'0601',64),(74,'09',65),(75,'0901',48),(76,'0901',49),(77,'0902',48),(78,'030101',56),(79,'0402',48);

/*Table structure for table `cms_category_relation` */

DROP TABLE IF EXISTS `cms_category_relation`;

CREATE TABLE `cms_category_relation` (
  `id` bigint(20) NOT NULL auto_increment,
  `category_code_primary` varchar(50) default NULL,
  `category_code_secondary` varchar(50) default NULL,
  `relation_id` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `cms_category_relation` */

insert  into `cms_category_relation`(`id`,`category_code_primary`,`category_code_secondary`,`relation_id`) values (6,'0102','0301','CMS_RELATION_LINK'),(7,'0102','0202','CMS_RELATION_LINK'),(8,'0102','12','CMS_RELATION_INSTALL'),(9,'0102','0902','CMS_RELATION_INSTALL'),(10,'0102','13','CMS_RELATION_DOCUMENT');

/*Table structure for table `cms_ci` */

DROP TABLE IF EXISTS `cms_ci`;

CREATE TABLE `cms_ci` (
  `id` bigint(20) NOT NULL auto_increment,
  `name_` varchar(255) default NULL COMMENT '名称',
  `model` varchar(255) default NULL COMMENT '型号',
  `security_no` varchar(255) default NULL COMMENT '保密编号',
  `security_level` varchar(50) default NULL COMMENT '密级',
  `system` varchar(50) default NULL COMMENT '所属系统',
  `category_code` varchar(50) default NULL COMMENT '分类代码',
  `incidence` varchar(255) default NULL COMMENT '影响范围',
  `location` varchar(255) default NULL COMMENT '物理位置',
  `department_in_use` varchar(255) default NULL COMMENT '使用部门',
  `department_in_maintenance` varchar(255) default NULL COMMENT '维护部门',
  `user_in_maintenance` varchar(255) default NULL COMMENT '维护人员',
  `service_start_time` datetime default NULL COMMENT '服务开始日期',
  `service_end_time` datetime default NULL COMMENT '服务结束日期',
  `service_level` varchar(255) default NULL COMMENT '服务级别',
  `service_provider` varchar(255) default NULL COMMENT '服务提供商',
  `service_provider_contact` varchar(255) default NULL COMMENT '服务联系方式',
  `review_status` varchar(50) default '02' COMMENT '审核状态',
  `last_review_time` datetime default NULL COMMENT '最近审核时间',
  `delete_status` varchar(50) default '01' COMMENT '删除状态',
  `delete_time` datetime default NULL COMMENT '删除时间',
  `producer` varchar(255) default NULL COMMENT '厂商',
  `status_` varchar(50) default '03' COMMENT '状态',
  `purpose` varchar(255) default NULL COMMENT '用途',
  `expiration_time` datetime default NULL COMMENT '硬件报废日期/软件许可过期日期',
  `ci_manager` varchar(50) default NULL COMMENT 'ci管理员',
  `created_time` datetime default NULL COMMENT '创建时间',
  `last_update_user` varchar(50) default NULL COMMENT '最后更新人',
  `last_update_time` datetime default NULL COMMENT '最后更新时间',
  `remark` varchar(500) default NULL COMMENT '备注',
  `properties_data` text COMMENT '属性数据',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `cms_ci` */

/*Table structure for table `cms_ci_relation` */

DROP TABLE IF EXISTS `cms_ci_relation`;

CREATE TABLE `cms_ci_relation` (
  `id` bigint(20) NOT NULL auto_increment,
  `primary_id` bigint(20) default NULL,
  `secondary_id` bigint(20) default NULL,
  `relation_id` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `cms_ci_relation` */

insert  into `cms_ci_relation`(`id`,`primary_id`,`secondary_id`,`relation_id`) values (1,1,2,'CMS_RELATION_DOCUMENT'),(2,1,1,'CMS_RELATION_INSTALL'),(3,1,1,'CMS_RELATION_DOCUMENT');

/*Table structure for table `cms_property` */

DROP TABLE IF EXISTS `cms_property`;

CREATE TABLE `cms_property` (
  `id` bigint(20) NOT NULL auto_increment,
  `property_id` varchar(255) default NULL COMMENT '属性id',
  `property_name` varchar(255) default NULL COMMENT '属性名',
  `property_type` varchar(255) default NULL COMMENT '属性类型',
  `property_constraint` varchar(255) default NULL COMMENT '属性约束条件',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `cms_property` */

insert  into `cms_property`(`id`,`property_id`,`property_name`,`property_type`,`property_constraint`) values (20,'CMS_FIELD_NAME','名称','string','Name'),(21,'CMS_FIELD_SECURITYNO','保密编号','string','SecurityNo'),(22,'CMS_FIELD_CATEGORYCODE','分类','string','CategoryCode'),(23,'CMS_FIELD_INCIDENCE','影响范围','string','Incidence'),(24,'CMS_FIELD_LOCATION','物理位置','string','Location'),(25,'CMS_FIELD_DEPARTMENTINUSE','使用部门','string','DepartmentInUse'),(26,'CMS_FIELD_DEPARTMENTINMAINTENANCE','维护部门','string','DepartmentInMaintenance'),(27,'CMS_FIELD_USERINMAINTENANCE','维护人员','string','UserInMaintenance'),(28,'CMS_FIELD_SERVICESTARTTIME','服务开始时间','date','ServiceStartTime'),(29,'CMS_FIELD_SERVICEENDTIME','服务结束时间','date','ServiceEndTime'),(30,'CMS_FIELD_SERVICELEVEL','服务级别','string','ServiceLevel'),(31,'CMS_FIELD_SERVICEPROVIDER','服务提供商','string','ServiceProvider'),(32,'CMS_FIELD_SERVICEPROVIDERCONTACT','服务联系方式','string','ServiceProviderContact'),(33,'CMS_FIELD_REVIEWSTATUS','审核状态','string','ReviewStatus'),(34,'CMS_FIELD_LASTREVIEWTIME','最近审核时间','date','LastReviewTime'),(35,'CMS_FIELD_DELETESTATUS','删除状态','string','DeleteStatus'),(36,'CMS_FIELD_DELETETIME','删除时间','date','DeleteTime'),(37,'CMS_FIELD_PRODUCER','厂商','string','Producer'),(38,'CMS_FIELD_STATUS','状态','string','Status'),(39,'CMS_FIELD_PURPOSE','用途','string','Purpose'),(40,'CMS_FIELD_EXPIRATIONTIME','到期时间','date','ExpirationTime'),(41,'CMS_FIELD_CIMANAGER','配置项管理人','string','CiMananger'),(42,'CMS_FIELD_CREATEDTIME','创建时间','date','CreatedTime'),(43,'CMS_FIELD_LASTUPDATEUSER','最后更新人','string','LastUpdateUser'),(44,'CMS_FIELD_LASTUPDATETIME','最后更新时间','date','LastUpdateTime'),(45,'CMS_PROPERTY_OSINSTALLTIME','操作系统安装时间','string',''),(46,'CMS_PROPERTY_DISKNO','硬盘序列号','string',''),(47,'CMS_PROPERTY_OSVERSION','操作系统版本','string',''),(48,'CMS_PROPERTY_IPADDRESS','IP地址','string',''),(49,'CMS_PROPERTY_MACADDRESS','MAC地址','string',''),(50,'CMS_PROPERTY_CPU','CPU','string',''),(51,'CMS_PROPERTY_DISK','硬盘','string',''),(52,'CMS_PROPERTY_MEMORY','内存','string',''),(53,'CMS_PROPERTY_CDROM','光驱','bool',''),(54,'CMS_PROPERTY_RAIDVERSION','RAID版本','string',''),(55,'CMS_PROPERTY_HBA','加密狗/HBA卡','string',''),(56,'CMS_PROPERTY_NETCARD','网卡','string',''),(57,'CMS_PROPERTY_NETCARDCOUNT','网卡数量','string',''),(58,'CMS_PROPERTY_POWER','电源模块','string',''),(59,'CMS_PROPERTY_SN','SN号','string',''),(60,'CMS_PROPERTY_NEWGATE','网关','string',''),(61,'CMS_PROPERTY_PORTSCOUNT','端口数量','string',''),(62,'CMS_PROPERTY_STORAGETYPE','介质类型','string',''),(63,'CMS_PROPERTY_STORAGESIZE','存储容量','string',''),(64,'CMS_PROPERTY_STORAGESERIALNO','序列号','string',''),(65,'CMS_PROPERTY_VERIFIEDNO','检测证书名称及编号','string',''),(66,'CMS_PROPERTY_KEYNO','KEY编号','string',''),(67,'CMS_FIELD_MODEL','型号','string','Model'),(68,'CMS_FIELD_SYSTEM','归属系统','string','System'),(69,'CMS_FIELD_SYSTEMNAME','归属系统名称','string','SystemName'),(70,'CMS_FIELD_SECURITYLEVEL','密级','string','SecurityLevel'),(71,'CMS_FIELD_SECURITYLEVELNAME','密级名称','string','SecurityLevelName');

/*Table structure for table `cms_relation` */

DROP TABLE IF EXISTS `cms_relation`;

CREATE TABLE `cms_relation` (
  `relation_id` varchar(50) NOT NULL,
  `relation_name` varchar(255) default NULL,
  `relation_description` varchar(255) default NULL,
  PRIMARY KEY  (`relation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `cms_relation` */

insert  into `cms_relation`(`relation_id`,`relation_name`,`relation_description`) values ('CMS_RELATION_CONSIST','组成关系',''),('CMS_RELATION_DEPEND','依赖关系','依赖关系'),('CMS_RELATION_DOCUMENT','文档关系','文档关系'),('CMS_RELATION_INSTALL','安装在...上',''),('CMS_RELATION_LINK','连接关系','连接关系'),('CMS_RELATION_PERFORM','运行于...上',''),('CMS_RELATION_STANDBY','热备',''),('CMS_RELATION_USE','使用关系','');

/*Table structure for table `contract` */

DROP TABLE IF EXISTS `contract`;

CREATE TABLE `contract` (
  `id` bigint(20) NOT NULL auto_increment,
  `name_` varchar(255) default NULL COMMENT '合同名称',
  `abstract_` varchar(255) default NULL COMMENT '合同摘要',
  `type_` varchar(50) default NULL COMMENT '类型',
  `sn_` varchar(255) default NULL COMMENT '服务代码',
  `contact_person` varchar(255) default NULL COMMENT '联系人',
  `contact_phone` varchar(255) default NULL COMMENT '联系电话',
  `sign_time` datetime default NULL COMMENT '签订时间',
  `start_time` datetime default NULL COMMENT '开始时间',
  `end_time` datetime default NULL COMMENT '结束时间',
  `attachment` varchar(255) default NULL COMMENT '附件',
  `created_user` varchar(255) default NULL COMMENT '创建者',
  `created_time` datetime default NULL COMMENT '创建时间',
  `status_` varchar(50) default NULL,
  `is_locked` tinyint(1) default NULL,
  `process_instance_id` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `contract` */

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `parent_id` int(11) default NULL COMMENT '上级部门id',
  `telephone` varchar(64) default NULL COMMENT '部门电话',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `department` */

/*Table structure for table `doc_style` */

DROP TABLE IF EXISTS `doc_style`;

CREATE TABLE `doc_style` (
  `id` int(11) NOT NULL auto_increment,
  `doc_id` int(11) NOT NULL,
  `style_id` int(11) NOT NULL,
  KEY `id` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=126 DEFAULT CHARSET=utf8 COMMENT='文档类别表';

/*Data for the table `doc_style` */

/*Table structure for table `document` */

DROP TABLE IF EXISTS `document`;

CREATE TABLE `document` (
  `id` int(11) NOT NULL auto_increment,
  `doc_id` varchar(64) default NULL COMMENT '文档id，用于多版本时标识文档的唯一标识',
  `name` varchar(255) NOT NULL,
  `keywords` varchar(255) NOT NULL COMMENT '关键字？是否单独成表？',
  `versions` int(8) default NULL COMMENT '版本号',
  `in_while` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `auth_` int(11) default NULL,
  `user_id` int(11) default NULL,
  `issue_time` timestamp NULL default NULL COMMENT '发布时间',
  `last_version` tinyint(4) default '1' COMMENT '是否是最新版本',
  `deposit` varchar(255) default NULL COMMENT '纸质文档存放位置',
  `secret_level` int(11) default NULL COMMENT '密级',
  `doc_num` varchar(255) default NULL COMMENT '文档编号',
  `style_id` int(11) NOT NULL COMMENT '所属类别',
  `link_` varchar(64) default NULL COMMENT '文档所在链接',
  `enabled` tinyint(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;

/*Data for the table `document` */

/*Table structure for table `document_sec_audit` */

DROP TABLE IF EXISTS `document_sec_audit`;

CREATE TABLE `document_sec_audit` (
  `id` int(11) NOT NULL auto_increment,
  `update_ts` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `version_num` varchar(30) NOT NULL COMMENT '版本号',
  `user_id` int(11) NOT NULL COMMENT '用户',
  `update_info` varchar(200) default NULL COMMENT '备注',
  `doc_id` int(11) NOT NULL COMMENT '对应的文档',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='文档安全审计表';

/*Data for the table `document_sec_audit` */

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

/*Table structure for table `kg_knowledge` */

DROP TABLE IF EXISTS `kg_knowledge`;

CREATE TABLE `kg_knowledge` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `desc_` varchar(255) default NULL,
  `solution` text,
  `keyword` varchar(255) default NULL,
  `apply_user` varchar(255) default NULL,
  `apply_time` datetime default NULL,
  `status_` tinyint(1) default NULL,
  `is_locked` tinyint(1) default NULL,
  `process_instance_id` varchar(255) default NULL,
  `current_activity_id` varchar(255) default NULL,
  `current_activity_name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `kg_knowledge` */

/*Table structure for table `leave_jpa` */

DROP TABLE IF EXISTS `leave_jpa`;

CREATE TABLE `leave_jpa` (
  `id` bigint(20) NOT NULL auto_increment,
  `apply_time` datetime default NULL,
  `dept_leader_approved` varchar(255) default NULL,
  `end_time` datetime default NULL,
  `hr_approved` varchar(255) default NULL,
  `leave_type` varchar(255) default NULL,
  `process_instance_id` varchar(255) default NULL,
  `reality_end_time` datetime default NULL,
  `reality_start_time` datetime default NULL,
  `reason` varchar(255) default NULL,
  `report_back_date` datetime default NULL,
  `start_time` datetime default NULL,
  `user_id` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `leave_jpa` */

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL auto_increment,
  `from_user` varchar(255) default NULL,
  `to_user` varchar(255) default NULL,
  `content` varchar(500) default NULL,
  `href` varchar(255) default NULL,
  `is_read` tinyint(1) default '0',
  `created_time` datetime default NULL,
  `read_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=368 DEFAULT CHARSET=utf8;

/*Data for the table `message` */

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `content` text,
  `created_time` datetime default NULL,
  `created_user` varchar(255) default NULL,
  `status_` varchar(50) default '01',
  `process_instance_id` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `notice` */

/*Table structure for table `operation_records` */

DROP TABLE IF EXISTS `operation_records`;

CREATE TABLE `operation_records` (
  `id` int(11) NOT NULL auto_increment,
  `user_name` varchar(255) NOT NULL,
  `operation_type` int(11) NOT NULL,
  `desc_` text NOT NULL COMMENT '操作描述',
  `in_while` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '记录时间',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2388 DEFAULT CHARSET=utf8;

/*Data for the table `operation_records` */

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
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `person` */

insert  into `person`(`id`,`name`,`country`) values (1,'admin','usa'),(2,'yang','chn'),(3,'杨海鹏','中国'),(5,'张三','中国');

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
  `name_` varchar(255) NOT NULL COMMENT '类型名',
  `desc_` varchar(255) default NULL COMMENT '概述',
  `parent_id` int(11) default NULL,
  `code_` varchar(10) default NULL COMMENT '代号',
  `order_` int(11) default NULL COMMENT '顺序',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=271 DEFAULT CHARSET=utf8;

/*Data for the table `styles` */

insert  into `styles`(`id`,`name_`,`desc_`,`parent_id`,`code_`,`order_`) values (1,'资产类型','服务器类型',NULL,NULL,NULL),(2,'服务器',NULL,1,NULL,NULL),(3,'机柜',NULL,1,NULL,NULL),(4,'涉密终端',NULL,1,NULL,NULL),(5,'网卡类型',NULL,NULL,NULL,NULL),(6,'类型1',NULL,5,NULL,NULL),(7,'类型2',NULL,5,NULL,NULL),(8,'类型3',NULL,5,NULL,NULL),(9,'软件',NULL,1,NULL,NULL),(10,'文档类别',NULL,NULL,'document_',NULL),(11,'保密管理文档',NULL,10,'01',1),(12,'服务厂商文档',NULL,10,'01',2),(13,'运维支持文档',NULL,10,'01',3),(16,'保密管理制度',NULL,11,NULL,NULL),(17,'安全策略文档',NULL,11,NULL,NULL),(18,'三员工作记录',NULL,11,NULL,NULL),(19,'安全审计报告',NULL,18,NULL,NULL),(20,'风险评估报告',NULL,18,NULL,NULL),(21,'安全事件报告',NULL,18,NULL,NULL),(22,'三员工作日志',NULL,18,NULL,NULL),(23,'巡检记录',NULL,18,NULL,NULL),(24,'应急演练',NULL,18,NULL,NULL),(25,'病毒库升级记录',NULL,18,NULL,NULL),(26,'漏洞扫描报告',NULL,18,NULL,NULL),(27,'进出要害部门部位记录表',NULL,18,NULL,NULL),(28,'保密培训记录表',NULL,18,NULL,NULL),(29,'其他文档',NULL,11,NULL,NULL),(30,'系统定级文件',NULL,29,NULL,NULL),(31,'网络运行许可证',NULL,29,NULL,NULL),(32,'安全产品的测评报告',NULL,29,NULL,NULL),(33,'安全产品的保密认证证书复印件',NULL,29,NULL,NULL),(34,'甲乙方合同文档',NULL,12,NULL,NULL),(35,'服务条款与协议SLA',NULL,12,NULL,NULL),(36,'厂商和联系方式管理',NULL,12,NULL,NULL),(37,'外部技术支持专家、工程师管理',NULL,12,NULL,NULL),(38,'业务视图文档',NULL,13,NULL,NULL),(39,'网络拓扑图',NULL,13,NULL,NULL),(40,'网络布线文档',NULL,13,NULL,NULL),(41,'Vlan划分表',NULL,13,NULL,NULL),(42,'IP地址分配表',NULL,13,NULL,NULL),(43,'设备配置文件',NULL,13,NULL,NULL),(44,'操作手册',NULL,13,NULL,NULL),(45,'操作规范',NULL,13,NULL,NULL),(46,'产品手册',NULL,13,NULL,NULL),(47,'检查项',NULL,NULL,'CI',NULL),(48,'BMB22-2007',NULL,47,'BMB22',1),(49,'BMB20-2007',NULL,47,'BMB20',2),(50,'7.1 技术要求（70分）',NULL,48,NULL,1),(51,'7.2 管理要求（30分）',NULL,48,NULL,2),(52,'7.1.1 基本保护要求（10分）',NULL,50,NULL,1),(53,'7.2.1 过程管理（7分）',NULL,51,NULL,1),(55,'7.1.1.2 安全保密产品选择（2分）','',52,'NON',1),(62,'7.2.1.1 系统定级（1分）','',53,'NON',1),(72,'人员管理','',49,'NON',1),(61,'7.1.2.2 设备安全（2分）','',93,'NON',24),(63,'--','',62,'NON',3),(64,'设备携带','',61,'NON',4),(65,'7.2.1.2 方案设计（1分）','',53,'NON',5),(66,'集成资质单位选择','',65,'NON',6),(67,'设备维修','',61,'NON',5),(68,'机房建设','',70,'NON',11),(69,'设备报废','',61,'NON',14),(70,'7.1.2.1 环境安全（2分）','',93,'NON',23),(71,'BMB17-2006',NULL,47,'BMB17',3),(73,'物理环境与设施管理','',49,'NON',2),(74,'内部工作人员','',72,'NON',1),(75,'外部相关人员','',72,'NON',2),(76,'人员录用','',74,'NON',5),(77,'岗位职责','',74,'NON',7),(79,'保密协议','',74,'NON',8),(82,'教育培训','',74,'NON',10),(83,'保密监管','',74,'NON',11),(84,'人员奖惩','',74,'NON',12),(85,'人员离岗离职','',74,'NON',13),(88,'周边环境','',73,'NON',1),(89,'周边监控','',88,'NON',1),(90,'周界安防','',88,'NON',3),(92,'--','',55,'NON',1),(93,'7.1.2 物理安全（6分）','',50,'NON',3),(94,'物理安全','',71,'NON',1),(95,'环境安全','',94,'NON',1),(96,'机房建设','',95,'NON',1),(97,'设备安全','',94,'NON',2),(98,'设备携带','',97,'NON',1),(99,'运行安全','',71,'NON',2),(100,'备份与恢复','',99,'NON',1),(101,'备份与恢复策略','',100,'NON',1),(102,'兵工网类别1','',10,'02',1),(103,'介质安全','',94,'NON',5),(104,'介质收发与传递','',103,'NON',1),(105,'7.1.2.3 介质安全（2分）','',93,'NON',35),(106,'介质收发与传递','',105,'NON',5),(107,'介质使用','',105,'NON',10),(108,'介质保存','',105,'NON',15),(109,'介质维修','',105,'NON',20),(110,'介质报废','',105,'NON',25),(111,'7.1.3 运行安全（9分）','',50,'NON',10),(112,'7.1.3.1 备份与恢复（2分）','',111,'NON',5),(113,'备份与恢复策略','',112,'NON',5),(114,'备份与恢复需求分析','',112,'NON',10),(115,'涉密数据备份','',112,'NON',15),(116,'关键业务数据备份','',112,'NON',20),(117,'电源备份','',112,'NON',26),(118,'系统恢复预案','',112,'NON',30),(119,'信息系统恢复与重建','',112,'NON',35),(123,'7.1.3.2 计算机病毒与恶意代码防护（3分）','',111,'NON',10),(124,'防护策略','',123,'NON',5),(125,'防护措施','',123,'NON',10),(126,'防护能力更新','',123,'NON',15),(127,'7.1.3.3 应急响应（2分）','',111,'NON',15),(128,'应急计划和响应策略','',127,'NON',5),(129,'应急响应培训','',127,'NON',10),(130,'事件监测与分类','',127,'NON',15),(131,'泄密事件处理方式','',127,'NON',20),(132,'运行安全事件处理方式','',127,'NON',25),(133,'7.1.3.4 运行管理（2分）','',111,'NON',20),(134,'运行管理策略','',133,'NON',5),(135,'系统配置','',133,'NON',10),(136,'设备接入控制','',133,'NON',15),(137,'权限划分','',133,'NON',20),(138,'7.1.4 信息安全保密（45分）','',50,'NON',20),(139,'7.1.4.1 身份鉴别（8分）','',138,'NON',5),(140,'身份鉴别策略','',139,'NON',5),(141,'传输线路的电磁泄露发射防护','',139,'NON',10),(142,'7.1.4.5 信息完整性校验（1分）','',138,'NON',10),(143,'信息完整性校验策略','',142,'NON',5),(144,'7.1.4.6 系统安全性能检测（2分）','',138,'NON',15),(145,'系统安全性能检测策略','',144,'NON',5),(146,'安全性能检测工具','',144,'NON',10),(147,'检测结果处理','',144,'NON',15),(148,'7.1.4.7 安全审计（4分）','',138,'NON',20),(149,'安全审计策略','',148,'NON',5),(150,'审计范围','',148,'NON',10),(151,'审计记录内容','',148,'NON',15),(152,'审计记录存储','',148,'NON',20),(153,'审计记录查询','',148,'NON',25),(154,'7.1.4.11 边界安全防护（4分）','',138,'NON',25),(155,'边界控制','',154,'NON',5),(156,'7.2.1.3 工程实施（1分）','',53,'NON',15),(157,'--','',156,'NON',5),(158,'7.2.1.4 系统测评（1分）','',53,'NON',20),(159,'---','',158,'NON',5),(160,'7.2.1.5 系统审批（1分）','',53,'NON',25),(161,'--','',160,'NON',5),(162,'7.2.1.6 日常管理','',53,'NON',30),(163,'---','',162,'NON',5),(164,'7.2.1.7 测评与检查（1分）','',53,'NON',35),(165,'--','',164,'NON',5),(166,'7.2.1.8 系统废止（1分）','',53,'NON',40),(167,'---','',166,'NON',5),(168,'7.2.2 基本管理要求（7分）','',51,'NON',10),(169,'7.2.2.1 管理策略（1分）','',168,'NON',5),(170,'策略制定','',169,'NON',5),(171,'策略更新','',169,'NON',10),(172,'* 7.2.2.2 管理机构（2分）','',168,'NON',10),(173,'组织结构','',172,'NON',5),(174,'机构职责','',172,'NON',10),(175,'* 7.2.2.3 管理制度（2分）','',168,'NON',15),(176,'责任制度','',175,'NON',5),(177,'工作制度','',175,'NON',10),(178,'* 7.2.2.4 管理人员（2分）','',168,'NON',20),(179,'人员分配','',178,'NON',5),(180,'人员权限','',178,'NON',10),(181,'7.2.3.1 人员管理（3分）','',51,'NON',15),(182,'7.2.3.1.1 内部工作人员（2分）','',181,'NON',5),(183,'人员录用','',182,'NON',5),(184,'岗位职责','',182,'NON',10),(185,'保密协议','',182,'NON',15),(186,'教育培训','',182,'NON',20),(187,'保密监管','',182,'NON',25),(188,'人员奖惩','',182,'NON',30),(189,'人员离岗离职','',182,'NON',35),(190,'7.2.3.1.2 外部相关人员（1分）','',181,'NON',10),(191,'保密要求知会','',190,'NON',5),(192,'安全控制区域隔离','',190,'NON',10),(193,'携带物品限制','',190,'NON',15),(194,'7.2.3.2 物理环境与设施管理（3分）','',51,'NON',20),(195,'7.2.3.2.1 周边环境（1分）','',194,'NON',5),(196,'旁站陪同控制','',190,'NON',20),(197,'周边监控','',195,'NON',5),(198,'出入控制','',195,'NON',10),(199,'7.2.3.2.2 涉密场所（1分）','',194,'NON',10),(200,'保密要害部门、部位管理',NULL,199,'NON',5),(201,'无线、多媒体产品使用',NULL,199,'NON',10),(202,'7.2.3.2.3 保障设施（1分）','',194,'NON',15),(203,'安全巡防巡查',NULL,199,'NON',15),(204,'定期检测检修',NULL,202,'NON',5),(205,'线路线缆保护',NULL,202,'NON',10),(206,'7.2.3.3 设备与介质管理（4分）','',51,'NON',25),(207,'7.2.3.3.1 采购与选型（1分）','',206,'NON',5),(208,'安全采购管理',NULL,207,'NON',5),(209,'产品选型管理',NULL,207,'NON',10),(210,'检测证书查验',NULL,207,'NON',15),(211,'货物交付验收',NULL,207,'NON',20),(212,'7.2.3.3.2 操作与使用','',206,'NON',10),(213,'安全操作使用',NULL,212,'NON',5),(214,'外出携带管理',NULL,212,'NON',10),(215,'设备外联控制',NULL,212,'NON',15),(216,'介质使用管理',NULL,212,'NON',20),(217,'安全准入许可',NULL,212,'NON',25),(218,'7.2.3.3.3 保管与保存','',206,'NON',15),(219,'清查登记核对',NULL,218,'NON',5),(220,'重要设备界定',NULL,218,'NON',10),(221,'明确责任主体',NULL,218,'NON',15),(222,'7.2.3.3.4 维修与报废','',206,'NON',20),(223,'申报审批',NULL,222,'NON',5),(224,'数据保护',NULL,222,'NON',10),(225,'登记备案',NULL,222,'NON',15),(226,'7.2.3.4 运行与开发管理（3分）','',51,'NON',30),(227,'7.2.3.4.1 运行使用（1分）','',226,'NON',5),(228,'策略规划审核',NULL,227,'NON',10),(229,'软件安装控制',NULL,227,'NON',15),(230,'系统变更管理',NULL,227,'NON',20),(231,'符合性检查',NULL,227,'NON',25),(232,'文档资料更新',NULL,227,'NON',30),(233,'7.2.3.4.2 应用系统开发（1分）','',226,'NON',10),(234,'安全开发同步',NULL,233,'NON',5),(235,'开发环境分离',NULL,233,'NON',10),(236,'测试连调控制',NULL,233,'NON',15),(237,'后期维护管理',NULL,233,'NON',20),(238,'7.2.3.4.3 异常事件（1分）','',226,'NON',15),(239,'响应预案管理',NULL,238,'NON',5),(240,'事件监测处置',NULL,238,'NON',10),(241,'灾难恢复管理',NULL,238,'NON',15),(242,'总结评估改进',NULL,238,'NON',20),(243,'7.2.3.5 信息保密管理（1分）','',51,'NON',35),(244,'7.2.3.5.1 信息分类与控制（1分）','',243,'NON',5),(245,'7.2.3.5.2 用户管理与授权（1分）','',243,'NON',10),(246,'7.2.3.5.3 信息系统互联（1分）','',243,'NON',15),(247,'密级类别确定',NULL,244,'NON',5),(248,'密级信息总量统计',NULL,244,'NON',10),(249,'密级标识添加',NULL,244,'NON',15),(250,'知悉范围确定',NULL,244,'NON',20),(251,'用户清理管理',NULL,245,'NON',5),(252,'用户标识符管理',NULL,245,'NON',10),(253,'权限列表审查',NULL,245,'NON',15),(254,'需求分析评估',NULL,246,'NON',5),(255,'安全互联控制',NULL,246,'NON',10),(256,'abc','abccdd',NULL,'NON',20),(264,'测试','123456',10,'01',50),(265,'ceshi1','1000',264,'test1',5),(266,'ceshi2','dfdfdfdf',264,'test2',56),(268,'ceshi3','1000',265,'test3',5),(269,'1234','5678',10,'',5),(270,'配置信息','',10,'05',1);

/*Table structure for table `sys_authorities` */

DROP TABLE IF EXISTS `sys_authorities`;

CREATE TABLE `sys_authorities` (
  `AUTHORITY_ID` int(11) NOT NULL auto_increment,
  `AUTHORITY_MARK` varchar(100) default NULL,
  `AUTHORITY_NAME` varchar(100) NOT NULL,
  `AUTHORITY_DESC` varchar(200) default NULL,
  `MESSAGE` varchar(100) default NULL,
  `ENABLE` int(11) default '1',
  `ISSYS` int(11) default '1',
  `MODULE_ID` int(11) default NULL,
  PRIMARY KEY  (`AUTHORITY_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `sys_authorities` */

insert  into `sys_authorities`(`AUTHORITY_ID`,`AUTHORITY_MARK`,`AUTHORITY_NAME`,`AUTHORITY_DESC`,`MESSAGE`,`ENABLE`,`ISSYS`,`MODULE_ID`) values (1,NULL,'AUTH_ADMIN','最高权限',NULL,1,1,NULL),(2,NULL,'AUTH_USER','用户权限，可以操作文档1',NULL,1,1,NULL),(3,NULL,'PUBLIC_','用于显示公共部分的权限',NULL,1,1,NULL),(4,NULL,'AUTH_WANGXIAO_TEST','王潇用于测试文档、保密检查项的权限',NULL,1,1,NULL),(5,NULL,'查看检查项权限','用于查看检查项',NULL,1,1,NULL),(6,NULL,'下载文档附件权限','下载文档的附件，在这里用于查看检查项文档',NULL,1,1,NULL),(8,NULL,'查看审计日志和搜索日志','见权限名称',NULL,1,1,NULL),(9,NULL,'general_user','普痛用户',NULL,1,1,NULL),(10,NULL,'三员之系统管理员权限','',NULL,1,1,NULL),(11,NULL,'三员之安全审计员','',NULL,1,1,NULL),(12,NULL,'三员之安全保密管理员','',NULL,1,1,NULL),(13,NULL,'文档管理','',NULL,1,1,NULL),(14,NULL,'主管','',NULL,1,1,NULL);

/*Table structure for table `sys_authorities_resources` */

DROP TABLE IF EXISTS `sys_authorities_resources`;

CREATE TABLE `sys_authorities_resources` (
  `ID` int(11) NOT NULL auto_increment,
  `RESOURCE_ID` int(11) NOT NULL,
  `AUTHORITY_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=465 DEFAULT CHARSET=utf8 COMMENT='权限资源表';

/*Data for the table `sys_authorities_resources` */

insert  into `sys_authorities_resources`(`ID`,`RESOURCE_ID`,`AUTHORITY_ID`) values (297,2,1),(296,3,1),(295,4,1),(294,5,1),(293,6,1),(292,7,1),(291,9,1),(290,10,1),(289,11,1),(288,12,1),(287,13,1),(286,14,1),(285,15,1),(284,16,1),(283,17,1),(282,21,1),(281,22,1),(280,23,1),(279,24,1),(20,17,2),(21,13,2),(22,12,2),(23,11,2),(24,10,2),(25,9,2),(26,7,2),(27,6,2),(28,5,2),(29,4,2),(30,3,2),(31,1,2),(278,25,1),(277,26,1),(276,27,1),(37,22,2),(446,49,9),(274,29,1),(273,30,1),(272,31,1),(271,32,1),(270,33,1),(269,34,1),(268,35,1),(267,36,1),(266,37,1),(298,1,1),(265,38,1),(264,39,1),(463,1,13),(462,5,13),(461,34,13),(443,7,6),(319,44,4),(320,43,4),(321,39,4),(322,38,4),(323,36,4),(324,35,4),(325,34,4),(326,33,4),(327,27,4),(328,17,4),(329,13,4),(330,12,4),(331,11,4),(332,9,4),(333,7,4),(334,6,4),(335,5,4),(336,4,4),(337,3,4),(338,1,4),(339,43,1),(340,39,1),(341,38,1),(342,37,1),(343,36,1),(344,35,1),(345,34,1),(346,33,1),(347,32,1),(348,31,1),(349,30,1),(350,29,1),(445,47,8),(352,27,1),(353,26,1),(354,25,1),(355,24,1),(356,23,1),(357,22,1),(358,21,1),(359,17,1),(360,16,1),(361,15,1),(362,14,1),(363,13,1),(364,12,1),(365,11,1),(366,10,1),(367,9,1),(368,7,1),(369,6,1),(370,5,1),(371,4,1),(372,3,1),(373,2,1),(374,1,1),(375,44,1),(376,43,1),(377,39,1),(378,38,1),(379,37,1),(380,36,1),(381,35,1),(382,34,1),(383,33,1),(384,32,1),(385,31,1),(386,30,1),(387,29,1),(444,72,8),(389,27,1),(390,26,1),(391,25,1),(392,24,1),(393,23,1),(394,22,1),(395,21,1),(396,17,1),(397,16,1),(398,15,1),(399,14,1),(400,13,1),(401,12,1),(402,11,1),(403,10,1),(404,9,1),(405,7,1),(406,6,1),(407,5,1),(408,4,1),(409,3,1),(410,2,1),(411,1,1),(460,43,13),(442,17,5),(441,35,5),(440,36,5),(464,101,14),(452,80,12),(451,47,11),(450,72,11),(449,29,10),(448,30,10),(447,48,9),(434,13,3),(433,14,3),(436,11,3),(435,12,3);

/*Table structure for table `sys_code` */

DROP TABLE IF EXISTS `sys_code`;

CREATE TABLE `sys_code` (
  `id` bigint(20) NOT NULL auto_increment,
  `code_` varchar(50) default NULL,
  `code_name` varchar(50) default NULL,
  `type_` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=232 DEFAULT CHARSET=utf8;

/*Data for the table `sys_code` */

insert  into `sys_code`(`id`,`code_`,`code_name`,`type_`) values (6,'01','主机','INCIDENT_CATEGORY'),(7,'02','备份','INCIDENT_CATEGORY'),(8,'03','存储','INCIDENT_CATEGORY'),(9,'04','网络','INCIDENT_CATEGORY'),(10,'05','安全','INCIDENT_CATEGORY'),(11,'06','应用','INCIDENT_CATEGORY'),(12,'07','机房','INCIDENT_CATEGORY'),(13,'08','终端','INCIDENT_CATEGORY'),(14,'0101','小型机','INCIDENT_CATEGORY'),(15,'0102','PC服务器','INCIDENT_CATEGORY'),(16,'010101','网卡','INCIDENT_CATEGORY'),(17,'010102','内存','INCIDENT_CATEGORY'),(18,'010201','网卡','INCIDENT_CATEGORY'),(19,'010202','内存','INCIDENT_CATEGORY'),(20,'010103','CPU','INCIDENT_CATEGORY'),(21,'010104','操作系统','INCIDENT_CATEGORY'),(22,'010105','NBU CLIENT','INCIDENT_CATEGORY'),(23,'010106','集群软件','INCIDENT_CATEGORY'),(24,'010203','CPU','INCIDENT_CATEGORY'),(25,'010204','硬盘','INCIDENT_CATEGORY'),(26,'010205','主板','INCIDENT_CATEGORY'),(27,'010206','电源','INCIDENT_CATEGORY'),(28,'010207','NBU CLIENT','INCIDENT_CATEGORY'),(29,'010208','操作系统','INCIDENT_CATEGORY'),(30,'0201','磁带库机械臂','INCIDENT_CATEGORY'),(31,'0202','磁带库驱动器','INCIDENT_CATEGORY'),(32,'0203','虚拟带库','INCIDENT_CATEGORY'),(33,'0204','备份软件','INCIDENT_CATEGORY'),(34,'0301','硬盘','INCIDENT_CATEGORY'),(35,'0302','I/O','INCIDENT_CATEGORY'),(36,'0303','控制器','INCIDENT_CATEGORY'),(37,'0401','光纤交换机','INCIDENT_CATEGORY'),(38,'0402','交换机','INCIDENT_CATEGORY'),(39,'0403','路由器','INCIDENT_CATEGORY'),(40,'0404','防火墙','INCIDENT_CATEGORY'),(41,'0405','线路','INCIDENT_CATEGORY'),(42,'0406','光纤收发器','INCIDENT_CATEGORY'),(43,'0407','网管软件','INCIDENT_CATEGORY'),(44,'040101','端口','INCIDENT_CATEGORY'),(45,'040201','MAC地址绑定','INCIDENT_CATEGORY'),(46,'040202','端口','INCIDENT_CATEGORY'),(47,'040203','访问规则','INCIDENT_CATEGORY'),(48,'040301','端口','INCIDENT_CATEGORY'),(49,'040302','访问规则','INCIDENT_CATEGORY'),(50,'040401','端口','INCIDENT_CATEGORY'),(51,'040402','访问规则','INCIDENT_CATEGORY'),(52,'040501','专线','INCIDENT_CATEGORY'),(53,'040502','Internet接入专线','INCIDENT_CATEGORY'),(54,'040503','主干光纤','INCIDENT_CATEGORY'),(55,'040504','网线（双绞线）','INCIDENT_CATEGORY'),(56,'0501','CA系统','INCIDENT_CATEGORY'),(57,'0502','防水墙','INCIDENT_CATEGORY'),(58,'0503','一KEY通','INCIDENT_CATEGORY'),(59,'0504','杀毒软件','INCIDENT_CATEGORY'),(60,'0505','入侵检测系统','INCIDENT_CATEGORY'),(61,'0506','漏洞扫描','INCIDENT_CATEGORY'),(62,'0507','数据库加固','INCIDENT_CATEGORY'),(63,'0508','操作系统加固','INCIDENT_CATEGORY'),(64,'0509','主机监控','INCIDENT_CATEGORY'),(65,'0510','审计系统','INCIDENT_CATEGORY'),(66,'0601','视频会议系统','INCIDENT_CATEGORY'),(67,'0602','公文系统','INCIDENT_CATEGORY'),(68,'060101','MCU','INCIDENT_CATEGORY'),(69,'060102','终端','INCIDENT_CATEGORY'),(70,'060103','会管平台','INCIDENT_CATEGORY'),(71,'060104','会控系统','INCIDENT_CATEGORY'),(72,'0701','供电系统','INCIDENT_CATEGORY'),(73,'070101','UPS','INCIDENT_CATEGORY'),(74,'070102','配电柜','INCIDENT_CATEGORY'),(75,'070103','PDU、线路','INCIDENT_CATEGORY'),(76,'0702','空调通风','INCIDENT_CATEGORY'),(77,'070201','空调','INCIDENT_CATEGORY'),(78,'070202','新风机','INCIDENT_CATEGORY'),(79,'070203','排风机','INCIDENT_CATEGORY'),(80,'0703','照明','INCIDENT_CATEGORY'),(81,'070301','灯具、开关','INCIDENT_CATEGORY'),(82,'070302','光源','INCIDENT_CATEGORY'),(83,'0704','装修','INCIDENT_CATEGORY'),(84,'070401','地板','INCIDENT_CATEGORY'),(85,'070402','墙面','INCIDENT_CATEGORY'),(86,'070403','顶棚','INCIDENT_CATEGORY'),(87,'0705','门禁','INCIDENT_CATEGORY'),(88,'070501','指纹机','INCIDENT_CATEGORY'),(89,'070502','读卡器','INCIDENT_CATEGORY'),(90,'070503','门禁系统软件','INCIDENT_CATEGORY'),(91,'070504','电磁锁','INCIDENT_CATEGORY'),(92,'070505','IC卡','INCIDENT_CATEGORY'),(93,'0706','电力计量','INCIDENT_CATEGORY'),(94,'070601','服务器','INCIDENT_CATEGORY'),(95,'070602','软件','INCIDENT_CATEGORY'),(96,'070603','信号传输线路','INCIDENT_CATEGORY'),(97,'0707','环境监控','INCIDENT_CATEGORY'),(98,'070701','供电','INCIDENT_CATEGORY'),(99,'070702','信号传输线路','INCIDENT_CATEGORY'),(100,'070703','温度','INCIDENT_CATEGORY'),(101,'070704','湿度','INCIDENT_CATEGORY'),(102,'070705','漏水','INCIDENT_CATEGORY'),(103,'0708','防盗、监控','INCIDENT_CATEGORY'),(104,'070801','红外感应探头','INCIDENT_CATEGORY'),(105,'070802','视频摄像头','INCIDENT_CATEGORY'),(106,'070803','光端机','INCIDENT_CATEGORY'),(107,'070804','线路','INCIDENT_CATEGORY'),(108,'0709','对讲机','INCIDENT_CATEGORY'),(109,'0710','大屏幕显示','INCIDENT_CATEGORY'),(110,'071001','等离子大屏幕','INCIDENT_CATEGORY'),(111,'071002','夏普60寸显示器','INCIDENT_CATEGORY'),(112,'071003','LED显示','INCIDENT_CATEGORY'),(113,'071004','矩阵控制端','INCIDENT_CATEGORY'),(114,'071005','信号传输线路','INCIDENT_CATEGORY'),(115,'071006','视频矩阵','INCIDENT_CATEGORY'),(116,'0711','屏蔽门','INCIDENT_CATEGORY'),(117,'071101','电动开关','INCIDENT_CATEGORY'),(118,'071102','手动开关','INCIDENT_CATEGORY'),(119,'0712','升降平台','INCIDENT_CATEGORY'),(120,'071201','升降电机','INCIDENT_CATEGORY'),(121,'071202','控制开关','INCIDENT_CATEGORY'),(122,'0713','机房电话系统','INCIDENT_CATEGORY'),(123,'071301','电话机','INCIDENT_CATEGORY'),(124,'071302','线路','INCIDENT_CATEGORY'),(125,'071303','光纤转换器','INCIDENT_CATEGORY'),(126,'0714','综合布线系统','INCIDENT_CATEGORY'),(127,'071401','光纤','INCIDENT_CATEGORY'),(128,'071402','双绞线','INCIDENT_CATEGORY'),(129,'0715','消防系统','INCIDENT_CATEGORY'),(130,'0801','PC机','INCIDENT_CATEGORY'),(131,'080101','操作系统','INCIDENT_CATEGORY'),(132,'080102','应用软件','INCIDENT_CATEGORY'),(133,'080103','网络','INCIDENT_CATEGORY'),(134,'080104','杀毒软件','INCIDENT_CATEGORY'),(135,'080105','一KEY 通','INCIDENT_CATEGORY'),(136,'080106','防水墙','INCIDENT_CATEGORY'),(137,'080107','打印管理软件','INCIDENT_CATEGORY'),(138,'0802','打印机','INCIDENT_CATEGORY'),(139,'01','新建','INCIDENT_STATUS'),(140,'02','已指派','INCIDENT_STATUS'),(141,'03','进行中','INCIDENT_STATUS'),(142,'04','待决','INCIDENT_STATUS'),(143,'05','已解决','INCIDENT_STATUS'),(144,'06','已关闭','INCIDENT_STATUS'),(145,'01','广泛/普遍','INFLUENCE'),(146,'02','极大/大型','INFLUENCE'),(147,'03','适度/受限','INFLUENCE'),(148,'04','次要/本地化','INFLUENCE'),(149,'01','严重','CRITICAL'),(150,'02','高','CRITICAL'),(151,'03','中等','CRITICAL'),(152,'04','低','CRITICAL'),(153,'01','严重','PRIORITY'),(154,'02','高','PRIORITY'),(155,'03','中等','PRIORITY'),(156,'04','低','PRIORITY'),(157,'01','新建','CHANGE_STATUS'),(158,'02','已指派','CHANGE_STATUS'),(159,'03','变更方案构建中','CHANGE_STATUS'),(160,'04','审批中','CHANGE_STATUS'),(161,'05','计划中','CHANGE_STATUS'),(162,'06','实施中','CHANGE_STATUS'),(163,'07','已实施','CHANGE_STATUS'),(164,'08','已关闭','CHANGE_STATUS'),(165,'01','正常','CHANGE_CATEGORY'),(166,'02','紧急','CHANGE_CATEGORY'),(167,'03','已加速','CHANGE_CATEGORY'),(168,'04','潜在的','CHANGE_CATEGORY'),(169,'05','无影响','CHANGE_CATEGORY'),(170,'06','标准','CHANGE_CATEGORY'),(171,'01','新建','KNOWLEDGE_STATUS'),(172,'02','待审核','KNOWLEDGE_STATUS'),(173,'03','被驳回','KNOWLEDGE_STATUS'),(174,'04','已发布','KNOWLEDGE_STATUS'),(175,'05','已撤销','KNOWLEDGE_STATUS'),(176,'01','电话报修','INCIDENT_SOURCE'),(177,'02','来访报修','INCIDENT_SOURCE'),(178,'03','巡检','INCIDENT_SOURCE'),(179,'01','用户事件','INCIDENT_TYPE'),(180,'02','基础架构事件','INCIDENT_TYPE'),(181,'01','严重','CHANGE_RISK'),(182,'02','高','CHANGE_RISK'),(183,'03','中等','CHANGE_RISK'),(184,'04','低','CHANGE_RISK'),(185,'01','正常','INSPECTION_STATUS'),(186,'02','异常','INSPECTION_STATUS'),(187,'01','正常','CI_DELETE_STATUS'),(188,'02','已删除','CI_DELETE_STATUS'),(189,'01','已审核','CI_REVIEW_STATUS'),(190,'02','未审核','CI_REVIEW_STATUS'),(191,'03','不匹配','CI_REVIEW_STATUS'),(192,'04','丢失','CI_REVIEW_STATUS'),(193,'01','借出','CI_STATUS'),(194,'02','开发','CI_STATUS'),(195,'03','入库','CI_STATUS'),(196,'04','已安装','CI_STATUS'),(197,'05','测试中','CI_STATUS'),(198,'06','运行良好','CI_STATUS'),(199,'07','运行正常','CI_STATUS'),(200,'08','运行基本正常','CI_STATUS'),(201,'10','维护中','CI_STATUS'),(202,'09','运行不正常','CI_STATUS'),(203,'11','报废','CI_STATUS'),(204,'12','丢失','CI_STATUS'),(205,'13','借用','CI_STATUS'),(206,'14','闲置','CI_STATUS'),(207,'15','热备','CI_STATUS'),(208,'16','冷备','CI_STATUS'),(209,'04','自助报修','INCIDENT_SOURCE'),(210,'01','非常好','INCIDENT_SATISFACTION'),(211,'02','较好','INCIDENT_SATISFACTION'),(212,'04','一般','INCIDENT_SATISFACTION'),(213,'05','较差','INCIDENT_SATISFACTION'),(214,'03','好','INCIDENT_SATISFACTION'),(215,'01','瑞星病毒库','UPDATE_TYPE'),(216,'02','补丁','UPDATE_TYPE'),(217,'03','漏洞扫描','UPDATE_TYPE'),(218,'04','入侵检测','UPDATE_TYPE'),(219,'01','审计报告','SECJOB_TYPE'),(220,'02','风险评估报告','SECJOB_TYPE'),(221,'03','漏洞扫描报告','SECJOB_TYPE'),(222,'04','应急演练','SECJOB_TYPE'),(223,'01','涉密信息系统','CI_SYSTEM'),(224,'02','兵工网','CI_SYSTEM'),(225,'03','国资委视频会议','CI_SYSTEM'),(226,'04','科工局视频会议','CI_SYSTEM'),(227,'05','外网','CI_SYSTEM'),(228,'01','机密','CI_SECURITY_LEVEL'),(229,'02','秘密','CI_SECURITY_LEVEL'),(230,'03','内部','CI_SECURITY_LEVEL'),(231,'04','非密','CI_SECURITY_LEVEL');

/*Table structure for table `sys_group` */

DROP TABLE IF EXISTS `sys_group`;

CREATE TABLE `sys_group` (
  `id` int(11) NOT NULL auto_increment,
  `group_name` varchar(255) NOT NULL,
  `parent_id` int(11) default NULL,
  `remark_` varchar(255) default NULL,
  `order_` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='用户组表';

/*Data for the table `sys_group` */

insert  into `sys_group`(`id`,`group_name`,`parent_id`,`remark_`,`order_`) values (21,'兵科院',NULL,'',5),(22,'XX科室',21,'兵科院XX科室',5),(23,'中国兵器工业信息中心',NULL,'',10),(24,'技术支持部',23,'',5),(25,'运维组',24,'',5),(26,'系统所',NULL,'',15),(27,'销售部',26,'测试测试',NULL),(28,'公司办事处2',26,'测试2',12),(30,'软件开发',24,'',10),(33,'导控所',NULL,NULL,30);

/*Table structure for table `sys_modules` */

DROP TABLE IF EXISTS `sys_modules`;

CREATE TABLE `sys_modules` (
  `MODULE_ID` int(11) NOT NULL auto_increment,
  `MODULE_NAME` varchar(100) NOT NULL,
  `MODULE_DESC` varchar(200) default NULL,
  `MODULE_TYPE` int(11) default NULL,
  `PARENT` int(11) default NULL,
  `MODULE_URL` varchar(100) default NULL,
  `I_LEVEL` int(4) default NULL,
  `LEAF` int(4) default '1',
  `APPLICATION` varchar(100) default NULL,
  `CONTROLLER` varchar(100) default NULL,
  `ENABLE` int(1) default '1',
  `PRIORITY` int(4) default '1',
  PRIMARY KEY  (`MODULE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=85 DEFAULT CHARSET=utf8 COMMENT='模块表，与菜单有关系';

/*Data for the table `sys_modules` */

insert  into `sys_modules`(`MODULE_ID`,`MODULE_NAME`,`MODULE_DESC`,`MODULE_TYPE`,`PARENT`,`MODULE_URL`,`I_LEVEL`,`LEAF`,`APPLICATION`,`CONTROLLER`,`ENABLE`,`PRIORITY`) values (1,'文档管理','文档管理第一层',0,NULL,'#',1,0,NULL,'isw-text_document',1,20),(2,'文档列表',NULL,0,1,'/document',2,0,NULL,'glyphicon glyphicon-th-large',1,1),(3,'新建文档',NULL,0,1,'/document/add',2,0,NULL,'glyphicon glyphicon-th',1,1),(4,'系统管理',NULL,0,NULL,'#',1,0,NULL,'isw-settings',1,40),(5,'账户管理',NULL,0,4,'#',2,0,NULL,'glyphicon glyphicon-warning-sign',1,1),(8,'菜单管理',NULL,0,4,'/moudle/list',2,0,NULL,'glyphicon glyphicon-th-list',1,40),(16,'用户管理','',0,4,'/user/list',2,0,NULL,'glyphicon glyphicon-user',1,10),(14,'资源管理','',0,4,'/resource/list',2,0,NULL,'glyphicon glyphicon-screenshot',1,25),(15,'权限管理',NULL,0,4,'/authority/list',2,1,NULL,'glyphicon glyphicon-briefcase',1,20),(17,'添加类别','添加类别（分类）',0,4,'/style/add',2,0,NULL,'glyphicon glyphicon-th-list',1,50),(26,'添加检查项','',0,25,'/checkitems/add',2,0,NULL,'glyphicon glyphicon-resize-full',1,1),(25,'保密检查管理','',0,NULL,'#',1,0,NULL,'isw-lock',1,16),(28,'BMB22-2007','',0,25,'/checkitems/bmb-list/BMB22',2,0,NULL,'glyphicon glyphicon-wrench',1,2),(29,'角色管理','',0,4,'/role/list',2,0,NULL,'glyphicon glyphicon-tag',1,15),(30,'检查项分类','',0,25,'/checkitems/additems',2,0,NULL,'glyphicon glyphicon-play-circle',1,1),(31,'BMB20-2007','',0,25,'/checkitems/bmb-list/BMB20',2,0,NULL,'glyphicon glyphicon-time',1,3),(32,'BMB17-2006',NULL,NULL,25,'/checkitems/bmb-list/BMB17',2,1,NULL,'glyphicon glyphicon-time',1,4),(33,'自助报修','',0,NULL,'#',1,0,NULL,'isw-power',1,1),(34,'我要报修','',0,33,'/incident/addbyuser',2,0,NULL,'glyphicon glyphicon-pencil',1,1),(35,'处理中的报修','',0,33,'/incident/mylist',2,0,NULL,'glyphicon glyphicon-th-large',1,1),(36,'历史报修信息','',0,33,'/incident/search',2,0,NULL,'glyphicon glyphicon-search',1,1),(37,'事件管理','',0,NULL,'#',1,0,NULL,'isw-list',1,5),(38,'创建新事件','',0,37,'/incident/add',2,0,NULL,'glyphicon glyphicon-plus',1,1),(39,'事件控制台','',0,37,'/incident/list',2,0,NULL,'glyphicon glyphicon-th-large',1,2),(40,'历史事件查询','',0,37,'/incident/search',2,0,NULL,'glyphicon glyphicon-search',1,3),(41,'变更管理','',0,NULL,'#',1,0,NULL,'isw-refresh',1,10),(42,'创建新变更','',0,41,'/change/add',2,0,NULL,'glyphicon glyphicon-plus',1,1),(43,'变更控制台','',0,41,'/change/list',2,0,NULL,'glyphicon glyphicon-th-large',1,2),(44,'历史变更查询','',0,41,'/change/search',2,0,NULL,'glyphicon glyphicon-search',1,3),(45,'配置管理','',0,NULL,'#',1,0,NULL,'isw-sound',1,15),(46,'属性池管理','',0,45,'/cms/property/list',2,0,NULL,'glyphicon glyphicon-th-large',1,1),(47,'配置项类别管理','',0,45,'/cms/category/list',2,0,NULL,'glyphicon glyphicon-tags',1,2),(48,'配置项关系管理','',0,45,'/cms/relation/list',2,0,NULL,'glyphicon glyphicon-share-alt',1,3),(49,'配置项模型管理','',0,45,'/cms/categoryrelation/list',2,0,NULL,'glyphicon glyphicon-align-justify',1,4),(50,'配置项管理','',0,45,'/cms/ci/list',2,0,NULL,'glyphicon glyphicon-list',1,5),(51,'运维工作管理','',0,NULL,'#',1,0,NULL,'isw-users',1,21),(52,'领导交办','',0,51,'/leadertask/list',2,0,NULL,'glyphicon glyphicon-star',1,1),(53,'日常巡检','',0,51,'/record/inspection',2,0,NULL,'glyphicon glyphicon-eye-open',1,2),(54,'下载升级','',0,51,'/record/update',2,0,NULL,'glyphicon glyphicon-download-alt',1,3),(55,'三员工作管理','',0,51,'/record/secjob',2,0,NULL,'glyphicon glyphicon-briefcase',1,4),(56,'记录管理','',0,51,'/record/income',2,0,NULL,'glyphicon glyphicon-comment',1,5),(57,'知识库管理','',0,NULL,'#',1,0,NULL,'isw-chats',1,25),(58,'知识库','',0,57,'/knowledge/search',2,0,NULL,'glyphicon glyphicon-search',1,1),(59,'新知识','',0,57,'/knowledge/add',2,0,NULL,'glyphicon glyphicon-plus',1,2),(60,'知识管理控制台','',0,57,'/knowledge/list',2,0,NULL,'glyphicon glyphicon-th-large',1,3),(61,'报表与统计管理','',0,NULL,'#',1,0,NULL,'isw-graph',1,30),(62,'事件统计','',0,61,'/stats/incident/count',2,0,NULL,'glyphicon glyphicon-check',1,1),(63,'变更统计','',0,61,'/stats/change/count',2,0,NULL,'glyphicon glyphicon-check',1,2),(64,'配置项统计','',0,61,'/stats/cms/count',2,0,NULL,'glyphicon glyphicon-check',1,3),(65,'知识统计','',0,61,'/stats/knowledge/count',2,0,NULL,'glyphicon glyphicon-check',1,4),(66,'运维工作统计','',0,61,'/stats/leadertask',2,0,NULL,'glyphicon glyphicon-check',1,5),(67,'台账','',0,61,'/stats/account/get/1',2,0,NULL,'glyphicon glyphicon-list-alt',1,6),(68,'工作流管理','',0,NULL,'#',1,0,NULL,'isw-target',1,35),(69,'流程列表','',0,68,'/workflow/process/list',2,0,NULL,'glyphicon glyphicon-tasks',1,1),(70,'启动流程','',0,68,'/workflow/process/active-list',2,0,NULL,'glyphicon glyphicon-tasks',1,2),(71,'运行中的流程','',0,68,'/workflow/processinstance/running',2,0,NULL,'glyphicon glyphicon-tasks',1,3),(72,'运行中的任务','',0,68,'/workflow/task/list',2,0,NULL,'glyphicon glyphicon-tasks',1,4),(73,'已结束的流程','',0,68,'/workflow/processinstance/finished',2,0,NULL,'glyphicon glyphicon-ok-sign',1,5),(74,'模型工作区','',0,68,'/workflow/model/list',2,0,NULL,'glyphicon glyphicon-tasks',1,6),(75,'数据字典','',0,4,'/system/syscode/list',2,0,NULL,'glyphicon glyphicon-book',1,30),(76,'添加文档类别','',0,1,'/document/add-style',2,0,NULL,'glyphicon glyphicon-plus-sign',1,1),(77,'操作日志','',0,4,'/records/list',2,0,NULL,'glyphicon glyphicon-th',1,5),(78,'按钮','负责控制按钮的链接选此菜单',0,NULL,'#',1,0,NULL,'isw-refresh',1,1),(79,'button控制','',0,78,'#',2,0,NULL,'glyphicon glyphicon-thumbs-down',1,1),(80,'部门管理','',0,4,'/group/list',2,0,NULL,'glyphicon glyphicon-indent-left',1,1),(81,'运维控制台','',0,51,'/workflow/task/board',2,0,NULL,'glyphicon glyphicon-th-large',1,1),(82,'意见反馈','',0,NULL,'#',1,0,NULL,'isw-documents',1,1),(83,'新建反馈','',0,82,'/feedback/add',2,0,NULL,'glyphicon glyphicon-plus',1,1),(84,'反馈意见列表','',0,82,'/feedback/list',2,0,NULL,'glyphicon glyphicon-th-large',1,1);

/*Table structure for table `sys_resources` */

DROP TABLE IF EXISTS `sys_resources`;

CREATE TABLE `sys_resources` (
  `RESOURCE_ID` int(11) NOT NULL auto_increment,
  `RESOURCE_TYPE` varchar(100) default 'URL' COMMENT 'url, method',
  `RESOURCE_NAME` varchar(100) default NULL,
  `RESOURCE_DESC` varchar(200) default NULL,
  `RESOURCE_PATH` varchar(200) default NULL,
  `PRIORITY` varchar(100) default NULL,
  `ENABLE` int(4) default '1',
  `ISSYS` int(4) default '1',
  `MODULE_ID` int(11) default NULL,
  PRIMARY KEY  (`RESOURCE_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=112 DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `sys_resources` */

insert  into `sys_resources`(`RESOURCE_ID`,`RESOURCE_TYPE`,`RESOURCE_NAME`,`RESOURCE_DESC`,`RESOURCE_PATH`,`PRIORITY`,`ENABLE`,`ISSYS`,`MODULE_ID`) values (1,'URL','文档列表','文档管理路径','/document','1',1,1,2),(2,'URL','资产','资产路径','/Asset','1',1,1,NULL),(3,'AJAX','文档列表ajax','Ajax获取文档列表','/document/list3','1',1,1,2),(4,'URL','初始化添加文档','初始化添加文档','/document/add','1',1,1,3),(5,'URL','保存文档信息',NULL,'/document/save','1',1,1,2),(6,'URL','附件上传',NULL,'/attachment/plupload','1',1,1,NULL),(7,'URL','附件下载',NULL,'/attachment/download/*','1',1,1,NULL),(9,'URL','文档分类查询',NULL,'/document/list/style/*','1',1,1,2),(10,'URL','初始化更新文档版本',NULL,'/document/update_version','1',1,1,2),(11,'URL','左侧目录',NULL,'/menu','1',1,1,NULL),(12,'URL','头部',NULL,'/header','1',1,1,NULL),(13,'URL','contentbuttons',NULL,'/contentbuttons','1',1,1,NULL),(14,'URL','菜单列表',NULL,'/moudle/list','1',1,1,8),(15,'URL','保存菜单',NULL,'/moudle/save','1',1,1,8),(16,'AJAX','修改菜单状态，停用或启用',NULL,'/moudle/enable/*','1',1,1,8),(17,'URL','按照检查项查找文档',NULL,'/document/list/item/*','1',1,1,2),(22,'URL','权限管理',NULL,'/authority/list','1',1,1,15),(21,'URL','资源管理',NULL,'/resource/list','1',1,1,14),(23,'URL','保存权限',NULL,'/authority/save','1',1,1,15),(24,'URL','保存资源','','/resource/save','1',1,1,14),(25,'URL','资源信息-修改-初始化','这个说明怎么没写','/resource/init-update','1',1,1,14),(26,'URL','权限修改-初始化','初始化权限修改页面','/authority/init-update/*','1',1,1,15),(27,'URL','文档关联检查项','','/document/relation-item','1',1,1,15),(70,'URL','已结束的流程','','/workflow/processinstance/finished','1',1,1,73),(29,'URL','用户列表','','/user/list','1',1,1,16),(30,'URL','保存用户','保存用户form提交路径','/user/save','1',1,1,16),(31,'URL','添加类别页面初始化','','/style/add','1',1,1,17),(32,'URL','添加类别action','','/style/save','1',1,1,17),(33,'URL','关联文档页面初始化','','/checkitems/add','1',1,1,26),(34,'URL','关联文档保存','','/checkitems/save','1',1,1,26),(35,'URL','按照检查项查找','','/checkitems/list/items/*','1',1,1,28),(36,'URL','检查项列表','','/checkitems/bmb-list/*','1',1,1,28),(37,'URL','角色列表','','/role/list','1',1,1,29),(38,'URL','添加检查项页面初始化','','/checkitems/additems','1',1,1,30),(39,'URL','添加检查项Action','','/checkitems/saveitems','1',1,1,30),(40,'URL','配置项管理','','/cms/ci/list','1',1,1,50),(41,'URL','属性池管理','','/cms/property/list','1',1,1,46),(42,'URL','配置项类别管理','','/cms/category/list','1',1,1,47),(43,'URL','新建文档类别','','/document/add-style','5',1,1,3),(44,'URL','按照系统分类查看文档列表','','/document/sys-code-list/*','15',1,1,2),(45,'URL','初始化添加文档页','','/document/increases/*','1',1,1,2),(46,'URL','初始化添加检查项分类','','/checkitems/additems','1',1,1,30),(47,'URL','操作日志列表','','/records/list','1',1,1,77),(48,'URL','处理中的报修','','/incident/mylist','1',1,1,35),(49,'URL','我要报修','','/incident/addbyuser','5',1,1,34),(50,'URL','历史报修信息','','/incident/search','10',1,1,40),(51,'URL','创建新事件','','/incident/add','1',1,1,38),(52,'URL','创建新变更','','/change/add','1',1,1,42),(53,'URL','配置项关系管理','','/cms/relation/list','5',1,1,48),(54,'URL','配置项模型管理','','/cms/categoryrelation/list','10',1,1,49),(55,'URL','下载升级','','/record/update','1',1,1,54),(56,'URL','三员工作管理','','/record/secjob','1',1,1,55),(57,'URL','记录管理','','/record/income','1',1,1,56),(58,'URL','新知识','','/knowledge/add','1',1,1,59),(59,'URL','知识管理控制台','','/knowledge/list','1',1,1,60),(60,'URL','报表与统计管理-事件管理','','/stats/incident/*','1',1,1,62),(61,'URL','报表与统计管理-变更统计','','/stats/change/*','1',1,1,63),(62,'URL','报表与统计管理-配置项统计','','/stats/cms/*','1',1,1,64),(63,'URL','报表与统计管理-知识统计','','/stats/knowledge/*','1',1,1,65),(64,'URL','运维工作统计','','/stats/leadertask','1',1,1,66),(65,'URL','台账','','/stats/account/get/*','1',1,1,67),(66,'URL','流程列表','','/workflow/process/list','1',1,1,69),(67,'URL','启动流程','','/workflow/process/active-list','1',1,1,70),(68,'URL','运行中的流程','','/workflow/processinstance/running','1',1,1,71),(69,'URL','运行中的任务','','/workflow/task/list','1',1,1,72),(71,'URL','模型工作区','','/workflow/model/list','1',1,1,74),(72,'URL','日志搜索','','/records/search','1',1,1,77),(73,'URL','数据字典','','/system/syscode/list','1',1,1,75),(74,'URL','事件控制台','','/incident/list','1',1,1,39),(75,'URL','变更控制台','','/change/list','1',1,1,43),(76,'URL','领导交办','','/leadertask/list','1',1,1,52),(77,'URL','日常巡检','','/record/inspection','1',1,1,53),(78,'URL','知识库','','/knowledge/search','1',1,1,58),(79,'URL','历史变更查询','','/change/search','1',1,1,44),(80,'AJAX','启用用户链接','启用用户，三员之安全保密管理员权限','/user/enable/*','1',1,1,79),(81,'URL','事件处理','','/incident/deal/**','1',1,1,39),(82,'URL','跟踪处理过的事件','','/incident/mydealedlist','1',1,1,39),(83,'URL','新建配置项','','/cms/ci/add','1',1,1,50),(84,'URL','部门管理','','/group/list','1',1,1,80),(85,'URL','三员培训','','/record/training','1',1,1,56),(86,'URL','事件统计-自定义一维统计','','/stats/incident1','1',1,1,62),(87,'URL','事件统计-自定义二维统计','','/stats/incident','1',1,1,62),(88,'URL','变更统计-自定义一维统计','','/stats/change1','1',1,1,63),(89,'URL','变更统计-自定义二维统计','','/stats/change','1',1,1,63),(90,'URL','配置项统计-自定义一维统计','','/stats/cms1','1',1,1,64),(91,'URL','配置项统计-自定义二维统计','','/stats/cms','1',1,1,64),(92,'URL','知识统计-自定义统计','','/stats/knowledg*','1',1,1,65),(93,'URL','日常巡检统计','','/stats/inspection','1',1,1,66),(94,'URL','升级统计','','/stats/update','1',1,1,66),(95,'URL','三员工作统计','','/stats/secjob','1',1,1,66),(96,'URL','台账信息列表','','/stats/account/*','1',1,1,67),(97,'URL','事件列表','','/incident/list/*','1',1,1,39),(98,'URL','修改文档','','/document/edit/*','1',1,1,2),(99,'URL','编辑检查项','','/checkitems/update/*','1',1,1,26),(100,'URL','修改资源信息','','/resource/init-update/*','1',1,1,14),(101,'URL','运维控制台','','/workflow/task/board','1',1,1,81),(102,'URL','事件信息修改','','/incident/update/*','1',1,1,39),(103,'URL','变更审批','','/change/deal/**','1',1,1,42),(104,'URL','变更列表','','/change/list/*','1',1,1,42),(105,'URL','修改变更信息','','/change/update/*','1',1,1,42),(106,'URL','配置项属性维护','','/cms/ci/addproperty/*','1',1,1,50),(107,'URL','配置项详细信息','','/cms/ci/detail/*','1',1,1,50),(108,'URL','知识库详细信息','','/knowledge/detail/*','1',1,1,58),(109,'URL','修改知识库详细信息','','/knowledge/update/*','1',1,1,58),(110,'AJAX','获取部门列表json','','/group/all-json','1',1,1,79),(111,'URL','巡检记录填写','','/record/inspection/deal/**','1',1,1,53);

/*Table structure for table `sys_roles` */

DROP TABLE IF EXISTS `sys_roles`;

CREATE TABLE `sys_roles` (
  `ROLE_ID` int(11) NOT NULL auto_increment,
  `ROLE_NAME` varchar(100) NOT NULL,
  `ROLE_DESC` varchar(200) default NULL,
  `ISENABLE` int(1) default NULL,
  `ISSYS` int(1) default NULL,
  `MODULE_ID` int(11) default NULL,
  `REV_` int(11) default '1',
  `TYPE_` varchar(255) default NULL,
  PRIMARY KEY  (`ROLE_ID`),
  UNIQUE KEY `unique_role_name` (`ROLE_NAME`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `sys_roles` */

insert  into `sys_roles`(`ROLE_ID`,`ROLE_NAME`,`ROLE_DESC`,`ISENABLE`,`ISSYS`,`MODULE_ID`,`REV_`,`TYPE_`) values (1,'ROLE_ADMIN','超级管理员',1,1,NULL,1,NULL),(2,'ROLE_USER','普通用户',1,1,NULL,1,NULL),(4,'WK_SERVICE_DESK','服务台',0,1,NULL,1,NULL),(5,'WK_ENGINEER','运维工程师',0,1,NULL,1,NULL),(6,'WK_MANAGER','运维经理',0,1,NULL,1,NULL),(7,'WK_LEADER','运维主管',0,1,NULL,1,NULL),(8,'ROLE_WANGXIAO','王潇用于测试文档，检查项等的角色',0,0,NULL,1,NULL),(9,'sys_admin','系统管理员',0,1,NULL,1,NULL),(11,'security_secrecy_admin','安全保密管理员',0,1,NULL,1,NULL),(12,'security_auditor','安全审计员',0,1,NULL,1,NULL),(13,'check_item_view','只能查看检查项，用于给检查的人看的角色',0,0,NULL,1,NULL),(14,'ROLE_WORKFLOW_MANAGER','流程管理者',0,1,NULL,1,NULL);

/*Table structure for table `sys_roles_authorities` */

DROP TABLE IF EXISTS `sys_roles_authorities`;

CREATE TABLE `sys_roles_authorities` (
  `ID` int(11) NOT NULL auto_increment,
  `AUTHORITY_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

/*Data for the table `sys_roles_authorities` */

insert  into `sys_roles_authorities`(`ID`,`AUTHORITY_ID`,`ROLE_ID`) values (19,12,11),(6,2,1),(7,1,1),(9,4,8),(16,3,12),(15,8,12),(12,6,13),(13,5,13),(14,3,13),(17,9,2),(18,3,2),(25,13,6),(22,13,4),(23,6,4),(24,2,4),(31,14,7),(28,13,5),(29,5,5),(30,2,5);

/*Table structure for table `sys_roles_moudles` */

DROP TABLE IF EXISTS `sys_roles_moudles`;

CREATE TABLE `sys_roles_moudles` (
  `ID` int(11) NOT NULL auto_increment,
  `MODULE_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=1076 DEFAULT CHARSET=utf8 COMMENT='角色模块表——控制角色对模块的访问权，主要用于生成菜单';

/*Data for the table `sys_roles_moudles` */

insert  into `sys_roles_moudles`(`ID`,`MODULE_ID`,`ROLE_ID`) values (288,31,8),(287,3,8),(1044,47,4),(1043,40,4),(1042,32,4),(1041,59,4),(1040,37,4),(739,52,1),(738,39,1),(737,80,1),(736,58,1),(735,41,1),(734,15,1),(733,59,1),(732,17,1),(731,48,1),(730,43,1),(729,16,1),(728,60,1),(727,29,1),(726,69,1),(725,57,1),(724,14,1),(723,76,1),(722,63,1),(721,53,1),(720,3,1),(719,77,1),(718,37,1),(717,70,1),(716,72,1),(715,55,1),(714,64,1),(713,67,1),(712,54,1),(711,42,1),(710,73,1),(709,32,1),(708,8,1),(707,62,1),(706,45,1),(705,30,1),(704,51,1),(703,1,1),(286,2,8),(702,50,1),(701,46,1),(700,31,1),(699,71,1),(698,74,1),(697,68,1),(696,2,1),(695,38,1),(694,75,1),(693,25,1),(692,40,1),(691,28,1),(690,4,1),(689,47,1),(688,44,1),(687,65,1),(686,61,1),(1039,56,4),(1038,55,4),(1037,57,4),(685,56,1),(829,33,2),(828,36,2),(827,35,2),(826,60,2),(285,28,8),(284,38,8),(283,30,8),(282,37,8),(281,25,8),(684,49,1),(267,28,13),(268,32,13),(269,31,13),(270,25,13),(271,77,12),(272,4,12),(1072,25,5),(1071,39,5),(1070,38,5),(1069,60,5),(944,25,6),(943,68,6),(942,60,6),(941,31,6),(289,1,8),(290,32,8),(1036,52,4),(1035,42,4),(1034,41,4),(1033,45,4),(1032,44,4),(1031,51,4),(1030,31,4),(1029,25,4),(1028,39,4),(1027,28,4),(1026,43,4),(1025,84,4),(1024,54,4),(1023,38,4),(1022,60,4),(1021,53,4),(1068,40,5),(1067,52,5),(1066,83,5),(1065,41,5),(1064,54,5),(1063,56,5),(1062,43,5),(1061,84,5),(1060,44,5),(1059,58,5),(1058,32,5),(1057,51,5),(1056,82,5),(1055,2,5),(1054,28,5),(1053,45,5),(1052,42,5),(1051,31,5),(1050,55,5),(1049,53,5),(1048,59,5),(940,16,6),(939,70,6),(938,69,6),(937,45,6),(936,63,6),(935,73,6),(934,76,6),(1020,2,4),(986,38,7),(985,28,7),(946,54,6),(945,57,6),(984,31,7),(1019,82,4),(831,57,2),(830,34,2),(983,44,7),(982,26,7),(981,64,7),(980,25,7),(979,61,7),(978,51,7),(977,16,7),(976,52,7),(975,42,7),(974,1,7),(973,41,7),(972,34,7),(971,56,7),(970,37,7),(969,65,7),(968,63,7),(967,67,7),(966,2,7),(965,60,7),(964,53,7),(963,39,7),(962,35,7),(961,57,7),(960,43,7),(959,4,7),(958,54,7),(957,36,7),(956,76,7),(955,62,7),(954,66,7),(953,59,7),(952,81,7),(951,32,7),(950,58,7),(949,33,7),(948,55,7),(947,40,7),(1018,50,4),(1017,1,4),(1047,3,5),(1046,57,5),(740,66,1),(933,71,6),(932,56,6),(931,4,6),(930,46,6),(929,50,6),(928,64,6),(927,39,6),(926,66,6),(925,1,6),(924,42,6),(923,47,6),(922,28,6),(921,53,6),(920,41,6),(919,55,6),(918,74,6),(917,37,6),(916,59,6),(915,67,6),(914,44,6),(913,65,6),(912,62,6),(911,32,6),(910,58,6),(909,48,6),(908,30,6),(907,52,6),(906,72,6),(905,61,6),(904,2,6),(903,3,6),(902,26,6),(901,38,6),(900,51,6),(899,43,6),(898,40,6),(897,49,6),(1016,83,4),(1045,58,4),(1073,37,5),(1074,50,5),(1075,1,5);

/*Table structure for table `sys_users` */

DROP TABLE IF EXISTS `sys_users`;

CREATE TABLE `sys_users` (
  `USER_ID` int(11) NOT NULL auto_increment,
  `USERNAME` varchar(100) NOT NULL,
  `NAME` varchar(100) default NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `DT_CREATE` timestamp NULL default CURRENT_TIMESTAMP,
  `LAST_LOGIN` timestamp NULL default NULL,
  `DEADLINE` date default NULL,
  `LOGIN_IP` varchar(100) default NULL,
  `MECH_ID` varchar(100) default NULL,
  `MECH_NAME` varchar(100) default NULL,
  `DEP_ID` int(11) default NULL,
  `DEP_NAME` varchar(100) default NULL,
  `ENABLED` tinyint(1) default NULL,
  `ACCOUNT_NON_EXPIRED` tinyint(1) default NULL,
  `ACCOUNT_NON_LOCKED` tinyint(1) default NULL,
  `CREDENTIALS_NON_EXPIRED` tinyint(1) default NULL,
  `GROUP_ID` int(11) NOT NULL default '24',
  `REV_` int(11) default '1',
  `LAST_` varchar(255) default NULL,
  `EMAIL_` varchar(255) default NULL,
  `PWD_` varchar(255) default NULL,
  `PICTURE_ID_` varchar(64) default NULL,
  PRIMARY KEY  (`USER_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8 COMMENT='系统用户表';

/*Data for the table `sys_users` */

insert  into `sys_users`(`USER_ID`,`USERNAME`,`NAME`,`PASSWORD`,`DT_CREATE`,`LAST_LOGIN`,`DEADLINE`,`LOGIN_IP`,`MECH_ID`,`MECH_NAME`,`DEP_ID`,`DEP_NAME`,`ENABLED`,`ACCOUNT_NON_EXPIRED`,`ACCOUNT_NON_LOCKED`,`CREDENTIALS_NON_EXPIRED`,`GROUP_ID`,`REV_`,`LAST_`,`EMAIL_`,`PWD_`,`PICTURE_ID_`) values (1,'admin','admin','ceb4f32325eda6142bd65215f4c0f371','2015-08-18 00:00:00','2016-07-25 14:51:45',NULL,'0:0:0:0:0:0:0:1',NULL,NULL,1,NULL,1,0,0,0,24,1,NULL,NULL,NULL,NULL),(2,'user','用户','47a733d60998c719cf3526ae7d106d13','2015-10-27 00:00:00','2015-12-29 14:35:54',NULL,'0:0:0:0:0:0:0:1',NULL,NULL,2,NULL,0,0,0,0,22,1,NULL,NULL,NULL,NULL),(6,'chenweijia','陈维佳','75edb8a90139c29e96291c32b8a5c3c2','2015-12-13 22:46:46','2016-06-29 13:05:16',NULL,'0:0:0:0:0:0:0:1',NULL,NULL,3,'68961552',1,0,0,0,25,1,NULL,NULL,NULL,NULL),(7,'yangche','杨掣','4ab193f43ce70958fef5cd66edaa1b03','2015-12-13 22:47:34','2016-06-29 11:41:06',NULL,'0:0:0:0:0:0:0:1',NULL,NULL,4,NULL,1,0,0,0,25,1,NULL,NULL,NULL,NULL),(8,'xiyang','习阳','8a9fdb92f7033b8df56b7d950c3f3869','2015-12-13 22:47:51','2016-05-20 15:13:13',NULL,'0:0:0:0:0:0:0:1','0','312',5,'2345',1,0,0,0,24,1,NULL,NULL,NULL,NULL),(9,'likai','李凯','e068c6c9d28029103fb7a2980e8b3099','2015-12-13 22:48:01','2016-06-14 15:25:39',NULL,'0:0:0:0:0:0:0:1','0','311',5,'68965678',1,0,0,0,24,1,NULL,NULL,NULL,NULL),(10,'renxiaoxia','张处长','f7c41594222aa76b90a9a05d60b6df68','2015-12-13 22:48:51','2016-06-14 15:26:32',NULL,'0:0:0:0:0:0:0:1','0','311',6,'68967220',1,0,0,0,24,1,NULL,NULL,NULL,NULL),(11,'andy','何昀峰','57619160a70c40a481915e8f1fee202f','2015-12-13 22:58:02','2015-12-13 23:34:19',NULL,'0:0:0:0:0:0:0:1',NULL,'311',7,NULL,0,0,0,0,24,1,NULL,NULL,NULL,NULL),(52,'liulin','柳林','5c20dc5f2289c560231aec2d79a0852a','2016-05-19 13:50:28',NULL,NULL,NULL,'0','206',2,'4567',1,0,0,0,22,1,NULL,NULL,NULL,NULL),(17,'mating','马听听','204a952ea4d581af482505e5cd1e8355','2016-02-24 13:33:08','2016-07-19 11:08:39',NULL,'0:0:0:0:0:0:0:1',NULL,'311',10,NULL,1,0,0,0,25,1,NULL,NULL,NULL,NULL),(51,'ASD_51','dfdfd','5c20dc5f2289c560231aec2d79a0852a','2016-05-19 13:45:57',NULL,NULL,NULL,'0','33',3,'3333',0,0,0,0,22,1,NULL,NULL,NULL,NULL),(37,'liyang','李阳','2097d978845fcc0dadcd928fe0dfc1ee','2016-04-08 09:29:12',NULL,NULL,NULL,'0','1111',5,'68967777',1,0,0,0,22,1,NULL,NULL,NULL,NULL),(21,'guochangsheng','郭长胜','1e54f1b1243770a2cb5d8b51af36aed6','2016-02-29 14:57:11','2016-06-16 08:19:46',NULL,'0:0:0:0:0:0:0:1',NULL,'311',13,NULL,1,0,0,0,25,1,NULL,NULL,NULL,NULL),(22,'qinyongfeng','秦泳峰','93d0c693eca141dfcd7a749fb2917b49','2016-02-29 14:58:02','2016-07-07 10:14:44',NULL,'0:0:0:0:0:0:0:1',NULL,'311',10,NULL,1,0,0,0,25,1,NULL,NULL,NULL,NULL),(23,'wanghongqiang','王红强','ca4643aacee8ffe797aa3f12a0f152ad','2016-02-29 15:17:25','2016-06-16 07:51:12',NULL,'0:0:0:0:0:0:0:1',NULL,NULL,14,NULL,1,0,0,0,25,1,NULL,NULL,NULL,NULL),(24,'songle','宋乐','590aed8cbfaf0a49bad082951996fb34','2016-02-29 15:17:54','2016-07-19 11:09:12',NULL,'0:0:0:0:0:0:0:1',NULL,NULL,15,NULL,1,0,0,0,25,1,NULL,NULL,NULL,NULL),(38,'litao','李涛','40f59c0ce368b2d4691b55a94e6e45e4','2016-04-08 09:33:37',NULL,NULL,NULL,'0','606',15,'68967777',1,0,0,0,22,1,NULL,NULL,NULL,NULL),(46,'wangdan','王丹','269bcbda2abddecce09612ea208e3c6d','2016-04-20 16:48:54',NULL,NULL,NULL,'0','311',8,'7777',1,0,0,0,21,1,NULL,NULL,NULL,NULL),(47,'lichengyuan','李呈远','9ab844b9c7ab5adf882b7e8b17c64c57','2016-04-28 16:11:07','2016-07-07 10:31:14',NULL,'0:0:0:0:0:0:0:1',NULL,NULL,14,'68961117',1,0,0,0,25,1,NULL,NULL,NULL,NULL),(48,'zhangbin','张斌','efc3682889134fa86367b25d518c5d7b','2016-05-11 15:27:33',NULL,NULL,NULL,'0','3456',5,'3456',1,0,0,0,33,1,NULL,NULL,NULL,NULL),(49,'AAAAA_49','aaaa','c890882c73b9f071e6af0a26acd4ebb9','2016-05-16 15:01:10',NULL,NULL,NULL,'0','44',5,'4567',0,0,0,0,21,1,NULL,NULL,NULL,NULL),(50,'yanghaipeng','杨海鹏','200cf46cae3563387e7b331e03643670','2016-05-17 09:01:26','2016-06-30 09:56:11',NULL,'0:0:0:0:0:0:0:1',NULL,NULL,311,'5678',1,0,0,0,30,1,NULL,NULL,NULL,NULL),(53,'zhangsan','张三','ab5fa8ae73ed73c7454579294d4a41d1','2016-06-15 23:02:29',NULL,NULL,NULL,'10','708',6,'5555',1,0,0,0,22,1,NULL,NULL,NULL,NULL),(54,'lisi','李四','01a8b4d4da0aa57277a71202ac308b2c','2016-06-16 07:48:55',NULL,NULL,NULL,'10','608',10,'68559999',1,0,0,0,22,1,NULL,NULL,NULL,NULL),(55,'zhaoliu','赵六','d544f8d9a0fc0ac7d21920bc0b972816','2016-06-16 09:56:12',NULL,NULL,NULL,'20','609',15,'688889999',1,0,0,0,22,1,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_users_roles` */

DROP TABLE IF EXISTS `sys_users_roles`;

CREATE TABLE `sys_users_roles` (
  `ID` int(11) NOT NULL auto_increment,
  `ROLE_ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `sys_users_roles` */

insert  into `sys_users_roles`(`ID`,`ROLE_ID`,`USER_ID`) values (83,14,9),(101,9,1),(70,4,17),(103,14,1),(23,9,11),(58,5,21),(106,2,8),(102,11,1),(88,7,22),(24,5,6),(25,11,6),(108,5,50),(107,2,50),(61,5,24),(53,5,7),(87,6,22),(60,5,23),(57,7,10),(104,5,47),(71,14,17),(100,7,1),(99,1,1),(82,1,9),(89,14,22);

/*Table structure for table `wk_account` */

DROP TABLE IF EXISTS `wk_account`;

CREATE TABLE `wk_account` (
  `id` bigint(20) NOT NULL auto_increment,
  `name_` varchar(255) default NULL COMMENT '台账名称',
  `category` varchar(50) default NULL COMMENT '配置项类别',
  `fields` varchar(500) default NULL COMMENT '字段',
  `properties` varchar(500) default NULL COMMENT '属性',
  `created_user` varchar(255) default NULL,
  `created_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `wk_account` */

/*Table structure for table `wk_change` */

DROP TABLE IF EXISTS `wk_change`;

CREATE TABLE `wk_change` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(255) default NULL COMMENT '变更描述',
  `apply_user` varchar(255) default NULL COMMENT '申请人',
  `apply_time` datetime default NULL COMMENT '申请时间',
  `change_type` varchar(50) default NULL COMMENT '变更分类',
  `solution` varchar(500) default NULL COMMENT '变更方案',
  `fallback` varchar(500) default NULL COMMENT '回退方案',
  `plan_start_time` datetime default NULL COMMENT '变更开始时间',
  `plan_end_time` datetime default NULL COMMENT '变更结束时间',
  `template` varchar(255) default NULL,
  `template_data` varchar(500) default NULL,
  `influence` varchar(50) default NULL COMMENT '影响度',
  `critical` varchar(50) default NULL COMMENT '紧急性',
  `priority` varchar(50) default NULL COMMENT '优先级',
  `risk` varchar(50) default NULL COMMENT '风险等级',
  `status_` varchar(50) default NULL COMMENT '状态',
  `attachment` varchar(255) default NULL,
  `delegate_user` varchar(255) default NULL COMMENT '变更实施人',
  `approve` varchar(255) default NULL COMMENT '变更审批记录',
  `result` varchar(255) default NULL COMMENT '变更结果',
  `process_instance_id` varchar(255) default NULL,
  `end_time` datetime default NULL COMMENT '变更结束时间',
  `endbyuser` tinyint(1) default '0' COMMENT '是否正常关闭',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `wk_change` */

/*Table structure for table `wk_change_item` */

DROP TABLE IF EXISTS `wk_change_item`;

CREATE TABLE `wk_change_item` (
  `id` bigint(20) NOT NULL auto_increment,
  `change_id` bigint(20) default NULL,
  `ci_id` bigint(20) default NULL COMMENT '配置项id',
  `properties_name` varchar(500) default NULL COMMENT '属性名称',
  `properties` varchar(500) default NULL COMMENT '配置项属性列表',
  `old_value` text COMMENT '属性原值',
  `new_value` text COMMENT '属性新值',
  `created_time` datetime default NULL,
  `updated_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `wk_change_item` */

/*Table structure for table `wk_event` */

DROP TABLE IF EXISTS `wk_event`;

CREATE TABLE `wk_event` (
  `id` bigint(20) NOT NULL auto_increment,
  `abstract` varchar(255) default NULL,
  `apply_time` datetime default NULL,
  `apply_user` varchar(255) default NULL,
  `attachment` varchar(255) default NULL,
  `category` varchar(255) default NULL,
  `critical` varchar(255) default NULL,
  `current_delegate_group` varchar(255) default NULL,
  `current_delegate_user` varchar(255) default NULL,
  `detail` varchar(255) default NULL,
  `influence` varchar(255) default NULL,
  `phone_number` varchar(255) default NULL,
  `priority` varchar(255) default NULL,
  `process_instance_id` varchar(255) default NULL,
  `recover_time` datetime default NULL,
  `solution` varchar(255) default NULL,
  `source` varchar(255) default NULL,
  `status_` varchar(255) default NULL,
  `template` varchar(255) default NULL,
  `template_data` varchar(255) default NULL,
  `event_type` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `wk_event` */

/*Table structure for table `wk_feedback` */

DROP TABLE IF EXISTS `wk_feedback`;

CREATE TABLE `wk_feedback` (
  `id` int(11) NOT NULL auto_increment,
  `apply_user` varchar(255) default NULL COMMENT '事件请求人',
  `detail_` varchar(2000) NOT NULL COMMENT '反馈意见',
  `priority_` varchar(20) default NULL COMMENT '优先级',
  `state_` varchar(20) default NULL COMMENT '状态',
  `type_` varchar(20) default NULL COMMENT '分类',
  `completion` varchar(2000) default NULL COMMENT '完成情况',
  `create_time` datetime default NULL COMMENT '创建时间',
  `execution_time` datetime default NULL COMMENT '完成时间',
  `process_instance_id` varchar(255) default NULL,
  `status_` varchar(255) default NULL,
  `endbyuser` tinyint(1) default '0' COMMENT '是否正常关闭',
  `current_delegate_user` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `wk_feedback` */

/*Table structure for table `wk_incident` */

DROP TABLE IF EXISTS `wk_incident`;

CREATE TABLE `wk_incident` (
  `id` bigint(20) NOT NULL auto_increment,
  `apply_user` varchar(255) default NULL COMMENT '事件请求人',
  `phone_number` varchar(50) default NULL COMMENT '联系电话',
  `abstract` varchar(255) default NULL COMMENT '摘要',
  `detail` varchar(500) default NULL COMMENT '详细描述',
  `template` varchar(255) default NULL COMMENT '事件模板',
  `template_data` varchar(500) default NULL COMMENT '事件模板数据',
  `apply_time` datetime default NULL COMMENT '报告时间',
  `source` varchar(50) default NULL COMMENT '事件来源',
  `influence` varchar(50) default NULL COMMENT '影响度',
  `critical` varchar(50) default NULL COMMENT '紧急度',
  `priority` varchar(50) default NULL COMMENT '优先级',
  `type_` varchar(50) default NULL COMMENT '事件类型',
  `category` varchar(50) default NULL COMMENT '事件分类',
  `current_delegate_group` varchar(255) default NULL COMMENT '受派组',
  `current_delegate_user` varchar(255) default NULL COMMENT '受派人',
  `status_` varchar(50) default NULL COMMENT '状态',
  `solution` varchar(255) default NULL COMMENT '解决方案',
  `recover_time` datetime default NULL COMMENT '恢复时间',
  `attachment` varchar(255) default NULL COMMENT '附件',
  `process_instance_id` varchar(255) default NULL,
  `event_type` varchar(255) default NULL,
  `satisfaction` varchar(50) default '00' COMMENT '满意度',
  `feedback` varchar(255) default NULL COMMENT '反馈意见',
  `endbyuser` tinyint(1) default '0' COMMENT '是否正常关闭',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

/*Data for the table `wk_incident` */

/*Table structure for table `wk_income` */

DROP TABLE IF EXISTS `wk_income`;

CREATE TABLE `wk_income` (
  `id` bigint(20) NOT NULL auto_increment,
  `person_count` int(11) default NULL COMMENT '人数',
  `person_name` varchar(255) default NULL COMMENT '人名',
  `person_of_company` varchar(255) default NULL COMMENT '单位',
  `accompany` varchar(255) default NULL COMMENT '陪同人员',
  `in_time` datetime default NULL COMMENT '进入时间',
  `out_time` datetime default NULL COMMENT '离开时间',
  `created_time` datetime default NULL COMMENT '创建时间',
  `created_user` varchar(255) default NULL COMMENT '创建用户',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `wk_income` */

/*Table structure for table `wk_inspection` */

DROP TABLE IF EXISTS `wk_inspection`;

CREATE TABLE `wk_inspection` (
  `id` bigint(20) NOT NULL auto_increment,
  `template` varchar(255) default NULL COMMENT '模板',
  `template_data` text COMMENT '模板数据',
  `execution_user` varchar(255) default NULL COMMENT '执行人',
  `execution_time` datetime default NULL COMMENT '执行时间',
  `status_` varchar(50) default NULL,
  `process_instance_id` varchar(255) default NULL,
  `created_time` datetime default NULL COMMENT '发起时间',
  `incident_id` bigint(20) default NULL COMMENT '事件单号',
  `endbyuser` tinyint(1) default '0' COMMENT '是否正常关闭',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `wk_inspection` */

/*Table structure for table `wk_itil_relation` */

DROP TABLE IF EXISTS `wk_itil_relation`;

CREATE TABLE `wk_itil_relation` (
  `id` bigint(20) NOT NULL auto_increment,
  `primary_id` bigint(20) default NULL,
  `secondary_id` bigint(20) default NULL,
  `relation_type` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `wk_itil_relation` */

/*Table structure for table `wk_knowledge` */

DROP TABLE IF EXISTS `wk_knowledge`;

CREATE TABLE `wk_knowledge` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `desc_` varchar(255) default NULL,
  `solution` text,
  `keyword` varchar(255) default NULL,
  `apply_user` varchar(255) default NULL,
  `apply_time` datetime default NULL,
  `category` varchar(50) default NULL,
  `status_` varchar(50) default NULL,
  `is_locked` tinyint(1) default NULL,
  `process_instance_id` varchar(255) default NULL,
  `hits` bigint(20) default NULL COMMENT '点击数',
  `last_read_time` datetime default NULL COMMENT '最后阅读时间',
  `update_time` datetime default NULL,
  `endbyuser` tinyint(1) default '0' COMMENT '是否正常关闭',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `wk_knowledge` */

/*Table structure for table `wk_sec_job` */

DROP TABLE IF EXISTS `wk_sec_job`;

CREATE TABLE `wk_sec_job` (
  `id` bigint(20) NOT NULL auto_increment,
  `type_` varchar(50) default NULL COMMENT '三员工作类别',
  `user_id` varchar(255) default NULL COMMENT '分配用户',
  `attachment` varchar(255) default NULL COMMENT '附件',
  `apply_time` datetime default NULL COMMENT '申请时间',
  `execution_time` datetime default NULL COMMENT '完成时间',
  `process_instance_id` varchar(255) default NULL,
  `endbyuser` tinyint(1) default '0' COMMENT '是否正常关闭',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `wk_sec_job` */

/*Table structure for table `wk_task` */

DROP TABLE IF EXISTS `wk_task`;

CREATE TABLE `wk_task` (
  `id` bigint(20) NOT NULL auto_increment,
  `from_user` varchar(255) default NULL COMMENT '交办领导',
  `to_user` varchar(255) default NULL COMMENT '交办对象',
  `task_title` varchar(255) default NULL COMMENT '任务标题',
  `task_content` varchar(255) default NULL COMMENT '任务内容',
  `task_result` varchar(255) default NULL COMMENT '工作结果',
  `process_instance_id` varchar(255) default NULL,
  `apply_time` datetime default NULL COMMENT '申请时间',
  `due_time` datetime default NULL COMMENT '到期时间',
  `user_id` varchar(255) default NULL,
  `execution_time` datetime default NULL COMMENT '执行完成时间',
  `current_activity_id` varchar(255) default NULL,
  `current_activity_name` varchar(255) default NULL,
  `task_desc` varchar(255) default NULL,
  `endbyuser` tinyint(1) default '0' COMMENT '是否正常关闭',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `wk_task` */

/*Table structure for table `wk_training` */

DROP TABLE IF EXISTS `wk_training`;

CREATE TABLE `wk_training` (
  `id` bigint(20) NOT NULL auto_increment,
  `user_name` varchar(255) default NULL COMMENT '用户',
  `company_` varchar(255) default NULL COMMENT '培训单位',
  `certification` varchar(255) default NULL COMMENT '资格证书',
  `certification_no` varchar(255) default NULL COMMENT '证书编号',
  `expiry_date` datetime default NULL COMMENT '有效期',
  `created_time` datetime default NULL,
  `created_user` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `wk_training` */

/*Table structure for table `wk_update` */

DROP TABLE IF EXISTS `wk_update`;

CREATE TABLE `wk_update` (
  `id` bigint(20) NOT NULL auto_increment,
  `type_` varchar(255) default NULL,
  `version_` varchar(255) default NULL,
  `update_time` datetime default NULL,
  `userid` varchar(255) default NULL,
  `process_instance_id` varchar(255) default NULL,
  `execution_time` datetime default NULL,
  `created_time` datetime default NULL,
  `name_` varchar(255) default NULL,
  `source` varchar(255) default NULL,
  `endbyuser` tinyint(1) default '0' COMMENT '是否正常关闭',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `wk_update` */

/*Table structure for table `wk_update_record` */

DROP TABLE IF EXISTS `wk_update_record`;

CREATE TABLE `wk_update_record` (
  `id` bigint(20) NOT NULL auto_increment,
  `name_` varchar(255) default NULL,
  `process_instance_id` varchar(255) default NULL,
  `update_time` datetime default NULL,
  `userid` varchar(255) default NULL,
  `version_` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `wk_update_record` */

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
