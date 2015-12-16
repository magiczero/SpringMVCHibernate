package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.exception.ParameterException;
import com.cngc.pm.dao.RoleDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.dao.UserRoleDAO;
import com.cngc.pm.model.Resources;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.cngc.pm.service.UserService;
import com.googlecode.genericdao.search.Search;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private UserRoleDAO urDao;
	
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
	@Transactional(readOnly=true)
	public List<SysUser> getAll()
	{
		return userDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Resources> getResourcesByUser(SysUser user) {
		// TODO Auto-generated method stub
//		userDao.refresh(user);
//		List<Resources> list = new ArrayList<>();
//		for(Role role : user.getRoles()) {
//			for(Authority auth : role.getAuths()) {
//				for(Resources resource: auth.getSetResources()) {
//					list.add(resource);
//				}
//			}
//		}
//		return list;
		return null;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Role> getRolesByUser(Long userid) {
		// TODO Auto-generated method stub
		Search search = new Search(Role.class);
		
		//search.addFilterSome("userRoles", Filter.equal("user.id", userid));
		search.addFilterEqual("userRoles.user.id", userid);
		//search.addfilter
		
		return roleDao.search(search);
	}
	
	@Override
	@Transactional
	public void setRole(SysUser user, String roleIds) {
		// TODO Auto-generated method stub
		String ids[] = roleIds.split(",");
		//首先清空权限
		urDao.deleteByUser(user);
		//添加权限
		for(int i=0; i<ids.length; i++) {
			
			String str = ids[i];
			 if (isNumeric(str)) {
				 Role role = roleDao.find(Long.valueOf(str));
				 if(role != null) {
					 UserRole ur = new UserRole();
					 ur.setUser(user);
					 ur.setRole(role);
					 urDao.save(ur);
				 }
			 } else {
				 throw new ParameterException("修改用户角色时出错，无法找到相应的角色");
			 }
		}
	}
}
