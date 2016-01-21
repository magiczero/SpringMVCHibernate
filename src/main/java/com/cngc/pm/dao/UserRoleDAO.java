package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface UserRoleDAO extends GenericDAO<UserRole, Long> {

	void deleteByUser(SysUser user);

	List<UserRole> getByUser(SysUser user);
	
	/**
	 * 是否有关系，即此角色是否有用户使用
	 * @param role 角色
	 * @return
	 */
	boolean haveRelation(Role role);
}
