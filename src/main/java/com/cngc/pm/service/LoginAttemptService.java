package com.cngc.pm.service;

import java.util.concurrent.ExecutionException;

public interface LoginAttemptService {
	void loginFailed(String username,String remoteAddress);
	void loginSucceeded(String remoteAddress);
	
	boolean isBlocked(String key);
	
	int getAttempts(String key) throws ExecutionException;
	void unLock(String username, String operator, String remortIP);
}
