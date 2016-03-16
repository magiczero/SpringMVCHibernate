package com.cngc.pm.activiti.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.identity.Group;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

	public String isGroup(DelegateExecution execution,String strgroup)
	{
		String username = (String)execution.getVariable("applyUserId");
//		Map<String,Object> map = execution.getVariables();
//		for(Map.Entry<String, Object> entry:map.entrySet())
//		{
//			System.out.println(entry.getKey() + ":" + entry.getValue().toString());
//		}
		if(username!=null)
		{
			List<Group> groups = execution.getEngineServices().getIdentityService().createGroupQuery().groupMember(username).list();
			for(Group group:groups)
			{
				if( StringUtils.equals(group.getId(),strgroup) )
					return "true";
			}
		}
		return "false";
	}
}
