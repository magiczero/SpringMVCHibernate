package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import com.cngc.pm.activiti.jpa.entity.IncidentJpaEntity;
import com.cngc.utils.PropertyFileUtil;

@Service
public class IncidentEntityManager {
    @PersistenceContext
    private EntityManager entityManager;
    
	@Transactional
	public IncidentJpaEntity getIncident(DelegateExecution execution){
		IncidentJpaEntity incident = entityManager.find(IncidentJpaEntity.class, Long.valueOf(execution.getVariable("id").toString()) );
		incident.setProcessInstanceId(execution.getProcessInstanceId());
		entityManager.persist(incident);
		return incident;
	}
	
	@Transactional
	public boolean setIncidentStatus(DelegateExecution execution,String status){
		IncidentJpaEntity incident = (IncidentJpaEntity)execution.getVariable("incident");
		incident.setStatus(status);
		if( status.equals(PropertyFileUtil.getStringValue("syscode.incident.status.finished")) )
			incident.setRecoverTime(new Date());
		entityManager.persist(incident);
		return true;
	} 
}
