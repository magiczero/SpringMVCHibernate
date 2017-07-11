package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.AuthorityDAO;
import com.cngc.pm.dao.MoudleDAO;
import com.cngc.pm.dao.RecordsDAO;
import com.cngc.pm.dao.RoleAuthDAO;
import com.cngc.pm.dao.RoleDAO;
import com.cngc.pm.dao.UserRoleDAO;
import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.RoleAuth;
import com.cngc.pm.service.RoleService;
import com.googlecode.genericdao.search.Search;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private AuthorityDAO authDao;
	
	@Autowired
	private RoleAuthDAO raDao;
	
	@Autowired
	private MoudleDAO moudleDao;
	
	@Autowired
	private RecordsDAO recordsDao;
	
	@Autowired
	private UserRoleDAO urDao;
	
	@Override
	@Transactional
	public void save(Role role, String username){
		roleDao.save(role);
		
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.role);
		record.setDesc("新建了角色，详情：" + role.toString());
		
		recordsDao.save(record);
	}
	@Override
	@Transactional(readOnly=true)
	public Role getById(Long id){
		return roleDao.find(id);
	}

	@Override
	@Transactional
	public boolean delById(Long id){
		if(roleDao.removeById(id)) {
			return true;
		}
		return false;
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
				roleDao.removeByIds(k);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Role> getAll()
	{
		return roleDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Set<Role> getRoleByIds(String ids)
	{
		return roleDao.getSet(ids);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Authority> getAuthsByRole(long id) {
		// TODO Auto-generated method stub
		Search search = new Search(Authority.class);
		
		search.addFilterEqual("roleAuths.role.id", id);
		
		return authDao.search(search);
	}
	
	@Override
	@Transactional
	public void setAuths(String username, Role role, String... authIds) {
		// TODO Auto-generated method stub
		//首先，清空角色对应的权限
		raDao.deleteByRole(role);
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.role);
		if(authIds == null) {
			record.setDesc("清空了角色"+role.getRoleName()+"的所有权限");
		} else {
			String desc = "";
			for(String id : authIds) {
				RoleAuth ra = new RoleAuth();
				Authority auth = authDao.find(Long.valueOf(id));
				
				ra.setAuth(auth);
				ra.setRole(role);
				
				raDao.save(ra);
				desc += "角色id：["+ auth.getId() +"]，角色名：["+auth.getAuthorityName() + "],";
			}
			record.setDesc("设置了新权限，详情："+ desc);
		}
		
		recordsDao.save(record);
	}
	
	@Override
	@Transactional
	public void setMenuByRole(String username, Role role, String[] menuIds) {
		// TODO Auto-generated method stub
		Set<Moudle> moudleSet = new HashSet<>();
		String desc = "";
		for(String id : menuIds) {
			Moudle m = moudleDao.find(Long.valueOf(id));
			desc +=m.getName()+"，";
			moudleSet.add(m);
		}
		
		role.setModules(moudleSet);
		
		roleDao.save(role);
		
		Records record = new Records();
		record.setUsername(username);
		record.setType(RecordsType.role);
		record.setDesc("修改了角色的菜单，菜单列表：" + desc);
		
		recordsDao.save(record);
	}
	@Override
	@Transactional
	public boolean del(Role role, String username) {
		// TODO Auto-generated method stub
		//删除前判断Role与User和Authority的关系
		if(urDao.haveRelation(role)) {
			return false;
		}
		
		if(raDao.hasRelationByRole(role)) {
			return false;
		}
		if(roleDao.remove(role)) {
			Records record = new Records();
			record.setUsername(username);
			record.setType(RecordsType.role);
			record.setDesc("删除了角色，被删除的角色信息是：" + role);
			
			recordsDao.save(record);
			return true;
		}
		return false;
	}
	@Override
	@Transactional(readOnly=true)
	public List<Role> getNonSysAll() {
		// TODO Auto-generated method stub
		Search search = new Search(Role.class);
		
		search.addFilterEqual("sys", false);
		
		return roleDao.search(search);
	}
	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return roleDao.findAll();
	}
}
