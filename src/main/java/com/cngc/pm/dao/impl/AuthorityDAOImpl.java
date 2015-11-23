package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.AuthorityDAO;
import com.cngc.pm.model.Authority;

@Repository
public class AuthorityDAOImpl extends BaseDAOImpl<Authority, Long> implements
		AuthorityDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Authority> getAlllWithOrder() {
		// TODO Auto-generated method stub
		String hql = "from Authority order by id desc";
		Query query = this.getSession().createQuery(hql);
		
		return  query.list();
	}

	@Override
	public void update(Authority authority) {
		// TODO Auto-generated method stub
		super._merge(authority);
	}

}
