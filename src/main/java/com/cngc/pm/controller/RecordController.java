package com.cngc.pm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Income;
import com.cngc.pm.model.Inspection;
import com.cngc.pm.model.Training;
import com.cngc.pm.service.IncidentService;
import com.cngc.pm.service.IncomeService;
import com.cngc.pm.service.InspectionService;
import com.cngc.pm.service.SecJobService;
import com.cngc.pm.service.TrainingService;
import com.cngc.pm.service.UpdateService;
import com.cngc.pm.service.UserService;
import com.cngc.utils.PropertyFileUtil;

@Controller
@RequestMapping(value = "/record")
public class RecordController {
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private FormService formService;
	@Resource
	private UpdateService updateService;
	@Resource
	private InspectionService inspectionService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private IncomeService incomeService;
	@Resource
	private TrainingService trainingService;
	@Resource
	private IncidentService incidentService;
	@Resource
	private SecJobService secjobService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private UserService userService;

	/**
	 * 获取升级记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, Authentication authentication) throws Exception {
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
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.update"))
				.taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);
		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.update")).active().list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", updateService.search(startTime, endTime).getResult());

		return "record/update-list";
	}
	
	/**
	 * 获取日常巡检信息（分页版）
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/inspection", method = RequestMethod.GET)
	public String inspection1(Model model) {
		return "record/inspection-list-1";
	}
	
	@RequestMapping(value="/inspection-ajax-list",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String tableDemoAjax(@RequestParam String aoData, HttpSession httpSession, Authentication authentication) throws ParseException {
		//System.out.println(aoData);
		JSONArray jsonarray = new JSONArray(aoData); 
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
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
	    } 
	    
//	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
//	    //判断权限
//    	SysUser sysuser = userService.getByUsername(username);
    	boolean isLeader = false;
    	 if (userUtil.IsLeader(authentication) || userUtil.IsAdmin(authentication) || userUtil.IsWorkflowManager(authentication) || userUtil.IsManager(authentication)) {// 服务台、领导、流程管理者可查看所有事件任务信息
    		 isLeader = true;
    	 }
//    	for(UserRole ur : sysuser.getUserRoles()) {
//    		String rolename = ur.getRole().getRoleName();
//    		if(rolename.equals("WK_MANAGER") || rolename.equals("WK_LEADER") || rolename.equals("ROLE_ADMIN") ) {
//    			isLeader = true;
//    			break;
//    		}
//    	}
	    HistoricProcessInstanceQuery hpq = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("INSPECTION");
	    if (!isLeader) {// 服务台、领导、流程管理者可查看所有事件任务信息
//	    	hpq = hpq.orderByProcessInstanceStartTime().desc();
//	    } else {
	    	hpq = hpq.involvedUser(SecurityContextHolder.getContext().getAuthentication().getName());
	    }
	    if(!("").equals(startTime)) {
	    	hpq.startedAfter(new SimpleDateFormat("yyyy-MM-dd").parse(startTime));
	    }
	    if(!("").equals(endTime)) {
	    	hpq.startedBefore(new SimpleDateFormat("yyyy-MM-dd").parse(endTime));
	    }
	    hpq = hpq.orderByProcessInstanceStartTime().desc();
	    List<HistoricProcessInstance> hpis = hpq.listPage(iDisplayStart, iDisplayLength);
	    
	    JSONObject getObj = new JSONObject();
	    
	    getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", hpq.count());//实际的行数
	    getObj.put("iTotalDisplayRecords",  hpq.count());//显示的行数,这个要和上面写的一样
	    
	    
	    List<Map<String, String>> list = new ArrayList<>();
	    for(HistoricProcessInstance hpi : hpis) {
	    	String processInstanceId = hpi.getId();
	    	Map<String, String> map = new LinkedHashMap<String, String>();
	    	map.put("processid", processInstanceId);
	    	Inspection ins = inspectionService.getByProcessInstaceId(processInstanceId);
	    	Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
	    	map.put("assign", task==null?ins.getExecutionUserName():userService.getByUsername(task.getAssignee()).getName());
	    	map.put("content", ins.getTemplateName());
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    	map.put("starttime", sdf.format(hpi.getStartTime()));
	    	map.put("endtime",hpi.getEndTime()==null?"":sdf.format(hpi.getEndTime()));
	    	String status = "";
	    	if(task == null) {
	    		if(hpi.getDeleteReason()==null)
	    			status = "已完成";
	    		else
	    			status = "由系统关闭";
	    	} else {
	    		status = "<a  href='#' onclick=\"trace('"+processInstanceId+"','"+task.getProcessDefinitionId()+"')\"  title=\"点击查看流程图\">"+task.getName()+"</a>";
	    	}
	    	
	    	map.put("states", status);
	    	
	    	String result = "";
	    	if(("01").equals(ins.getStatus())) {
	    		result = "<span class=\"label label-success\">"+ins.getStatusName()+"</span>";
	    	} else if(("02").equals(ins.getStatus()))
	    		result = "<span class=\"label label-danger\">"+ins.getStatusName()+"</span>";
	    	map.put("result", result);
	    	
	    	String op = "";
	    	String path = userUtil.getContextPath(httpSession);
	    	map.put("num", ins.getIncidentId()==null?"":"<a target=\"_blank\" href=\""+path+"/incident/view/"+ins.getIncidentId()+"\">"+ins.getIncidentId()+"</a>");
	    	if(task ==null) {
	    		op = "<a href=\"#\" onclick=\"javascript:viewInspection('"+ins.getTemplate()+"',"+ins.getId()+");\"><span class=\"glyphicon glyphicon-search\"></span> 查看</a>";
	    	} else {
	    		op = "<a href=\""+path+"/record/inspection/deal/"+ins.getId()+"/"+task.getId()+"\"><span class=\"glyphicon glyphicon-edit\"></span> 办理</a>";
	    	}
	    	map.put("op", op);
	    	list.add(map);
	    }
	       
	    getObj.put("aaData", list);//要以JSON格式返回
	    
		return getObj.toString();
	}

	/**
	 * 获取日常巡检信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/inspection1", method = RequestMethod.GET)
	public String inspection(Model model, HttpServletRequest request, Authentication authentication) throws Exception {
		
//		JSONArray jsonarray = JSONArray.fromObject(aoData);
//		HistoricProcessInstanceQuery hpq = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("INSPECTION");
//		System.out.println(hpq.count());
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
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.inspection"))
				.taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);
		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.inspection")).active()
				.list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", inspectionService.search(startTime, endTime).getResult());

		return "record/inspection-list";
	}

	/**
	 * 处理日常巡检
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inspection/deal/{id}/{taskid}", method = RequestMethod.GET)
	public String dealInspection(@PathVariable("id") long id, @PathVariable("taskid") String taskId, Model model) {
		Inspection inspection = null;
		Task task = null;
		if (id != 0)
			inspection = inspectionService.getById(id);
		if (taskId != null)
			// 获取当前事件的task信息
			task = taskService.createTaskQuery().taskId(taskId).singleResult();

		model.addAttribute("inspection", inspection);
		model.addAttribute("task", task);

		return "record/inspection-deal";
	}
	@RequestMapping(value = "/inspection/dealbyprocess/{pid}/{taskid}", method = RequestMethod.GET)
	public String dealByProcessInstanceId(@PathVariable("pid") String pid,@PathVariable("taskid") String taskid){
		
		return "redirect:/record/inspection/deal/" + inspectionService.getIdByProcessInstance(pid) + "/"+taskid;
	}

	@RequestMapping(value = "/income", method = RequestMethod.GET)
	public String income(Model model) {
		model.addAttribute("list", incomeService.getAll());
		model.addAttribute("income", new Income());
		model.addAttribute("users", userService.getEngineer());
		return "record/income-list";
	}

	@RequestMapping(value = "/income/delete/{id}", method = RequestMethod.GET)
	public String incomeDelete(@PathVariable("id") long id, Model model) {
		if (id != 0) {
			incomeService.delById(id);
		}

		return "redirect:/record/income";
	}

	@RequestMapping(value = "/income/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("income") Income income, BindingResult result, 
			Authentication authentication) throws Exception {

		income.setCreatedTime(new Date());
		income.setCreatedUser(userUtil.getUsernameByAuth(authentication));
		incomeService.save(income);

		return "redirect:/record/income";
	}

	@RequestMapping(value = "/inspection/{id}/template/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveTemplate(@PathVariable("id") long id, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();

		ObjectMapper mapper = new ObjectMapper();

		Inspection inspection = inspectionService.getById(id);
		try {
			inspection.setTemplateData(mapper.writeValueAsString(request.getParameterMap()));
		} catch (JsonGenerationException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}
		inspectionService.save(inspection);

		result.put("result", "true");

		return result;
	}

	@RequestMapping(value = "/inspection/{id}/template/get")
	@ResponseBody
	public Map<String, Object> getTemplate(@PathVariable("id") long id) {
		Map<String, Object> result = new HashMap<String, Object>();

		Inspection inspection = inspectionService.getById(id);

		ObjectMapper mapper = new ObjectMapper();
		try {
			if(inspection.getTemplateData()!=null && inspection.getTemplateData()!="")
				result.put("result", mapper.readValue(inspection.getTemplateData(), Map.class));
			else
				result.put("result", "");
		} catch (JsonParseException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}

		return result;
	}

	@RequestMapping(value = "/training", method = RequestMethod.GET)
	public String training(Model model) {
		model.addAttribute("list", trainingService.getAll());
		model.addAttribute("training", new Training());
		model.addAttribute("users", userService.getEngineer());
		return "record/training-list";
	}

	@RequestMapping(value = "/training/delete/{id}", method = RequestMethod.GET)
	public String trainingDelete(@PathVariable("id") long id, Model model) {
		if (id != 0) {
			trainingService.delById(id);
		}

		return "redirect:/record/training";
	}

	@RequestMapping(value = "/training/save", method = RequestMethod.POST)
	public String trainingSave(@Valid @ModelAttribute("training") Training training, 
			Authentication authentication) throws Exception {

		training.setCreatedTime(new Date());
		training.setCreatedUser(userUtil.getUsernameByAuth(authentication));
		trainingService.save(training);

		return "redirect:/record/training";
	}

	/**
	 * 三员工作管理
	 * 
	 * @param model
	 * @param request
	 * @param authentication
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/secjob", method = RequestMethod.GET)
	public String secjob(Model model, HttpServletRequest request, Authentication authentication) throws Exception {
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
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.secjob"))
				.taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);
		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.secjob")).active().list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", secjobService.search(startTime, endTime).getResult());

		return "record/secjob-list";
	}
}
