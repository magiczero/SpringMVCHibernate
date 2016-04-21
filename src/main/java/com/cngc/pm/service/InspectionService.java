package com.cngc.pm.service;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.Inspection;
import com.googlecode.genericdao.search.SearchResult;

public interface InspectionService {
	void save(Inspection inspection);
	Inspection getById(Long id);
	List<Inspection> getAll();
	SearchResult<Inspection> search(String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime);
	Long getIdByProcessInstance(String processInstanceId);
}
