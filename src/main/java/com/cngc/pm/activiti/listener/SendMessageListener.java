package com.cngc.pm.activiti.listener;

import javax.annotation.Resource;

import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

import com.cngc.pm.service.MessageService;
import com.cngc.utils.PropertyFileUtil;

public class SendMessageListener implements ActivitiEventListener {
	@Resource
	private MessageService messageService;

	@Override
	public void onEvent(ActivitiEvent event) {
		ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
		Object entity = entityEvent.getEntity();
		ProcessDefinition process = event.getEngineServices().getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(event.getProcessDefinitionId()).singleResult();
		String processName = process.getName();
		String processKey = process.getKey();
		switch (event.getType()) {
		case TASK_CREATED:
			if (entity instanceof TaskEntity) {
				TaskEntity task = (TaskEntity) entity;
				if (getSendFlagByTaskKey("workflow.listener.taskcreated", "message.workflow.taskkey.taskcreated",
						task.getTaskDefinition().getKey())) {
					// 消息提醒
					if (!task.getAssignee().isEmpty()) {
						String msg = PropertyFileUtil.getStringValue("message.format.workflow.taskcreated");
						msg = msg.replaceAll("%PROCESS_NAME%", processName).replaceAll("%TASK_NAME%", task.getName());
						
						messageService.sendMessage("系统", task.getAssignee(), msg, "#");
					}
				}
			}
			break;
		case TASK_ASSIGNED:
			if (entity instanceof TaskEntity) {
				TaskEntity task = (TaskEntity) entity;
				if (getSendFlagByTaskKey("workflow.listener.taskassigned", "message.workflow.taskkey.taskassigned",
						task.getTaskDefinition().getKey())) {
					// 消息提醒
					if (!task.getAssignee().isEmpty()) {
						String msg = PropertyFileUtil.getStringValue("message.format.workflow.taskassigned");
						msg = msg.replaceAll("%PROCESS_NAME%", processName).replaceAll("%TASK_NAME%", task.getName());
						
						messageService.sendMessage("系统", task.getAssignee(), msg, "#");
					}
				}
			}
			break;
		case TASK_COMPLETED:
			break;
		case PROCESS_COMPLETED:

			if (getSendFlagByProcessKey("workflow.listener.processcompleted",
					"message.workflow.processkey.processcompleted", processKey)) {
				if (entity instanceof ExecutionEntity) {
					ExecutionEntity execution = (ExecutionEntity) entity;

					HistoricProcessInstance instance = event.getEngineServices().getHistoryService()
							.createHistoricProcessInstanceQuery().processInstanceId(event.getProcessInstanceId())
							.singleResult();
					String msg = PropertyFileUtil.getStringValue("message.format.workflow.processcompleted");
					msg = msg.replaceAll("%PROCESS_NAME%", processName);
					messageService.sendMessage("系统", instance.getStartUserId(), msg, "#");
				}
			}
			break;
		}
	}

	/**
	 * 根据流程key判断是否发送消息
	 * 
	 * @param msgswitch
	 * @param processkeys
	 * @param key
	 * @return
	 */
	private boolean getSendFlagByProcessKey(String msgswitch, String processkeys, String key) {

		// 先检查是否发送消息
		if (!PropertyFileUtil.getBoolValue(msgswitch))
			return false;
		// 再判断该流程是否需要发送消息
		if (processkeys == null)
			return false;
		if (processkeys.isEmpty())
			return false;
		String k = PropertyFileUtil.getStringValue(processkeys);
		if (k.equals("ALL"))
			return true;
		else {
			String ks[] = k.split(",");
			for (String s : ks) {
				if (s.equals(key)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 根据任务key判断是否发送消息
	 * 
	 * @param msgswitch
	 * @param taskKeys
	 * @param key
	 * @return
	 */
	private boolean getSendFlagByTaskKey(String msgswitch, String taskKeys, String key) {

		// 先检查是否发送消息
		if (!PropertyFileUtil.getBoolValue(msgswitch))
			return false;
		// 再判断该流程是否需要发送消息
		if (taskKeys == null)
			return false;
		if (taskKeys.isEmpty())
			return false;
		String k = PropertyFileUtil.getStringValue(taskKeys);
		if (k.equals("ALL"))
			return true;
		else {
			String ks[] = k.split(",");
			for (String s : ks) {
				if (s.equals(key)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isFailOnException() {
		return false;
	}
}
