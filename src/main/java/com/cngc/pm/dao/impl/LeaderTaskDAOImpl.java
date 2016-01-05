package com.cngc.pm.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.LeaderTaskDAO;
import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.model.LeaderTask;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class LeaderTaskDAOImpl extends BaseDAOImpl<LeaderTask,Long> implements LeaderTaskDAO{
	@Autowired
	private StatsDAO statsDao;

	@Override
	public SearchResult<LeaderTask> getNotFinishedTask()
	{
		Search search = new Search();
		search.addFilterEmpty("executionTime");
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<LeaderTask> search(String startTime,String endTime)
	{
		Search search = new Search();
		search.addFilterCustom("DATE(apply_time)>='"+startTime+"'");
		search.addFilterCustom("DATE(apply_time)<='"+endTime+"'");
		
		return this.searchAndCount(search);
	}
	@Override
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime)
	{
		return statsDao.getStats("LEADERTASK", column, row, startTime, endTime, null);
	}
	
}
