package com.cngc.pm.activiti.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.stereotype.Service;

import com.cngc.pm.activiti.jpa.entity.AttachmentEntity;
import com.cngc.pm.activiti.jpa.entity.SecJobJpaEntity;
import com.cngc.pm.service.AttachService;

@Service
public class SecJobEntityManager {
	   @PersistenceContext
	   private EntityManager entityManager;
	   @Resource
	   private AttachService attachService;
	    
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
			if(execution.getCurrentActivityName()!=null)
			{
				// 按流程步骤运行至结束
				if(execution.getCurrentActivityName().equals("End"))
				{
					//job.setUserId(execution.);
					job.setEndbyuser(true);
				}
			}
	    	job.setExecutionTime(new Date());
	    	entityManager.persist(job);
	    	return true;
	    }
	    @Transactional
	    public boolean saveAttach(DelegateExecution execution){
	    	SecJobJpaEntity job = (SecJobJpaEntity)execution.getVariable("secjob");
			if(!StringUtils.isEmpty(job.getAttachment())) {
				String[] ids = job.getAttachment().split(",");
				
				Set<AttachmentEntity> set = new HashSet<>();
				
				for(String id : ids) {
					AttachmentEntity attach = entityManager.find(AttachmentEntity.class, Long.valueOf(id));
					set.add(attach);
				}
				job.setAttachs(set);
				entityManager.persist(job);
			}
	    	return true;
	    }
}
