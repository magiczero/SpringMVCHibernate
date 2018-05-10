package com.cngc.pm.dao;

import java.util.List;

import com.cngc.pm.model.SysUser;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;

public interface UserDAO extends GenericDAO<SysUser, Long> {

	SysUser getUserByUserName(String username);
	
	SysUser getUserByName(String name);

	void update(SysUser user);
	
	/**
	 * 按照条件查询用户，并按照单位排序
	 * @param enable					是否启用
	 * @param accountNonExpired			是否未过期
	 * @param accountNonLocked			是否未锁定
	 * @param creadentialsNonExpired	证书是否未过期
	 * @return
	 */
	List<SysUser> getAllByCondition(boolean enable, boolean accountNonExpired, boolean accountNonLocked, boolean creadentialsNonExpired);
}
