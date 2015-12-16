package com.cngc.pm.dao;

import com.cngc.pm.model.Role;
import com.cngc.pm.model.RoleAuth;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface RoleAuthDAO extends GenericDAO<RoleAuth, Long> {

	void deleteByRole(Role role);

}
