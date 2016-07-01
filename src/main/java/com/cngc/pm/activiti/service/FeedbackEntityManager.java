package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import com.cngc.pm.activiti.jpa.entity.FeedbackJpaEntity;

@Service
public class FeedbackEntityManager {
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional
	public FeedbackJpaEntity getIncident(DelegateExecution execution){
		FeedbackJpaEntity feedback = entityManager.find(FeedbackJpaEntity.class, Long.valueOf(execution.getVariable("id").toString()) );
		feedback.setProcessInstanceId(execution.getProcessInstanceId());
		entityManager.persist(feedback);
		return feedback;
	}
	
	@Transactional
	public boolean setFeedbackStatus(DelegateExecution execution,String status){
		FeedbackJpaEntity feedback = (FeedbackJpaEntity)execution.getVariable("feedback");
		feedback.setStatus(status);
		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				feedback.setEndbyuser(true);
		}
		if( status.equals("03") )
			feedback.setDoneTime(new Date());
		entityManager.persist(feedback);
		return true;
	} 
}
