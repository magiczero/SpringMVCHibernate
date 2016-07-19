package com.cngc.pm.activiti.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.FeedbackDAO;
import com.cngc.pm.model.Feedback;

@Service
public class FeedbackEntityManager {
	@Autowired
	private FeedbackDAO feedbackDao;
	
	@Transactional
	public Feedback getIncident(DelegateExecution execution){
		Feedback feedback = feedbackDao.find(Long.valueOf(execution.getVariable("id").toString()) );
		feedback.setProcessInstanceId(execution.getProcessInstanceId());
//		entityManager.persist(feedback);
		feedbackDao.save(feedback);
		return feedback;
	}
	
	@Transactional
	public boolean setFeedbackStatus(DelegateExecution execution,String status){
		Feedback feedback = (Feedback)execution.getVariable("feedback");
		feedback.setStatus(status);
		if(execution.getCurrentActivityName()!=null)
		{
			// 按流程步骤运行至结束
			if(execution.getCurrentActivityName().equals("End"))
				feedback.setEndbyuser(true);
		}
		if( status.equals("03") )
			feedback.setDoneTime(new Date());
//		entityManager.persist(feedback);
		feedbackDao.save(feedback);
		return true;
	} 
}
