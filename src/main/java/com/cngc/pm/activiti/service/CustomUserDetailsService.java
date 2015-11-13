package com.cngc.pm.activiti.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class CustomUserDetailsService implements UserDetailsService {
//	@Autowired
//	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		com.cngc.pm.activiti.jpa.entity.User user = userRepository.findByUsername(username);
		
		return new User(
				"username",
				username, getAuthorities());
	}
	
	public List<String> getRoles() {
		List<String> roles = new ArrayList<String>();
		
			roles.add("ROLE_USER");
			roles.add("ROLE_ADMIN");
			
			return roles;
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles());
		return authList;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
