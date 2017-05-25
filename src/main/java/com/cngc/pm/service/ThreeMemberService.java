package com.cngc.pm.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.cngc.exception.ResourceNotFoundException;
import com.cngc.pm.exception.NotDeleteAuthorityException;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.Style;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.manage.ManageType;
import com.cngc.pm.model.manage.ManagerForm;
import com.cngc.pm.model.manage.Relations;
import com.cngc.pm.model.manage.Relationship;
import com.cngc.pm.model.manage.WorkRecord;
import com.googlecode.genericdao.search.SearchResult;

public interface ThreeMemberService {

	/**
	 * 根据角色选择要填写的项
	 * @param type
	 * @return
	 */
	List<Relations> getRelationItemListByType(ManageType type);
	
	List<Relations> getRelationListByType(ManageType type);
	
	void save(ManagerForm form, Task task);
	
	ManagerForm getFormByPid(String pid);
	
	Map<String, Object> getDocumentData(ManagerForm mf) throws JsonParseException, JsonMappingException, IOException;
	
	Style getSystem();

	/**
	 * 验证某个cmdb的三员角色是否已经存在
	 * @param cmdb  需要验证的CMDB
	 * @param mt	三员角色
	 * @return
	 */
	boolean haveRelationshipByCmdbAndRole(Ci cmdb, ManageType mt);

	/**
	 * 批量保存某个角色的cmdb
	 * @param sysUser
	 * @param mt
	 * @param cmdbList
	 */
	void saveRelationshipWithBatch(SysUser sysUser, ManageType mt, List<Ci> cmdbList);

	/**
	 * 验证某个人是否已经是某个cmdb的三员
	 * @param cmdb
	 * @param selectedUser
	 * @return
	 */
	boolean haveRelationshipByCmdbAndUser(Ci cmdb, SysUser selectedUser);

	List<Relationship> getAllRelationshipList();

	/**
	 * 获取某个单位下的所有cmdb的三员信息
	 * @param group
	 * @return
	 */
	List<Relationship> getRelationshipListByUnit(Group group);

	/**
	 * 根据条件查找设备的三员
	 * @param unit   设备的单位
	 * @param searchCmdb  查找的设备
	 * @param mt		查找的角色
	 * @param searchUser	查找的三员人员
	 * @return
	 */
	List<Relationship> getRelationshipList(Group unit, Ci searchCmdb, ManageType mt, SysUser searchUser);

	/**
	 * 根据条件查询工作记录
	 * @param searchUser		填写人
	 * @param mt				角色
	 * @param searchCmdb		产品或系统
	 * @param startTime_		开始时间
	 * @param endTime_			结束时间
	 * @param iDisplayStart		分页开始
	 * @param iDisplayLength	每页条数
	 * @return
	 */
	SearchResult<WorkRecord> getWorkrecord(SysUser searchUser, ManageType mt, Ci searchCmdb, Date startTime_, Date endTime_,
			int iDisplayStart, int iDisplayLength);

	List<Relationship> getRelationshipListByUser(SysUser currentUser);

	/**
	 * 到权限表中查找CMDB的三员角色
	 * @param cmdbId
	 * @return
	 */
	ManageType getMtByCmdb(SysUser user,int cmdbId);

	/**
	 * 根据操作项与角色获取Relations
	 * @param itemId
	 * @param mt
	 * @return
	 */
	List<Relations> getRelationListByItemAndMt(int itemId, int mt);

	/**
	 * 根据条件获得唯一的Relations
	 * @param mt
	 * @param actionId
	 * @param itemId
	 * @return
	 */
	Relations getRelationOne(ManageType mt, int actionId, int itemId);

	/**
	 * 根据条件获得Relationship
	 * @param mt
	 * @param currentUser
	 * @param cmdbId
	 * @return
	 */
	Relationship getRelationshipOne(ManageType mt, SysUser currentUser, int cmdbId);

	boolean saveWorkrecord(WorkRecord wr);

	boolean deleteWorkrecordById(Long id, SysUser user) throws ResourceNotFoundException,NotDeleteAuthorityException;
}
