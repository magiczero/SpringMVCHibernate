package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.ChangeItem;
import com.googlecode.genericdao.search.SearchResult;

public interface ChangeItemService {

	ChangeItem getById(Long id);
	void save(ChangeItem item);
	boolean delById(Long id);
	SearchResult<ChangeItem> getByCi(Long id);
	SearchResult<ChangeItem> getByCi(List<Long> ids);
}
