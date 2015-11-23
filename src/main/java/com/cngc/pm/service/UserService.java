package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.SysUser;

public interface UserService {

	void save(SysUser user);
	
	SysUser getById(Long id);
	
	boolean delByIds(String ids);
	
	boolean delById(Long id);
	
	SysUser getByUsername(String username);

	void update(SysUser user);
	
	List<SysUser> getAll();
}
