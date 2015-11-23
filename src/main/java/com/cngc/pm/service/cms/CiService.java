package com.cngc.pm.service.cms;

import java.util.List;

import com.cngc.pm.model.cms.Ci;
import com.googlecode.genericdao.search.SearchResult;

public interface CiService {

	void save(Ci ci);

	boolean delById(Long id);

	Ci getById(Long id);

	List<Ci> getAll();

	List<Ci> getByRelation(String relationId, Long primaryId);
	
	SearchResult<Ci> getByCategoryCode(String code);

	boolean deleteRelation(Long primaryId, Long secondaryId, String relationId);

	boolean saveRelation(Long primaryId, Long secondaryId, String relationId);
}
