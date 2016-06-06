package com.cngc.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.NoticeDAO;
import com.cngc.pm.model.Notice;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

@Repository
public class NoticeDAOImpl extends BaseDAOImpl<Notice,Long> implements NoticeDAO{

	@Override
	public SearchResult<Notice> getLastNotice(){
		Search search = new Search();
		search.setMaxResults(5);
		search.addSort("createdTime", true);

		return this.searchAndCount(search);
	}
}
