package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.model.Knowledge;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Override
	@Transactional
	public void save(SysUser user){
		userDao.save(user);
	}
	@Override
	@Transactional
	public SysUser getById(Long id){
		return userDao.find(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public SysUser getByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(username);
	}
	@Override
	@Transactional
	public boolean delById(Long id){
		userDao.removeById(id);
		return true;
	}
	
	@Override
	@Transactional
	public boolean delByIds(String ids)
	{
		String id[] = ids.split(",");
		int j = id.length;
		Long[] idss = new Long[j];
		for(int i=0; i<id.length; i++) {
			String str = id[i];
			 if (!isNumeric(str)) {
				 return false;
			 }
			 idss[i] = Long.valueOf(str);
		}
		try {
			for(Long k : idss) {
				userDao.removeByIds(k);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public void update(SysUser user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}
	
	@Override
	@Transactional
	public List<SysUser> getAll()
	{
		return userDao.findAll();
	}

}
