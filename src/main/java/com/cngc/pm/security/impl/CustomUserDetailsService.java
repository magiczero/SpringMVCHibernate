package com.cngc.pm.security.impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
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
	
	protected MessageSourceAccessor messages = SpringSecurityMessageSource
            .getAccessor();
	
	@Resource
	private UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(username == null){
			logger.error("username is null");
			throw new UsernameNotFoundException(messages.getMessage(
                    "User.notFound", new Object[] { username },
                    "请输入用户名"));
		}
		SysUser user = userService.getByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(messages.getMessage(
                    "User.notFound", new Object[] { username },
                    "用户名 {0} 不存在"));
		}
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
		for(Role r : userService.getRolesByUser(user.getId())) {
			auths.add(new SimpleGrantedAuthority(r.getRoleName()));
		}
		
		//accountLocked  定义账号是否已经锁定
		//accountExpired  定义账号是否已经过期
		//credentialsExpired  定义凭证是否已经过期
		User userdetail = new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, auths);
		return userdetail;
	}

}
