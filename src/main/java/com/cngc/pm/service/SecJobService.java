package com.cngc.pm.service;

import java.util.Map;

import com.cngc.pm.model.SecJob;
import com.googlecode.genericdao.search.SearchResult;

public interface SecJobService {
	SearchResult<SecJob> search(String startTime,String endTime);
	Map<String,Object> getStats(String column,String row,String startTime,String endTime);
}
