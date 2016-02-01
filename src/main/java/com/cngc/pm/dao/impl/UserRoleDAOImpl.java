package com.cngc.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.UserRoleDAO;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.googlecode.genericdao.search.Search;

@Repository
public class UserRoleDAOImpl extends BaseDAOImpl<UserRole, Long> implements UserRoleDAO {


	@Override
	public void deleteByUser(SysUser user) {
		// TODO Auto-generated method stub
		for(UserRole ur : this.getByUser(user))
			this.remove(ur);
	}

	@Override
	public List<UserRole> getByUser(SysUser user) {
		// TODO Auto-generated method stub
		Search search = new Search(UserRole.class);
		
		search.addFilterEqual("user", user);
		
		return this.search(search);
	}


	@Override
	public boolean haveRelation(Role role) {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.addFilterEqual("role", role);
		int count = this.count(search);
		
		if(count>0)
			return true;
		
		return false;
	}

}
