package com.cngc.pm.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import static com.cngc.utils.Constants.STREAM_OPERATE_LOG;

public class MyAccessDecisionManager implements AccessDecisionManager {

	//检查用户是否够权限访问资源
    //参数authentication是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
    //参数object是url
    //参数configAttributes所需的权限
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		// TODO Auto-generated method stub
//		System.out.println("访问决策方法");
		if (configAttributes == null) {
			return;
		}
		
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.equals(ga.getAuthority())) { // ga is user's role.
					STREAM_OPERATE_LOG.info(object + ",允许访问");
					return;
				}
			}
		}
		throw new AccessDeniedException("权限不足！");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
//		System.out.println("访问决策支持1");
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
//		System.out.println("访问决策方法2");
		return true;
	}

}
