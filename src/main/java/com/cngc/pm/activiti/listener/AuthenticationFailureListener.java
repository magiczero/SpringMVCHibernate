package com.cngc.pm.activiti.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.cngc.pm.service.LoginAttemptService;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired  
    private LoginAttemptService loginAttemptService;
	
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
		// TODO Auto-generated method stub
		WebAuthenticationDetails auth = (WebAuthenticationDetails)   
		          e.getAuthentication().getDetails();  
		
//		System.out.println(e.getAuthentication().getPrincipal());	//用户名
		loginAttemptService.loginFailed((String)e.getAuthentication().getPrincipal(),auth.getRemoteAddress());  
		
		//登陆失败，需要进行日志保存 
	}

}
