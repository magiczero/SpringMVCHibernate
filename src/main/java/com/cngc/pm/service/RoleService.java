package com.cngc.pm.service;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Role;

public interface RoleService {

	void save(Role role);
	Role getById(Long id);
	boolean delById(Long id);
	boolean delByIds(String ids);
	List<Role> getAll();
	Set<Role> getRoleByIds(String ids);
	List<Authority> getAuthsByRole(long id);
	void setAuths(Role role, String... authIds);
	void setMenuByRole(Role role, String[] menuIds);
}
