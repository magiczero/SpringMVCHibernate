package com.cngc.pm.service.cms;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.cms.Property;
import com.googlecode.genericdao.search.SearchResult;

public interface PropertyService {

	void save(Property property);

	boolean delById(Long id);

	Property getById(Long id);

	List<Property> getAll();

	Set<Property> getPropertyByIds(String ids);

	List<Property> getPropertyByNIds(List<Long> ids);
	
	List<Property> getFields();
	
	SearchResult<Property> getByPropertyIds(String propertyIds);
}
