package com.cngc.pm.service.cms;

import java.util.List;

import com.cngc.pm.model.cms.CategoryRelation;

public interface CategoryRelationService {

	void save(CategoryRelation categoryRelation);

	boolean delById(Long id);

	CategoryRelation getById(Long id);

	List<CategoryRelation> getAll();

	List<CategoryRelation> getByPrimaryCode(String code);

}
