package com.cngc.pm.service;

import java.util.List;

import com.cngc.pm.model.Group;

public interface GroupService {

	public List<Group> getAll();
	
	public List<Group> getAllTop();
	
	public String getAllWithJson();
	
	public Group getById(Long id);
	
	boolean saveGroup(Group group);
}
