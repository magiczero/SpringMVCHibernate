package com.cngc.pm.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.SecJobDAO;
import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.model.SecJob;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class SecJobDAOImpl extends BaseDAOImpl<SecJob, Long> implements SecJobDAO {
	@Autowired
	private StatsDAO statsDao;

	@Override
	public SearchResult<SecJob> search(String startTime, String endTime) {
		Search search = new Search();
		search.addFilterCustom("DATE(apply_time)>='" + startTime + "'");
		search.addFilterCustom("DATE(apply_time)<='" + endTime + "'");

		return this.searchAndCount(search);
	}

	@Override
	public Map<String, Object> getStats(String column, String row, String startTime, String endTime) {
		return statsDao.getStats("SECJOB", column, row, startTime, endTime, null);
	}
	
	@Override
	public SearchResult<SecJob> getNotFinishedTask()
	{
		Search search = new Search();
		search.addFilterEmpty("executionTime");
		
		return this.searchAndCount(search);
	}
}
