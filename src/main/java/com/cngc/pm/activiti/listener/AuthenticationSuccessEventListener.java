package com.cngc.pm.activiti.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.cngc.pm.service.LoginAttemptService;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired  
    private LoginAttemptService loginAttemptService;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent e) {
		// TODO Auto-generated method stub
		User userdetails = (User)e.getAuthentication().getPrincipal();
		loginAttemptService.loginSucceeded(userdetails.getUsername());
	}

}
