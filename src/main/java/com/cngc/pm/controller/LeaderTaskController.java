package com.cngc.pm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
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
	private HistoryService historyService;
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
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, Authentication authentication) throws ParseException, Exception {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}
		
//		SimpleDateFormat sdf =  new SimpleDateFormat( "yyyy-MM-dd" );
//		Date start = sdf.parse(startTime);
//		Date end = sdf.parse(endTime);

		List<LeaderTask> list = null;
		List<Task> tasks = null;
		List<Task> mytasks = null;
		Map<String, Task> taskmap = null;																		//所有任务
		Map<String, Task> mytaskmap = new HashMap<String, Task>();					//我的任务
		List<String> processInstanceIds = new ArrayList<String>();
		
//		String leaderTaskProcessId = PropertyFileUtil.getStringValue("workflow.processkey.leadertask");
//		
//		HistoricProcessInstanceQuery hpq = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(leaderTaskProcessId);
//		
//		if (userUtil.IsEngineer(authentication) || userUtil.IsServiceDesk(authentication)) // 工程师只能看自己的任务
//			hpq = hpq.involvedUser(userUtil.getUserId(authentication));		//用户参与
//		
//		List<HistoricProcessInstance> historys = hpq.startedAfter(start).startedBefore(end).list();
//		
//		List<LinkedHashMap<String, Object>> list1 = new LinkedList<LinkedHashMap<String, Object>>();
//		
//		for(HistoricProcessInstance hpi : historys) {
//			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//			
//			List<HistoricVariableInstance> list2 = historyService.createHistoricVariableInstanceQuery().processInstanceId(hpi.getId()).list();  
//	        for (HistoricVariableInstance variableInstance : list2) {  
//	          System.out.println("variable: " + variableInstance.getVariableName() + " = " + variableInstance.getValue());  
//	        } 
//			
//			 Task task1 = taskService.createTaskQuery()  
//	                    .processInstanceId(hpi.getId()).singleResult();  
//			TaskFormData formData = formService.getTaskFormData(task1.getId());
//			
//			List<HistoricTaskInstance> historyTasks = historyService.createHistoricTaskInstanceQuery().processInstanceId(hpi.getId()).list();
//			
//			for(HistoricTaskInstance historyTask : historyTasks) {
//				
//			}
//			
//			map.put("processInstanceId", hpi.getId());
//			Date dueTime = new Date();
//			for(FormProperty fp : formData.getFormProperties()) {
//				switch(fp.getName()) {
//					case "fromUser" :
//						map.put("fromUserName", userUtil.getNameByUsername(fp.getValue()));
//						break;
//					case "toUser" :
//						map.put("toUserName", userUtil.getNameByUsername(fp.getValue()));
//						break;
//					case "taskTitle" :
//						break;
//					case "dueTime" :
//						dueTime = sdf.parse(fp.getValue());
//						map.put("dueTime", dueTime);
//						break;
//				}
//			}
//			
//			map.put("startTime", hpi.getStartTime());
//			 
//			String  status = "";
//			String operation = "";
//			//正在运行中的任务
//			if(hpi.getEndTime() == null) {
//				Task mytask = taskService.createTaskQuery().processInstanceId(hpi.getId()).taskAssignee(userUtil.getUserId(authentication)).active().singleResult();
//				
//				if(mytask == null) {
//					Task task = taskService.createTaskQuery().processInstanceId(hpi.getId()).active().singleResult();
//					status = "<a class=\"lnk_trace\" href='#' pid=\""+hpi.getId()+"\" pdid=\""+hpi.getProcessDefinitionId()+"\" title=\"点击查看流程图\">"+task.getName()+"</a>";
//					
//					operation = "<a href=\"javascript:void(0);\" onclick=\"act_comment_open('"+hpi.getId()+"',true)\"><span class=\"glyphicon glyphicon-edit\"></span> 意见</a> <a href=\"${contextPath }/leadertask/view/${leaderTask.id}\"  target=\"_blank\"><span class=\"glyphicon glyphicon-search\"></span> 详情</a>";
//				} else {
//					status ="<a class=\"lnk_trace\" href='#' pid=\""+hpi.getId()+"\" pdid=\""+hpi.getProcessDefinitionId()+"\" title=\"点击查看流程图\">"+mytask.getName()+"</a>";
//					
//					operation = "<a href=\"javascript:void(0);\" onclick=\"act_comment_open('"+hpi.getId()+"',false)\"><span class=\"glyphicon glyphicon-edit\"></span> 意见</a> ";
//					
//					if(mytask.getAssignee() == null) {
//						operation += "<a class=\"claim confirm\" href=\"${contextPath }/workflow/task/claim/"+mytask.getId()+"\"><span class=\"glyphicon glyphicon-edit\"></span> 签收</a>";
//					} else {
//						operation += "<a href=\"${contextPath }/leadertask/deal/${leaderTask.id}/${mytask.id}\"><span class=\"glyphicon glyphicon-edit\"></span> 办理</a>";
//					}
//				}
//			} else {
//				status = "已完成";
//			}
//
//			if(dueTime !=null && dueTime.before(new Date())) {
//				status += " <span class=\"label label-danger\">已超时</span>";
//			}
//			
//			operation += "<a href=\"javascript:void(0);\" onclick=\"act_history_open('"+hpi.getId()+"')\"><span class=\"glyphicon glyphicon-list-alt\"></span> 历史</a>";
//			//状态
//			map.put("status", status);
//			//操作
//			map.put("operation", operation);
//			
//			list1.add(map);
//		}
		
		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.leadertask"))
				.taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active().list();
		for (Task task : mytasks)
		{
			mytaskmap.put(task.getProcessInstanceId(), task);
			processInstanceIds.add(task.getProcessInstanceId());
		}
		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.leadertask")).active()
				.list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);
		
		// 我参与过的任务
		List<HistoricTaskInstance> myhistory = historyService.createHistoricTaskInstanceQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.leadertask"))
				.taskAssignee(userUtil.getUsernameByAuth(authentication)).list();
		for (HistoricTaskInstance task : myhistory)
			processInstanceIds.add(task.getProcessInstanceId());
		if (userUtil.IsWorkflowManager(authentication)) // 流程管理者可查看所有领导交办任务信息
		{
			list = leaderTaskService.search(startTime, endTime).getResult();
		}
		else
			list = leaderTaskService.search(processInstanceIds, startTime, endTime).getResult();

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", list);
//		model.addAttribute("list1", list1);

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
	@RequestMapping(value = "/dealbyprocess/{pid}/{taskid}", method = RequestMethod.GET)
	public String dealByProcessInstanceId(@PathVariable("pid") String pid,@PathVariable("taskid") String taskid){
		
		return "redirect:/leadertask/deal/" + leaderTaskService.getIdByProcessInstance(pid) + "/"+taskid;
	}
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") long id, Model model) {
		LeaderTask leaderTask = null;
		if (id != 0) 
			leaderTask = leaderTaskService.getById(id);

		model.addAttribute("leaderTask", leaderTask);

		return "leadertask/view";
	}
	@RequestMapping(value = "/viewbyprocess/{pid}", method = RequestMethod.GET)
	public String viewByProcessInstanceId(@PathVariable("pid") String pid){
		
		return "redirect:/leadertask/view/" + leaderTaskService.getIdByProcessInstance(pid);
	}
}
