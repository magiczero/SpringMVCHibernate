package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.search.Search;

@Repository
public class UserDAOImpl extends BaseDAOImpl<SysUser, Long> implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@Override
	public SysUser getUserByUserName(String username) {
		// TODO Auto-generated method stub
		 String hql = "from SysUser where username = ?";
		 Query query = this.getSession().createQuery(hql);
		 
		 query.setParameter(0, username);
		 @SuppressWarnings("unchecked")
		List<SysUser> list = query.list();
		 if(list.size() > 0) {
			 SysUser user = (SysUser)query.list().get(0);

			 logger.info("user is " + user.getName());
			 return user;
		 }
		return null;
	}

	@Override
	public void update(SysUser user) {
		super._merge(user);
	}

	@Override
	public SysUser getUserByName(String name) {
		// TODO Auto-generated method stub
		Search search = new Search();
		
		search.addFilterEqual("name", name);
		
		return searchUnique(search);
	}
}
