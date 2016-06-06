package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Notice;
import com.googlecode.genericdao.search.SearchResult;

public interface NoticeService {

	void save(Notice notice);
	boolean delById(Long id);
	Notice getById(Long id);
	List<Notice> getAll();
	SearchResult<Notice> getLastNotice();
}
