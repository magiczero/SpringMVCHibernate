package com.cngc.pm.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.dao.UpdateDAO;
import com.cngc.pm.model.Update;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class UpdateDAOImpl extends BaseDAOImpl<Update,Long> implements UpdateDAO{
	@Autowired
	private StatsDAO statsDao;

	
	@Override
	public SearchResult<Update> search(String startTime,String endTime)
	{
		Search search = new Search();
		search.addFilterCustom("DATE(created_time)>='"+startTime+"'");
		search.addFilterCustom("DATE(created_time)<='"+endTime+"'");
		
		return this.searchAndCount(search);
	}
	@Override
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime)
	{
		return statsDao.getStats("UPDATE", column, row, startTime, endTime, null);
	}
	
}
