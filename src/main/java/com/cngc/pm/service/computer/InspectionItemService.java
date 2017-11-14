package com.cngc.pm.service.computer;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.computer.InspectionItem;
import com.googlecode.genericdao.search.SearchResult;

public interface InspectionItemService {

	void save(InspectionItem item);
	boolean delById(Long id);
	InspectionItem getById(Long id);
	List<InspectionItem> getAll();
	Set<InspectionItem> getItemByIds(String ids);
	List<InspectionItem> getItemByNIds(List<Long> ids);
	SearchResult<InspectionItem> getByIds(String ids);
}
