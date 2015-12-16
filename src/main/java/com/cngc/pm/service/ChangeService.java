package com.cngc.pm.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cngc.pm.model.Change;
import com.googlecode.genericdao.search.SearchResult;

public interface ChangeService {
	
	void save(Change change);
	boolean delById(Long id);
	Change getById(Long id);
	List<Change> getAll();
	Map<String,Object> getCountByStatus();
	SearchResult<Change> getByStatus(String status);
	SearchResult<Change> getNotFinished();
	Map<String,Object> getCountByCategory(String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime,String status);
	SearchResult<Change> search(String description,String applyUser,String engineer,String risk,Date startDate,Date endDate);
	boolean updateCi(Long changeId);
	SearchResult<Change> getByIds(List<Long> ids);
}
