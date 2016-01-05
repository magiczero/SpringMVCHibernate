package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.MoudleDAO;
import com.cngc.pm.dao.ResourcesDAO;
import com.cngc.pm.dao.RoleDAO;
import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.Resources;
import com.cngc.pm.model.Role;
import com.cngc.pm.service.ResourcesService;
import com.googlecode.genericdao.search.Search;

@Service
public class ResourcesServiceImpl implements ResourcesService {

	@Autowired
	private ResourcesDAO resourcesDao;
	@Autowired
	private MoudleDAO moduleDao;
	@Autowired
	private RoleDAO roleDao;

	@Override
	@Transactional(readOnly=true)
	public List<Resources> getAll() {
		// TODO Auto-generated method stub
		return resourcesDao.findAllWithOrder();
	}

	@Override
	@Transactional
	public void save(Resources resources) {
		// TODO Auto-generated method stub
		resourcesDao.save(resources);
	}

	@Override
	@Transactional
	public boolean updateEnable(long id) {
		// TODO Auto-generated method stub
		Resources re = resourcesDao.find(id);
		boolean enable = re.isEnable();
		
		if(enable) {
			re.setEnable(false);
		} else {
			re.setEnable(true);
		}
		
		Resources r = resourcesDao.update(re);
		
		if(r.isEnable() == enable)
			return false;
		
		return true;
	}

	@Override
	@Transactional(readOnly=true)
	public Resources getById(long id) {
		// TODO Auto-generated method stub
		return resourcesDao.find(id);
	}

	@Override
	@Transactional
	public void update(Resources resources) {
		// TODO Auto-generated method stub
		resourcesDao.update(resources);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Moudle> getModules() {
		// TODO Auto-generated method stub
		return moduleDao.getAllByLevel();
	}

	@Override
	@Transactional(readOnly=true)
	public Resources getByURL(String servletPath) {
		// TODO Auto-generated method stub
		return resourcesDao.getByURL(servletPath);
	}

	@Override
	public List<Role> getRoles(Resources resources) {
		// TODO Auto-generated method stub
		Search search = new Search(Role.class);
		
		search.addFilterEqual("roleAuths.auth.authResos.resources", resources);
		
		return roleDao.search(search);
	}

	
}
