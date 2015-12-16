package com.cngc.pm.service;

import com.cngc.pm.model.ItilRelation;
import com.googlecode.genericdao.search.SearchResult;

public interface ItilRelationService {
	boolean save(ItilRelation relation);
	boolean save(Long primaryId,Long secondaryId,String type);
	SearchResult<ItilRelation> getByType(Long id,String type,boolean isprimary);
	boolean delById(Long id);
	boolean delById(Long primaryId,Long secondaryId,String type);
}
