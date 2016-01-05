package com.cngc.pm.dao;

import java.util.List;
import java.util.Set;

import com.cngc.pm.model.Role;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface RoleDAO extends GenericDAO<Role,Long>{
	
	Set<Role> getSet(String ids);
	List<Role> getRoleByNames(String names);
}
