package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.exception.ParameterException;
import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.dao.RoleDAO;
import com.cngc.pm.dao.UserDAO;
import com.cngc.pm.dao.UserRoleDAO;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.model.Resources;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.cngc.pm.service.UserService;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.Search;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private UserRoleDAO urDao;
	
	@Autowired
	private RecordsDAO recordsDao;
	
	@Override
	@Transactional
	public void save(SysUser user, String username , boolean enable){
		user.setEnabled(enable);
	
		userDao.save(user);
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.user);
		record.setDesc("新建了用户，用户id：[" + user.getId() +"]，用户名：["+ user.getUsername()+"]");
		recordsDao.save(record);
	}
	@Override
	@Transactional(readOnly=true)
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
	public boolean delById(Long id, String username){
		SysUser user = userDao.find(id);
		//userDao.removeByIds(k);
		userDao.remove(user);
		
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.user);
		record.setDesc("删除了用户，用户id：[" + user.getId() +"]，用户名：["+ user.getUsername()+"]");
		recordsDao.save(record);
		return true;
	}
	
	@Override
	@Transactional
	public boolean delByIds(String username, String ids)
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
		String desc = "";
		try {
			for(Long k : idss) {
				SysUser user = userDao.find(k);
				//userDao.removeByIds(k);
				userDao.remove(user);
				desc +="用户id：[" + user.getId() +"]，用户名：[" + user.getUsername()+"]";
			}
		} catch (Exception e) {
			return false;
		}
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.user);
		record.setDesc("批量删除了用户，详情：" + desc);
		recordsDao.save(record);
		return true;
	}

	@Override
	@Transactional
	public void update(SysUser user, String username) {
		// TODO Auto-generated method stub
		userDao.update(user);
//		Records record = new Records();
//		record.setUsername(username);
//		record.setType(RecordsType.user);
//		record.setDesc("修改了用户信息，用户id：[" + user.getId() +"]，用户名：["+ user.getUsername()+"]");
//		recordsDao.save(record);
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
	public void setRole(String username, SysUser user, String roleIds) {
		// TODO Auto-generated method stub
		String ids[] = roleIds.split(",");
		//首先清空权限
		urDao.deleteByUser(user);
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.user);
		if(roleIds.isEmpty()) {
			record.setDesc("用户id：["+user.getId() +"]，用户名：["+user.getName()+"]，被清空了角色");
		} else {
			String desc = "";
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
						 desc += "角色id：["+role.getId() + "]，角色名：["+role.getRoleName()+"],";
					 }
				 } else {
					 throw new ParameterException("修改用户角色时出错，无法找到相应的角色");
				 }
			}
			record.setDesc("用户id：["+user.getId() +"]，用户名：["+user.getName()+"]，设置了角色，详情：" + desc);
		}
		
		recordsDao.save(record);
	}
	@Override
	@Transactional
	public List<SysUser> getEngineer()
	{
		return getByRole("system.user.engineer");
	}
	@Override
	@Transactional
	public List<SysUser> getLeader()
	{
		return getByRole("system.user.leader");
	}
	@Override
	@Transactional
	public List<SysUser> getCommonUser()
	{
		return getByRole("system.user.common");
	}
	private List<SysUser> getByRole(String roleMagic)
	{
		List<SysUser> users = new LinkedList<SysUser>();
		List<Role> roles = roleDao.getRoleByNames(PropertyFileUtil.getStringValue(roleMagic));
		for(Role role:roles)
		{
			Set<UserRole> userRoles = role.getUserRoles();
			for(UserRole userRole:userRoles)
			{
				// 不唯一
				Boolean bIn = false;
				SysUser tmpUser = userRole.getUser();
				for(SysUser user:users)
				{
					if(user.getUsername().equals(tmpUser.getUsername()))
						bIn = true;
				}
				if(!bIn)
					users.add( tmpUser );
			}
		}
		return users;	
	}
	
	@Override
	@Transactional
	public boolean enableUser(String loginname, SysUser user) {
		// TODO Auto-generated method stub
		user.setEnabled(true);
		if(userDao.save(user)) {			//源码中如果是修改，返回false
			return false;
		} else {
			Records record = new Records();
			record.setUsername(loginname);
			record.setType(RecordsType.user);
			record.setDesc("【启用】了用户，用户id：[" + user.getId() +"]，用户名：["+ user.getUsername()+"]");
			recordsDao.save(record);
			return true;
		}
		
	}
	@Override
	@Transactional
	public boolean disableUser(String loginname, SysUser user) {
		// TODO Auto-generated method stub
		user.setEnabled(false);
		user.setUsername(user.getUsername()+"_"+user.getId());
		if(userDao.save(user)) {			//源码中如果是修改，返回false
			return false;
		} else {
			Records record = new Records();
			record.setUsername(loginname);
			record.setType(RecordsType.user);
			record.setDesc("【禁用】删除了用户，用户id：[" + user.getId() +"]，用户名：["+ user.getUsername()+"]");
			recordsDao.save(record);
			return true;
		}
	}
	@Override
	@Transactional
	public String getUserName(String userid)
	{
		SysUser user = userDao.getUserByUserName(userid);
		if(user==null)
			return "";
		else
			return user.getName();
	}
}
