package com.cngc.pm.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.LeaderTask;
import com.cngc.pm.service.LeaderTaskService;

@Controller
@RequestMapping(value="/leadertask")
public class LeaderTaskController {
	@Resource
	private LeaderTaskService leaderTaskService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private FormService formService;
	@Resource
	private TaskService taskService;
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model,HttpServletRequest request){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
		Calendar now = Calendar.getInstance();
		if(startTime==null || startTime.isEmpty())
			startTime = String.valueOf( now.get(Calendar.YEAR) ) + "-01-01";
		if(endTime==null || endTime.isEmpty())
		{
			endTime = formatter.format( now.getTime());
		}
		
		model.addAttribute("list", leaderTaskService.search(startTime, endTime).getResult());
		model.addAttribute("runtime",runtimeService);
		model.addAttribute("res", repositoryService);
		model.addAttribute("task", taskService);
		return "leadertask/list";
	}
	@RequestMapping(value="/deal/{id}", method = RequestMethod.GET)
	public String deal(@PathVariable("id") long id,Model model){
		LeaderTask leaderTask = null;
		Task task = null;
		if(id!=0)
		{
			leaderTask = leaderTaskService.getById(id);
			// 获取当前事件的task信息
			task = taskService.createTaskQuery().processInstanceId(leaderTask.getProcessInstanceId()).singleResult();
		}
		
		model.addAttribute("leaderTask", leaderTask);
		model.addAttribute("task",task);
		
		return "leadertask/deal";
	}
}
