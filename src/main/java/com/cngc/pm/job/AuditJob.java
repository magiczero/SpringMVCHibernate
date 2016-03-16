package com.cngc.pm.job;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

import com.cngc.utils.PropertyFileUtil;

public class AuditJob {

	@Resource
	private FormService formService;
	@Resource
	private RepositoryService repositoryService;
	
	/**
	 * 启动审计报告
	 */
	public void startAudit(){
		System.out.println("发起审计报告任务...");
		String processKey = PropertyFileUtil.getStringValue("workflow.processkey.secjob");
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey( processKey ).latestVersion().singleResult();
		
		Map<String,String> variables = new HashMap<String,String>();
		variables.put("type", PropertyFileUtil.getStringValue("syscode.secjob.audit"));
		variables.put("user", "chenweijia");
		
		formService.submitStartFormData(processDefinition.getId(), variables);
	}
}
