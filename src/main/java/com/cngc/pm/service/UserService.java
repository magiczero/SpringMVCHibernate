package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Group;
import com.cngc.pm.model.Resources;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;

public interface UserService {

	/**
	 * @param user
	 * @param username
	 * @param enable	是否启用
	 * @param ip
	 */
	void save(SysUser user, String username, boolean enable, String ip);
	
	
	/**
	 * 系统管理员新建用户
	 * @param user
	 * @param SysAdminName
	 * @param ip
	 */
	void saveUserWithSysAdmin(SysUser user, String SysAdminName, String ip);
	
	SysUser getById(Long id);
	
	SysUser getByName(String name);
	
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

	boolean disableUser(String username, SysUser user);
	
	String getUserName(String userid);
	
	Group getTopGroupByUser(SysUser user);

	List<SysUser> getThreemembers();
	
	/**
	 * 锁定/解锁用户
	 * @param isLocking 锁定/解锁
	 * @param user 用户
	 * @param operator 操作者
	 * @return
	 */
	void lockingOrUnlockingWithUser(boolean isLocking, String username, String operator);
}
