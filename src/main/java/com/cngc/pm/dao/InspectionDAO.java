package com.cngc.pm.dao;

import java.util.Map;

import com.cngc.pm.model.Inspection;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface InspectionDAO extends GenericDAO<Inspection,Long>{
	SearchResult<Inspection> search(String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime);
	SearchResult<Inspection> getByProcessInstance(String processInstanceId);
	SearchResult<Inspection> getNotFinishedTask();
}
