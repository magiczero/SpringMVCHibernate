package com.cngc.pm.dao;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.LeaderTask;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface LeaderTaskDAO extends GenericDAO<LeaderTask,Long>{

	SearchResult<LeaderTask> getNotFinishedTask();
	SearchResult<LeaderTask> search(String startTime,String endTime);
	SearchResult<LeaderTask> search(List<String> processInstanceIds, String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime);
	SearchResult<LeaderTask> getByProcessInstance(String processInstanceId);
}
