package com.cngc.pm.activiti.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.IncidentDAO;
import com.cngc.pm.dao.InspectionDAO;
import com.cngc.pm.model.Incident;
import com.cngc.pm.model.Inspection;
import com.cngc.utils.PropertyFileUtil;

@Service
public class InspectionEntityManager {
//	@PersistenceContext
//	private EntityManager entityManager;
	@Autowired
	private InspectionDAO inspectionDao;
	@Autowired
	private IncidentDAO incidentDao;

	@Transactional
	public Inspection newInspection(DelegateExecution execution) {
		Inspection inspection = new Inspection();
		inspection.setProcessInstanceId(execution.getProcessInstanceId());
		inspection.setExecutionUser(execution.getVariable("user").toString());
		if(execution.getVariable("template")!=null)
			inspection.setTemplate(execution.getVariable("template").toString());
		inspection.setCreatedTime(new Date());
//		entityManager.persist(inspection);
		inspectionDao.save(inspection);
		return inspection;
	}

	@Transactional
	public boolean setInspectionStatus(DelegateExecution execution) {
		Inspection inspection = (Inspection) execution.getVariable("inspection");
		if(inspection==null)
			return true;
		if(execution.getVariable("user")!=null)
			inspection.setExecutionUser(execution.getVariable("user").toString());
		inspection.setExecutionTime(new Date());
		if(execution.getVariable("status")!=null)
			inspection.setStatus(execution.getVariable("status").toString());
		else
			inspection.setStatus( PropertyFileUtil.getStringValue("syscode.inspection.normal") );
		
		//查找template_data
		inspection.setTemplateData(inspectionDao.getTemplateData(inspection.getId()));

		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				inspection.setEndbyuser(true);
		}
		
		if (inspection.getStatus().equals( PropertyFileUtil.getPropertyValue("syscode.inspection.abnormal") )) {
			// 异常生成工单
			Incident incident = new Incident();
			incident.setAbs(execution.getVariable("abstract").toString());
			incident.setApplyUser(execution.getVariable("user").toString());
			incident.setInfluence(execution.getVariable("influence").toString());
			incident.setCritical(execution.getVariable("critical").toString());
			incident.setPriority(execution.getVariable("priority").toString());
			incident.setSource("03");
			incident.setStatus("01");
			incident.setType("01");
			incident.setApplyTime(new Date());
//			entityManager.persist(incident);
			incidentDao.save(incident);
			incidentDao.flush();
//			entityManager.flush();

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
//		entityManager.persist(inspection);
		inspectionDao.save(inspection);
		
		return true;
	}
}
