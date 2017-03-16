package com.cngc.pm.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.manage.Item;
import com.cngc.pm.model.manage.ManageType;
import com.cngc.pm.model.manage.ManagerForm;
import com.cngc.pm.model.manage.Relations;
import com.cngc.pm.service.ThreeMemberService;
import com.cngc.pm.service.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping("/three-member")
public class ThreeMemberController {

	@Resource
	ThreeMemberService tmService;
	@Resource
	private HistoryService historyService;
	@Resource
	private UserService userService;
	@Resource
	private TaskService taskService;
	@Resource
	private FormService formService;
	@Resource
	private UserUtil userUtil;
	
	@RequestMapping(value = "/list/{manageType}",method=RequestMethod.GET)
	public String list(@PathVariable("manageType") String mt, Model model) {
		model.addAttribute("engineers", userService.getEngineer());
		
		if("system-manager".equals(mt)) {
			model.addAttribute("type", 1);
		} else if("security-manager".equals(mt)) {
			model.addAttribute("type", 2);
		} else if("security-comptroller".equals(mt)) {
			model.addAttribute("type", 3);
		} else if("bm-system-manager".equals(mt)) {
			model.addAttribute("type", 4);
		} else if("bm-security-manager".equals(mt)) {
			model.addAttribute("type", 5);
		} else if("bm-security-comptroller".equals(mt)) {
			model.addAttribute("type", 6);
		} else if("oa-system-manager".equals(mt)) {
			model.addAttribute("type", 7);
		} else if("oa-security-manager".equals(mt)) {
			model.addAttribute("type", 8);
		} else if("oa-security-comptroller".equals(mt)) {
			model.addAttribute("type", 9);
		}
		
		return "threemember/list";
	}
	
	@RequestMapping(value="/ajax-list",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String tableAjax(@RequestParam String type,@RequestParam String aoData, Authentication authentication) throws Exception {
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
	        } 
	    } 
	    
	    String currentUsername = userUtil.getUsernameByAuth(authentication);
	    //判断权限
	    boolean isLeader = false;
	    
	    if(userUtil.IsLeader(authentication))
	    	isLeader = true;
	    
	    HistoricProcessInstanceQuery hpq = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("threeMember");
    	if(isSearch.equals("1") ) hpq = hpq.finished();
    	//需要查找的人员
    	if(findUsername.equals("00")) {
		    if(!isLeader) {
		    	hpq = hpq.involvedUser(currentUsername);
		    }
	    } else {
	    	hpq = hpq.involvedUser(findUsername);
	    }
    	
    	hpq = hpq.variableValueEquals("type", type);
    	
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
    	
    	if(!("").equals(startTime)) {
  	    	hpq.startedAfter(sdf.parse(startTime + " 00:00"));
  	    }
  	    if(!("").equals(endTime)) {
  	    	hpq.startedBefore(sdf.parse(endTime + " 23:59"));
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
	    	map.put("pid", processInstanceId);
	    	map.put("time", sdf.format(hpi.getStartTime()));
	    	
	    	List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
	    	
	    	String username = "";
	    	for(HistoricDetail historicDetail : formProperties) {
	   			 HistoricFormProperty	 field = (HistoricFormProperty) historicDetail;
	   			 if(field.getPropertyId().equals("user")) {
	   				username = field.getPropertyValue();
	   				break;
	   			 }
	    	}
	    	
	    	map.put("username", userUtil.getNameByUsername(username));
	    	
	    	//完成时间
	    	if(hpi.getEndTime()==null) {
	    		map.put("finishTime", "");
	    		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).active().singleResult();
	    		
	    		if(task.getAssignee().equals(currentUsername))	{		//如果是自己的任务
	    			map.put("op", "<a href=\"deal/"+task.getId()+"\">办理</a>");
	    		} else {			//否则就是发起人
	    			map.put("op", "");
	    		}
	    	} else {
	    		map.put("finishTime", sdf.format(hpi.getEndTime()));
	    		map.put("op", "<a href=\"view/"+processInstanceId+"\">查看</a>");
	    	}
	    	
	    	list.add(map);
	    }
	    
	    getObj.put("aaData", list);//要以JSON格式返回
	    
		return getObj.toString();
	}
	
	@RequestMapping(value="deal/{tid}", method=RequestMethod.GET)
	public String deal(@PathVariable("tid") String tid, Model model) {
		Task task = taskService.createTaskQuery().taskId(tid).singleResult();
		taskService.getVariable(task.getId(), "type");
		
		String typeId = (String)taskService.getVariable(task.getId(), "type");;
		
		ManageType type = ManageType.get(Integer.parseInt(typeId));
		
		List<Relations> list = tmService.getRelationListByType(type);
		
		Set<Item> items = new LinkedHashSet<>();
		
		for(Relations r : list) {
			items.add(r.getItem());
		}
		
		Map<Item, List<Relations>> map = new LinkedHashMap<>();
		
		for(Item i : items) {
			List<Relations> relationList = new ArrayList<>();
			for(Relations r : list) {
				if(i == r.getItem()) {
					relationList.add(r);
				}
			}
			map.put(i, relationList);
		}
		
		model.addAttribute("formMap", map);
		model.addAttribute("taskid", tid);

		return "threemember/deal";
	}
	
	@RequestMapping(value="finish/{tid}", method=RequestMethod.POST)
	public String finish(@PathVariable("tid") String tid, HttpServletRequest request, Authentication auth) throws Exception {
		Task task = taskService.createTaskQuery().taskId(tid).singleResult();
		
		String typeId = (String)taskService.getVariable(task.getId(), "type");
		//提交表单后，结束
		ObjectMapper mapper = new ObjectMapper();
		//先获取被选中的项目
		String formValue = mapper.writeValueAsString(request.getParameterMap());
				
		ManagerForm mf = new ManagerForm();
		
		mf.setUsername(userUtil.getUsernameByAuth(auth));
		mf.setValue(formValue);
		
		tmService.save(mf, task);
		
		String type = "";
		
		switch(typeId) {
			case "1":
				type = "system-manager";
				break;
			case "2":
				type = "security-manager";
				break;
			case "3":
				type = "security-comptroller";
				break;
			case "4":
				type = "bm-system-manager";
				break;
			case "5":
				type = "bm-security-manager";
				break;
			case "6":
				type = "bm-security-comptroller";
				break;
			case "7":
				type = "oa-system-manager";
				break;
			case "8":
				type = "oa-security-manager";
				break;
			case "9":
				type = "oa-security-comptroller";
				break;
		}
		
		return "redirect:/three-member/list/"+type;
	}
	
	@RequestMapping(value="view/{pid}", method=RequestMethod.GET)
	public void view(@PathVariable("pid") String pid, HttpServletRequest request, Authentication auth, HttpServletResponse response) throws Exception {
		ManagerForm form = tmService.getFormByPid(pid);
		
		Map<String, Object> dataMap = tmService.getDocumentData(form);
		
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
		
		//configuration.setServletContextForTemplateLoading(request.getSession().getServletContext(),"/WEB-INF/classes/com/cngc/pm/threemember/template");
		configuration.setClassForTemplateLoading(this.getClass(), "/com/cngc/pm/threemember/template");
		
		Template t=null;
		try {
			//test.ftl为要装载的模板
			t = configuration.getTemplate("first.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//输出文档路径及名称
				File outFile = new File("D:/temp/outFile"+pid+".doc");
				Writer out = null;
				try {
					out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					t.process(dataMap, out);
				} catch (TemplateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		OutputStream os = response.getOutputStream();  
	    try {  
	    	response.reset();  
	    	response.setHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode("工作记录-"+pid+".doc", "UTF-8"));  
	    	response.setContentType("application/octet-stream; charset=utf-8");  
	        os.write(FileUtils.readFileToByteArray(outFile));  
	        os.flush();  
	    } finally {  
	        if (os != null) {  
	            os.close();  
	        }  
	    } 
		
	}
}
