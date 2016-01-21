package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.Resources;
import com.cngc.pm.model.Role;

public interface ResourcesService {

	List<Resources> getAll();

	void save(Resources resources, String username);

	boolean updateEnable(long id);
	
	boolean enableOrNot(Resources resources, String username);

	Resources getById(long id);
	
	void update(Resources resources, String username);

	List<Moudle> getModules();

	Resources getByURL(String servletPath);
	
	List<Role> getRoles(Resources resources);

}
