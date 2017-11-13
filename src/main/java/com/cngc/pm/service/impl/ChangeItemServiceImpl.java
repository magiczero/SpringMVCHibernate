package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.ChangeItemDAO;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.ChangeitemType;
import com.cngc.pm.service.ChangeItemService;
import com.googlecode.genericdao.search.Search;
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
	@Transactional(readOnly=true)
	public SearchResult<ChangeItem> getByCi(Long id)
	{
		return changeItemDao.getByCi(id);
	}
	@Override
	@Transactional(readOnly=true)
	public ChangeItem getById(Long id)
	{
		return changeItemDao.find(id);
	}
	@Override
	@Transactional(readOnly=true)
	public SearchResult<ChangeItem> getByCi(List<Long> ids)
	{
		return changeItemDao.getByCi(ids);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ChangeItem> getByChangeId(Long id) {
		// TODO Auto-generated method stub
		return changeItemDao.getByChangeId(id);
	}

	@Override
	@Transactional
	public void setChangeid(String itemids, String changeId) {
		// TODO Auto-generated method stub
		String[] ids = itemids.split(",");
		
		for(String id : ids) {
			ChangeItem item = changeItemDao.find(Long.valueOf(id));
			
			item.setChangeId(Long.valueOf(changeId));
			//item.setCreatedTime(new java.util.Date());
			
			changeItemDao.save(item);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<ChangeItem> getByCiAndType(Long id, ChangeitemType type) {
		// TODO Auto-generated method stub
		Search search = new Search(ChangeItem.class);
		
		search.addFilterEqual("ciId", id);
		search.addFilterEqual("type", type);
		
		return changeItemDao.search(search);
	}
}
