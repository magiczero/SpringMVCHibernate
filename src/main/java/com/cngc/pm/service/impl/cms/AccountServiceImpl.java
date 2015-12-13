package com.cngc.pm.service.impl.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.cms.AccountDAO;
import com.cngc.pm.model.cms.Account;
import com.cngc.pm.service.cms.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDAO accountDao;
	
	@Override
	@Transactional
	public void save(Account account)
	{
		accountDao.save(account);
	}
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		accountDao.removeById(id);
		return true;
	}
	@Override
	@Transactional
	public Account getById(Long id)
	{
		return accountDao.find(id);
	}
	@Override
	@Transactional
	public List<Account> getAll()
	{
		return accountDao.findAll();
	}
}
