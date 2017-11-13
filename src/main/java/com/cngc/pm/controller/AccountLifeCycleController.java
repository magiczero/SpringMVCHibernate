package com.cngc.pm.controller;

import static com.cngc.utils.Common.getRemortIP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricFormProperty;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.cxf.common.util.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.ChangeitemType;
import com.cngc.pm.model.cms.AccountType;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.ChangeItemService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.UserService;
import com.cngc.pm.service.cms.AccountService;
import com.cngc.pm.service.cms.CategoryService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.pm.service.cms.PropertyService;
import com.cngc.utils.PropertyFileUtil;

@Controller
@RequestMapping("/account-life-cycle")
public class AccountLifeCycleController {
	
	@Resource
	private ChangeItemService changeitemService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private FormService formService;
	@Resource
	private IdentityService identityService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private UserService userService;
	@Resource
	private CiService ciService;
	@Resource
	private HistoryService historyService;
	@Resource
	private AttachService attachService;
	@Resource
	private AccountService accountService;
	@Resource
	private PropertyService propertyService;
	
	private static final String _STATUS = "CMS_FIELD_STATUS";
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(java.sql.Date.class,  new CustomDateEditor(new com.cngc.utils.MyDateFormat("yyyy-MM-dd HH:mm:ss"), true));
   
	}

	@RequestMapping(value = "/start", method = RequestMethod.GET)
	public String start(Model model) {
		
		return "account-life-cycle/start";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@RequestParam(required=true) int typeid,Model model) {
		model.addAttribute("ci",new Ci());
		model.addAttribute("status", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.status")).getResult());
		model.addAttribute("securityLevel", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.securitylevel")).getResult());
		String codeType = "";
		switch(typeid) {
		case 1:
			codeType = "14";		//信息系统
			break;
		case 2:
			codeType = "15";		//信息设备
			break;
		case 3:
			codeType = "16";		//存储设备
			break;
		case 4:
			codeType = "17";		//安全保密产品
			break;
		case 5:
			codeType = "12";		//应用系统
			break;
		}
		model.addAttribute("types", categoryService.getChildListByParent(codeType));
		model.addAttribute("users", userService.getAll());
		return "account-life-cycle/add";
	}
	
	/**
	 * 保存
	 * @param ci
	 * @param request
	 * @param authentication
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("ci") Ci ci, HttpServletRequest request,Authentication authentication) throws Exception{
		int typeid ;
		if(ci.getId()==null) {	//新建
			if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
				String attachIds = request.getParameter("fileids");
				Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
				ci.setAttachs(attachSet);
			}
			
			ci.setReviewStatus("02");
			ci.setDeleteStatus("01");
			//ci.setCreatedTime(new Date());
			ci.setLastUpdateTime(new Date());
			ci.setLastUpdateUser(userUtil.getUsernameByAuth(authentication));
			
			ciService.save(ci, getRemortIP(request));
			
			AccountType at = accountService.getByCiCategoryCode(ci.getCategoryCode()).getType();
			
			typeid = at.getNum();
		} else {
			Ci originalCi = ciService.getById(ci.getId());
			//修改的数据
			originalCi.setName(ci.getName());
			originalCi.setNum(ci.getNum());
			originalCi.setCategoryCode(ci.getCategoryCode());
			originalCi.setSerial(ci.getSerial());
			originalCi.setModel(ci.getModel());
			originalCi.setProducer(ci.getProducer());
			originalCi.setSecurityLevel(ci.getSecurityLevel());
			originalCi.setSecurityNo(ci.getSecurityNo());
			originalCi.setDepartmentInUse(ci.getDepartmentInUse());
			originalCi.setUserInMaintenance(ci.getUserInMaintenance());
			originalCi.setPurpose(ci.getPurpose());
			originalCi.setServiceStartTime(ci.getServiceStartTime());//启用日期
			originalCi.setExpirationTime(ci.getExpirationTime());//维保截止时间
			originalCi.setStatus(ci.getStatus());//使用情况/状态
			originalCi.setLocation(ci.getLocation());
			originalCi.setCreatedTime(ci.getCreatedTime());//购置日期
			originalCi.setRemark(ci.getRemark());
			
			originalCi.setLastUpdateTime(new Date());
			originalCi.setLastUpdateUser(userUtil.getUsernameByAuth(authentication));
			
			ciService.save(originalCi, getRemortIP(request));
			
			AccountType at = accountService.getByCiCategoryCode(originalCi.getCategoryCode()).getType();
			
			typeid = at.getNum();
		}
		
		return "redirect:/stats/account/list-by-type?typeid="+typeid;
	}
	
	@RequestMapping(value="/addproperty/{id}")
	public String addProperty(@PathVariable("id") long id,Model model){
		Ci ci = ciService.getById(id);
		model.addAttribute("ci",ci);
		String code = ci.getCategoryCode();
		String tmpcode = code.substring(0,2);
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		while(tmpcode.length()<=code.length())
		{
			//根据code分级判断
			Category category = categoryService.getByCode(tmpcode);
			//
			List<String> htmlCodes = new ArrayList<>();
			for(Property p : category.getProperties()) {
				htmlCodes.add(p.getPropertyName()+"-"+propertyService.analyzePropertyToHtml(p));
			}
			map.put(category.getCategoryName(), htmlCodes);
			if(tmpcode.length()+2>code.length())
				break;
			tmpcode = code.substring(0,tmpcode.length()+2);
		}
		model.addAttribute("properties", map);
		return "account-life-cycle/ci-addproperty";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ci/saveproperty/{id}",method=RequestMethod.POST)
	public String saveProperty(@PathVariable("id") long id,HttpServletRequest request){	
		ObjectMapper mapper = new ObjectMapper();
		Ci ci = ciService.getById(id);
		try
		{
			Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
			Map<String,String> parameters = new HashMap<String,String>();
			for (Entry<String, String[]> entry : entrySet)
			{
				parameters.put(entry.getKey(), entry.getValue()[0]);
			}
			ci.setPropertiesData(mapper.writeValueAsString(parameters));
			
		}catch(JsonGenerationException e){
			
		}catch(JsonMappingException e){
			
		}catch(IOException e){
			
		}
		ciService.save(ci, getRemortIP(request));
		
		AccountType at = accountService.getByCiCategoryCode(ci.getCategoryCode()).getType();
		
		return "redirect:/stats/account/list-by-type?typeid="+at.getNum();
	}
	
	/**
	 * 启动流程
	 * @param ciid
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/start-by-ciid", method = RequestMethod.GET)
	public String startByCiid(@RequestParam(required=true) long ciid,@RequestParam(required=true) int typeid,Model model,Authentication authentication) throws Exception {
		Ci ci = ciService.getById(ciid);
		if(ci!=null) {	//启动流程
			String procInstanceId = "";
			try {
				identityService.setAuthenticatedUserId( userUtil.getUsernameByAuth(authentication) );
				ProcessInstance procInstance= runtimeService.startProcessInstanceByKey("accountLifeCycle");
				
				procInstanceId = procInstance.getId();
				
				ChangeItem changeItem = new ChangeItem();
				
				changeItem.setCiId(ci.getId());
				changeItem.setChangeId(Long.valueOf(procInstanceId));
				changeItem.setCreatedTime(new java.util.Date());
				Map<String, String> oldvalue = new HashMap<String, String>();
				ObjectMapper mapper = new ObjectMapper();
				switch(typeid) {
				case 2:
					changeItem.setType(ChangeitemType.change);
					break;
				case 3:
					changeItem.setType(ChangeitemType.stop);
					//更改状态为停用
					changeItem.setProperties(_STATUS);
					changeItem.setPropertiesName("状态");
					//把旧的值捕捉
					oldvalue.put(_STATUS, ci.getStatusName());
					
					changeItem.setOldValue(mapper.writeValueAsString(oldvalue));
					break;
				case 4:
					changeItem.setType(ChangeitemType.scrap);
					//更改状态为报废
					changeItem.setProperties(_STATUS);
					changeItem.setPropertiesName("状态");
					//把旧的值捕捉
					oldvalue.put(_STATUS, ci.getStatusName());
					changeItem.setOldValue(mapper.writeValueAsString(oldvalue));
					break;
				case 5:
					changeItem.setType(ChangeitemType.destroy);
					//更改状态为销毁
					changeItem.setProperties(_STATUS);
					changeItem.setPropertiesName("状态");
					//把旧的值捕捉
					oldvalue.put(_STATUS, ci.getStatusName());
					changeItem.setOldValue(mapper.writeValueAsString(oldvalue));
					break;
				case 6:
					changeItem.setType(ChangeitemType.reloados);
					//更改状态为销毁
					changeItem.setProperties("CMS_PROPERTY_OSINSTALLTIME,CMS_PROPERTY_OSVERSION");
					changeItem.setPropertiesName("操作系统安装时间,操作系统版本");
					//把旧的值捕捉
					@SuppressWarnings("unchecked") Map<String, String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
					String ps[] = changeItem.getProperties().split(",");
					for (String s : ps) {
						oldvalue.put(s, propertymap.get(s));
					}

					changeItem.setOldValue(mapper.writeValueAsString(oldvalue));
					
					break;
				case 7:
					changeItem.setType(ChangeitemType.maintain);
					//更改状态为销毁
					changeItem.setProperties(_STATUS);
					changeItem.setPropertiesName("状态");
					//把旧的值捕捉
					oldvalue.put(_STATUS, ci.getStatusName());
					changeItem.setOldValue(mapper.writeValueAsString(oldvalue));
					break;
				}
				
				changeitemService.save(changeItem);
				//直接转向下一个task
				Task task = taskService.createTaskQuery().processInstanceId(procInstanceId).active().singleResult();
				
				return "redirect:/account-life-cycle/deal/"+task.getId();
			} finally {
				identityService.setAuthenticatedUserId(null);
			}
		}
		return "";
	}
	
	@RequestMapping(value = "/start-process", method = RequestMethod.POST)
	public String startProcess(Model model,HttpServletRequest request,Authentication authentication) {
		Map<String, String> formProperties = new HashMap<String, String>();

		// 从request中读取参数然后转换
		@SuppressWarnings("unchecked")
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			formProperties.put(entry.getKey(), entry.getValue()[0]);
		}

		try {
			identityService.setAuthenticatedUserId( userUtil.getUsernameByAuth(authentication) );
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionKey("accountLifeCycle").latestVersion().singleResult();
			formService.submitStartFormData(processDefinition.getId(), formProperties);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		//redirectAttributes.addFlashAttribute("message", "启动成功，流程ID：" + processInstance.getId());

		// return "redirect:/workflow/processinstance/running";
		return "redirect:/workflow/task/list";
	}
	
	@RequestMapping(value = "/deal/{taskid}", method = RequestMethod.GET)
	public String deal( @PathVariable("taskid") String taskId, Model model) {
		Task task = null;

		if (taskId != null)
			// 获取当前事件的task信息
			task = taskService.createTaskQuery().taskId(taskId).singleResult();

		//查找操作类型
		List<ChangeItem> list = changeitemService.getByChangeId(Long.valueOf(task.getProcessInstanceId()));
		if(list.size()>0) {
			ChangeItem item = list.get(0);
			
			model.addAttribute("type", item.getType());
		}
		model.addAttribute("task", task);

		return "account-life-cycle/deal";
	}
	
	@RequestMapping(value = "/saveitems")
	@ResponseBody
	public Map<String, Object> saveItems(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		String ciIds = request.getParameter("ci_ids").toString();

		String itemids = "";
		String ids[] = ciIds.split(",");
		for (String id : ids) {
			ChangeItem item = new ChangeItem();
			item.setCreatedTime(new java.util.Date());
			item.setCiId(Long.valueOf(id));
			item.setType(ChangeitemType.change);
			changeitemService.save(item);
			itemids += item.getId()+",";
		}

		map.put("itemids", itemids);

		return map;
	}
	
	@RequestMapping(value = "/get-changes-by-ci")
	@ResponseBody
	public List<Map<String, Object>> getChangesByCiid(@RequestParam(required=true) long ciid, Authentication authentication) throws Exception {
		//List<ChangeItem> list = changeitemService.getByCi(ciid).getResult();
		List<ChangeItem> list = changeitemService.getByCiAndType(ciid, ChangeitemType.change);
		
		List<Map<String, Object>> detailList = new ArrayList<>();
		
		//权限使用
		//String currentUsername = userUtil.getUsernameByAuth(authentication);
		
		for(ChangeItem changeItem : list) {
			
			
			String processInstanceId = changeItem.getChangeId().toString();
			
			HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			
			if(hpi!=null) {
				List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
				Map<String, Object> map = new HashMap<String, Object>();
				for(HistoricDetail historicDetail : formProperties) {
					HistoricFormProperty field = (HistoricFormProperty) historicDetail;
		   			
		   			switch(field.getPropertyId()) {
		   				case "solution" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "delegateUser" :
							map.put(field.getPropertyId(), userService.getUserName(field.getPropertyValue()));
							break;
						case "startTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "endTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "resultView" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
		   			}
		   			
		   			
		    	}
				detailList.add(map);
			}
//				for(Entry<String, Object> entry :hpi.getProcessVariables().entrySet()) {
//					Map<String, Object> map = new HashMap<String, Object>();
//					switch(entry.getKey()) {
//					case "solution" :
//						map.put(entry.getKey(), entry.getValue());
//						break;
//					case "delegateUser" :
//						map.put(entry.getKey(), entry.getValue());
//						break;
//					case "startTime" :
//						map.put(entry.getKey(), entry.getValue());
//						break;
//					case "endTime" :
//						map.put(entry.getKey(), entry.getValue());
//						break;
//					case "resultView" :
//						map.put(entry.getKey(), entry.getValue());
//						break;
//					}
//					detailList.add(map);
//				}
			
		}
		
		return detailList;
	}
	
	@RequestMapping(value = "/get-stops-by-ci")
	@ResponseBody
	public List<Map<String, Object>> getStopsByCiid(@RequestParam(required=true) long ciid, Authentication authentication) throws Exception {
		List<ChangeItem> list = changeitemService.getByCiAndType(ciid, ChangeitemType.stop);
		
		List<Map<String, Object>> detailList = new ArrayList<>();
		
		for(ChangeItem changeItem : list) {
			
			
			String processInstanceId = changeItem.getChangeId().toString();
			
			HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			
			if(hpi!=null) {
				List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
				Map<String, Object> map = new HashMap<String, Object>();
				for(HistoricDetail historicDetail : formProperties) {
					HistoricFormProperty field = (HistoricFormProperty) historicDetail;
		   			
		   			switch(field.getPropertyId()) {
		   				case "solution" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "delegateUser" :
							map.put(field.getPropertyId(), userService.getUserName(field.getPropertyValue()));
							break;
						case "startTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "endTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "resultView" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
		   			}
		   			
		   			
		    	}
				detailList.add(map);
			}
			
		}
		
		return detailList;
	}
	
	@RequestMapping(value = "/get-scraps-by-ci")
	@ResponseBody
	public List<Map<String, Object>> getScrapsByCiid(@RequestParam(required=true) long ciid, Authentication authentication) throws Exception {
		List<ChangeItem> list = changeitemService.getByCiAndType(ciid, ChangeitemType.scrap);
		
		List<Map<String, Object>> detailList = new ArrayList<>();
		
		for(ChangeItem changeItem : list) {
			
			
			String processInstanceId = changeItem.getChangeId().toString();
			
			HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			
			if(hpi!=null) {
				List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
				Map<String, Object> map = new HashMap<String, Object>();
				for(HistoricDetail historicDetail : formProperties) {
					HistoricFormProperty field = (HistoricFormProperty) historicDetail;
		   			
		   			switch(field.getPropertyId()) {
		   				case "solution" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "delegateUser" :
							map.put(field.getPropertyId(), userService.getUserName(field.getPropertyValue()));
							break;
						case "startTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "endTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "resultView" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
		   			}
		   			
		   			
		    	}
				detailList.add(map);
			}
			
		}
		
		return detailList;
	}
	
	@RequestMapping(value = "/get-destroys-by-ci")
	@ResponseBody
	public List<Map<String, Object>> getDestroysByCiid(@RequestParam(required=true) long ciid, Authentication authentication) throws Exception {
		List<ChangeItem> list = changeitemService.getByCiAndType(ciid, ChangeitemType.destroy);
		
		List<Map<String, Object>> detailList = new ArrayList<>();
		
		for(ChangeItem changeItem : list) {
			
			
			String processInstanceId = changeItem.getChangeId().toString();
			
			HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			
			if(hpi!=null) {
				List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
				Map<String, Object> map = new HashMap<String, Object>();
				for(HistoricDetail historicDetail : formProperties) {
					HistoricFormProperty field = (HistoricFormProperty) historicDetail;
		   			
		   			switch(field.getPropertyId()) {
		   				case "solution" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "delegateUser" :
							map.put(field.getPropertyId(), userService.getUserName(field.getPropertyValue()));
							break;
						case "startTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "endTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "resultView" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
		   			}
		   			
		   			
		    	}
				detailList.add(map);
			}
			
		}
		
		return detailList;
	}
	
	@RequestMapping(value = "/get-maintains-by-ci")
	@ResponseBody
	public List<Map<String, Object>> getMaintainsByCiid(@RequestParam(required=true) long ciid, Authentication authentication) throws Exception {
		List<ChangeItem> list = changeitemService.getByCiAndType(ciid, ChangeitemType.maintain);
		
		List<Map<String, Object>> detailList = new ArrayList<>();
		
		for(ChangeItem changeItem : list) {
			
			
			String processInstanceId = changeItem.getChangeId().toString();
			
			HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			
			if(hpi!=null) {
				List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
				Map<String, Object> map = new HashMap<String, Object>();
				for(HistoricDetail historicDetail : formProperties) {
					HistoricFormProperty field = (HistoricFormProperty) historicDetail;
		   			
		   			switch(field.getPropertyId()) {
		   				case "solution" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "delegateUser" :
							map.put(field.getPropertyId(), userService.getUserName(field.getPropertyValue()));
							break;
						case "startTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "endTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "resultView" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
		   			}
		   			
		   			
		    	}
				detailList.add(map);
			}
			
		}
		
		return detailList;
	}
	
	@RequestMapping(value = "/get-os-by-ci")
	@ResponseBody
	public List<Map<String, Object>> getOSByCiid(@RequestParam(required=true) long ciid, Authentication authentication) throws Exception {
		List<ChangeItem> list = changeitemService.getByCiAndType(ciid, ChangeitemType.reloados);
		
		List<Map<String, Object>> detailList = new ArrayList<>();
		
		for(ChangeItem changeItem : list) {
			String processInstanceId = changeItem.getChangeId().toString();
			
			HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			
			if(hpi!=null) {
				List<HistoricDetail> formProperties = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).formProperties().list();
				Map<String, Object> map = new HashMap<String, Object>();
				
				ObjectMapper mapper = new ObjectMapper();
				@SuppressWarnings("unchecked")
				Map<String, String> newpropertymap = mapper.readValue(changeItem.getNewValue(), Map.class);
				//新安装时间
				map.put("NEW_OSINSTALLTIME", newpropertymap.get("CMS_PROPERTY_OSINSTALLTIME"));
				//新操作系统类型
				map.put("NEW_OSVERSION", newpropertymap.get("CMS_PROPERTY_OSVERSION"));
				
				@SuppressWarnings("unchecked")
				Map<String, String> oldpropertymap = mapper.readValue(changeItem.getOldValue(), Map.class);
				
				//新安装时间
				map.put("OLD_OSINSTALLTIME", oldpropertymap.get("CMS_PROPERTY_OSINSTALLTIME"));
				//新操作系统类型
				map.put("OLD_OSVERSION", oldpropertymap.get("CMS_PROPERTY_OSVERSION"));
				
				for(HistoricDetail historicDetail : formProperties) {
					HistoricFormProperty field = (HistoricFormProperty) historicDetail;
		   			
		   			switch(field.getPropertyId()) {
		   				case "solution" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "delegateUser" :
							map.put(field.getPropertyId(), userService.getUserName(field.getPropertyValue()));
							break;
						case "startTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "endTime" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
						case "resultView" :
							map.put(field.getPropertyId(), field.getPropertyValue());
							break;
		   			}
		   			
		   			
		    	}
				detailList.add(map);
			}
			
		}
		
		return detailList;
	}
	
	@RequestMapping(value = "/getitems")
	@ResponseBody
	public Map<String, Object> getItems(@RequestParam(required=true) String processInstanceId) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("items", changeitemService.getByChangeId(Long.valueOf(processInstanceId)));

		return map;
	}
	
	@RequestMapping(value = "/deleteitem")
	@ResponseBody
	public Map<String, Object> deleteItems(@RequestParam(required=true) long itemid) {
		Map<String, Object> map = new HashMap<String, Object>();

		// Change change = changeService.getById(id);
		changeitemService.delById(itemid);

		map.put("result", true);

		return map;
	}
}
