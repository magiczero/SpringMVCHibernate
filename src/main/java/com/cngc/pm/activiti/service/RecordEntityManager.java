package com.cngc.pm.activiti.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;
import com.cngc.pm.activiti.jpa.entity.RecordJpaEntity;

@Service
public class RecordEntityManager {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public RecordJpaEntity newRecord(DelegateExecution execution) {
    	RecordJpaEntity record = new RecordJpaEntity();
    	record.setProcessInstanceId(execution.getProcessInstanceId());
    	record.setUserId(execution.getVariable("user").toString());
        entityManager.persist(record);
        return record;
    }
}
