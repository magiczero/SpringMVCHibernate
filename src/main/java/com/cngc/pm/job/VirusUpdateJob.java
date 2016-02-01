package com.cngc.pm.job;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

import com.cngc.utils.PropertyFileUtil;

public class VirusUpdateJob {
	@Resource
	private FormService formService;
	@Resource
	private RepositoryService repositoryService;
	

	/**
	 * 启动病毒升级任务
	 */
	public void startVirusUpdate() {
		System.out.println("发起病毒升级任务...");
		String processKey = PropertyFileUtil.getStringValue("workflow.processkey.update");
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey( processKey ).latestVersion().singleResult();
		
		Map<String,String> variables = new HashMap<String,String>();
		variables.put("user", "chenweijia");
		variables.put("updateType",PropertyFileUtil.getStringValue("syscode.update.virus"));
		
		formService.submitStartFormData(processDefinition.getId(), variables);
	}
}
