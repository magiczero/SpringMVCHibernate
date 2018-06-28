package com.cngc.pm.service.cms;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

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

	void subAccountCommit(AuditTask at, Group group);

	List<Map<String, Object>> getCiMapList(AuditTask at, List<Ci> result) throws JsonParseException, JsonMappingException, IOException;
	
	AuditTask getByGroupAndCategory(Group group, Category category);

	void endTask(Long id) throws Exception;

	List<Ci> getCiListByGroupAndCategory(Category ciCategory, Group ciDepartment);
	
}
