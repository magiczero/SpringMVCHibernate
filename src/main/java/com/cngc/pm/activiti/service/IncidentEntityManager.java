package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.IncidentDAO;
import com.cngc.pm.model.Incident;
import com.cngc.utils.PropertyFileUtil;

@Service
public class IncidentEntityManager {
//    @PersistenceContext
//    private EntityManager entityManager;
	@Autowired
	private IncidentDAO incidentDao;
	
	@Transactional
	public Incident getIncident(DelegateExecution execution){
		//Incident incident = entityManager.find(Incident.class, Long.valueOf(execution.getVariable("id").toString()) );
		Incident incident = incidentDao.find(Long.valueOf(execution.getVariable("id").toString()));
		incident.setProcessInstanceId(execution.getProcessInstanceId());
//		entityManager.persist(incident);
		incidentDao.save(incident);
		return incident;
	}
	
	@Transactional
	public boolean setIncidentStatus(DelegateExecution execution,String status){
		Incident incident = (Incident)execution.getVariable("incident");
		incident.setStatus(status);
		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				incident.setEndbyuser(true);
		}
		if( status.equals(PropertyFileUtil.getStringValue("syscode.incident.status.finished")) )
			incident.setRecoverTime(new Date());
//		entityManager.persist(incident);
		incidentDao.save(incident);
		return true;
	} 
}
