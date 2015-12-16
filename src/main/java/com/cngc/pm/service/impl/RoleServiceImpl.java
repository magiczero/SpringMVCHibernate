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
import com.cngc.pm.dao.RoleAuthDAO;
import com.cngc.pm.dao.RoleDAO;
import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Moudle;
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
	
	@Override
	@Transactional
	public void save(Role role){
		roleDao.save(role);
	}
	@Override
	@Transactional(readOnly=true)
	public Role getById(Long id){
		return roleDao.find(id);
	}

	@Override
	@Transactional
	public boolean delById(Long id){
		roleDao.removeById(id);
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
	public void setAuths(Role role, String... authIds) {
		// TODO Auto-generated method stub
		//首先，清空角色对应的权限
		raDao.deleteByRole(role);
		
		for(String id : authIds) {
			RoleAuth ra = new RoleAuth();
			Authority auth = authDao.find(Long.valueOf(id));
			
			ra.setAuth(auth);
			ra.setRole(role);
			
			raDao.save(ra);
		}
	}
	
	@Override
	@Transactional
	public void setMenuByRole(Role role, String[] menuIds) {
		// TODO Auto-generated method stub
		Set<Moudle> moudleSet = new HashSet<>();
		for(String id : menuIds) {
			Moudle m = moudleDao.find(Long.valueOf(id));
			
			moudleSet.add(m);
		}
		
		role.setModules(moudleSet);
		
		roleDao.save(role);
	}
}
