package com.cngc.pm.dao;

import com.cngc.pm.model.ItilRelation;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface ItilRelationDAO extends GenericDAO<ItilRelation,Long>{

	SearchResult<ItilRelation> getByType(Long id,String type,boolean isprimary);
	
	SearchResult<ItilRelation> getByType(String type);
	
	boolean delById(Long primaryId,Long secondaryId,String type);
	
}
