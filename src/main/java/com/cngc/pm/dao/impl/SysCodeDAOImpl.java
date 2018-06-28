package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.SysCodeDAO;
import com.cngc.pm.model.SysCode;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class SysCodeDAOImpl extends BaseDAOImpl<SysCode,Long> implements SysCodeDAO{

	@Override
	public SearchResult<SysCode> getCodeByType(String type)
	{
		Search search = new Search();
		search.addFilterEqual("type", type);
		search.addSort("code", false);
		
		return this.searchAndCount(search);
	}
	
	@Override
	public SysCode getCode(String code,String type)
	{
		Search search = new Search();
		
		search.addFilterEqual("code", code);
		search.addFilterEqual("type", type);
		
		return this.searchUnique(search);
	}
	
	@Override
	public SearchResult<SysCode> getParentCodeByType(String type){
		Search search = new Search();
		search.addFilterEqual("type", type).addFilterCustom("LENGTH(code_)=2");
		
		return this.searchAndCount(search);
	}

	@Override
	public SysCode getByCodeNameAndType(String codeName, String type) {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.addFilterEqual("type", type);
		search.addFilterEqual("codeName", codeName);
		
		return this.searchUnique(search);
	}
}
