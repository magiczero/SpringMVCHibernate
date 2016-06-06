package com.cngc.pm.service;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.Update;
import com.googlecode.genericdao.search.SearchResult;


public interface UpdateService {

	List<Update> getAll();
	SearchResult<Update> search(String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime);
	SearchResult<Update> getNotFinishedTask();
}
