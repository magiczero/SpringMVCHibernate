package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Resources;

public interface AuthorityService {

	List<Authority> getAll();

	List<Resources> getListResources();

	Resources loadResourcesById(Long id);

	void save(Authority authority);

	Authority getById(long id);
	
	void update(Authority authority);

	void save(Authority authority, String...resourcesIds );

	void update(Authority authority, String...resourIds);
}
