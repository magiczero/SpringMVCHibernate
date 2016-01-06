package com.cngc.pm.controller.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.service.MessageService;
import com.cngc.pm.service.UserService;

@Controller
@RequestMapping("/workflow/task")
public class TaskController {

	@Resource
	private FormService formService;
	@Resource
	private IdentityService identityService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private MessageService messageService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private UserService userService;

	/**
	 * 待办任务
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mytask", method = RequestMethod.GET)
	public String myTaskList(Model model, HttpSession session, Authentication authentication) {
		// User user = UserUtil.getUserFromSession(request.getSession());

		List<Task> tasks = new ArrayList<Task>();

		tasks = taskService.createTaskQuery().taskCandidateOrAssigned(userUtil.getUserId(authentication)).active()
				.list();

		model.addAttribute("tasks", tasks);
		model.addAttribute("res", repositoryService);

		return "/workflow/mytask";
	}

	/**
	 * 获取待办任务数量
	 * 
	 * @param model
	 * @param session
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "/getmytaskcount", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMyTaskCount(Model model, HttpSession session, Authentication authentication) {
		Map<String, Object> map = new HashMap<String, Object>();
		int myjob = 0, claim = 0;
		
/*		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = null;
		if(auth!=null)
			userid = ((UserDetails) auth.getPrincipal()).getUsername();*/

		myjob = taskService.createTaskQuery().taskCandidateOrAssigned(userUtil.getUserId(authentication)).active()
				.list().size();

		claim = taskService.createTaskQuery().taskAssignee(userUtil.getUserId(authentication)).active().list().size();

		map.put("count", myjob);
		map.put("claim", claim);

		return map;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String taskList(Model model, HttpSession session) {
		// User user = UserUtil.getUserFromSession(request.getSession());
		Map<String, Object> map = new HashMap<String, Object>();
		List<Task> tasks = new ArrayList<Task>();

		tasks = taskService.createTaskQuery().active().orderByTaskId().desc().list();
		for (Task task : tasks) {
			List<IdentityLink> identity = taskService.getIdentityLinksForTask(task.getId());
			map.put(task.getId(), identity);
		}

		model.addAttribute("tasks", tasks);
		model.addAttribute("res", repositoryService);
		model.addAttribute("candidate", map);
		model.addAttribute("users", userService.getEngineer());

		return "/workflow/task-list";
	}

	@RequestMapping(value = "/setuser")
	public String setUser(Model model, HttpServletRequest request) {
		// User user = UserUtil.getUserFromSession(request.getSession());

		String assignee = request.getParameter("assignee");
		String candidateuser = request.getParameter("candidateUser");
		String candidategroup = request.getParameter("candidateGroup");
		String taskid = request.getParameter("taskId");

		if (taskid != "0") {
			if (!assignee.isEmpty()) {
				if(!assignee.equals("00"))
					taskService.setAssignee(taskid, assignee);
			}
			if (candidateuser!=null) {
				taskService.addCandidateUser(taskid, candidateuser);
			}
			if (!candidategroup.isEmpty()) {
				taskService.addCandidateGroup(taskid, candidategroup);
			}
		}
		return "redirect:list";
	}

	/**
	 * 任务签收
	 * 
	 * @param taskId
	 * @param session
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/claim/{id}")
	public String claim(@PathVariable("id") String taskId, Authentication authentication) {

		String re = "/workflow/task/mytask";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

		taskService.claim(taskId, userUtil.getUserId(authentication));
		// redirectAttributes.addFlashAttribute("message", "任务已签收");
		if (task == null)
			return "redirect:/workflow/task/mytask";

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(task.getProcessDefinitionId()).singleResult();
		switch (processDefinition.getKey()) {
		case "INCIDENT":
			if (userUtil.IsCommonUser(authentication))
				re = "/incident/mylist";
			else
				re = "/incident/list";
			break;
		case "CHANGE":
			re = "/change/list";
			break;
		}
		return "redirect:" + re;
	}

	/**
	 * 任务处理
	 * 
	 * @param taskId
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/complete/{taskId}")
	@SuppressWarnings("unchecked")
	public String completeTask(@PathVariable("taskId") String taskId, RedirectAttributes redirectAttributes,
			HttpServletRequest request, HttpSession session, Authentication authentication) {
		Map<String, String> formProperties = new HashMap<String, String>();

		// 从request中读取参数然后转换
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String key = entry.getKey();

			// fp_的意思是form paremeter
			if (StringUtils.defaultString(key).startsWith("fp_")) {
				formProperties.put(key.split("_")[1], entry.getValue()[0]);
			}
		}
		try {
			identityService.setAuthenticatedUserId(userUtil.getUserId(authentication));
			formService.submitTaskFormData(taskId, formProperties);
		} finally {
			// identityService.setAuthenticatedUserId(null);
		}

		redirectAttributes.addFlashAttribute("message", "任务完成：taskId=" + taskId);

		if (StringUtils.isEmpty(request.getParameter("redirectAddress")))
			return "redirect:/workflow/task/list";
		else
			return "redirect:" + request.getParameter("redirectAddress");
	}

	@RequestMapping(value = "/comment/save", method = RequestMethod.POST)
	public String commentSave(@RequestParam("fp_taskId") String taskId,
			@RequestParam("fp_processInstanceId") String processInstanceId, @RequestParam("fp_message") String message,
			HttpServletRequest request, Model model, Authentication authentication) {

		// 设置当前人为意见的所属人
		identityService.setAuthenticatedUserId(userUtil.getUserId(authentication));
		Task task;
		if (taskId==null || taskId.equals("") || taskId.equals("0")) {
			task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
			taskId = task.getId();
		} else {
			task = taskService.createTaskQuery().taskId(taskId).singleResult();
		}
		taskService.addComment(taskId, processInstanceId, message);
		// 是否提醒任务办理人
		String isnotify = request.getParameter("isnotify");
		if (isnotify != null) {
			if (isnotify.equals("true")) {
				String s = message;
				s = userUtil.getUserId(authentication) + "对 [" + task.getName() + "] 任务" + "发表了意见:" + message;
				messageService.sendMessage("系统提示", task.getAssignee(), s, "#");
			}
		}

		if (StringUtils.isEmpty(request.getParameter("redirectAddress")))
			return "redirect:/workflow/task/list";
		else
			return "redirect:" + request.getParameter("redirectAddress");
	}
	
	@RequestMapping(value = "/comment/delete/{id}")
	@ResponseBody
	public Map<String,Object> deleteComment(@PathVariable("id") String id){
		Map<String,Object> result = new HashMap<String,Object>();
		taskService.deleteComment(id);
		result.put("result", "true");
		return result;
	}

	@RequestMapping(value = "/comment/list")
	@ResponseBody
	public Map<String, Object> getCommentList(@RequestParam("processInstanceId") String processInstanceId,
			@RequestParam("taskId") String taskId, Model model) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> commentAndEventsMap = new LinkedHashMap<String, Object>();
		/*
		 * 根据不同情况使用不同方式查询
		 */
		if (StringUtils.isNotBlank(processInstanceId)) {
			List<Comment> processInstanceComments = taskService.getProcessInstanceComments(processInstanceId);
			for (Comment comment : processInstanceComments) {
				// String commentId = (String)
				// PropertyUtils.getProperty(comment, "id");
				String commentId = comment.getId();
				commentAndEventsMap.put(commentId, comment);
			}
			// 提取任务任务名称
			List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
					.processInstanceId(processInstanceId).list();
			Map<String, String> taskNames = new HashMap<String, String>();
			for (HistoricTaskInstance historicTaskInstance : list) {
				taskNames.put(historicTaskInstance.getId(), historicTaskInstance.getName());
			}
			result.put("taskNames", taskNames);
		}
		/*
		 * 查询所有类型的事件
		 */
		if (StringUtils.isNotBlank(taskId)) { // 根据任务ID查询
			List<Event> taskEvents = taskService.getTaskEvents(taskId);
			for (Event event : taskEvents) {
				// String eventId = (String) PropertyUtils.getProperty(event,
				// "id");
				String eventId = event.getId();
				commentAndEventsMap.put(eventId, event);
			}
		}
		result.put("events", commentAndEventsMap.values());
		return result;
	}

}
