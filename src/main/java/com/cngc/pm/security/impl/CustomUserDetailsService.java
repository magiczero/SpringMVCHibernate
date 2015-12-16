package com.cngc.pm.security.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.UserService;

public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
//	private SessionFactory sessionFactory;
	
	@Resource
	private UserService userService;
	
//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//
//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(username == null){
			logger.error("username is null");
			return null;
		}
		SysUser user = userService.getByUsername(username);
		
//		Session session = sessionFactory.getCurrentSession();
//		String hql="select r from SysUser r where  r.username=?";
//		Query query = session.createQuery(hql);
//		query.setParameter(0, username);
//		
//		SysUser user = (SysUser)query.uniqueResult();
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		//for(Role r : user.getRoles()) {
		for(Role r : userService.getRolesByUser(user.getId())) {
			auths.add(new SimpleGrantedAuthority(r.getRoleName()));
		}
//		auths.add(new SimpleGrantedAuthority("ROLE_USER"));
		
//		for(Role role : user.getRoles()) {
//			for(Authority authority : role.getAuths()) {
//				//auths.add(new GrantedAuthorityImpl(authority.getAuthorityName()));
//				auths.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
//			}
//		}
		
		User userdetail = new User(user.getUsername(), user.getPassword(), true, true, true, true, auths);
		return userdetail;
	}

}
