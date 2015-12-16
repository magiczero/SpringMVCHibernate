package com.cngc.pm.service;

import java.util.List;
import java.util.Map;

import com.cngc.pm.model.Knowledge;
import com.googlecode.genericdao.search.SearchResult;

public interface KnowledgeService {

	void save(Knowledge knowledge);
	
	boolean delByIds(String ids);
	
	boolean delById(Long id);
	
	Knowledge getById(Long id);
	
	List<Knowledge> getAll();
	
	Map<String,Object> getCountByStatus();
	SearchResult<Knowledge> getByStatus(String status);
	SearchResult<Knowledge> getNotFinished();
	Map<String,Object> getCountByCategory(String startTime,String endTime);
	SearchResult<Knowledge> getSearchResult(String keyword, Integer offset,Integer maxResults);
	boolean addHits(Knowledge knowledge);
	List<Knowledge> getLastRead();
	Map<String,Object> getStats(String column,String row,String startTime,String endTime,String status);
	SearchResult<Knowledge> getByIds(List<Long> ids);
}
