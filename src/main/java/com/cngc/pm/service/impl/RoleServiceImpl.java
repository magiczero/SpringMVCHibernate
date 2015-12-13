package com.cngc.pm.service.impl;

import static com.cngc.utils.Common.isNumeric;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cngc.pm.dao.AuthorityDAO;
import com.cngc.pm.dao.RoleDAO;
import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Role;
import com.cngc.pm.service.RoleService;
import com.googlecode.genericdao.search.Search;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDAO roleDao;
	
	@Autowired
	private AuthorityDAO authDao;
	
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
	public List<Authority> getAuthsByRole(long id) {
		// TODO Auto-generated method stub
		Search search = new Search(Authority.class);
		
		search.addFilterEqual("roleAuths.role.id", id);
		
		return authDao.search(search);
	}
}
