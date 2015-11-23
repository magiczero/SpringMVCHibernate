package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;
import com.cngc.pm.activiti.jpa.entity.ChangeJpaEntity;


@Service
public class ChangeEntityManager {
    @PersistenceContext
    private EntityManager entityManager;
    
	@Transactional
	public ChangeJpaEntity getChange(DelegateExecution execution){
		ChangeJpaEntity change = entityManager.find(ChangeJpaEntity.class, Long.valueOf(execution.getVariable("id").toString()) );
		change.setProcessInstanceId(execution.getProcessInstanceId());
		change.setApplyTime(new Date());
		entityManager.persist(change);
		return change;
	}
	
	@Transactional
	public boolean setChangeStatus(DelegateExecution execution){
		ChangeJpaEntity change = (ChangeJpaEntity)execution.getVariable("change");
		change.setStatus("07");
		change.setEndTime(new Date());
		entityManager.persist(change);
		return true;
	} 
}
