package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface UserRoleDAO extends GenericDAO<UserRole, Long> {

	void deleteByUser(SysUser user);

	List<UserRole> getByUser(SysUser user);
}
