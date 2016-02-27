package com.cngc.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.MoudleDAO;
import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.dao.ResourcesDAO;
import com.cngc.pm.dao.RoleDAO;
import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.model.Resources;
import com.cngc.pm.model.Role;
import com.cngc.pm.service.ResourcesService;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;

@Service
public class ResourcesServiceImpl implements ResourcesService {

	@Autowired
	private ResourcesDAO resourcesDao;
	@Autowired
	private MoudleDAO moduleDao;
	@Autowired
	private RoleDAO roleDao;
	@Autowired
	private RecordsDAO recordsDao;

	@Override
	@Transactional(readOnly=true)
	public List<Resources> getAll() {
		// TODO Auto-generated method stub
		return resourcesDao.findAllWithOrder();
	}

	@Override
	@Transactional
	public void save(Resources resources, String username) {
		// TODO Auto-generated method stub
		resourcesDao.save(resources);
		//保存到操作日志中
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.rescourcs);
		record.setDesc("新建了资源，详细信息：资源名称：" + resources.getName()+"，资源路径："+resources.getPath());
		recordsDao.save(record);
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
	public void update(Resources resources, String username) {
		// TODO Auto-generated method stub
		resourcesDao.update(resources);
		//保存到操作日志中
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.rescourcs);
		record.setDesc("修改了资源，详细信息：资源id：["+resources.getId()+"]，资源名称：[" + resources.getName()+"]，资源路径：["+resources.getPath() + "]");
		recordsDao.save(record);
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
	@Transactional(readOnly=true)
	public List<Role> getRoles(Resources resources) {
		// TODO Auto-generated method stub
//		List<Role> list = new ArrayList<>();
		
		Search search = new Search(Role.class);
		//new Filter();
		search.addFilterOr(new Filter("roleAuths.auth.authResos.resources", resources),  Filter.some("modules.resSet", Filter.equal("id", resources.getId())));
//		search.addFilterEqual("roleAuths.auth.authResos.resources", resources);
//		list.addAll(roleDao.search(search));
//		
//		Search search1 = new Search(Role.class);
//		search1.addFilterEqual("modules.resSet", resources);
//		list.addAll(roleDao.search(search1));
		
		return roleDao.search(search);
	}

	@Override
	@Transactional(readOnly=true)
	public boolean enableOrNot(Resources resources, String username) {
		// TODO Auto-generated method stub
		boolean enable = resources.isEnable();
		String upd = "";
		if(resources.isEnable()) {
			resources.setEnable(false);
			upd = "禁用";
		} else {
			resources.setEnable(true);
			upd = "启用";
		}
		
		Resources r = resourcesDao.update(resources);
		
		if(r.isEnable() == enable)
			return false;
		
		//保存到操作日志中
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.rescourcs);
		record.setDesc(upd + "了资源，详细信息：资源id：["+r.getId()+"]，资源名称：[" + r.getName()+"]，资源路径：["+r.getPath() + "]");
		recordsDao.save(record);
				
		return true;
	}

	
}
