package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Resources;

public interface AuthorityService {

	List<Authority> getAll();

	List<Resources> getListResources();

	Resources loadResourcesById(Long id);

	void save(Authority authority);

	Authority getById(long id);
	
	void update(Authority authority);

	/**
	 * 保存或修改权限信息，并保存到操作日志中
	 * @param save					true为保存，false为修改
	 * @param authority
	 * @param username
	 * @param resourcesIds
	 */
	void save(boolean save, Authority authority,String username, String...resourcesIds );

	void update(Authority authority, String...resourIds);
	
	/**
	 * 删除权限，首先要确保权限与其他关联关系失效，删除后记录到操作日志中
	 * @param auth
	 * @param username	 谁删除的
	 * @return
	 */
	boolean delete(Authority auth, String username);
}
