package com.cngc.pm.activiti.listener;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.cngc.pm.service.ChangeItemService;

public class AccountLifeCycleStartListener implements TaskListener {

	@Resource
	private ChangeItemService changeitemService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6924349183936551711L;

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		String itemids = (String) delegateTask.getVariable("itemid");
		String processId = delegateTask.getProcessInstanceId();
		
		//String[] ids = itemids.split(",");
		changeitemService.setChangeid(itemids,processId);
		
		
	}

}
