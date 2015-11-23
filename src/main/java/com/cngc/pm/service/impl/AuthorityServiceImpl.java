package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.AuthorityDAO;
import com.cngc.pm.dao.ResourcesDAO;
import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Resources;
import com.cngc.pm.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityDAO authDao;
	@Autowired
	private ResourcesDAO resourcesDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Authority> getAll() {
		// TODO Auto-generated method stub
		return authDao.getAlllWithOrder();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Resources> getListResources() {
		// TODO Auto-generated method stub
		return resourcesDao.findAllWithOrder();
	}

	@Override
	@Transactional(readOnly=true)
	public Resources loadResourcesById(Long id) {
		// TODO Auto-generated method stub
		return resourcesDao.find(id);
	}

	@Override
	@Transactional
	public void save(Authority authority) {
		// TODO Auto-generated method stub
		authDao.save(authority);
	}

	@Override
	@Transactional(readOnly=true)
	public Authority getById(long id) {
		// TODO Auto-generated method stub
		return authDao.find(id);
	}

	@Override
	@Transactional
	public void update(Authority authority) {
		// TODO Auto-generated method stub
		authDao.update(authority);
	}

}
