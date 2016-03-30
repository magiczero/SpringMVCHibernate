package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import com.cngc.pm.activiti.jpa.entity.UpdateJpaEntity;

@Service
public class UpdateEntityManager {
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public UpdateJpaEntity newUpdate(DelegateExecution execution) {
    	UpdateJpaEntity update = new UpdateJpaEntity();
    	update.setProcessInstanceId(execution.getProcessInstanceId());
    	update.setUserId(execution.getVariable("user").toString());
    	update.setUpdateType(execution.getVariable("updateType").toString());
    	update.setCreatedTime(new Date());
        entityManager.persist(update);
        return update;
    }
    @Transactional
    public boolean setUpdateStatus(DelegateExecution execution){
    	UpdateJpaEntity update = (UpdateJpaEntity)execution.getVariable("update");
		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				update.setEndbyuser(true);
		}
    	update.setExecutionTime(new Date());
    	entityManager.persist(update);
    	return true;
    }
}
