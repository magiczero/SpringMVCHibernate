package com.cngc.pm.job;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

import com.cngc.utils.PropertyFileUtil;

/**
 * 日常巡检
 * 
 * @author andy
 *
 */
public class InspectionJob {
	
	@Resource
	private FormService formService;
	@Resource
	private RepositoryService repositoryService;
	

	/**
	 * 启动日常巡检
	 */
	public void startInspection() {
		System.out.println("发起巡检任务...");
		String processKey = PropertyFileUtil.getStringValue("workflow.processkey.inspection");
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey( processKey ).latestVersion().singleResult();
		
		Map<String,String> variables = new HashMap<String,String>();
		variables.put("user", "admin");
		
		formService.submitStartFormData(processDefinition.getId(), variables);
	}
}
