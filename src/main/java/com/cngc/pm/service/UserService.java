package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Group;
import com.cngc.pm.model.Resources;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;

public interface UserService {

	/**
	 * 保存用户，建议只可系统管理员使用
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
	
	boolean delByIds(String username, String ids, String ip);
	
	boolean delById(Long id, String username, String ip);
	
	SysUser getByUsername(String username);

	void update(SysUser user, String username);
	
	List<SysUser> getAll();
	
	List<Resources> getResourcesByUser(SysUser user);
	
	List<Role> getRolesByUser(Long userid);

	void setRole(String username, SysUser user, String roleIds, String ip);
	
	List<SysUser> getEngineer();
	List<SysUser> getLeader();
	List<SysUser> getCommonUser();
	
	/**
	 * 启用/停用 用户
	 * @param adminName 管理员name
	 * @param userid 启用/停用 用户id
	 * @param ip	操作IP地址
	 * @return
	 */
	boolean enableUser(String adminName,long userid, String ip );

	boolean disableUser(String username, SysUser user, String ip);
	
	String getUserName(String userid);
	
	Group getTopGroupByUser(SysUser user);

	List<SysUser> getThreemembers();
	
	/**
	 * 锁定/解锁用户
	 * @param isLocking 锁定/解锁
	 * @param user 用户
	 * @param operator 操作者
	 * @param ip 操作ip
	 * @return
	 */
	void lockingOrUnlockingWithUser(boolean isLocking, String username, String operator, String ip);


	/**
	 * 验证密码是否正确
	 * @param pwd
	 * @param currentUsername
	 * @return
	 */
	boolean validatePwd(String pwd, String currentUsername);


	/**
	 * 修改密码操作
	 * @param user
	 * @param username
	 * @param remortIP
	 */
	void updatePwd(String newPwd, String username, boolean isAdmin, String adminName, String remortIP);
}
