package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Resources;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;

public interface UserService {

	void save(SysUser user, String username);
	
	SysUser getById(Long id);
	
	boolean delByIds(String username, String ids);
	
	boolean delById(Long id, String username);
	
	SysUser getByUsername(String username);

	void update(SysUser user, String username);
	
	List<SysUser> getAll();
	
	List<Resources> getResourcesByUser(SysUser user);
	
	List<Role> getRolesByUser(Long userid);

	void setRole(String username, SysUser user, String roleIds);
	
	List<SysUser> getEngineer();
	List<SysUser> getLeader();
	List<SysUser> getCommonUser();
	
	boolean enableUser(String loginname,SysUser user );
}
