package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.IncidentDAO;
import com.cngc.pm.model.Incident;
import com.cngc.pm.service.IncidentService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class IncidentServiceImpl implements IncidentService{

	@Autowired
	private IncidentDAO incidentDao;
	
	@Override
	@Transactional
	public void save(Incident incident)
	{
		incidentDao.save(incident);
	}
	
	@Override
	@Transactional
	public boolean delByIds(String ids)
	{
		String id[] = ids.split(",");
		int j = id.length;
		Long[] idss = new Long[j];
		for(int i=0; i<id.length; i++) {
			String str = id[i];
			 if (!isNumeric(str)) {
				 return false;
			 }
			 idss[i] = Long.valueOf(str);
		}
		try {
			for(Long k : idss) {
				incidentDao.removeByIds(k);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	

	@Override
	@Transactional
	public boolean delById(Long id)
	{
		incidentDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Incident getById(Long id){
		return incidentDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Incident> getAll()
	{
		return incidentDao.findAll();
	}
	@Override
	@Transactional
	public Map<String,Object> getCountByStatus()
	{
		return incidentDao.getCountByStatus();
	}
	@Override
	@Transactional
	public SearchResult<Incident> getByStatus(String status)
	{
		return incidentDao.getByStatus(status);
	}
	@Override
	@Transactional
	public SearchResult<Incident> search(String abs,String applyUser,String engineer,String satisfaction,Date startTime,Date endTime)
	{
		return incidentDao.search(abs,applyUser, engineer, satisfaction, startTime, endTime);
	}
	@Override
	@Transactional
	public SearchResult<Incident> getNotFinished()
	{
		return incidentDao.getNotFinished();
	}
	@Override
	@Transactional
	public SearchResult<Incident> getByProcessInstance(List<String> processInstanceIds)
	{
		return incidentDao.getByProcessInstance(processInstanceIds);
	}
	@Override
	@Transactional
	public Map<String,Object> getCountByCategory(String startTime,String endTime)
	{
		return incidentDao.getCountByCategory(startTime,endTime);
	}
	@Override
	@Transactional
	public Map<String,Object> getStats(String column,String row,String startTime,String endTime,String status)
	{
		return incidentDao.getStats(column, row,startTime,endTime,status);
	}
	@Override
	@Transactional
	public SearchResult<Incident> getByApplyUser(String user)
	{
		return incidentDao.getByApplyUser(user);
	}
	@Override
	@Transactional
	public SearchResult<Incident> getByApplyUser(String user,Boolean bClose)
	{
		return incidentDao.getByApplyUser(user, bClose);
	}
	@Override
	@Transactional
	public SearchResult<Incident> getByIds(List<Long> ids)
	{
		return incidentDao.getByIds(ids);
	}

}
