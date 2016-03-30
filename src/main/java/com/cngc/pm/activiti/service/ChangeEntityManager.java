package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import com.cngc.pm.activiti.jpa.entity.ChangeJpaEntity;
import com.cngc.pm.service.ChangeService;
import com.cngc.utils.PropertyFileUtil;


@Service
public class ChangeEntityManager {
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private ChangeService changeService;
    
	@Transactional
	public ChangeJpaEntity getChange(DelegateExecution execution){
		ChangeJpaEntity change = entityManager.find(ChangeJpaEntity.class, Long.valueOf(execution.getVariable("id").toString()) );
		change.setProcessInstanceId(execution.getProcessInstanceId());
		change.setApplyTime(new Date());
		entityManager.persist(change);
		return change;
	}
	
	@Transactional
	public boolean setChangeStatus(DelegateExecution execution,String status){
		ChangeJpaEntity change = (ChangeJpaEntity)execution.getVariable("change");
		change.setStatus( status );
		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				change.setEndbyuser(true);
		}
		if( status.equals(PropertyFileUtil.getStringValue("syscode.change.status.finished")) )
		{
			changeService.updateCi(change.getId());
			change.setEndTime(new Date());
		}
		entityManager.persist(change);
		return true;
	} 
}
