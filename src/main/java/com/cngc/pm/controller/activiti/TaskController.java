package com.cngc.pm.controller.activiti;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
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
import com.cngc.pm.model.LeaderTask;
import com.cngc.pm.service.ChangeService;
import com.cngc.pm.service.IncidentService;
import com.cngc.pm.service.InspectionService;
import com.cngc.pm.service.LeaderTaskService;
import com.cngc.pm.service.MessageService;
import com.cngc.pm.service.NoticeService;
import com.cngc.pm.service.SecJobService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.UpdateService;
import com.cngc.pm.service.UserService;
import com.cngc.utils.PropertyFileUtil;
import com.cngc.utils.activiti.ProcessDefinitionCache;

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
	@Resource
	private IncidentService incidentService;
	@Resource
	private ChangeService changeService;
	@Resource
	private LeaderTaskService leaderTaskService;
	@Resource
	private SecJobService secjobService;
	@Resource
	private InspectionService inspectionService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private UpdateService updateService;

	/**
	 * 待办任务
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mytask", method = RequestMethod.GET)
	public String myTaskList(Model model,  Authentication authentication) throws Exception {
		// User user = UserUtil.getUserFromSession(request.getSession());

		List<Task> tasks = new ArrayList<Task>();
		int nLeaderTask = 0;

		tasks = taskService.createTaskQuery().taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active()
				.list();
		Map<String, String> startusers = new HashMap<String, String>();
		// 未填报领导交办
		FastDateFormat fmt = FastDateFormat.getInstance("yyyy-MM-dd");
		Date current = new Date();
		String sdate = fmt.format(current);
		for (Task task : tasks) {
			//System.out.println(task.getProcessDefinitionId());
			if (task.getProcessDefinitionId()
					.indexOf(PropertyFileUtil.getStringValue("workflow.processkey.leadertask")) >= 0) {
				// 领导交办
				boolean isadd = false;
				List<Comment> comments = taskService.getProcessInstanceComments(task.getProcessInstanceId());
				for (Comment cmt : comments) {
					if (cmt.getUserId().equals(task.getAssignee()) && fmt.format(cmt.getTime()).equals(sdate))
						isadd = true;
				}
				if (!isadd) {
					// 当天没有添加意见
					nLeaderTask++;
				}
			}
			String startuser = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId();
			startusers.put(task.getProcessInstanceId(), userService.getUserName(startuser));
		}
		model.addAttribute("tasks", tasks);
		model.addAttribute("res", repositoryService);
		model.addAttribute("LeaderTask", nLeaderTask);
		model.addAttribute("notices", noticeService.getLastNotice().getResult());
		model.addAttribute("startusers", startusers);

		return "/workflow/mytask";
	}

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String board(Model model, HttpSession session, Authentication authentication) {
		int leadertaskout = 0, leadertaskfeed = 0;
		Date currentTime = Calendar.getInstance().getTime();
		List<LeaderTask> leaderlist = leaderTaskService.getNotFinishedTask().getResult();
		// 已超时的领导交办工作
		for(LeaderTask leader:leaderlist)
		{
			if(leader.getDueTime().getTime() < currentTime.getTime())
				leadertaskout += 1;
		}
		// 未反馈领导交办
		FastDateFormat fmt = FastDateFormat.getInstance("yyyy-MM-dd");
		Date current = new Date();
		String sdate = fmt.format(current);
		boolean isadd = false;
		for(LeaderTask leader:leaderlist)
		{
			List<Comment> comments = taskService.getProcessInstanceComments(leader.getProcessInstanceId());
			for (Comment cmt : comments) {
				if (cmt.getUserId().equals(leader.getToUser()) && fmt.format(cmt.getTime()).equals(sdate))
					isadd = true;
			}
			if (!isadd) {
				// 当天没有添加意见
				leadertaskfeed++;
			}
		}
		model.addAttribute("notices", noticeService.getLastNotice().getResult());
		model.addAttribute("incidentCount", incidentService.getCountByStatus());
		model.addAttribute("incidentType",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.status")).getResult());
		model.addAttribute("changeCount", changeService.getCountByStatus());
		model.addAttribute("changeType",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.change.status")).getResult());
		model.addAttribute(
				"leadertaskCount",
				runtimeService.createProcessInstanceQuery().processDefinitionKey(
						PropertyFileUtil.getStringValue("workflow.processkey.leadertask")).active().count());
		model.addAttribute("leadertaskOut",leadertaskout);
		model.addAttribute("leadertaskFeed",leadertaskfeed);
		model.addAttribute(
				"inspectionCount",
				runtimeService.createProcessInstanceQuery().processDefinitionKey(
						PropertyFileUtil.getStringValue("workflow.processkey.inspection")).active().count());
		model.addAttribute(
				"secjobCount",
				runtimeService.createProcessInstanceQuery().processDefinitionKey(
						PropertyFileUtil.getStringValue("workflow.processkey.secjob")).active().count());
		model.addAttribute(
				"updateCount",
				runtimeService.createProcessInstanceQuery().processDefinitionKey(
						PropertyFileUtil.getStringValue("workflow.processkey.update")).active().count());
		// 事件统计
		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());
		model.addAttribute(
				"incidentStat",
				incidentService.getStats("DATE_MONTH", null, startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.incident.status.finished")));
		model.addAttribute(
				"incidentSatisfaction",
				incidentService.getStats("CODE_SATISFACTION", null, startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.incident.status.finished")));
		model.addAttribute("engineers", userService.getEngineer());
		Map<String,Object> usertask = new HashMap<String,Object>();
		List<Task> tasks = taskService.createTaskQuery().active().list();
		for(Task task:tasks)
		{
			if(task.getAssignee()!=null)
			{
				if(usertask.get(task.getAssignee())!=null)
					usertask.put(task.getAssignee(), Integer.valueOf(usertask.get(task.getAssignee()).toString()));
				else
					usertask.put(task.getAssignee(), 1);
			}
		}
		model.addAttribute("usertask", usertask);
		model.addAttribute("leadertasks", leaderTaskService.getNotFinishedTask().getResult());
		model.addAttribute("secjobs", secjobService.getNotFinishedTask().getResult());
		model.addAttribute("inspections", inspectionService.getNotFinishedTask().getResult());
		model.addAttribute("updates", updateService.getNotFinishedTask().getResult());
		
		return "/workflow/board";
	}

	@RequestMapping(value = "/getmytask", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMyTaskList(Model model, Authentication authentication) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Task> tasks = new ArrayList<Task>();
		String s = "";
		tasks = taskService.createTaskQuery().taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active()
				.list();

		s = "[";
		int i = 0;
		for (Task task : tasks) {
			if (i >= 5)
				break;
			if (s.length() > 2)
				s += ",";
			String startuser = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId();
			String processname = task.getProcessDefinitionId().substring(0, task.getProcessDefinitionId().indexOf(':'));
			String url = "";
			switch (processname) {
			case "INSPECTION":
			case "UPDATE":
			case "SECJOB":
				url = "/record/" + processname.toLowerCase();
				break;
			default:
				url = "/" + processname.toLowerCase() + "/list";
				break;
			}
			s += "{\"id\":\"" + task.getId() + "\",\"processname\":\""
					+ ProcessDefinitionCache.getProcessName(task.getProcessDefinitionId()) + "\",\"taskname\":\""
					+ task.getName() + "\",\"createtime\":\"" + task.getCreateTime().toString() + "\",\"user\":\""
					+ userService.getUserName(startuser) + "\",\"url\":\"" + url + "\"}";

			i++;
		}
		s += "]";

		map.put("tasks", s);
		return map;
	}

	/**
	 * 获取待办任务数量
	 * 
	 * @param model
	 * @param session
	 * @param authentication
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getmytaskcount", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMyTaskCount(Model model, Authentication authentication) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int myjob = 0, claim = 0;

		/*
		 * Authentication auth =
		 * SecurityContextHolder.getContext().getAuthentication(); String userid
		 * = null; if(auth!=null) userid = ((UserDetails)
		 * auth.getPrincipal()).getUsername();
		 */

		myjob = taskService.createTaskQuery().taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active()
				.list().size();

		claim = taskService.createTaskQuery().taskAssignee(userUtil.getUsernameByAuth(authentication)).active().list().size();

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
				if (!assignee.equals("00"))
					taskService.setAssignee(taskid, assignee);
			}
			if (candidateuser != null) {
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
	 * @throws Exception 
	 */
	@RequestMapping(value = "/claim/{id}")
	public String claim(@PathVariable("id") String taskId, Authentication authentication) throws Exception {

		String re = "/workflow/task/mytask";
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

		taskService.claim(taskId, userUtil.getUsernameByAuth(authentication));
		// redirectAttributes.addFlashAttribute("message", "任务已签收");
		if (task == null)
			return "redirect:/workflow/task/mytask";

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(task.getProcessDefinitionId()).singleResult();
		// switch (processDefinition.getKey()) {
		// case "INCIDENT":
		// if (userUtil.IsEngineer(authentication))
		// {
		// re = "/incident/list";
		// break;
		// }
		// if (userUtil.IsCommonUser(authentication))
		// {
		// re = "/incident/mylist";
		// break;
		// }
		// case "CHANGE":
		// re = "/change/list";
		// break;
		// }
		switch (processDefinition.getKey()) {
		case "INCIDENT":
			re = "/incident/deal/" + incidentService.getIdByProcessInstance(task.getProcessInstanceId()) + "/" + taskId;
			break;
		case "CHANGE":
			re = "/change/deal/" + changeService.getIdByProcessInstance(task.getProcessInstanceId()) + "/" + taskId;
			break;
		case "LEADERTASK":
			re = "/leadertask/deal/" + leaderTaskService.getIdByProcessInstance(task.getProcessInstanceId()) + "/"
					+ taskId;
			break;
		case "INSPECTION":
			re = "/record/inspection/deal/" + inspectionService.getIdByProcessInstance(task.getProcessInstanceId())
					+ "/" + taskId;
			break;
		// case "SECJOB":
		// re = "";
		// break;
		// case "UPDATE":
		// re = "";
		// break;
		// case "KNOWLEDGE":
		// re = "";
		// break;
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
	 * @throws Exception 
	 */
	@RequestMapping(value = "/complete/{taskId}")
	@SuppressWarnings("unchecked")
	public String completeTask(@PathVariable("taskId") String taskId, RedirectAttributes redirectAttributes,
			HttpServletRequest request,  Authentication authentication) throws Exception {
		Map<String, String> formProperties = new HashMap<String, String>();

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String pid = task.getProcessInstanceId();
		String taskName = task.getTaskDefinitionKey();
		
		// 从request中读取参数然后转换
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		String confirmNum = "0";
		for (Entry<String, String[]> entry : entrySet) {
			String key = entry.getKey();

			// fp_的意思是form paremeter
			if (StringUtils.defaultString(key).startsWith("fp_")) {
				formProperties.put(key.split("_")[1], entry.getValue()[0]);
				taskService.setVariable(taskId, key.split("_")[1], entry.getValue()[0]);  
			} else if(key.equals("radio1")) {
				confirmNum = entry.getValue()[0];
			}
		}
		try {
			identityService.setAuthenticatedUserId(userUtil.getUsernameByAuth(authentication));
			formService.submitTaskFormData(taskId, formProperties);
		} finally {
			// identityService.setAuthenticatedUserId(null);
		}
		
		Task taskNow = taskService.createTaskQuery().processInstanceId(pid).singleResult();
		if( userUtil.IsServiceDesk(authentication)){
			if(taskNow.getTaskDefinitionKey().equals("incidentConfirm"))
				return "redirect:/workflow/task/claim/"+taskNow.getId();
			else if(taskName.equals("incidentConfirm")) {
				if(confirmNum.equals("1")) {	//关闭
					return "redirect:/incident/end-process/"+taskNow.getId();
				}
			}
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
			HttpServletRequest request, Model model, Authentication authentication) throws Exception {
		String username = userUtil.getUsernameByAuth(authentication);
		// 设置当前人为意见的所属人
		identityService.setAuthenticatedUserId(username);
		Task task;
		if (taskId == null || taskId.equals("") || taskId.equals("0")) {
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
				String name = userService.getUserName(username);
				s = name + " 对 [" + task.getName() + "] 任务"
						+ "发表了意见:" + message;
				
				messageService.sendMessage("系统提示", "系统",task.getAssignee(), userService.getUserName(task.getAssignee()), s, "#");
			}
		}

		if (StringUtils.isEmpty(request.getParameter("redirectAddress")))
			return "redirect:/workflow/task/list";
		else
			return "redirect:" + request.getParameter("redirectAddress");
	}

	@RequestMapping(value = "/comment/delete/{id}")
	@ResponseBody
	public Map<String, Object> deleteComment(@PathVariable("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		taskService.deleteComment(id);
		result.put("result", "true");
		return result;
	}

	@RequestMapping(value = "/comment/list")
	@ResponseBody
	public Map<String, Object> getCommentList(@RequestParam("processInstanceId") String processInstanceId,
			@RequestParam("taskId") String taskId, Model model, Authentication authentication) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> commentAndEventsMap = new LinkedHashMap<String, Object>();
		Map<String, String> username = new HashMap<String, String>();
		Map<String, String> enables = new HashMap<String, String>();
		String currentuser = userUtil.getUsernameByAuth(authentication);
		/*
		 * 根据不同情况使用不同方式查询
		 */
		if (StringUtils.isNotBlank(processInstanceId)) {
			List<Comment> processInstanceComments = taskService.getProcessInstanceComments(processInstanceId);
			for (Comment comment : processInstanceComments) {
				String commentId = comment.getId();
				username.put(comment.getUserId(), userService.getUserName(comment.getUserId()));
				if (comment.getUserId().equals(currentuser)) {
					// 可以删除
					enables.put(commentId, "true");
				} else
					enables.put(commentId, "false");

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
				String eventId = event.getId();
				commentAndEventsMap.put(eventId, event);
			}
		}
		result.put("usernames", username);
		result.put("enables", enables);
		result.put("events", commentAndEventsMap.values());
		return result;
	}

}
