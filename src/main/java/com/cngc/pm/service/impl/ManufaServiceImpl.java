package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ManufaDAO;
import com.cngc.pm.model.Manufacturer;
import com.cngc.pm.service.ManufaService;

@Service
public class ManufaServiceImpl implements ManufaService {

	@Autowired
	private ManufaDAO manufaDao;

	@Override
	@Transactional(readOnly=true)
	public List<Manufacturer> getList() {
		// TODO Auto-generated method stub
		
		return manufaDao.findAll();
	}

	@Override
	@Transactional
	public void save(Manufacturer entity) {
		// TODO Auto-generated method stub
		manufaDao.save(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean isExistsNum(String num) {
		// TODO Auto-generated method stub
		return manufaDao.isExsitsNum(num);
	}
	
	
}
