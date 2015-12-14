package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;
import com.cngc.pm.activiti.jpa.entity.SecJobJpaEntity;

@Service
public class SecJobEntityManager {
	   @PersistenceContext
	    private EntityManager entityManager;
	    
	    @Transactional
	    public SecJobJpaEntity newSecJob(DelegateExecution execution) {
	    	SecJobJpaEntity job = new SecJobJpaEntity();
	    	job.setProcessInstanceId(execution.getProcessInstanceId());
	    	job.setUserId(execution.getVariable("user").toString());
	    	job.setType(execution.getVariable("type").toString());
	    	job.setApplyTime(new Date());
	        entityManager.persist(job);
	        return job;
	    }
	    @Transactional
	    public boolean setSecJobStatus(DelegateExecution execution){
	    	SecJobJpaEntity job = (SecJobJpaEntity)execution.getVariable("secjob");
	    	job.setExecutionTime(new Date());
	    	entityManager.persist(job);
	    	return true;
	    }
}
