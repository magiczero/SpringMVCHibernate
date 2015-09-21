package com.cngc.pm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	@Override
	public SysUser getByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(username);
	}

}
