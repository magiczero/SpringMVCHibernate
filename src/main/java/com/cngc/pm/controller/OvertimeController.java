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

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		HistoricProcessInstanceQuery hpq = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("OVERTIME");
		
		//权限判断
		SysUser currentUser = userService.getByUsername(candidateUser);
		boolean isRole = false;
		for(UserRole ur : currentUser.getUserRoles()) {
			if(ur.getRole().getRoleName().equals("WK_LEADER")) {
				isRole = true;
			}
		}
		
		if(!isRole) {
			hpq.involvedUser(candidateUser);
		}
		
		List<HistoricProcessInstance> hpis = hpq.orderByProcessInstanceStartTime().desc().list();
		
		List<Map<String, String>> list = new ArrayList<>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数  
		long nh = 1000 * 60 * 60;// 一小时的毫秒数  
		long nc = 1000*60;		//一分钟的毫秒数
		
		for(HistoricProcessInstance hpi : hpis) {
	    	String processInstanceId = hpi.getId();
	    	Map<String, String> map = new LinkedHashMap<String, String>();
	    	map.put("pid", processInstanceId);
	    	//发起人
	    	map.put("applicant", userService.getByUsername(hpi.getStartUserId()).getName());
	    	List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
	    	Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
	    	//获取form值
	    	if(task ==null) {
	    		map.put("status_", "finished");
	    	} else {
	    		map.put("tid", task.getId());
	    	}
	    		Date start = new Date(); 
	    		Date end = new Date(); String reason="";
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
	    		
	    		long diff = end.getTime() - start.getTime();
				long day = diff / nd;// 计算差多少天    
		        long hour = diff % nd / nh + day * 24;// 计算差多少小时  
		        long second = diff % nd % nh /nc;
				
				map.put("overtimeStart", sdf.format(start));
				map.put("overtimeEnd", sdf.format(end));
				map.put("reason", reason);
				map.put("hour", String.valueOf(hour)+"小时"+String.valueOf(second)+"分钟");
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
			
	    	list.add(map);
		}
		
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
		  
		model.addAttribute("list", list);  
		  
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
