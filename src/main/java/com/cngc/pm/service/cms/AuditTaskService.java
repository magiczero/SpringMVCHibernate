package com.cngc.pm.service.cms;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.cngc.exception.BusinessException;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.AuditTask;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.googlecode.genericdao.search.SearchResult;

public interface AuditTaskService {

	AuditTask startTask(AuditTask auditTask);
	
	AuditTask getById(Long id);
	
	AuditTask findUnfinishedTask(SysUser assessor);
	
	AuditTask findUnfinishedTask(Group group);
	
	AuditTask findUnfinishedTask(Group group, Category category);

	void endTask(SysUser auditMasterUser) throws Exception;

	List<Map<String, Object>> getListByUser(SysUser user,int iDisplayStart,int iDisplayLength) throws Exception;

	AuditTask isUnTaskWithUser(Long id, SysUser currentUser) throws Exception;

	SearchResult<Ci> getCiList(Collection<String> groupIdSet, Collection<String> codeSet, int iDisplayStart, int iDisplayLength);
	
	SearchResult<Ci> getCiListByAudit(Long auditTaskId,Collection<String> groupIdSet, Collection<String> codeSet, int iDisplayStart, int iDisplayLength);

	void subAccountCommit(AuditTask at, Group group) throws BusinessException;

	List<Map<String, Object>> getCiMapList(AuditTask at, List<Ci> result) throws Exception;
	
	AuditTask getByGroupAndCategory(Group group, Category category);

	void endTask(Long id) throws BusinessException, JsonParseException, JsonMappingException, IOException;

	List<Ci> getCiListByGroupAndCategory(Category ciCategory, Group ciDepartment);

	/**
	 * 确认操作
	 * @param at
	 * @param ci
	 */
	void verity(AuditTask at, Ci ci);

	/**
	 * 批量确认操作
	 * @param at
	 * @param ids
	 */
	void verityByCiids(AuditTask at, Long[] ids);

	void reviewAudit(Long at, Long group) throws BusinessException;

	/**
	 * 根据条件查找数量
	 * @param at
	 * @param id
	 * @param categoryCode
	 * @param commit 是否提交
	 * @param status		//状态码
	 * @return
	 */
	int getCountByStatus(long at, Long groupId, String categoryCode,boolean isCommit, String status);
	
}
