package com.cngc.pm.service;

import java.text.ParseException;
import java.util.List;

import com.cngc.pm.model.Records;
import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.search.SearchResult;

public interface RecordsService {

	void save(Records record);
	
	List<Records> getListAll();
	
	SearchResult<Records> getAllWithPage(Integer offset, Integer maxResults);
	
	/**
	 * 根据角色获得用户
	 * @param roles 多个角色
	 * @return
	 */
	List<SysUser> getUsersByRole(String...names);
	
	List<Records> searchList(String username, int[] type, String start, String end)  throws ParseException;
	
}
