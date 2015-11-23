package com.cngc.pm.service.cms;

import java.util.List;
import java.util.Set;
import com.cngc.pm.model.cms.Property;

public interface PropertyService {

	void save(Property property);

	boolean delById(Long id);

	Property getById(Long id);

	List<Property> getAll();

	Set<Property> getPropertyByIds(String ids);

	List<Property> getPropertyByNIds(List<Long> ids);
}
