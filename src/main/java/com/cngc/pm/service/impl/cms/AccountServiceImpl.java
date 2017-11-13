package com.cngc.pm.service.impl.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.cms.AccountDAO;
import com.cngc.pm.model.cms.Account;
import com.cngc.pm.model.cms.AccountType;
import com.cngc.pm.service.cms.AccountService;
import com.googlecode.genericdao.search.Search;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountDAO accountDao;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void save(Account account)
	{
		accountDao.save(account);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public boolean delById(Long id)
	{
		accountDao.removeById(id);
		return true;
	}
	@Override
	public Account getById(Long id)
	{
		return accountDao.find(id);
	}
	@Override
	public List<Account> getAll()
	{
		return accountDao.findAll();
	}
	@Override
	public List<Account> getListByType(AccountType at) {
		// TODO Auto-generated method stub
		Search search = new Search(Account.class);
		
		search.addFilterEqual("type", at);
		
		return accountDao.search(search);
	}
	@Override
	public Account getByCiCategoryCode(String categoryCode) {
		// TODO Auto-generated method stub
		Search search = new Search(Account.class);
		
		search.addFilterEqual("category", categoryCode);
		
		return accountDao.searchUnique(search);
	}
}
