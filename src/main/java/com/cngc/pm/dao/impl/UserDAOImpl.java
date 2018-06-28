package com.cngc.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

@Repository
public class UserDAOImpl extends BaseDAOImpl<SysUser, Long> implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@Override
	public SysUser getUserByUserName(String username) {
		// TODO Auto-generated method stub
		 String hql = "from SysUser where username =:un";
		 Query query = this.getSession().createQuery(hql);
		 
		 query.setParameter("un", username);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<SysUser> getAllByCondition(boolean enable, boolean accountNonExpired, boolean accountNonLocked,
			boolean creadentialsNonExpired) {
		// TODO Auto-generated method stub
		Search search = new Search(SysUser.class);
		
		search.addFilterEqual("enabled", enable);
		search.addFilterEqual("accountNonExpired", accountNonExpired);
		search.addFilterEqual("accountNonLocked", accountNonLocked);
		search.addFilterEqual("creadentialsNonExpired", creadentialsNonExpired);
		
		search.addSortAsc("group.id");
		
		return _searchAndCount(search).getResult();
	}

	@Override
	public boolean isRole(SysUser user, Role role) {
		// TODO Auto-generated method stub
		Search search = new Search(SysUser.class);
		
		search.addFilterEqual("id", user.getId());
		
		search.addFilterSome("userRoles", Filter.equal("role", role));
		
		if(this.searchUnique(search)==null)
			return false;
			
		return true;
	}
}
