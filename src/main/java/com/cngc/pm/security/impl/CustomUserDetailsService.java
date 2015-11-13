package com.cngc.pm.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
//	@Autowired
//	private UserRepository userDao;
	
//	@Autowired  
//	private UserCache userCache;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(username == null) logger.error("username is null");
		
		
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		auths.add(new SimpleGrantedAuthority("ROLE_USER"));
		
//		for(Role role : user.getRoles()) {
//			for(Authority authority : role.getAuths()) {
//				//auths.add(new GrantedAuthorityImpl(authority.getAuthorityName()));
//				auths.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
//			}
//		}
		
		User userdetail = new User(username, "password", true, true, true, true, auths);
		return userdetail;
	}
//
//	public void setUserDao(UserDAO userDao) {
//		this.userDao = userDao;
//	}

}
