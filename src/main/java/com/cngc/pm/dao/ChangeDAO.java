package com.cngc.pm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cngc.pm.model.Change;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface ChangeDAO extends GenericDAO<Change,Long>{

	Map<String,Object> getCountByStatus();
	SearchResult<Change> getByStatus(String status);
	SearchResult<Change> getNotFinished();
	Map<String,Object> getCountByCategory(String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime,String status);
	SearchResult<Change> search(String description,String applyUser,String engineer,String risk,Date startDate,Date endDate);
	SearchResult<Change> getByIds(List<Long> ids);
}
