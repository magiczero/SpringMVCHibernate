package com.cngc.pm.service.cms;

import java.util.List;

import com.cngc.pm.model.cms.Category;

public interface CategoryService {

	void save(Category category);

	boolean delByCode(String code);

	Category getByCode(String code);

	List<Category> getAll();
	
	String getJSON();

}
