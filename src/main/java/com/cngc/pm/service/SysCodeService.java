package com.cngc.pm.service;


import com.cngc.pm.model.SysCode;
import com.googlecode.genericdao.search.SearchResult;

public interface SysCodeService {

	void save(SysCode code);

	boolean delById(Long id);

	SysCode getById(Long id);

	SearchResult<SysCode> getAllByType(String type);
	
	String getJSON(String type);
	
	SysCode getCode(String code,String type);
	
	SearchResult<SysCode> getParentCodeByType(String type);
}
