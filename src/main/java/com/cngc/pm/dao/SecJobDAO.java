package com.cngc.pm.dao;

import java.util.Map;

import com.cngc.pm.model.SecJob;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface SecJobDAO extends GenericDAO<SecJob,Long>{
	SearchResult<SecJob> search(String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime);
}
