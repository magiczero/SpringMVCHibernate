package com.cngc.pm.dao.impl;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.model.SysUser;

@Repository
public class UserDAOImpl extends BaseDAOImpl<SysUser, Long> implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@Override
	public SysUser getUserByUserName(String username) {
		// TODO Auto-generated method stub
		 String hql = "from SysUser where username = ?";
		 Query query = this.getSession().createQuery(hql);
		 
		 query.setParameter(0, username);
		 SysUser user = (SysUser)query.list().get(0);
		 
		 if(user == null) return null;
		 
		 logger.info("user is " + user.getName());
		return user;
	}

	@Override
	public void update(SysUser user) {
		super._merge(user);
	}
}
