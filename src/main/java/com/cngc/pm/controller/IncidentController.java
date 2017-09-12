package com.cngc.pm.controller;

import static com.cngc.utils.Common.getRemortIP;

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
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;
import org.apache.cxf.common.util.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.Incident;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.ChangeItemService;
import com.cngc.pm.service.ChangeService;
import com.cngc.pm.service.GroupService;
import com.cngc.pm.service.IncidentService;
import com.cngc.pm.service.ItilRelationService;
import com.cngc.pm.service.NoticeService;
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
	private IdentityService identityService;
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
	@Resource
	private GroupService groupService;
	@Resource
	private NoticeService noticeService;

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
		model.addAttribute("supporttype",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.support.type")).getResult());
		model.addAttribute("type", syscodeService
				.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.type")).getResult());
		model.addAttribute("influence",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.influence")).getResult());
		model.addAttribute("critical", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.critical"))
				.getResult());
		model.addAttribute("priority", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.priority"))
				.getResult());
		//model.addAttribute("users", userService.getCommonUser());
		return "incident/add";
	}
	
	@RequestMapping(value = "/end-process/{taskid}", method = RequestMethod.GET)
	public String endProcess(@PathVariable("taskid") String taskId, Model model) throws Exception {
		
		ActivityImpl endActivity = findActivitiImpl(taskId, "end");  
        commitProcess(taskId, null, endActivity.getId());  
        
        return "redirect:/incident/list";
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
		model.addAttribute("supporttype",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.support.type")).getResult());
		model.addAttribute("type", syscodeService
				.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.type")).getResult());
		model.addAttribute("influence",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.influence")).getResult());
		model.addAttribute("critical", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.critical"))
				.getResult());
		model.addAttribute("priority", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.priority"))
				.getResult());
		//model.addAttribute("users", userService.getCommonUser());

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
	 * @throws Exception 
	 */
	@RequestMapping(value = "/savebyuser", method = RequestMethod.POST)
	public String savebyuser(Model model, HttpServletRequest request, Authentication authentication) throws Exception {
		String abs = request.getParameter("fm_abs");
		String detail = request.getParameter("fm_description");
		

		Incident incident = new Incident();
		incident.setAbs(abs);
		incident.setDetail(detail);
		incident.setSource("04");
		incident.setInfluence("04");
		incident.setCritical("04");
		incident.setPriority("04");
		incident.setType("01");
		incident.setStatus("01");
		SysUser user = userUtil.getUserByAuth(authentication);
		incident.setApplyUser(user.getUsername());
		//incident.setApplyUserRoom(user.getMechName());
		incident.setPhoneNumber(user.getDepName());
		incident.setApplyTime(new Date());
		
		if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
			String attachIds = request.getParameter("fileids");
		
			Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
			
			incident.setAttachs(attachSet);
		}
		

		incidentService.save(incident, user.getUsername(),getRemortIP(request));

		// 启动流程
//		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident")).active()
//				.latestVersion().singleResult();
//		if (processDefinition != null) {
//			Map<String, String> variables = new HashMap<String, String>();
//			variables.put("id", String.valueOf(incident.getId()));
//			formService.submitStartFormData(processDefinition.getId(), variables);
//		}

		return "redirect:/incident/mylist";
	}

	/**
	 * 保存事件信息
	 * 
	 * @param incident
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("incident") Incident incident,HttpServletRequest request, Authentication authentication) throws Exception {
		if(incident.getId()==null)
		{
			if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
				String attachIds = request.getParameter("fileids");
				
				Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
				
				incident.setAttachs(attachSet);
			}
			
			incident.setStatus(PropertyFileUtil.getStringValue("syscode.incident.status.new"));
			incident.setApplyTime(new Date());
			incidentService.save(incident,userUtil.getUsernameByAuth(authentication),getRemortIP(request));

			// 启动流程
//			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//					.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident")).active()
//					.latestVersion().singleResult();
//			if (processDefinition != null) {
//				Map<String, String> variables = new HashMap<String, String>();
//				variables.put("id", String.valueOf(incident.getId()));
//				identityService.setAuthenticatedUserId(userUtil.getUserId(authentication));
//				formService.submitStartFormData(processDefinition.getId(), variables);
//			}
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
			newincident.setFinishTime(incident.getFinishTime());
			newincident.setSupportType(incident.getSupportType());
			newincident.setPriority(incident.getPriority());
			incidentService.update(newincident);
		}

		return "redirect:/incident/list";
	}

	/**
	 * 获取待处理的事件
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Authentication authentication) throws Exception {

		List<Task> tasks = null;
		List<Task> mytasks = null;
		List<Incident> incidents = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident"))
				.taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有为未关闭的事件信息
		incidents = incidentService.getNotFinished().getResult();
		if (userUtil.IsLeader(authentication) || userUtil.IsServiceDesk(authentication) || userUtil.IsWorkflowManager(authentication) || userUtil.IsManager(authentication)) // 服务台、领导、流程管理者可查看所有事件任务信息
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
	
	@RequestMapping(value = "/list/{status}", method = RequestMethod.GET)
	public String list(@PathVariable("status") String status, Model model,Authentication authentication) throws Exception {
		List<Task> tasks = null;
		List<Task> mytasks = null;
		List<Incident> incidents = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident"))
				.taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有指定状态的事件信息
		incidents = incidentService.getByStatus(status).getResult();
		if (userUtil.IsLeader(authentication) || userUtil.IsServiceDesk(authentication) || userUtil.IsWorkflowManager(authentication) || userUtil.IsManager(authentication)) // 服务台、领导、流程管理者可查看所有事件任务信息
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
		model.addAttribute("status", syscodeService.getCode(status, PropertyFileUtil.getStringValue("syscode.incident.status")));
		model.addAttribute("count", incidentService.getCountByStatus());
		model.addAttribute("group",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.status")).getResult());
		
		return "incident/list";
	}

	@RequestMapping(value = "/mydealedlist", method = RequestMethod.GET)
	public String myDealedList(Model model, Authentication authentication) throws Exception {

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
				.taskAssignee(userUtil.getUsernameByAuth(authentication)).list();
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
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mylist", method = RequestMethod.GET)
	public String mylist(Model model, Authentication authentication) throws Exception {
		List<Task> tasks = null;
		List<Task> mytasks = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();
		Map<String, Task> taskmap = null;

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.incident"))
				.taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active().list();
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
		model.addAttribute("list", incidentService.getByApplyUser(userUtil.getUsernameByAuth(authentication), false)
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
		model.addAttribute("notices", noticeService.getAll());

		return "incident/deal";
	}
	@RequestMapping(value = "/dealbyprocess/{pid}/{taskid}", method = RequestMethod.GET)
	public String dealByProcessInstanceId(@PathVariable("pid") String pid,@PathVariable("taskid") String taskid){
		
		return "redirect:/incident/deal/" + incidentService.getIdByProcessInstance(pid) + "/"+taskid;
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

		model.addAttribute("incident", incident);

		return "incident/view";
	}
	@RequestMapping(value = "/viewbyprocess/{pid}", method = RequestMethod.GET)
	public String viewByProcessInstanceId(@PathVariable("pid") String pid){
		
		return "redirect:/incident/view/" + incidentService.getIdByProcessInstance(pid);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model) {
		if (id != 0)
			incidentService.delById(id);

		return "redirect:/incident/list";
	}

	@RequestMapping(value = "/{id}/template/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveTemplate(@PathVariable("id") long id, HttpServletRequest request,Authentication authentication) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();

		ObjectMapper mapper = new ObjectMapper();

		Incident incident = incidentService.getById(id);
		try {
			incident.setTemplateData(mapper.writeValueAsString(request.getParameterMap()));
		} catch (JsonGenerationException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}
		incidentService.save(incident,userUtil.getUsernameByAuth(authentication),getRemortIP(request));

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
	 * 跳转到搜索页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchIndex(Model model) {
		
		model.addAttribute("units", groupService.getAllTop());
		model.addAttribute("engineers", userService.getEngineer());
		model.addAttribute("satisfaction", syscodeService.getAllByType("INCIDENT_SATISFACTION").getResult());				//满意度
		
		return "incident/search-index";
	}
	
	@RequestMapping(value="/table-page-ajax-list",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String tablePageAjax(@RequestParam String aoData) throws ParseException {
		JSONArray jsonarray = new JSONArray(aoData); 
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
		//搜索条件
		String abs = "";
		String applyUser = "";
		String engineer = "";
		String satisfaction = "";
		String unit = "";
		String startTime = "";
		String endTime = "";
		
		for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  
	            sEcho = obj.get("value").toString();  
	   
	        if (obj.get("name").equals("iDisplayStart"))  
	            iDisplayStart = obj.getInt("value");  
	   
	        if (obj.get("name").equals("iDisplayLength"))  
	            iDisplayLength = obj.getInt("value");  
	        
	        if (obj.get("name").equals("starttime"))  
	            startTime = obj.getString("value");  
	        
	        if (obj.get("name").equals("endtime"))  
	            endTime = obj.getString("value");  
	        
	        if (obj.get("name").equals("abs"))  
	            abs = obj.getString("value");  
	        
	        if (obj.get("name").equals("applyUser"))  
	        	applyUser = obj.getString("value");  
	        
	        if (obj.get("name").equals("engineer"))  
	        	engineer = obj.getString("value");  
	        
	        if (obj.get("name").equals("satisfaction"))  
	        	satisfaction = obj.getString("value");  
	        
	        if (obj.get("name").equals("unit"))  
	            unit = obj.getString("value");  
	    } 
		
		Date startDate = null; Date endDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		if(!("").equals(startTime))
			startDate = sdf.parse(startTime);
		if(!("").equals(endTime))
			endDate = sdf.parse(endTime);
		
		if (engineer.equals("00")) // 查看全部
			engineer = null;
		
		if (satisfaction.equals("00")) // 查看全部
			satisfaction = null;
		
		List<Incident> list = new LinkedList<>();
		SearchResult<Incident> result = new SearchResult<>();
		int count = 0;
		if(unit.equals("00")) {
			result = incidentService.search(abs, applyUser, engineer, satisfaction, startDate, endDate,iDisplayStart,iDisplayLength);
//			list = result.getResult();
//			count = result.getTotalCount();
		} else {
			result =incidentService.searchByUnit(abs, unit, engineer, satisfaction, startDate, endDate,iDisplayStart,iDisplayLength);
//			List<Incident> listAll = incidentService.search(abs, applyUser, engineer, satisfaction, startDate, endDate);
//			Group group = groupService.getById(Long.valueOf(unit));
//				
//			for(Incident in : listAll) {
//				Group group1 = userService.getTopGroupByUser(userService.getByUsername(in.getApplyUser()));
//				if(group1 == group) {
//					list.add(in);
//				}
//			}
//			count = list.size();
//			if(iDisplayLength>-1)
//				list = list.subList(iDisplayStart, iDisplayStart+iDisplayLength>list.size()?list.size():iDisplayStart+iDisplayLength);
			
		}
		
		list = result.getResult();
		count = result.getTotalCount();
		
		JSONObject getObj = new JSONObject();
	    
	    getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", count);//实际的行数
	    getObj.put("iTotalDisplayRecords",  count);//显示的行数,这个要和上面写的一样
	    getObj.put("aaData", list);
		
		return getObj.toString();
	}

	/**
	 * 查询
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search/index", method = RequestMethod.GET)
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
		String unit = request.getParameter("unit");
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
		List<Incident> list = new LinkedList<>();
		//List<Incident> list1 = new LinkedList<>();
		int count = 0;
		if(unit ==null) {
			SearchResult<Incident> result = incidentService.search(abs, applyUser, engineer, satisfaction, startDate, endDate,offset,maxResults);
			list = result.getResult();
			count = result.getTotalCount();
		} else {
			SearchResult<Incident> result = incidentService.searchByUnit(abs, unit, engineer, satisfaction, startDate, endDate, offset, maxResults);
			list = result.getResult();
			count = result.getTotalCount();
//			if(!unit.equals("00")) {
//				List<Incident> listAll = incidentService.search(abs, applyUser, engineer, satisfaction, startDate, endDate);
//				Group group = groupService.getById(Long.valueOf(unit));
//				
//				for(Incident in : listAll) {
//					Group group1 = userService.getTopGroupByUser(userService.getByUsername(in.getApplyUser()));
//					if(group1 == group) {
//						list.add(in);
//					}
//				}
//				count = list.size();
//			}
		}
		model.addAttribute("satisfaction", syscodeService.getAllByType("INCIDENT_SATISFACTION").getResult());				//满意度
		model.addAttribute("list", list);
		model.addAttribute("units", groupService.getAllTop());
		model.addAttribute("engineers", userService.getEngineer());
		model.addAttribute("users", userService.getCommonUser());
		model.addAttribute("offset", offset);
		model.addAttribute("count", count);
		model.addAttribute("url", request.getRequestURI());

		return "incident/search";
	}

	/**
	 * 历史报修信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/mysearch", method = RequestMethod.GET)
	public String mysearch(Model model, Integer offset,Integer maxResults, HttpServletRequest request, Authentication authentication) throws Exception {

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
		SearchResult<Incident> result = incidentService.search(null, userUtil.getUsernameByAuth(authentication), null, null, startDate, endDate,offset,maxResults);
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
	
	/** 
     * 根据任务ID获得任务实例 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
    private TaskEntity findTaskById(String taskId) throws Exception {  
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();  
        if (task == null) {  
            throw new Exception("任务实例未找到!");  
        }  
        return task;  
    } 
	
	 /** 
     * 根据任务ID获取流程定义 
     *  
     * @param taskId 
     *            任务ID 
     * @return 
     * @throws Exception 
     */  
    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(  
            String taskId) throws Exception {  
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService) .getDeployedProcessDefinition(findTaskById(taskId)  
                        .getProcessDefinitionId());  
  
        if (processDefinition == null) {  
            throw new Exception("流程定义未找到!");  
        }  
  
        return processDefinition;  
    } 
    
    /** 
     * 根据任务ID和节点ID获取活动节点 <br> 
     *  
     * @param taskId 
     *            任务ID 
     * @param activityId 
     *            活动节点ID <br> 
     *            如果为null或""，则默认查询当前活动节点 <br> 
     *            如果为"end"，则查询结束节点 <br> 
     *  
     * @return 
     * @throws Exception 
     */  
    private ActivityImpl findActivitiImpl(String taskId, String activityId)  
            throws Exception {  
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);  
  
        // 获取当前活动节点ID  
        if (StringUtils.isEmpty(activityId)) {  
            activityId = findTaskById(taskId).getTaskDefinitionKey();  
        }  
  
        // 根据流程定义，获取该流程实例的结束节点  
        if (activityId.toUpperCase().equals("END")) {  
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {  
                List<PvmTransition> pvmTransitionList = activityImpl  
                        .getOutgoingTransitions();  
                if (pvmTransitionList.isEmpty()) {  
                    return activityImpl;  
                }  
            }  
        }  
  
        // 根据节点ID，获取对应的活动节点  
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)  
                .findActivity(activityId);  
  
        return activityImpl;  
    } 
    
    /** 
     * @param taskId 
     *            当前任务ID 
     * @param variables 
     *            流程变量 
     * @param activityId 
     *            流程转向执行任务节点ID<br> 
     *            此参数为空，默认为提交操作 
     * @throws Exception 
     */  
    private void commitProcess(String taskId, Map<String, Object> variables,  
            String activityId) throws Exception {  
        if (variables == null) {  
            variables = new HashMap<String, Object>();  
        }  
        // 跳转节点为空，默认提交操作  
        if (StringUtils.isEmpty(activityId)) {  
            taskService.complete(taskId, variables);  
        } else {// 流程转向操作  
            turnTransition(taskId, activityId, variables);  
        }  
    }  
    
    /** 
     * 流程转向操作 
     *  
     * @param taskId 
     *            当前任务ID 
     * @param activityId 
     *            目标节点任务ID 
     * @param variables 
     *            流程变量 
     * @throws Exception 
     */  
    private void turnTransition(String taskId, String activityId,  
            Map<String, Object> variables) throws Exception {  
        // 当前节点  
        ActivityImpl currActivity = findActivitiImpl(taskId, null);  
        // 清空当前流向  
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);  
  
        // 创建新流向  
        TransitionImpl newTransition = currActivity.createOutgoingTransition();  
        // 目标节点  
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);  
        // 设置新流向的目标节点  
        newTransition.setDestination(pointActivity);  
  
        // 执行转向任务  
        taskService.complete(taskId, variables);  
        // 删除目标节点新流入  
        pointActivity.getIncomingTransitions().remove(newTransition);  
  
        // 还原以前流向  
        restoreTransition(currActivity, oriPvmTransitionList);  
    }  
    
    /** 
     * 清空指定活动节点流向 
     *  
     * @param activityImpl 
     *            活动节点 
     * @return 节点流向集合 
     */  
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {  
        // 存储当前节点所有流向临时变量  
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();  
        // 获取当前节点所有流向，存储到临时变量，然后清空  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        for (PvmTransition pvmTransition : pvmTransitionList) {  
            oriPvmTransitionList.add(pvmTransition);  
        }  
        pvmTransitionList.clear();  
  
        return oriPvmTransitionList;  
    }  
    
    /** 
     * 还原指定活动节点流向 
     *  
     * @param activityImpl 
     *            活动节点 
     * @param oriPvmTransitionList 
     *            原有节点流向集合 
     */  
    private void restoreTransition(ActivityImpl activityImpl,  
            List<PvmTransition> oriPvmTransitionList) {  
        // 清空现有流向  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        pvmTransitionList.clear();  
        // 还原以前流向  
        for (PvmTransition pvmTransition : oriPvmTransitionList) {  
            pvmTransitionList.add(pvmTransition);  
        }  
    }  
}
