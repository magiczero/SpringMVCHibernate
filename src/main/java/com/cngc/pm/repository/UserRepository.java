package com.cngc.pm.repository;

import com.cngc.pm.activiti.jpa.entity.User;
import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface UserRepository extends  GenericDAO<SysUser, Long> {
	User findByUsername(String username);
}
