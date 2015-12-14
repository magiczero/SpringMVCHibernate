package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ChangeItemDAO;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.service.ChangeItemService;
import com.googlecode.genericdao.search.SearchResult;

@Service
public class ChangeItemServiceImpl implements ChangeItemService{
	
	@Autowired
	private ChangeItemDAO changeItemDao;
	
	@Override
	@Transactional
	public void save(ChangeItem item)
	{
		changeItemDao.save(item);
	}
	
	@Override
	@Transactional
	public boolean delById(Long id)
	{
		changeItemDao.removeById(id);
		return true;
	}
	@Override
	@Transactional
	public SearchResult<ChangeItem> getByCi(Long id)
	{
		return changeItemDao.getByCi(id);
	}
	@Override
	@Transactional
	public ChangeItem getById(Long id)
	{
		return changeItemDao.find(id);
	}
	@Override
	@Transactional
	public SearchResult<ChangeItem> getByCi(List<Long> ids)
	{
		return changeItemDao.getByCi(ids);
	}
}
