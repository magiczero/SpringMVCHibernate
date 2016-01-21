package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Records;
import com.googlecode.genericdao.search.SearchResult;

public interface RecordsService {

	void save(Records record);
	
	List<Records> getListAll();
	
	SearchResult<Records> getAllWithPage(Integer offset, Integer maxResults);
}
