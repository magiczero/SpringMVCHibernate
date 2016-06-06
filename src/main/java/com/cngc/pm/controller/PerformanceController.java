package com.cngc.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.service.UserService;

@Controller
@RequestMapping(value = "/performance")
public class PerformanceController {

	@Resource
	private UserService userService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private RepositoryService repositoryService;
	
	@RequestMapping(value = "/user/{userid}", method = RequestMethod.GET)
	public String userPerformance(@PathVariable("userid") String userid, Model model)
	{
		//待办任务
		List<Task> tasks = new ArrayList<Task>();
		Map<String, String> startusers = new HashMap<String, String>();
		tasks = taskService.createTaskQuery().taskAssignee(userid).active().list();
		
		for (Task task : tasks) {
			String startuser = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId();
			if(startuser!=null)
				startusers.put(task.getProcessInstanceId(), userService.getUserName(startuser));
		}
		// 参与的任务
		Set<String> processInstanceIds = new HashSet<String>();
		List<HistoricTaskInstance> mytasks = historyService.createHistoricTaskInstanceQuery()
				.taskAssignee(userid).list();
		for (HistoricTaskInstance task : mytasks)
			processInstanceIds.add(task.getProcessInstanceId());
		List<HistoricProcessInstance> processes = historyService.createHistoricProcessInstanceQuery().processInstanceIds(processInstanceIds).list();
		for(HistoricProcessInstance process:processes)
		{
			if(process.getStartUserId()!=null)
				startusers.put(process.getId(), userService.getUserName(process.getStartUserId()));
		}

		model.addAttribute("res", repositoryService);
		model.addAttribute("tasks", tasks);
		model.addAttribute("startusers", startusers);
		model.addAttribute("username", userService.getUserName(userid));
		model.addAttribute("processes", processes);
		
		return "performance/user";
	}
}
