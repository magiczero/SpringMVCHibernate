package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import com.cngc.pm.activiti.jpa.entity.InspectionJpaEntity;

@Service
public class InspectionEntityManager {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public InspectionJpaEntity newInspection(DelegateExecution execution){
    	InspectionJpaEntity inspection = new InspectionJpaEntity();
    	inspection.setProcessInstanceId(execution.getProcessInstanceId());
    	inspection.setExecutionUser(execution.getVariable("user").toString());
    	inspection.setCreatedTime(new Date());
        entityManager.persist(inspection);
    	return inspection;
    }
    @Transactional
    public boolean setInspectionStatus(DelegateExecution execution){
    	InspectionJpaEntity inspection = (InspectionJpaEntity)execution.getVariable("inspection");
    	inspection.setExecutionUser(execution.getVariable("user").toString());
    	inspection.setExecutionTime(new Date());
    	entityManager.persist(inspection);
    	return true;
    }
}
