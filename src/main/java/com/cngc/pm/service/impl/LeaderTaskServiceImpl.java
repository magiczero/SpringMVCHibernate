package com.cngc.pm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.LeaderTaskDAO;
import com.cngc.pm.model.LeaderTask;
import com.cngc.pm.service.LeaderTaskService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class LeaderTaskServiceImpl implements LeaderTaskService{

	@Autowired
	private LeaderTaskDAO leaderTaskDao;
	
	@Override
	@Transactional
	public void save(LeaderTask leaderTask){
		leaderTaskDao.save(leaderTask);
	}
	
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		leaderTaskDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public LeaderTask getById(Long id){
		return leaderTaskDao.find(id);
	}
	
	@Override
	@Transactional
	public List<LeaderTask> getAll()
	{
		return leaderTaskDao.findAll();
	}
	
	@Override
	@Transactional
	public SearchResult<LeaderTask> getNotFinishedTask()
	{
		return leaderTaskDao.getNotFinishedTask();
	}
	@Override
	@Transactional
	public SearchResult<LeaderTask> search(String startTime,String endTime)
	{
		return leaderTaskDao.search(startTime, endTime);
	}
	@Override
	@Transactional
	public SearchResult<LeaderTask> search(List<String> processInstanceIds, String startTime,String endTime)
	{
		return leaderTaskDao.search(processInstanceIds, startTime, endTime);
	}
	@Override
	@Transactional
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime)
	{
		return leaderTaskDao.getStats(column, row, startTime, endTime);
	}
}
