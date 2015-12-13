package com.cngc.pm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.UpdateDAO;
import com.cngc.pm.model.Update;
import com.cngc.pm.service.UpdateService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class UpdateServiceImpl implements UpdateService{
	@Autowired
	private UpdateDAO updateDao;
	
	@Override
	@Transactional
	public List<Update> getAll()
	{
		return updateDao.findAll();
	}
	@Override
	@Transactional
	public SearchResult<Update> search(String startTime,String endTime)
	{
		return updateDao.search(startTime, endTime);
	}
	@Override
	@Transactional
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime)
	{
		return updateDao.getStats(column, row, startTime, endTime);
	}
}
