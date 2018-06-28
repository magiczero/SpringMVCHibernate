package com.cngc.pm.controller;

import static com.cngc.utils.Common.getRemortIP;
import static com.cngc.utils.Constants._accountMaster;
import static com.cngc.utils.Constants._accountSub;
import static com.cngc.utils.Constants.STREAM_OPERATE_LOG;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.cxf.common.util.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
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
import org.springframework.web.multipart.MultipartFile;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.ChangeitemType;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Account;
import com.cngc.pm.model.cms.AccountType;
import com.cngc.pm.model.cms.AuditTask;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.model.cms.TaskCategoryDepartmentRelation;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.ChangeItemService;
import com.cngc.pm.service.GroupService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.UserService;
import com.cngc.pm.service.cms.AccountService;
import com.cngc.pm.service.cms.AuditTaskService;
import com.cngc.pm.service.cms.CategoryService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.pm.service.cms.PropertyService;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.SearchResult;

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
	@Resource
	private AuditTaskService auditTaskService;
	@Resource
	private GroupService groupService;
	
	private static final String _STATUS = "CMS_FIELD_STATUS";
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(java.sql.Date.class,  new CustomDateEditor(new com.cngc.utils.MyDateFormat("yyyy-MM-dd HH:mm:ss"), true));
   
	}
	
	@RequestMapping(value = "/get-html-by-code", method = RequestMethod.GET)
	@ResponseBody 
	public Map<String, Object> getHtmlByCode(@RequestParam(required=true) String code) {
		Category category = categoryService.getByCode(code);
		if(category == null)
			return null;
		else
			return getHtmlMapByCategory(category.getCategoryCode());
		
	}
	
	@RequestMapping(value = "/export-xls", method = RequestMethod.POST)
	public String exportXls(Model model, HttpServletRequest request,HttpServletResponse response,Authentication auth) throws Exception {
		if(request.getParameterValues("exportGroups")==null ) return "false";
			
		if(request.getParameterValues("exportCodes") == null) return "false";
		
		String[] groups = request.getParameterValues("exportGroups");
		String[] codes = request.getParameterValues("exportCodes");
		
		List<Group> groupList = new ArrayList<>();
		for(String groupid : groups) {
			Group group = groupService.getById(Long.valueOf(groupid));
			if(group == null) return "redirect:/404";
			groupList.add(group);
		}
		
		List<Category> categorys =  new ArrayList<>();
		for(String code : codes) {
			Category category = categoryService.getByCode(code);
			if(category==null)return "redirect:/404";
			categorys.add(category);
		}
		
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String fileName = "台账-"+sDateFormat.format(new Date())+".xlsx";
		
        response.setContentType("application/vnd.ms-excel");   
//        response.setHeader("Content-disposition", "attachment;filename="+ java.net.URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Content-disposition", "attachment;filename="+ new String(fileName.getBytes("GBK"),"ISO-8859-1"));  
        response.setHeader("Pragma", "No-cache"); 
		ciService.exportXls(groupList, categorys, response.getOutputStream());
		
		return "true";
	}
	
	@RequestMapping(value = "/end-aduit-task/{id}", method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, Object> endAuditTask(@PathVariable Long id,@RequestParam(required=true) boolean nonforce,Authentication auth) throws Exception {
		Map<String, Object> map = new HashMap<>();
		SysUser currentUser = userUtil.getUserByAuth(auth);
		if(userUtil.isRolesWithUser(currentUser, _accountMaster)) {
			AuditTask at = auditTaskService.getById(id);
			AuditTask at0 = auditTaskService.findUnfinishedTask(currentUser);
			
			if(at == at0) {
				for(TaskCategoryDepartmentRelation relation :at.getRelationSet()) {
					if(relation.getStatus() == 2){
						map.put("flag", false);
						map.put("msg", "还有未完成的部门:"+relation.getCiDepartment().getGroupName()+"，所以无法结束");
						return map;
					}
					
					if(nonforce){
						//查看ci是否审核
						for(Ci ci :auditTaskService.getCiListByGroupAndCategory(relation.getCiCategory(), relation.getCiDepartment())) {
							if(ci.getReviewStatus().equals("05")) {
								map.put("flag", false);
								map.put("force", true);
								map.put("msg", "还有未完成的审核的台账:"+ci.getName()+"-"+relation.getCiDepartment().getGroupName()+"，所以无法结束");
							}
						}
					}
				}
				auditTaskService.endTask(id);
				STREAM_OPERATE_LOG.info("审核已经结束，id:"+at.getId()+",操作者是："+currentUser.getUsername());
				map.put("flag", true);
			} else {
				map.put("flag", false);
				map.put("msg", "参数错误");
			}
		} else {
			map.put("flag", false);
			map.put("msg", "没有权限");
		}
		
		
		return map;
	}
	
	@RequestMapping(value = "/start-aduit-task", method = RequestMethod.POST)
	@ResponseBody 
	public boolean startAuditTask(Model model, HttpServletRequest request,Authentication auth) {
		
		if(request.getParameterValues("groups")==null || request.getParameterValues("codes") == null){
			return false;
		}
		SysUser currentUser = userUtil.getUserByAuth(auth);
		//查找没完成任务
		if(auditTaskService.findUnfinishedTask(currentUser)==null){
			String name = request.getParameter("auditName");
			String reason = request.getParameter("reason");
			
			Set<TaskCategoryDepartmentRelation> relationSet = new HashSet<>();
			
			//部门
			for(String groupid : request.getParameterValues("groups")) {
				Group group = groupService.getById(Long.valueOf(groupid));
				//分类
				for(String accountTypeId : request.getParameterValues("codes")) {
					TaskCategoryDepartmentRelation relation = new TaskCategoryDepartmentRelation();
					Category category = categoryService.getByCode(accountTypeId);
					relation.setCiCategory(category);
					relation.setCiDepartment(group);
					//状态改为未审核
					relation.setStatus(2);					//审核状态 1-已审核，2-未审核，5-审核中
					relationSet.add(relation);
				}
			}
			
			if(relationSet.size() == 0) {
				return false;
			}
			
			AuditTask auditTask = new AuditTask();
			auditTask.setName(name);
			auditTask.setReason(reason);
			auditTask.setAssessor(currentUser);
			auditTask.setRelationSet(relationSet);
			
			auditTaskService.startTask(auditTask);
			
			STREAM_OPERATE_LOG.info("开启了一个审核，id:"+auditTask.getId()+",操作者是："+currentUser.getUsername());
			return true;
		}
		
		return false;
	}
	
	@RequestMapping(value="/audit-task/contains0", method=RequestMethod.GET)
	@ResponseBody 
	public Map<String, String[]> auditTaskContains0(@RequestParam(required=true) long ciid, Model model, Authentication auth) throws Exception {
		Ci ci = ciService.getById(ciid);
		//首先判断是否有审核任务
		AuditTask at = auditTaskService.findUnfinishedTask(groupService.getById(Long.valueOf(ci.getDepartmentInUse())),categoryService.getByCode(ci.getCategoryCode()));
		if(at==null){
			STREAM_OPERATE_LOG.info("没有审核任务，所以無法讀取");
			throw new Exception("没有审核任务，所以無法讀取,台账id="+ciid);
		} else {
			Map<String, String[]> map = ciService.getContrastByCi(ci.getId(), at.getId());
			
			return map;
		}
	}
	
	@RequestMapping(value="/audit-task/contains", method=RequestMethod.GET)
	public String auditTaskContains(@RequestParam(required=true) long ciid, Model model, Authentication auth) throws Exception {
//		SysUser currentUser = userUtil.getUserByAuth(auth);
		Ci ci = ciService.getById(ciid);
		//首先判断是否有审核任务
		AuditTask at = auditTaskService.findUnfinishedTask(groupService.getById(Long.valueOf(ci.getDepartmentInUse())),categoryService.getByCode(ci.getCategoryCode()));
		if(at==null){
			STREAM_OPERATE_LOG.info("没有审核任务，所以無法讀取");
			throw new Exception("没有审核任务，所以無法讀取,台账id="+ciid);	
		} else {
			Map<String, String[]> map = ciService.getContrastByCi(ci.getId(), at.getId());
				
			model.addAttribute("mapContains", map);
			model.addAttribute("ci", ci);
				
			return "account-life-cycle/audit-task-contains";
		}
	}
	
	@RequestMapping(value="/audit-task/contains1", method=RequestMethod.GET)
	@ResponseBody 
	public Map<String, String[]> auditTaskContains1(@RequestParam(required=true) long ciid,@RequestParam(required=true) long at, Model model) throws Exception {
		Ci ci = ciService.getById(ciid);
		//首先判断是否有审核任务
		AuditTask auditTask = auditTaskService.getById(at);
		if(auditTask==null || ci==null){
			STREAM_OPERATE_LOG.info("没有审核任务或者没有这个台账，所以無法讀取");
			throw new Exception("没有审核任务或者没有台账信息，所以無法讀取,审核任务id="+at);	
		} else {
			Map<String, String[]> map = ciService.getContrastByCi(ci.getId(), at);
				
			return map;
		}
	}
	
	@RequestMapping(value = "/audit-task/import-xls", method = RequestMethod.POST)
	public String auditTaskImportXls(@RequestParam("xls_file") MultipartFile file, HttpServletRequest request, Model model,Authentication auth) throws Exception {
		// 先判断文件是否为空 
        if (file.isEmpty()) {
        	throw new Exception("没有可操作的文件");
        }
        
        //验证权限以及是否有任务
        SysUser currentUser = userUtil.getUserByAuth(auth);
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			//首先判断是否有审核任务
			AuditTask at = auditTaskService.findUnfinishedTask(currentUser.getGroup());
			
			if(at==null){
				throw new Exception("无法完成导入，当前没有审核任务");
			} else {
				ciService.importXlsByTask(file,at,currentUser);
				return "redirect:/account-life-cycle/audit-task/"+at.getId();
			}
		}else {
			throw new Exception("当前用户的角色不允许执行此操作");
		}
	}
	
	//atid
	@RequestMapping(value = "/audit-task/commit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> auditTaskCommit(@PathVariable Long id,Authentication auth) {
		Map<String, Object> map = new HashMap<>();
		//验证权限以及是否有任务
        SysUser currentUser = userUtil.getUserByAuth(auth);
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			//首先判断是否有审核任务
			AuditTask at = auditTaskService.findUnfinishedTask(currentUser.getGroup());
			
			AuditTask at0 = auditTaskService.getById(id);
			
			if(at==at0 && at.getEndTime() == null) {
				//把at中relation的状态和所有ci的状态修改
				//提交
				auditTaskService.subAccountCommit(at, currentUser.getGroup());
				map.put("flag", true);
				map.put("msg", "提交成功");
			} else {
				map.put("flag", false);
				map.put("msg", "参数错误");
			}
		} else {
			map.put("flag", false);
			map.put("msg", "没有权限");
		}
		return map;
	}
	
	@RequestMapping(value = "/audit-task/save", method = RequestMethod.POST)
	public String auditTaskSave(@Valid @ModelAttribute("ci") Ci ci, HttpServletRequest request, Model model,Authentication auth) throws Exception {
		SysUser currentUser = userUtil.getUserByAuth(auth);
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			//首先判断是否有审核任务
			AuditTask at = auditTaskService.findUnfinishedTask(currentUser.getGroup(),categoryService.getByCode(ci.getCategoryCode()));
			if(at==null){
				return "redirect:/404";	//没有审核任务
			} else {
				ObjectMapper mapper = new ObjectMapper();
				//找到属性
				try
				{
					@SuppressWarnings("unchecked")
					Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
					Map<String,String> parameters = new HashMap<String,String>();
					for (Entry<String, String[]> entry : entrySet)
					{
						if(entry.getKey().startsWith("CMS_PROPERTY_")) {
							parameters.put(entry.getKey(), entry.getValue()[0]);
						}
					}
					ci.setPropertiesData(mapper.writeValueAsString(parameters));
					
				}catch(JsonGenerationException e){
					
				}catch(JsonMappingException e){
					
				}catch(IOException e){
					
				}
				
				ciService.saveByAuditTask(ci,at);
				
				return "redirect:/account-life-cycle/audit-task/"+at.getId();
			}
		} else {
			return "redirect:/403";				//没有权限
		}

	}
	
	@RequestMapping(value="/audit-task/pass", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> auditTaskPass(@RequestParam(required=true) Long[] ids, Authentication auth) throws Exception {
		SysUser currentUser = userUtil.getUserByAuth(auth);
		Map<String, Object> map = new HashMap<>();
		if(userUtil.isRolesWithUser(currentUser, _accountMaster)) {
			map.put("flag", true);
			//判断当前是否有审核任务
			AuditTask at = auditTaskService.findUnfinishedTask(currentUser);
			
			if(at == null) { 
				map.put("flag", true);
				return map;
			}else {
				boolean b = true;
				List<Ci> ciList = ciService.getByIds(Arrays.asList(ids)).getResult();
				if(ciList.size()==0) {
					map.put("flag", true);
					return map;
				}
				for(Ci ci : ciList) {
					AuditTask at0 = auditTaskService.findUnfinishedTask(groupService.getById(Long.valueOf(ci.getDepartmentInUse())), categoryService.getByCode(ci.getCategoryCode()));
					
					if(at0!=at){
						b = false;
						break;
					}
				}
				
				if(!b) {
					map.put("flag", true);
				} else {
					ciService.passCis(at,ids);
					map.put("flag", true);
				}
			}
		}
		return map;
	}
	
//	@RequestMapping(value="/audit-task/pass", method=RequestMethod.GET)
//	public String auditTaskPass(@RequestParam(required=true) long ciid,@RequestParam(required=true) boolean flag, Authentication auth) throws Exception {
//		Ci ci = ciService.getById(ciid);
//		if(ci ==null ) return "redirect:/404";
//		
//		//首先判断是否有审核任务
//		AuditTask at = auditTaskService.findUnfinishedTask(groupService.getById(Long.valueOf(ci.getDepartmentInUse())),categoryService.getByCode(ci.getCategoryCode()));
//		
//		if(at == null) return "redirect:/404";
//		
//		ciService.passCi(ci, at, flag);
//		
//		return "redirect:/account-life-cycle/index";
//	}
	
	@RequestMapping(value = "/audit-task/del/{ciid}", method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, Object> auditTaskDel(@PathVariable Long ciid, HttpServletRequest request, Model model,Authentication auth) throws Exception {
		SysUser currentUser = userUtil.getUserByAuth(auth);
		Map<String, Object> map = new HashMap<>();
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			Ci ci = ciService.getById(ciid);
			if(ci==null) {
				
			} else {
				if(ci.getDepartmentInUse().equals(String.valueOf(currentUser.getGroup().getId()))) {
					AuditTask at= auditTaskService.findUnfinishedTask(currentUser.getGroup(), categoryService.getByCode(ci.getCategoryCode()));
					if(at==null) {
						
					} else {
						ciService.delByAuditTask(ci,at);
						map.put("flag", true);
					}
				}
			}
		}
		
		return map;
	}
	
	@RequestMapping(value = "/audit-task/recover/{ciid}", method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, Object> auditTaskRecover(@PathVariable Long ciid, HttpServletRequest request, Model model,Authentication auth) throws Exception {
		SysUser currentUser = userUtil.getUserByAuth(auth);
		Map<String, Object> map = new HashMap<>();
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			Ci ci = ciService.getById(ciid);
			if(ci==null) {
				
			} else {
				if(ci.getDepartmentInUse().equals(String.valueOf(currentUser.getGroup().getId()))) {
					AuditTask at= auditTaskService.findUnfinishedTask(currentUser.getGroup(), categoryService.getByCode(ci.getCategoryCode()));
					if(at==null) {
						
					} else {
						ciService.recoverByAuditTask(ci,at);
						map.put("flag", true);
					}
				}
			}
		}
		
		return map;
	}
	
	@RequestMapping(value = "/audit-task/update", method = RequestMethod.POST)
	public String auditTaskUpdate(@Valid @ModelAttribute("ci") Ci ci, HttpServletRequest request, Model model,Authentication auth) throws Exception {
		SysUser currentUser = userUtil.getUserByAuth(auth);
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			//首先判断是否有审核任务
			AuditTask at = auditTaskService.findUnfinishedTask(currentUser.getGroup(),categoryService.getByCode(ci.getCategoryCode()));
			
			if(at==null) return "redirect:/404";	//没有审核任务
				ObjectMapper mapper = new ObjectMapper();
				//找到属性
				try
				{
					@SuppressWarnings("unchecked")
					Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
					Map<String,String> parameters = new HashMap<String,String>();
					for (Entry<String, String[]> entry : entrySet)
					{
						if(entry.getKey().startsWith("CMS_PROPERTY_")) {
							parameters.put(entry.getKey(), entry.getValue()[0]);
						}
					}
					ci.setPropertiesData(mapper.writeValueAsString(parameters));
					
				}catch(JsonGenerationException e){
					
				}catch(JsonMappingException e){
					
				}catch(IOException e){
					
				}
				ciService.updateByAuditTask(ci,at);
				return "redirect:/account-life-cycle/audit-task/"+at.getId();
		} else {
			return "redirect:/403";				//没有权限
		}

	}
	
	@RequestMapping(value = "/audit-task/{id}", method = RequestMethod.GET)
	public String auditTaskDetails(@PathVariable Long id,Model model,Authentication auth) throws Exception {
		//判断有没有权限进入
		SysUser currentUser = userUtil.getUserByAuth(auth);
		
		AuditTask at= auditTaskService.getById(id);
		
		if(at==null)
			throw new Exception("没有这个任务，参数错误 ： "+id);
		
		if(at.getEndTime() != null)
			throw new Exception("任务已结束");
		boolean isAccountMaster = false, isAccountSub = false;
		if(userUtil.isRolesWithUser(currentUser, _accountMaster)){
			if(at.getAssessor() == currentUser) {
				model.addAttribute("at", at);
			} else {
				throw new Exception("此用户没有这个任务");
			}
			isAccountMaster = true;
			//获取部门
			Set<Map<String, Object>> setGroup = new HashSet<>();
			for(TaskCategoryDepartmentRelation relation : at.getRelationSet()) {
				Map<String, Object> mapGroup = new HashMap<>();
				
				Group group = relation.getCiDepartment();
				mapGroup.put("id", group.getId());
				mapGroup.put("groupName", group.getGroupName());
				setGroup.add(mapGroup);
				
			}
			model.addAttribute("groups", setGroup);
			model.addAttribute("groupId", 0);
		} else if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			boolean isTask = false;
			int departmentAuditStatus = 0;	//部门的审核状态
			for(TaskCategoryDepartmentRelation relation : at.getRelationSet()) {
				if(relation.getCiDepartment() == currentUser.getGroup()){
					departmentAuditStatus = relation.getStatus();
					isTask = true;
					break;
				}
			}
			if(isTask) {
				model.addAttribute("at", at);
				model.addAttribute("departmentAuditStatus",departmentAuditStatus);
			} else
				throw new Exception("此用户没有这个任务");
			isAccountSub = true;
			Set<Category> categorySet = new HashSet<>();
			for(TaskCategoryDepartmentRelation relation : at.getRelationSet()) {
				categorySet.add(relation.getCiCategory());
			}
			model.addAttribute("categorys", categorySet);
			model.addAttribute("groupId", currentUser.getGroup().getId());
		} else 
			throw new Exception("没有权限");
		
		model.addAttribute("isAccountMaster", isAccountMaster);
		model.addAttribute("isAccountSub", isAccountSub);
		
		return "account-life-cycle/audit-task-details";
	}
	
	@RequestMapping(value = "/audit-task/index", method = RequestMethod.GET)
	public String auditTaskIndexHtml(Model model,Authentication auth) {
		SysUser currentUser = userUtil.getUserByAuth(auth);
		boolean isAccountMaster = false, isAccountSub = false;
		if(userUtil.isRolesWithUser(currentUser, _accountMaster)) {
			//将部门和分类传入
			model.addAttribute("topGroup", userUtil.getTopGroup(currentUser.getGroup()));
			model.addAttribute("accountTypes", accountService.getAll());
			isAccountMaster = true;
			
			//查找本人是否有任务
			if(auditTaskService.findUnfinishedTask(currentUser)==null)
				model.addAttribute("nonTask", true);
			else
				model.addAttribute("nonTask", false);
		} 
		model.addAttribute("isAccountMaster", isAccountMaster);
		
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			isAccountSub = true;
			
		} 
		model.addAttribute("isAccountSub", isAccountSub);
		
	
		
		return "account-life-cycle/audit-task-index-html";
	}
	
	@RequestMapping(value = "/get-aduit-task-list", method = RequestMethod.GET)
	@ResponseBody 
	public String getAuditTaskList(@RequestParam(required=true) String aoData,Authentication auth) throws Exception {
		JSONArray jsonarray = new JSONArray(aoData);
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
	    
	    for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  {
	            sEcho = obj.get("value").toString();  
	        } else if (obj.get("name").equals("iDisplayStart")) {  
	            iDisplayStart = obj.getInt("value");  
	        } else if (obj.get("name").equals("iDisplayLength")) {  
	            iDisplayLength = obj.getInt("value");  
	        }  
	    } 
		SysUser currentUser = userUtil.getUserByAuth(auth);
		Map<String, Object> map = new HashMap<>();
		if(currentUser == null) {
			map.put("status", false);
			map.put("msg", "当前没有用户");
		}
		
		List<Map<String, Object>> list = auditTaskService.getListByUser(currentUser,iDisplayStart,iDisplayLength);
		
		 JSONObject getObj = new JSONObject();
			
			getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
		    getObj.put("iTotalRecords", list.size());//实际的行数
		    getObj.put("iTotalDisplayRecords", list.size());//显示的行数,这个要和上面写的一样
		    
		    getObj.put("aaData", list);//要以JSON格式返回
		    
			return getObj.toString();
	}
	
	@RequestMapping(value = "/audit-task/history", method = RequestMethod.GET)
	public String auditTaskHistory(@RequestParam(required=true) long at,Model model,Authentication auth) throws Exception {
		AuditTask auditTask = auditTaskService.getById(at);
		
		if(auditTask == null){
			STREAM_OPERATE_LOG.debug(this.getClass().getName()+"查找审核任务时出错，id="+at);
			throw new Exception("没有这个审核记录，id="+at);
		}
		
		
		SysUser currentUser = userUtil.getUserByAuth(auth);
		boolean isAccountMaster = false, isAccountSub = false;
		if(userUtil.isRolesWithUser(currentUser, _accountMaster)){
			if(auditTask.getAssessor() == currentUser) {
				model.addAttribute("at", auditTask);
			} else {
				throw new Exception("此用户没有这个任务");
			}
			isAccountMaster = true;
			//获取部门
			Set<Map<String, Object>> setGroup = new HashSet<>();
			for(TaskCategoryDepartmentRelation relation : auditTask.getRelationSet()) {
				Map<String, Object> mapGroup = new HashMap<>();
				
				Group group = relation.getCiDepartment();
				mapGroup.put("id", group.getId());
				mapGroup.put("groupName", group.getGroupName());
				setGroup.add(mapGroup);
				
			}
			model.addAttribute("groups", setGroup);
			model.addAttribute("groupId", 0);
		} else if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			boolean isTask = false;
			for(TaskCategoryDepartmentRelation relation : auditTask.getRelationSet()) {
				if(relation.getCiDepartment() == currentUser.getGroup()){
					isTask = true;
					break;
				}
			}
			if(isTask) {
				model.addAttribute("at", auditTask);
			} else
				throw new Exception("此用户没有这个任务");
			isAccountSub = true;
			Set<Category> categorySet = new HashSet<>();
			for(TaskCategoryDepartmentRelation relation : auditTask.getRelationSet()) {
				categorySet.add(relation.getCiCategory());
			}
			model.addAttribute("categorys", categorySet);
			model.addAttribute("groupId", currentUser.getGroup().getId());
		} else 
			throw new Exception("没有权限");
		
		model.addAttribute("isAccountMaster", isAccountMaster);
		model.addAttribute("isAccountSub", isAccountSub);
		
		return "account-life-cycle/audit-task-history";
	}
	
	@RequestMapping(value = "/audit-task/init-update-html", method = RequestMethod.GET)
	public String auditTaskInitUpdate(@RequestParam(required=true) long ciid,Model model,Authentication auth) {
		Ci ci = ciService.getById(ciid);
		if(ci ==null ) return "redirect:/404";
		Category category = categoryService.getByCode(ci.getCategoryCode());
		//if(category == null) return "redirect:/404";		
		SysUser currentUser = userUtil.getUserByAuth(auth);
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {			//只有部门台账负责人有权限
			Group group = currentUser.getGroup();
			AuditTask auditTask = auditTaskService.findUnfinishedTask(group);
			//查看是否有审核任务
			if(auditTask != null) {
				//设置group
				model.addAttribute("group", group);
				
				model.addAttribute("category", category);
				model.addAttribute("properties", getHtmlMapByCategory(category.getCategoryCode()));
				model.addAttribute("ci", ci);
				model.addAttribute("status", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.status")).getResult());
				model.addAttribute("securityLevel", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.securitylevel")).getResult());
				model.addAttribute("users", group.getUsers());
				
				return "account-life-cycle/audit-task-update";
			}
		}
		return "redirect:/404";
	}
	
	@RequestMapping(value = "/audit-task/init-create-html", method = RequestMethod.POST)
	public String auditTaskInitCreate(@RequestParam(required=true) String code,Model model,Authentication auth) {
		Category category = categoryService.getByCode(code);
		if(category == null) return "redirect:/404";		
		SysUser currentUser = userUtil.getUserByAuth(auth);
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {			//只有部门台账负责人有权限
			Group group = currentUser.getGroup();
			AuditTask auditTask = auditTaskService.findUnfinishedTask(group);
			//查看是否有审核任务
			if(auditTask != null) {
				//设置group
				model.addAttribute("group", group);
				
				model.addAttribute("category", category);
				model.addAttribute("properties", getHtmlMapByCategory(category.getCategoryCode()));
				model.addAttribute("ci", new Ci());
				model.addAttribute("status", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.status")).getResult());
				model.addAttribute("securityLevel", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.securitylevel")).getResult());
				model.addAttribute("users", group.getUsers());
				
				return "account-life-cycle/audit-task-create";
			}
		}
		return "redirect:/404";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model,Authentication auth) {
		Map<String, List<Account>> map = new HashMap<>();
		//根据权限获取相应的CI列表
		for(AccountType at : AccountType.values()) {
			map.put(at.getName(), accountService.getListByType(at));
		}
		
		SysUser currentUser = userUtil.getUserByAuth(auth);
		boolean isAccountMaster = false, isAccountSub = false;
		if(userUtil.isRolesWithUser(currentUser, _accountMaster)) {
			//将部门和分类传入
			model.addAttribute("topGroup", userUtil.getTopGroup(currentUser.getGroup()));
			model.addAttribute("accountTypes", accountService.getAll());
			isAccountMaster = true;
		} 
		model.addAttribute("isAccountMaster", isAccountMaster);
		
		if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			isAccountSub = true;
		} 
		model.addAttribute("isAccountSub", isAccountSub);
		
		if(isAccountMaster) { 
			//查找本人是否有任务
			if(auditTaskService.findUnfinishedTask(currentUser)==null)
				model.addAttribute("nonTask", true);
			else
				model.addAttribute("nonTask", false);
		}
		
		if(isAccountSub) {			//查找部门下是否有为完成的审核任务
			AuditTask at = auditTaskService.findUnfinishedTask(currentUser.getGroup());
			if(at==null) {
				model.addAttribute("nonTask", true);
			}
			else {
				model.addAttribute("nonTask", false);
				//将类别
				Set<Category> categorySet = new HashSet<>();
				for(TaskCategoryDepartmentRelation relation : at.getRelationSet()) {
					categorySet.add(relation.getCiCategory());
				}
				model.addAttribute("categorys", categorySet);
			}
		}
		
		model.addAttribute("types", map);
		
		return "account-life-cycle/audit-task-index";
	}
	
	@RequestMapping(value="/get-accounts-by-group",method = RequestMethod.GET)
	@ResponseBody
	public String getAuditTaskListCiByGroup(@RequestParam(required=true) String aoData,Authentication auth) throws Exception{
		SysUser currentUser = userUtil.getUserByAuth(auth);
		boolean isAccountMaster = false;//, isAccountSub = false;
		if(userUtil.isRolesWithUser(currentUser, _accountMaster)) {
			isAccountMaster = true;
		} else if(userUtil.isRolesWithUser(currentUser, _accountSub)) {
			//isAccountSub = true;
		} else {
			throw new Exception("没有权限");
		}
		JSONArray jsonarray = new JSONArray(aoData);
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
	    Long groupId = 0L;			//部门
	    Long atId = 0L;
	    
	    
	    for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  {
	            sEcho = obj.get("value").toString();  
	        } else if (obj.get("name").equals("iDisplayStart")) {  
	            iDisplayStart = obj.getInt("value");  
	        } else if (obj.get("name").equals("iDisplayLength")) {  
	            iDisplayLength = obj.getInt("value");  
	        } else if (obj.get("name").equals("groupId")) {  
	        	groupId = obj.getLong("value");  
	        } else if (obj.get("name").equals("atId")) {  
	        	atId = obj.getLong("value");  
	        } 
	    } 
	    
	    AuditTask at = auditTaskService.getById(atId);
	    
	    if(at==null) throw new Exception("参数错误，id"+atId);
	    
	    
	    	JSONObject getObj = new JSONObject();
	    	//找出逻辑
	    	Set<String> groupIdSet = new HashSet<>();
	    	Set<String> codeSet = new HashSet<>();
	    	Map<String,Integer> groupStatusMap = new HashMap<>();
	    	for(TaskCategoryDepartmentRelation relation : at.getRelationSet()) {
				String groupIdStr = String.valueOf(relation.getCiDepartment().getId());
				String categoryCode = relation.getCiCategory().getCategoryCode();
				groupIdSet.add(groupIdStr);
				codeSet.add(categoryCode);
				groupStatusMap.put(groupIdStr+"-"+categoryCode, relation.getStatus());
	    	}

	    		if(groupId == 0 && isAccountMaster) {
	    			if(at.getAssessor() != currentUser)
	    				 throw new Exception("没有权限");
	    		} else {		//按部门查
	    			Group group = new Group();
	    			if(isAccountMaster) {
	    				group = groupService.getById(groupId);
	    			} else {
	    				group = currentUser.getGroup();
	    			}
	    			if(groupIdSet.contains(group.getId().toString())) {
	    				groupIdSet.clear();
	    				groupIdSet.add(group.getId().toString());
	    			} else {
	    				throw new Exception("没有权限");
	    			}
	    		}
	    	

	    	SearchResult<Ci> result = auditTaskService.getCiList(groupIdSet,codeSet,iDisplayStart, iDisplayLength);
	    	
	    	List<Map<String, Object>> resultList = auditTaskService.getCiMapList(at, result.getResult());

	    	getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
		    getObj.put("iTotalRecords", result.getTotalCount());//实际的行数
		    getObj.put("iTotalDisplayRecords", result.getTotalCount());//显示的行数,这个要和上面写的一样
		    
		    getObj.put("aaData", resultList);//要以JSON格式返回
		    
			return getObj.toString();
	   
		
	}
	
	@RequestMapping(value="/get-all",method = RequestMethod.GET)
	@ResponseBody
	public String getListByCategoryCode(@RequestParam(required=true) String aoData,Authentication auth) throws JsonParseException, JsonMappingException, IOException{
		JSONArray jsonarray = new JSONArray(aoData);
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
	    String categoryCode = "0";			//部门
	    
	    for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  {
	            sEcho = obj.get("value").toString();  
	        } else if (obj.get("name").equals("iDisplayStart")) {  
	            iDisplayStart = obj.getInt("value");  
	        } else if (obj.get("name").equals("iDisplayLength")) {  
	            iDisplayLength = obj.getInt("value");  
	        }  
	        else if (obj.get("name").equals("categoryCode")) {  
	        	categoryCode = obj.getString("value");  
	        } 
	    } 
	    //获取当前用户的角色
	    
	    SearchResult<Ci> result = ciService.getListByUserAndType(userUtil.getUserByAuth(auth), categoryCode,iDisplayStart, iDisplayLength);
	    
	    JSONObject getObj = new JSONObject();
		
		getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", result.getTotalCount());//实际的行数
	    getObj.put("iTotalDisplayRecords", result.getTotalCount());//显示的行数,这个要和上面写的一样
	    
	    getObj.put("aaData", result.getResult());//要以JSON格式返回
	    
		return getObj.toString();
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
		model.addAttribute("users", userService.getAllByCondition(true, true));
		
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
			//属性初始化，都为空
			String code = ci.getCategoryCode();
			String tmpcode = code.substring(0,2);
			Map<String,String> map = new LinkedHashMap<>();
			while(tmpcode.length()<=code.length())
			{
				//根据code分级判断
				Category category = categoryService.getByCode(tmpcode);
				//
				for(Property p : category.getProperties()) {
					//htmlCodes.add(p.getPropertyName()+"-"+propertyService.analyzePropertyToHtml(p));
					map.put(p.getPropertyId(), "");
				}
				
				if(tmpcode.length()+2>code.length())
					break;
				tmpcode = code.substring(0,tmpcode.length()+2);
			}
			
			ObjectMapper mapper = new ObjectMapper();
			ci.setPropertiesData(mapper.writeValueAsString(map));
			//--属性初始化完毕
			
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
		
		model.addAttribute("properties", getHtmlMapByCategory(ci.getCategoryCode()));
		return "account-life-cycle/ci-addproperty";
	}
	
	/**
	 * 根据分类代码生成html
	 * @param code
	 * @return
	 */
	private Map<String, Object> getHtmlMapByCategory(String code) {
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
		
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ci/saveproperty/{id}",method=RequestMethod.POST)
	public String saveProperty(@PathVariable("id") long id,HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{	
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
				case 6://重装操作系统
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
		List<ChangeItem> list = changeitemService.getByChangeId(Long.valueOf(task.getProcessInstanceId()),ChangeitemType.change);
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

		map.put("items", changeitemService.getByChangeId(Long.valueOf(processInstanceId), ChangeitemType.change));

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
