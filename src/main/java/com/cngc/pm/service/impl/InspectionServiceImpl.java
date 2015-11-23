package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.InspectionDAO;
import com.cngc.pm.model.Inspection;
import com.cngc.pm.service.InspectionService;

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
}
