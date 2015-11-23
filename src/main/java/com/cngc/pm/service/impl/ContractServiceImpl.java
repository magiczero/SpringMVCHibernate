package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ContractDAO;
import com.cngc.pm.model.Contract;
import com.cngc.pm.service.ContractService;

@Service
public class ContractServiceImpl implements ContractService{

	@Autowired
	private ContractDAO contractDao;
	
	@Override
	@Transactional
	public void save(Contract contract){
		contractDao.save(contract);
	}
	
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		contractDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Contract getById(Long id){
		return contractDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Contract> getAll()
	{
		return contractDao.findAll();
	}
}
