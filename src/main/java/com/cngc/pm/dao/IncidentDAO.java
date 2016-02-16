package com.cngc.pm.dao;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.Incident;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface IncidentDAO extends GenericDAO<Incident, Long> {

	Map<String, Object> getCountByStatus();

	SearchResult<Incident> getByStatus(String status);

	SearchResult<Incident> getNotFinished();

	Map<String, Object> getCountByCategory(String startTime, String endTime);

	/**
	 * 获取统计信息
	 * 
	 * @param column
	 * @param row
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @return
	 */
	Map<String, Object> getStats(String column, String row, String startTime, String endTime, String status);

	//SearchResult<Incident> search(String abs, String applyUser, String engineer, String satisfaction, Date startTime,
	//		Date endTime);

	SearchResult<Incident> getByApplyUser(String user);

	SearchResult<Incident> getByApplyUser(String user, Boolean bClose);

	SearchResult<Incident> getByIds(List<Long> ids);

	/**
	 * @param processInstanceIds
	 * @return
	 */
	SearchResult<Incident> getByProcessInstance(List<String> processInstanceIds);
	
}