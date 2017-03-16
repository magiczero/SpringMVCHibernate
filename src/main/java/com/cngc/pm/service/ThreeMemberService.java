package com.cngc.pm.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.cngc.pm.model.manage.ManageType;
import com.cngc.pm.model.manage.ManagerForm;
import com.cngc.pm.model.manage.Relations;

public interface ThreeMemberService {

	/**
	 * 根据角色选择要填写的项
	 * @param type
	 * @return
	 */
	List<Relations> getRelationItemListByType(ManageType type);
	
	List<Relations> getRelationListByType(ManageType type);
	
	void save(ManagerForm form, Task task);
	
	ManagerForm getFormByPid(String pid);
	
	Map<String, Object> getDocumentData(ManagerForm mf) throws JsonParseException, JsonMappingException, IOException;
}
