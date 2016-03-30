package com.cngc.pm.activiti.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;

import com.cngc.pm.activiti.jpa.entity.IncidentJpaEntity;
import com.cngc.pm.activiti.jpa.entity.InspectionJpaEntity;
import com.cngc.utils.PropertyFileUtil;

@Service
public class InspectionEntityManager {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public InspectionJpaEntity newInspection(DelegateExecution execution) {
		InspectionJpaEntity inspection = new InspectionJpaEntity();
		inspection.setProcessInstanceId(execution.getProcessInstanceId());
		inspection.setExecutionUser(execution.getVariable("user").toString());
		if(execution.getVariable("template")!=null)
			inspection.setTemplate(execution.getVariable("template").toString());
		inspection.setCreatedTime(new Date());
		entityManager.persist(inspection);
		return inspection;
	}

	@Transactional
	public boolean setInspectionStatus(DelegateExecution execution) {
		InspectionJpaEntity inspection = (InspectionJpaEntity) execution.getVariable("inspection");
		if(inspection==null)
			return true;
		if(execution.getVariable("user")!=null)
			inspection.setExecutionUser(execution.getVariable("user").toString());
		inspection.setExecutionTime(new Date());
		if(execution.getVariable("status")!=null)
			inspection.setStatus(execution.getVariable("status").toString());
		else
			inspection.setStatus( PropertyFileUtil.getStringValue("syscode.inspection.normal") );

		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				inspection.setEndbyuser(true);
		}
		
		if (inspection.getStatus().equals( PropertyFileUtil.getPropertyValue("syscode.inspection.abnormal") )) {
			// 异常生成工单
			IncidentJpaEntity incident = new IncidentJpaEntity();
			incident.setAbs(execution.getVariable("abstract").toString());
			incident.setApplyUser(execution.getVariable("user").toString());
			incident.setInfluence(execution.getVariable("influence").toString());
			incident.setCritical(execution.getVariable("critical").toString());
			incident.setPriority(execution.getVariable("priority").toString());
			incident.setSource("03");
			incident.setStatus("01");
			incident.setType("01");
			incident.setApplyTime(new Date());
			entityManager.persist(incident);
			entityManager.flush();

			// 启动流程
			ProcessDefinition processDefinition = execution.getEngineServices().getRepositoryService()
					.createProcessDefinitionQuery()
					.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident")).active()
					.latestVersion().singleResult();
			Map<String, String> variables = new HashMap<String, String>();
			variables.put("id", String.valueOf(incident.getId()));
			execution.getEngineServices().getFormService().submitStartFormData(processDefinition.getId(), variables);
			inspection.setIncidentId(incident.getId());
		}
		entityManager.persist(inspection);
		
		return true;
	}
}
