package com.cngc.pm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.InspectionDAO;
import com.cngc.pm.model.Inspection;
import com.cngc.pm.service.InspectionService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class InspectionServiceImpl implements InspectionService{

	@Autowired
	private InspectionDAO inspectionDao;
	
	@Override
	@Transactional
	public void save(Inspection inspection)
	{
		inspectionDao.save(inspection);
	}
	
	@Override
	@Transactional
	public List<Inspection> getAll()
	{
		return inspectionDao.findAll();
	}
	@Override
	@Transactional
	public Inspection getById(Long id)
	{
		return inspectionDao.find(id);
	}
	@Override
	@Transactional
	public SearchResult<Inspection> search(String startTime,String endTime)
	{
		return inspectionDao.search(startTime, endTime);
	}
	@Override
	@Transactional
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime)
	{
		return inspectionDao.getStats(column, row, startTime, endTime);
	}
	@Override
	@Transactional
	public Long getIdByProcessInstance(String processInstanceId)
	{
		SearchResult<Inspection> re = inspectionDao.getByProcessInstance(processInstanceId);
		if(re.getTotalCount()==0)
			return Long.getLong("0");
		else
			return re.getResult().get(0).getId();
	}	
	@Override
	@Transactional
	public SearchResult<Inspection> getNotFinishedTask()
	{
		return inspectionDao.getNotFinishedTask();
	}
}
