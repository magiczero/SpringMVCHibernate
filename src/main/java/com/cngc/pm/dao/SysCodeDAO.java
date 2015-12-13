package com.cngc.pm.dao;

import com.cngc.pm.model.SysCode;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface SysCodeDAO extends GenericDAO<SysCode,Long>{

	SearchResult<SysCode> getCodeByType(String type);
	SearchResult<SysCode> getParentCodeByType(String type);
	SysCode getCode(String code,String type);
}
