package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.ChangeitemType;
import com.googlecode.genericdao.search.SearchResult;

public interface ChangeItemService {

	ChangeItem getById(Long id);
	void save(ChangeItem item);
	boolean delById(Long id);
	SearchResult<ChangeItem> getByCi(Long id);
	SearchResult<ChangeItem> getByCi(List<Long> ids);
	
	List<ChangeItem> getByChangeId(Long changeId, ChangeitemType type);
	void setChangeid(String itemids, String changeId);
	
	List<ChangeItem> getByCiAndType(Long id, ChangeitemType type);
}
