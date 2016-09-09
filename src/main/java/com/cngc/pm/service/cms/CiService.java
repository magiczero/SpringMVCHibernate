package com.cngc.pm.service.cms;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.cms.Category;
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
	
	SearchResult<Ci> getByIds(List<Long> ids);
	
	Map<String,Object> getStats(String column,String row,String status);
	
	SearchResult<Ci> getByUser(String user);
	
	SearchResult<Ci> getDocumentAllByCategory(Category category, int iDisplayStart,int iDisplayLength);
	
	List<Ci> getDocumentAllByCategory(Category category);
}
