package com.cngc.pm.activiti.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.dao.AttachmentDAO;
import com.cngc.pm.dao.SecJobDAO;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.SecJob;

@Service
public class SecJobEntityManager {
//	   @PersistenceContext
//	   private EntityManager entityManager;
	@Autowired
	private SecJobDAO secJobDao;
//	   @Resource
//	   private AttachService attachService;
	@Autowired
	private AttachmentDAO attachDao;
	    
	    @Transactional
	    public SecJob newSecJob(DelegateExecution execution) {
	    	SecJob job = new SecJob();
	    	job.setProcessInstanceId(execution.getProcessInstanceId());
	    	job.setUserId(execution.getVariable("user").toString());
	    	job.setType(execution.getVariable("type").toString());
	    	job.setApplyTime(new Date());
//	        entityManager.persist(job);
	        secJobDao.save(job);
	        return job;
	    }
	    @Transactional
	    public boolean setSecJobStatus(DelegateExecution execution){
	    	SecJob job = (SecJob)execution.getVariable("secjob");
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
	    	secJobDao.save(job);
	    	return true;
	    }
	    @Transactional
	    public boolean saveAttach(DelegateExecution execution){
	    	SecJob job = (SecJob)execution.getVariable("secjob");
			if(!StringUtils.isEmpty(job.getAttachment())) {
				String[] ids = job.getAttachment().split(",");
				
				Set<Attachment> set = new HashSet<>();
				
				for(String id : ids) {
					Attachment attach = attachDao.find( Long.valueOf(id));
					set.add(attach);
				}
				job.setAttachs(set);
				secJobDao.save(job);
			}
	    	return true;
	    }
}
