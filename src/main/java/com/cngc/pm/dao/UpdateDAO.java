package com.cngc.pm.dao;

import java.util.Map;

import com.cngc.pm.model.Update;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface UpdateDAO extends GenericDAO<Update,Long> {
	SearchResult<Update> search(String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime);
	SearchResult<Update> getNotFinishedTask();
}
