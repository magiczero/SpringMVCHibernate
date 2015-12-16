package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.ItilRelationDAO;
import com.cngc.pm.model.ItilRelation;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class ItilRelationDAOImpl extends BaseDAOImpl<ItilRelation,Long> implements ItilRelationDAO{

	@Override
	public SearchResult<ItilRelation> getByType(Long id,String type,boolean isprimary){
		Search search = new Search();
		search.addFilterEqual("relationType", type);
		if(isprimary)
			search.addFilterEqual("primaryId", id);
		else
			search.addFilterEqual("secondaryId", id);
		
		return this.searchAndCount(search);
	}
	@Override
	public SearchResult<ItilRelation> getByType(String type){
		Search search = new Search();
		search.addFilterEqual("relationType", type);
		
		return this.searchAndCount(search);
	}
	@Override
	public boolean delById(Long primaryId,Long secondaryId,String type)
	{
		Search search = new Search();
		search.addFilterEqual("relationType", type);
		search.addFilterEqual("primaryId", primaryId);
		search.addFilterEqual("secondaryId", secondaryId);
		
		SearchResult<ItilRelation> s = this.searchAndCount(search);
		
		if(s.getTotalCount()==1)
		{
			this.remove( s.getResult().get(0) );
		}
		
		return true;
	}
}
