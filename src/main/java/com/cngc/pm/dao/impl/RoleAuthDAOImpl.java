package com.cngc.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.RoleAuthDAO;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.RoleAuth;
import com.googlecode.genericdao.search.Search;

@Repository
public class RoleAuthDAOImpl extends BaseDAOImpl<RoleAuth, Long> implements RoleAuthDAO {

	@Override
	public void deleteByRole(Role role) {
		// TODO Auto-generated method stub
		for(RoleAuth ra : getListByRole(role))
			this.remove(ra);
	}
	
	private List<RoleAuth> getListByRole(Role role) {
		Search search = new Search(RoleAuth.class);
		
		search.addFilterEqual("role", role);
		
		return this.search(search);
	}

}
