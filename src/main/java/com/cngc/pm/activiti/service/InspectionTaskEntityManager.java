package com.cngc.pm.activiti.service;

import javax.transaction.Transactional;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cngc.pm.model.computer.InspectionTask;
import com.cngc.pm.dao.computer.InspectionTaskDAO;

@Service
public class InspectionTaskEntityManager {

	@Autowired
	private InspectionTaskDAO taskDao;
	
	@Transactional
	public InspectionTask getTask(DelegateExecution execution){
		InspectionTask task = taskDao.find( Long.valueOf(execution.getVariable("id").toString()) );
		task.setProcessInstanceId(execution.getProcessInstanceId());
		taskDao.save(task);
		return task;
	}

	@Transactional
	public boolean setTaskStatus(DelegateExecution execution)
	{
		InspectionTask task = (InspectionTask)execution.getVariable("inspectionTask");
		//String s = execution.getVariable("pmApproval").toString();
		task.setPublish(false);
		if(execution.getVariable("pmApproval")!=null&&execution.getVariable("pmApproval").toString().equals("true"))
		{
			if(execution.getVariable("infoApproval")!=null&&execution.getVariable("infoApproval").toString().equals("true"))
				task.setPublish(true);
		}
		taskDao.save(task);
		return true;
	}
}
