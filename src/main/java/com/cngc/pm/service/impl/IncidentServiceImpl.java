package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cngc.pm.dao.IncidentDAO;
import com.cngc.pm.model.Incident;
import com.cngc.pm.service.IncidentService;

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
}
