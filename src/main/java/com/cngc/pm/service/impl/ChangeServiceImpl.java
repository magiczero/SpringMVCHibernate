package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ChangeDAO;
import com.cngc.pm.model.Change;
import com.cngc.pm.service.ChangeService;

@Service
public class ChangeServiceImpl implements ChangeService{
	
	@Autowired
	private ChangeDAO changeDao;
	

	@Override
	@Transactional
	public void save(Change event)
	{
		changeDao.save(event);
	}

	@Override
	@Transactional
	public boolean delById(Long id)
	{
		changeDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Change getById(Long id){
		return changeDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Change> getAll()
	{
		return changeDao.findAll();
	}
}
