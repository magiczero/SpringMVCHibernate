package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Group;

public interface GroupService {

	public List<Group> getAll();
	
	public List<Group> getAllTop();
	
	public String getAllWithJson();
	
	public Group getById(Long id);
	
	boolean saveGroup(Group group);
	
	String getChildByGroup(String group, boolean haveUsers);

	/**
	 * 获取单位中id最大的
	 * @return
	 */
	public Group getMaxTop();

	/**
	 * 获取子部门中id最大的
	 * @param parentGroup 上级部门
	 * @return
	 */
	public Group getMaxChild(Group parentGroup);
}
