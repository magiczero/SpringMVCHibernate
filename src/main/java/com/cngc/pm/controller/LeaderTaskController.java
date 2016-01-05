package com.cngc.pm.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.LeaderTask;
import com.cngc.pm.service.LeaderTaskService;
import com.cngc.utils.PropertyFileUtil;

@Controller
@RequestMapping(value = "/leadertask")
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
	@Resource
	private UserUtil userUtil;

	/**
	 * 领导交办信息
	 * 
	 * @param model
	 * @param request
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, Authentication authentication) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}

		List<Task> tasks = null;
		List<Task> mytasks = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();
		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.leadertask"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.leadertask")).active()
				.list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", leaderTaskService.search(startTime, endTime).getResult());

		return "leadertask/list";
	}

	@RequestMapping(value = "/deal/{id}/{taskid}", method = RequestMethod.GET)
	public String deal(@PathVariable("id") long id,@PathVariable("taskid") String taskId, Model model) {
		LeaderTask leaderTask = null;
		Task task = null;
		if (id != 0) 
			leaderTask = leaderTaskService.getById(id);
		if(taskId!=null)
			// 获取当前事件的task信息
			task = taskService.createTaskQuery().taskId(taskId).singleResult();

		model.addAttribute("leaderTask", leaderTask);
		model.addAttribute("task", task);

		return "leadertask/deal";
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") long id, Model model) {
		LeaderTask leaderTask = null;
		if (id != 0) 
			leaderTask = leaderTaskService.getById(id);

		model.addAttribute("leaderTask", leaderTask);

		return "leadertask/view";
	}
}
