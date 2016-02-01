package com.cngc.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.AuthResoDAO;
import com.cngc.pm.model.AuthReso;
import com.cngc.pm.model.Authority;
import com.googlecode.genericdao.search.Search;

@Repository
public class AuthResoDAOImpl extends BaseDAOImpl<AuthReso, Long> implements AuthResoDAO {

	@Override
	public void deleteByAuth(Authority authority) {
		// TODO Auto-generated method stub
		for(AuthReso ar : getListByAuth(authority)) {
			this.remove(ar);
		}
	}

	@Override
	public List<AuthReso> getListByAuth(Authority auth) {
		// TODO Auto-generated method stub
		Search search = new Search();
		search.addFilterEqual("auth", auth);
		
		return this.search(search);
	}

}
