package com.cngc.pm.service.impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.LoginAttemptService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
public class LoginAttemptServiceImpl implements LoginAttemptService {
	private final int MAX_ATTEMPT = 5;  
    private LoadingCache<String, Integer> attemptsCache;  
    
    @Autowired
	private UserDAO userDao;
    
    public LoginAttemptServiceImpl() {
    	super();  
        attemptsCache = CacheBuilder.newBuilder().  
          expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {  
            public Integer load(String key) {  
                return 0;  
            }  
        }); 
    }
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void loginFailed(String key) {
		// TODO Auto-generated method stub
		
		int attempts = 0;  
        try {  
            attempts = attemptsCache.get(key);  
        } catch (ExecutionException e) {  
            attempts = 0;  
        }  
        attempts++;  
        attemptsCache.put(key, attempts);  
        
        //数据库锁定
        if(isBlocked(key)) {
        	SysUser user0 = userDao.getUserByUserName(key);
			user0.setAccountNonLocked(true);
			
			userDao.save(user0);
        }
	}

	@Override
	public void loginSucceeded(String key) {
		// TODO Auto-generated method stub
		attemptsCache.invalidate(key); 
	}
	@Override
	public boolean isBlocked(String key) {
		// TODO Auto-generated method stub
		try {  
            return attemptsCache.get(key) >= MAX_ATTEMPT;  
        } catch (ExecutionException e) {  
            return false;  
        }  
	}

}
