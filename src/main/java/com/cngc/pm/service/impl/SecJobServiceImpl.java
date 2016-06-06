package com.cngc.pm.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.SecJobDAO;
import com.cngc.pm.model.SecJob;
import com.cngc.pm.service.SecJobService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class SecJobServiceImpl implements SecJobService{
	@Autowired
	private SecJobDAO secjobDao;
	
	@Override
	@Transactional
	public SearchResult<SecJob> search(String startTime,String endTime)
	{
		return secjobDao.search(startTime, endTime);
	}
	@Override
	@Transactional
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime)
	{
		return secjobDao.getStats(column, row, startTime, endTime);
	}
	@Override
	@Transactional
	public SearchResult<SecJob> getNotFinishedTask()
	{
		return secjobDao.getNotFinishedTask();
	}
}
