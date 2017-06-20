package com.cngc.pm.service;

public interface LoginAttemptService {
	void loginFailed(String remoteAddress);
	void loginSucceeded(String remoteAddress);
	
	boolean isBlocked(String key);
}
