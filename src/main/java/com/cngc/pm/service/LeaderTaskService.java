package com.cngc.pm.service;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.LeaderTask;
import com.googlecode.genericdao.search.SearchResult;

public interface LeaderTaskService {

	void save(LeaderTask leaderTask);
	boolean delById(Long id);
	LeaderTask getById(Long id);
	List<LeaderTask> getAll();
	SearchResult<LeaderTask> getNotFinishedTask();
	SearchResult<LeaderTask> search(String startTime,String endTime);
	SearchResult<LeaderTask> search(List<String> processInstanceIds, String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime);
	Long getIdByProcessInstance(String processInstanceId);
}
