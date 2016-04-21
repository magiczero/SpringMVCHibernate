package com.cngc.pm.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.InspectionDAO;
import com.cngc.pm.dao.StatsDAO;
import com.cngc.pm.model.Inspection;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class InspectionDAOImpl extends BaseDAOImpl<Inspection,Long> implements InspectionDAO{
	@Autowired
	private StatsDAO statsDao;
	
	@Override
	public SearchResult<Inspection> search(String startTime,String endTime)
	{
		Search search = new Search();
		search.addFilterCustom("DATE(created_time)>='"+startTime+"'");
		search.addFilterCustom("DATE(created_time)<='"+endTime+"'");
		
		return this.searchAndCount(search);
	}
	@Override
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime)
	{
		return statsDao.getStats("INSPECTION", column, row, startTime, endTime, null);
	}
	@Override
	public SearchResult<Inspection> getByProcessInstance(String processInstanceId) {
		Search search = new Search();
		search.addFilterEqual("processInstanceId", processInstanceId);
		return this.searchAndCount(search);
	}
}
