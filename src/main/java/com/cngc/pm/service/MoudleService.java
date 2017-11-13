package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.Role;

public interface MoudleService {

	List<Moudle> getAllTopMenu();

	void save(Moudle moudle, String username);

	boolean enableStatus(long id, String username);
	
	List<Moudle> getAll();
	
	Moudle getById(Long id);

	List<Moudle> getTopByRoles(List<Role> roleList);
	
	List<Moudle> getSecondByRole(List<Role> roleList, Moudle topMoudle);
}
