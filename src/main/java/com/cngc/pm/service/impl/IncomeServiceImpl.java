package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.IncomeDAO;
import com.cngc.pm.model.Income;
import com.cngc.pm.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService{
	
	@Autowired
	private IncomeDAO incomeDao;
	@Override
	@Transactional
	public void save(Income income){
		incomeDao.save(income);
	}

	@Override
	@Transactional
	public boolean delById(Long id)
	{
		incomeDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public Income getById(Long id){
		return incomeDao.find(id);
	}
	
	@Override
	@Transactional
	public List<Income> getAll()
	{
		return incomeDao.findAll();
	}
}
