package com.cngc.pm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.cngc.pm.service.UserService;

@Controller
@RequestMapping(value = "/overtime")
public class OvertimeController {
	
	@Resource
	private TaskService taskService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private HistoryService historyService;
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource
	private UserUtil userUtil;
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String approve(HttpServletRequest request,Model model) throws ParseException {
		String pid = request.getParameter("pid");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String reason = request.getParameter("reason");
		
		Task task = taskService.createTaskQuery()  
	            .processInstanceId(pid)//使用流程实例ID  
//	            .taskAssignee(username)//任务办理人  
	            .singleResult(); 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		
		taskService.setVariable(task.getId(), "startWhile", sdf.parse(start));
		taskService.setVariable(task.getId(), "endWhile", sdf.parse(end));
		taskService.setVariable(task.getId(), "reason", reason);
		
		return "redirect:/overtime";
	}
	
	@RequestMapping(value="/task/{processInstanceId}",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> getTaskDetailAjax(@PathVariable("processInstanceId") String processInstanceId) {
		//String username= SecurityContextHolder.getContext().getAuthentication().getName();
		 //查询当前办理人的任务ID  
	    Task task = taskService.createTaskQuery()  
	            .processInstanceId(processInstanceId)//使用流程实例ID  
//	            .taskAssignee(username)//任务办理人  
	            .singleResult(); 
	    
	    Date start = (Date) taskService.getVariable(task.getId(), "startWhile");
	    Date end = (Date) taskService.getVariable(task.getId(), "endWhile");
	    String reason = (String)taskService.getVariable(task.getId(), "reason");
	    
	    Map<String, String> result = new HashMap<String, String>();
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	    result.put("start", sdf.format(start));
	    result.put("end", sdf.format(end));
	    result.put("reason", reason);
	    result.put("pid", processInstanceId);
	    
	    return result;
	}
	
	@RequestMapping(value="/ajax-list",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String tableAjax(@RequestParam String aoData,HttpSession httpSession) throws ParseException {
		JSONArray jsonarray = new JSONArray(aoData); 
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
	    String startTime = "";
	    String endTime = "";
	    String findUsername = "";
	    String isSearch = "0";
	   
	    for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  {
	            sEcho = obj.get("value").toString();  
	        } else if (obj.get("name").equals("iDisplayStart")) {  
	            iDisplayStart = obj.getInt("value");  
	        } else if (obj.get("name").equals("iDisplayLength")) {  
	            iDisplayLength = obj.getInt("value");  
	        } else if (obj.get("name").equals("starttime")) {  
	            startTime = obj.getString("value");  
	        } else if (obj.get("name").equals("endtime")) {  
	            endTime = obj.getString("value");  
	        } else if (obj.get("name").equals("username")) {  
	        	findUsername = obj.getString("value");  
	        } else if (obj.get("name").equals("searchBtn")) {  
	        	isSearch = obj.getString("value");  
	        }
	    } 
	    
	    String username= SecurityContextHolder.getContext().getAuthentication().getName();
	    //判断权限
    	SysUser sysuser = userService.getByUsername(username);
    	boolean isLeader = false;
    	for(UserRole ur : sysuser.getUserRoles()) {
    		String rolename = ur.getRole().getRoleName();
    		if(rolename.equals("WK_LEADER") ) {
    			isLeader = true;
    			break;
    		}
    	}
    	
    	HistoricProcessInstanceQuery hpq = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("OVERTIME");
    	if(isSearch.equals("1") ) hpq = hpq.finished();
    	//需要查找的人员
    	if(findUsername.equals("00")) {
		    if(!isLeader) {
		    	hpq = hpq.involvedUser(username);
		    }
	    } else {
	    	hpq = hpq.involvedUser(findUsername);
	    }
    	
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
    	
    	if(!("").equals(startTime)) {
  	    	hpq.startedAfter(sdf.parse(startTime));
  	    }
  	    if(!("").equals(endTime)) {
  	    	hpq.startedBefore(sdf.parse(endTime));
  	    }
  	    hpq = hpq.orderByProcessInstanceStartTime().desc();
  	    List<HistoricProcessInstance> hpis = hpq.listPage(iDisplayStart, iDisplayLength);
  	    
  	    JSONObject getObj = new JSONObject();
	    
	    getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", hpq.count());//实际的行数
	    getObj.put("iTotalDisplayRecords",  hpq.count());//显示的行数,这个要和上面写的一样
	    
	    List<Map<String, String>> list = new ArrayList<>();
	    
	    long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数  
		long nh = 1000 * 60 * 60;// 一小时的毫秒数  
		long nc = 1000*60;		//一分钟的毫秒数
		long countTime = 0l;
	    
	    for(HistoricProcessInstance hpi : hpis) {
	    	String processInstanceId = hpi.getId();
	    	Map<String, String> map = new LinkedHashMap<String, String>();
	    	map.put("pid", processInstanceId);
	    	//发起人
	    	map.put("applicant", userService.getByUsername(hpi.getStartUserId()).getName());
	    	List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
	    	
	    	Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
	    	
	    	Date start = new Date(); 
    		Date end = new Date(); String reason="";
	    	//获取form值
	    	if(task ==null) {
	    		map.put("status_", "审批通过");
	    		map.put("op", "");
	    		
	    		for(HistoricDetail historicDetail : formProperties) {
	    			 HistoricFormProperty	 field = (HistoricFormProperty) historicDetail;
	    			 if(field.getPropertyId().equals("startWhile")) {
	    				 start = sdf.parse(field.getPropertyValue());
	    			 } else if(field.getPropertyId().equals("endWhile")) {
	    				 end = sdf.parse(field.getPropertyValue());
	    			 } else if(field.getPropertyId().equals("reason")) {
	    				 reason = field.getPropertyValue();
	    			 } 
	    		}
	    	} else {
	    		map.put("status_", "未审批");
	    		if(isLeader)
	    			map.put("op", "<form class=\"dynamic-form\" method=\"post\" action=\"/PmSys/workflow/task/complete/"+task.getId()+"\"><div class=\"title\"><input name=\"redirectAddress\" value=\"/overtime\" type=\"hidden\"></div><div class=\"text\"><button class=\"btn btn-primary\" aria-hidden=\"true\">审批</button></div></form>");
	    		else
	    			map.put("op", "<a href=\"#\" onclick=\"initUpdateModal("+processInstanceId+");\" > 修改</a>");
	    		
	    		Task task1 = taskService.createTaskQuery()  
    		            .processInstanceId(processInstanceId)//使用流程实例ID  
    		            .singleResult(); 
    		
	    		start = (Date) taskService.getVariable(task1.getId(), "startWhile");
	    	    end = (Date) taskService.getVariable(task1.getId(), "endWhile");
	    	    reason = (String)taskService.getVariable(task1.getId(), "reason");
	    	}
	    		
	    		long diff = end.getTime() - start.getTime();
	    		countTime+=diff;
				long day = diff / nd;// 计算差多少天    
		        long hour = diff % nd / nh + day * 24;// 计算差多少小时  
		        long second = diff % nd % nh /nc;
				
				map.put("overtimeStart", sdf.format(start));
				map.put("overtimeEnd", sdf.format(end));
				map.put("reason", reason);
				map.put("hour", String.valueOf(hour)+"小时"+String.valueOf(second)+"分钟");
				
				list.add(map);
				
				if(isSearch.equals("1") && task!=null) {
	    			list.remove(map);
	    			countTime=countTime - diff;
				}
	    }
	    if(isSearch.equals("1") && !findUsername.equals("00")){
			long day = countTime / nd;// 计算差多少天    
	        long hour = countTime % nd / nh + day * 24;// 计算差多少小时  
	        long second = countTime % nd % nh /nc;
	    	getObj.put("countTime", String.valueOf(hour)+"小时"+String.valueOf(second)+"分钟");
	    }
	    getObj.put("aaData", list);//要以JSON格式返回
	    
		return getObj.toString();
	}
	/**
	 * 进入加班申请页
	 * @param model
	 * @param request
	 * @param authentication
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping
	public String list(Model model, HttpServletRequest request, Authentication authentication) throws ParseException {
		// 创建任务查询对象
//		TaskQuery taskQuery = taskService.createTaskQuery();
		  // 配置查询对象
//		     String assignee="user";
		String candidateUser=userUtil.getUserId(authentication);
		  
//		taskQuery
		   // 过滤条件
//		   .taskCandidateUser(candidateUser)
		   // 分页条件
//		     .listPage(firstResult, maxResults)
		   // 排序条件
//		   .orderByTaskCreateTime().desc();
		  // 执行查询
//		List<Task> tasks = taskQuery
//				.processDefinitionKey("OVERTIME")
//				.taskCandidateOrAssigned(candidateUser).active().list();
//		HistoricProcessInstanceQuery hpq = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("OVERTIME");
		
		//权限判断
		SysUser currentUser = userService.getByUsername(candidateUser);
		boolean isRole = false;
		for(UserRole ur : currentUser.getUserRoles()) {
			if(ur.getRole().getRoleName().equals("WK_LEADER")) {
				isRole = true;
			}
		}
	/**	
//		if(!isRole) {
//			hpq.involvedUser(candidateUser);
//		}
		
//		List<HistoricProcessInstance> hpis = hpq.orderByProcessInstanceStartTime().desc().list();
//		
//		List<Map<String, String>> list = new ArrayList<>();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
//		
//		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数  
//		long nh = 1000 * 60 * 60;// 一小时的毫秒数  
//		long nc = 1000*60;		//一分钟的毫秒数
//		
//		for(HistoricProcessInstance hpi : hpis) {
//	    	String processInstanceId = hpi.getId();
//	    	Map<String, String> map = new LinkedHashMap<String, String>();
//	    	map.put("pid", processInstanceId);
//	    	//发起人
//	    	map.put("applicant", userService.getByUsername(hpi.getStartUserId()).getName());
//	    	List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
//	    	Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
//	    	//获取form值
//	    	if(task ==null) {
//	    		map.put("status_", "finished");
//	    	} else {
//	    		map.put("tid", task.getId());
//	    	}
//	    		Date start = new Date(); 
//	    		Date end = new Date(); String reason="";
//	    		for(HistoricDetail historicDetail : formProperties) {
//	    			 HistoricFormProperty	 field = (HistoricFormProperty) historicDetail;
//	    			 if(field.getPropertyId().equals("startWhile")) {
//	    				 start = sdf.parse(field.getPropertyValue());
//	    			 } else if(field.getPropertyId().equals("endWhile")) {
//	    				 end = sdf.parse(field.getPropertyValue());
//	    			 } else if(field.getPropertyId().equals("reason")) {
//	    				 reason = field.getPropertyValue();
//	    			 } 
//	    		}
//	    		
//	    		long diff = end.getTime() - start.getTime();
//				long day = diff / nd;// 计算差多少天    
//		        long hour = diff % nd / nh + day * 24;// 计算差多少小时  
//		        long second = diff % nd % nh /nc;
//				
//				map.put("overtimeStart", sdf.format(start));
//				map.put("overtimeEnd", sdf.format(end));
//				map.put("reason", reason);
//				map.put("hour", String.valueOf(hour)+"小时"+String.valueOf(second)+"分钟");
//	    	} else {
//	    		Date start = (Date)taskService.getVariable(task.getId(), "startWhile");
//				Date end = (Date)taskService.getVariable(task.getId(), "endWhile");
//				
//				long diff = end.getTime() - start.getTime();
//				long day = diff / nd;// 计算差多少天    
//		        long hour = diff % nd / nh + day * 24;// 计算差多少小时  
//		        long second = diff % nd % nh /nc;
//				
//				map.put("overtimeStart", sdf.format(start));
//				map.put("overtimeEnd", sdf.format(end));
//				map.put("reason", (String)taskService.getVariable(task.getId(), "reason"));
//				map.put("hour", String.valueOf(hour)+"小时"+String.valueOf(second)+"分钟");
//	    	}
			
//	    	list.add(map);
//		}
		
//		for(Task task : tasks) {
//			Date start = (Date)taskService.getVariable(task.getId(), "startWhile");
//			Date end = (Date)taskService.getVariable(task.getId(), "endWhile");
//			
//			long diff = end.getTime() - start.getTime();
//			long day = diff / nd;// 计算差多少天    
//	        long hour = diff % nd / nh + day * 24;// 计算差多少小时  
//	        long second = diff % nd % nh /nc;
//			
//			Map<String, String> taskMap = new HashMap<>();
//			taskMap.put("pid", task.getProcessInstanceId());
//			taskMap.put("overtimeStart", sdf.format(start));
//			taskMap.put("overtimeEnd", sdf.format(end));
//			taskMap.put("reason", (String)taskService.getVariable(task.getId(), "reason"));
//			taskMap.put("hour", String.valueOf(hour)+"小时"+String.valueOf(second)+"分钟");
//			list.add(taskMap);
//		}
 * */

		model.addAttribute("engineers", userService.getEngineer());  
		model.addAttribute("isLeader", isRole);  
		  
		return "overtime/index";
	}
	
	@RequestMapping(value="dealbyprocess/{pid}/{tid}", method=RequestMethod.GET)
	public String approve(@PathVariable("pid") String pid, @PathVariable("tid") String tid, Model model) {
		Task task = taskService.createTaskQuery().taskId(tid).singleResult();
		// 通过流程实例ID查询流程实例
//		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
		HistoricProcessInstance hi = historyService.createHistoricProcessInstanceQuery().processInstanceId(pid).singleResult();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		
		Map<String, String> taskMap = new HashMap<>();
		
		Date start = (Date)taskService.getVariable(task.getId(), "startWhile");
		Date end = (Date)taskService.getVariable(task.getId(), "endWhile");
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数  
		long nh = 1000 * 60 * 60;// 一小时的毫秒数  
		long nc = 1000*60;		//一分钟的毫秒数
		long diff = end.getTime() - start.getTime();
		long day = diff / nd;// 计算差多少天    
        long hour = diff % nd / nh + day * 24;// 计算差多少小时  
        long second = diff % nd % nh /nc;
		
		taskMap.put("pid", task.getProcessInstanceId());
		taskMap.put("tid", task.getId());
		taskMap.put("overtimeStart", sdf.format(start));
		taskMap.put("overtimeEnd", sdf.format(end));
		taskMap.put("reason", (String)taskService.getVariable(task.getId(), "reason"));
		taskMap.put("applicant", userService.getByUsername(hi.getStartUserId()).getName());
		taskMap.put("hour", String.valueOf(hour)+"小时"+String.valueOf(second)+"分钟");
		
		model.addAttribute("task", taskMap);  
		
		return "overtime/deal";
	}
	
	@RequestMapping(value = "/view/{pid}", method = RequestMethod.GET)
	public String view(@PathVariable("pid") String pid, Model model) {
		//taskService.getProcessInstanceComments(pid);
		return "overtime/view";
	}
}
