package com.cngc.pm.dao;

import com.cngc.pm.model.Notice;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.SearchResult;

public interface NoticeDAO extends GenericDAO<Notice,Long>{

	SearchResult<Notice> getLastNotice();
}
