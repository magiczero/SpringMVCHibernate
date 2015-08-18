package com.cngc.pm.dao;

import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface UserDAO extends GenericDAO<SysUser, Long> {

	SysUser getUserByUserName(String username);
}
