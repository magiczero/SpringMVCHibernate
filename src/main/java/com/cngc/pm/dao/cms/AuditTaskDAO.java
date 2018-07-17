package com.cngc.pm.dao.cms;

import java.util.Collection;
import java.util.List;

import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.AuditTask;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface AuditTaskDAO extends GenericDAO<AuditTask, Long> {

	/**
	 * 获取本人发起的审核任务
	 * @param assessor
	 * @return
	 */
	List<AuditTask> getListByAssessor(SysUser assessor,int iDisplayStart,int iDisplayLength);

	List<AuditTask> getListByGroup(Group group,int iDisplayStart,int iDisplayLength);
	
	void update(AuditTask at);
	
	/**
	 * 根据部门和种类找到未完成的任务
	 * @param group
	 * @param category
	 * @return
	 */
	AuditTask getByGroupAndCode(Group group, Category category);
	
	SearchResult<Ci> getCiList(Long atId,Collection<String> groupIdSet, Collection<String> codeSet, int iDisplayStart, int iDisplayLength);

	/**
	 * 根据条件获取数量
	 * @param at
	 * @param groupId
	 * @param categoryCode
	 * @param isCommit
	 * @param status
	 * @return
	 */
	int getCountByCondition(long at, Long groupId, String categoryCode, boolean isCommit, String status);
}
