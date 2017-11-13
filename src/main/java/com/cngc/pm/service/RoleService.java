package com.cngc.pm.service;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Role;

public interface RoleService {

	void save(Role role, String username);
	Role getById(Long id);
	Role getByName(String roleName);
	boolean delById(Long id);
	boolean delByIds(String ids);
	boolean del(Role role, String username);
	List<Role> getAll();
	Set<Role> getRoleByIds(String ids);
	List<Authority> getAuthsByRole(long id);
	void setAuths(String username, Role role, String... authIds);
	void setMenuByRole(String username, Role role, String[] menuIds);
	/**
	 * 获取非系统角色
	 * @return
	 */
	List<Role> getNonSysAll();
	List<Role> getAllRole();
	
}
