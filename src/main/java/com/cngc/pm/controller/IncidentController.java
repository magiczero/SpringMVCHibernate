package com.cngc.pm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.Incident;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.ChangeItemService;
import com.cngc.pm.service.ChangeService;
import com.cngc.pm.service.IncidentService;
import com.cngc.pm.service.ItilRelationService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.UserService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.SearchResult;

@Controller
@RequestMapping(value = "/incident")
public class IncidentController {

	@Resource
	private IncidentService incidentService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private TaskService taskService;
	@Resource
	private FormService formService;
	@Resource
	private HistoryService historyService;
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private ItilRelationService itilRelationService;
	@Resource
	private CiService ciService;
	@Resource
	private ChangeItemService changeitemService;
	@Resource
	private ChangeService changeService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private UserService userService;
	@Resource
	private AttachService attachService;

	/**
	 * 创建新事件
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("incident", new Incident());
		model.addAttribute("source",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.source")).getResult());
		model.addAttribute("category",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.category")).getResult());
		model.addAttribute("type", syscodeService
				.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.type")).getResult());
		model.addAttribute("influence",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.influence")).getResult());
		model.addAttribute("critical", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.critical"))
				.getResult());
		model.addAttribute("priority", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.priority"))
				.getResult());
		model.addAttribute("users", userService.getCommonUser());
		return "incident/add";
	}

	/**
	 * 修改事件信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("incident", incidentService.getById(id));
		model.addAttribute("source",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.source")).getResult());
		model.addAttribute("category",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.category")).getResult());
		model.addAttribute("type", syscodeService
				.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.type")).getResult());
		model.addAttribute("influence",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.influence")).getResult());
		model.addAttribute("critical", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.critical"))
				.getResult());
		model.addAttribute("priority", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.priority"))
				.getResult());
		model.addAttribute("users", userService.getCommonUser());

		return "incident/add";
	}

	/**
	 * 自助报修
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addbyuser", method = RequestMethod.GET)
	public String addbyuser(Model model) {

		return "incident/addbyuser";
	}

	/**
	 * 保存自助报修信息
	 * 
	 * @param model
	 * @param request
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "/savebyuser", method = RequestMethod.POST)
	public String savebyuser(Model model, HttpServletRequest request, Authentication authentication) {
		String abs = request.getParameter("fm_abs");
		String detail = request.getParameter("fm_description");
		String attachIds = request.getParameter("fileids");
		
		Set<Attachment> attachSet = attachService.getSetByIds(attachIds);

		Incident incident = new Incident();
		incident.setAbs(abs);
		incident.setDetail(detail);
		incident.setSource("04");
		incident.setInfluence("04");
		incident.setCritical("04");
		incident.setPriority("04");
		incident.setType("01");
		incident.setStatus("01");
		incident.setApplyUser(userUtil.getUserId(authentication));
		incident.setApplyTime(new Date());
		incident.setAttachs(attachSet);

		incidentService.save(incident);

		// 启动流程
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident")).active()
				.latestVersion().singleResult();
		if (processDefinition != null) {
			Map<String, String> variables = new HashMap<String, String>();
			variables.put("id", String.valueOf(incident.getId()));
			formService.submitStartFormData(processDefinition.getId(), variables);
		}

		return "redirect:/incident/mylist";
	}

	/**
	 * 保存事件信息
	 * 
	 * @param incident
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("incident") Incident incident) {
		if(incident.getId()==null)
		{
			incident.setStatus(PropertyFileUtil.getStringValue("syscode.incident.status.new"));
			incident.setApplyTime(new Date());
			incidentService.save(incident);

			// 启动流程
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident")).active()
					.latestVersion().singleResult();
			if (processDefinition != null) {
				Map<String, String> variables = new HashMap<String, String>();
				variables.put("id", String.valueOf(incident.getId()));
				formService.submitStartFormData(processDefinition.getId(), variables);
			}
		}
		else
		{
			// 更新
			Incident newincident = incidentService.getById(incident.getId());
			newincident.setAbs(incident.getAbs());
			newincident.setDetail(incident.getDetail());
			newincident.setApplyUser(incident.getApplyUser());
			newincident.setPhoneNumber(incident.getPhoneNumber());
			newincident.setCategory(incident.getCategory());
			newincident.setType(incident.getType());
			newincident.setSource(incident.getSource());
			newincident.setInfluence(incident.getInfluence());
			newincident.setCritical(incident.getCritical());
			newincident.setPriority(incident.getPriority());
			incidentService.save(newincident);
		}

		return "redirect:/incident/list";
	}

	/**
	 * 获取待处理的事件
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Authentication authentication) {

		List<Task> tasks = null;
		List<Task> mytasks = null;
		List<Incident> incidents = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有为未关闭的事件信息
		incidents = incidentService.getNotFinished().getResult();
		if (userUtil.IsWorkflowManager(authentication)) // 流程管理者可查看所有事件任务信息
		{
			// 所有任务
			tasks = taskService.createTaskQuery()
					.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident")).active()
					.list();
			taskmap = new HashMap<String, Task>();
			for (Task task : tasks)
				taskmap.put(task.getProcessInstanceId(), task);
		}
		if (userUtil.IsServiceDesk(authentication)) {
			// 服务台用户拥有修改权限
			model.addAttribute("ROLE_MODIFY", true);
		}
		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", incidents);
		model.addAttribute("count", incidentService.getCountByStatus());
		model.addAttribute("group",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.status")).getResult());
		return "incident/list";
	}

	@RequestMapping(value = "/mydealedlist", method = RequestMethod.GET)
	public String myDealedList(Model model, Authentication authentication) {

		List<Task> tasks = null;
		Map<String, Task> taskmap = null;

		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident")).active().list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		// 我参与的任务
		List<String> processInstanceIds = new ArrayList<String>();
		List<HistoricTaskInstance> mytasks = historyService.createHistoricTaskInstanceQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident"))
				.taskAssignee(userUtil.getUserId(authentication)).list();
		for (HistoricTaskInstance task : mytasks)
			processInstanceIds.add(task.getProcessInstanceId());

		model.addAttribute("list", incidentService.getByProcessInstance(processInstanceIds).getResult());
		model.addAttribute("task", taskmap);

		return "incident/mydealedlist";
	}

	/**
	 * 获取报修信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mylist", method = RequestMethod.GET)
	public String mylist(Model model, Authentication authentication) {
		List<Task> tasks = null;
		List<Task> mytasks = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();
		Map<String, Task> taskmap = null;

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident")).active().list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", incidentService.getByApplyUser(userUtil.getUserId(authentication), false)
				.getResult());

		return "incident/mylist";
	}

	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String stats(Model model, HttpServletRequest request) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}
		model.addAttribute("count", incidentService.getCountByCategory(startTime, endTime));
		model.addAttribute("group",
				syscodeService.getParentCodeByType(PropertyFileUtil.getStringValue("syscode.incident.category"))
						.getResult());
		return "incident/stats";
	}

	@RequestMapping(value = "/list/{status}", method = RequestMethod.GET)
	public String list(@PathVariable("status") String status, Model model) {
		model.addAttribute("list", incidentService.getByStatus(status).getResult());
		model.addAttribute("runtime", runtimeService);
		model.addAttribute("res", repositoryService);
		model.addAttribute("count", incidentService.getCountByStatus());
		model.addAttribute("status",
				syscodeService.getCode(status, PropertyFileUtil.getStringValue("syscode.incident.status")));
		model.addAttribute("group",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.status")).getResult());
		return "incident/list";
	}

	/**
	 * 处理事件
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deal/{id}/{taskid}", method = RequestMethod.GET)
	public String deal(@PathVariable("id") long id, @PathVariable("taskid") String taskId, Model model) {
		Incident incident = null;
		Task task = null;

		if (id != 0)
			incident = incidentService.getById(id);
		if (taskId != null)
			// 获取当前事件的task信息
			task = taskService.createTaskQuery().taskId(taskId).singleResult();

		// 根据申请用户获取其相关资产
		List<Ci> cilist = ciService.getByUser(incident.getApplyUser()).getResult();
		List<Long> ciids = new LinkedList<Long>();
		for (Ci ci : cilist)
			ciids.add(ci.getId());
		// 根据用户资产获取相关变更
		List<ChangeItem> items = changeitemService.getByCi(ciids).getResult();
		List<Long> changeids = new LinkedList<Long>();
		for (ChangeItem item : items)
			changeids.add(item.getChangeId());

		model.addAttribute("incident", incident);
		model.addAttribute("task", task);
		model.addAttribute("relIncidents", incidentService.getByApplyUser(incident.getApplyUser()).getResult());
		model.addAttribute("relCis", cilist);
		model.addAttribute("relchanges", changeService.getByIds(changeids).getResult());

		return "incident/deal";
	}

	/**
	 * 查看事件信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") long id, Model model) {
		Incident incident = null;

		if (id != 0)
			incident = incidentService.getById(id);

		// 根据申请用户获取其相关资产
		List<Ci> cilist = ciService.getByUser(incident.getApplyUser()).getResult();
		List<Long> ciids = new LinkedList<Long>();
		for (Ci ci : cilist)
			ciids.add(ci.getId());
		// 根据用户资产获取相关变更
		List<ChangeItem> items = changeitemService.getByCi(ciids).getResult();
		List<Long> changeids = new LinkedList<Long>();
		for (ChangeItem item : items)
			changeids.add(item.getChangeId());

		model.addAttribute("incident", incident);
		model.addAttribute("relIncidents", incidentService.getByApplyUser(incident.getApplyUser()).getResult());
		model.addAttribute("relCis", cilist);
		model.addAttribute("relchanges", changeService.getByIds(changeids).getResult());

		return "incident/view";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model) {
		if (id != 0)
			incidentService.delById(id);

		return "redirect:/incident/list";
	}

	@RequestMapping(value = "/{id}/template/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveTemplate(@PathVariable("id") long id, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();

		ObjectMapper mapper = new ObjectMapper();

		Incident incident = incidentService.getById(id);
		try {
			incident.setTemplateData(mapper.writeValueAsString(request.getParameterMap()));
		} catch (JsonGenerationException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}
		incidentService.save(incident);

		result.put("result", "true");

		return result;
	}

	@RequestMapping(value = "/{id}/template/get")
	@ResponseBody
	public Map<String, Object> getTemplate(@PathVariable("id") long id) {
		Map<String, Object> result = new HashMap<String, Object>();

		Incident incident = incidentService.getById(id);

		ObjectMapper mapper = new ObjectMapper();
		try {
			result.put("result", mapper.readValue(incident.getTemplateData(), Map.class));
		} catch (JsonParseException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}

		return result;
	}

	@RequestMapping(value = "/mystats", method = RequestMethod.GET)
	public String mystats(Model model, HttpServletRequest request) {
		/*
		 * String startTime = request.getParameter("startTime"); String endTime
		 * = request.getParameter("endTime"); SimpleDateFormat formatter = new
		 * SimpleDateFormat ("yyyy-MM-dd"); Calendar now =
		 * Calendar.getInstance(); if(startTime==null || startTime.isEmpty())
		 * startTime = String.valueOf( now.get(Calendar.YEAR) ) + "-01-01";
		 * if(endTime==null || endTime.isEmpty()) { endTime = formatter.format(
		 * now.getTime()); } model.addAttribute("count",
		 * incidentService.getCountByCategory(startTime,endTime));
		 * model.addAttribute("group",
		 * syscodeService.getParentCodeByType(PropertyFileUtil
		 * .getStringValue("syscode.incident.category")).getResult());
		 */
		// String columnCategory = request.getParameter("columnCategory");
		// String rowCategory = request.getParameter("rowCategory");
		// if(rowCategory!=null)
		// model.addAttribute("stat", incidentService.getStats(columnCategory,
		// rowCategory));

		return "incident/mystats";
	}

	/**
	 * 查询
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model,Integer offset,Integer maxResults, HttpServletRequest request) {

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}
		Date startDate = null, endDate = null;

		try {
			startDate = formatter.parse(startTime);

			endDate = formatter.parse(endTime);
			now.setTime(endDate);
			now.add(Calendar.DATE, 1);
			endDate = now.getTime();

		} catch (ParseException e) {
			e.printStackTrace();
		}
		String abs = request.getParameter("abstract");
		String applyUser = request.getParameter("applyUser");
		String engineer = request.getParameter("engineer");
		String satisfaction = request.getParameter("satisfaction");
		if (satisfaction != null) {
			if (satisfaction.equals("00")) // 查看全部
				satisfaction = null;
		}
		if (applyUser != null) {
			if (applyUser.equals("00")) // 查看全部
				applyUser = null;
		}
		if (engineer != null) {
			if (engineer.equals("00")) // 查看全部
				engineer = null;
		}
		SearchResult<Incident> result = incidentService.search(abs, applyUser, engineer, satisfaction, startDate, endDate,offset,maxResults);
		model.addAttribute("satisfaction", syscodeService.getAllByType("INCIDENT_SATISFACTION").getResult());
		model.addAttribute("list", result.getResult());
		model.addAttribute("engineers", userService.getEngineer());
		model.addAttribute("users", userService.getCommonUser());
		model.addAttribute("offset", offset);
		model.addAttribute("count", result.getTotalCount());
		model.addAttribute("url", request.getRequestURI());

		return "incident/search";
	}

	/**
	 * 历史报修信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mysearch", method = RequestMethod.GET)
	public String mysearch(Model model, Integer offset,Integer maxResults, HttpServletRequest request, Authentication authentication) {

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}
		Date startDate = null, endDate = null;

		try {
			startDate = formatter.parse(startTime);

			endDate = formatter.parse(endTime);
			now.setTime(endDate);
			now.add(Calendar.DATE, 1);
			endDate = now.getTime();

		} catch (ParseException e) {
			e.printStackTrace();
		}
		SearchResult<Incident> result = incidentService.search(null, userUtil.getUserId(authentication), null, null, startDate, endDate,offset,maxResults);
		model.addAttribute("list",result.getResult());
		model.addAttribute("offset", offset);
		model.addAttribute("count", result.getTotalCount());
		model.addAttribute("url", request.getRequestURI());

		return "incident/mysearch";
	}

	@RequestMapping(value = "/getjson/{ids}")
	@ResponseBody
	public Map<String, Object> getCi(@PathVariable("ids") String ids, HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> list = new ArrayList<Long>();

		String sids[] = ids.split(",");
		for (String s : sids)
			list.add(Long.valueOf(s));

		List<Incident> incidentlist = incidentService.getByIds(list).getResult();
		map.put("incidents", incidentlist);

		return map;
	}
}
