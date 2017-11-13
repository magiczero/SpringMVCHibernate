package com.cngc.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ChangeItemDAO;
import com.cngc.pm.model.ChangeItem;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class ChangeItemDAOImpl extends BaseDAOImpl<ChangeItem,Long> implements ChangeItemDAO{

	@Override
	public SearchResult<ChangeItem> getByCi(Long id)
	{
		Search search = new Search();
		
		search.addFilterEqual("ciId", id);
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<ChangeItem> getByCi(List<Long> ids)
	{
		Search search = new Search();
		
		search.addFilterIn("ciId", ids);
		
		return this.searchAndCount(search);	
		
	}
	
	@Override
	public List<ChangeItem> getByChangeId(Long id) {
		// TODO Auto-generated method stub
		Search search = new Search();
		
		search.addFilterEqual("changeId", id);
		
		return this.search(search);
	}
}
