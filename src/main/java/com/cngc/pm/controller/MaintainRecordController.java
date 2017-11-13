package com.cngc.pm.controller;

import static com.cngc.utils.Common.getRemortIP;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.MaintainRecord;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.MaintainRecordService;
import com.cngc.pm.service.RoleService;
import com.cngc.pm.service.cms.CiService;
import com.googlecode.genericdao.search.SearchResult;


@Controller
@RequestMapping(value = "/maintain-record")
public class MaintainRecordController {

	@Resource
	private MaintainRecordService mrService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private AttachService attachService;
	@Resource
	private RoleService roleService;
	@Resource
	private CiService ciService;
	
	@RequestMapping
	public String list(Model model) throws Exception {
		//根据角色
//		List<MaintainRecord> list = new ArrayList<>();
//		SysUser currentUser = userUtil.getUserByAuth(authentication);
//		//String username = userUtil.getUsernameByAuth(authentication);
//		
//		
//		if(userUtil.IsLeader(authentication)) {
//			list = mrService.getAllRecords();
//		} else {
//			String[] usernames;
//			//三员角色
//			if(userUtil.isRolesWithUser(currentUser, "safety_manager")){		//安全管理员可以看系统管理员和审计员的		
//				Role role = roleService.getByName("safety_manager");
//				usernames = new String[role.getUserRoles().size()];
//				int i = 0;
//				for(UserRole ur :role.getUserRoles()) {
//					usernames[i] = ur.getUser().getUsername();
//					i++;
//				}
//				
//			} else if(userUtil.isRolesWithUser(currentUser, "comptoller")) {	//审计员可以看安全管理员的
//				Role role = roleService.getByName("comptoller");
//				usernames = new String[role.getUserRoles().size()];
//				int i = 0;
//				for(UserRole ur :role.getUserRoles()) {
//					usernames[i] = ur.getUser().getUsername();
//					i++;
//				}
//			} else {//如果是系统管理员，只能看自己的
//				usernames = new String[1];
//				usernames[0] = currentUser.getUsername();
//				//list = mrService.getRecordsByUsername(currentUser.getUsername());
//			}
//			list = mrService.getRecordListByusernames(usernames);
//		}
//		
//		model.addAttribute("list", list);
//		model.addAttribute("maintainRecord", new MaintainRecord());
		return "maintain-record/list";
	}
	
	@RequestMapping(value="/maintainrecord-list-with-table",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String maintainListWithTable(@RequestParam(required=true) String aoData, Authentication authentication) throws ParseException {
		JSONArray jsonarray = new JSONArray(aoData);
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
	    String startTime = "";
	    String endTime = "";
	    //String findUserId = "";			//userid
	    String equipName = "0";
	    String type_ = "";
	   
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
	        } else if (obj.get("name").equals("type_")) {  
	        	type_ = obj.getString("value");  
	        } else if (obj.get("name").equals("equipname")) {  
	        	equipName = obj.getString("value");  
	        } 
	    } 
	    
	    //获取当前用户，分析权限
	    SysUser currentUser = userUtil.getUserByAuth(authentication);
	  	
//	  	boolean isAllUser = false;
	  	
//	  	if(userUtil.IsLeader(authentication)) isAllUser = true;
	  	
	  	SearchResult<MaintainRecord> result = mrService.search(currentUser,equipName, startTime, endTime, type_, iDisplayStart, iDisplayLength);
	  	
	  	JSONObject getObj = new JSONObject();
		
		getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", result.getTotalCount());//实际的行数
	    getObj.put("iTotalDisplayRecords",  result.getTotalCount());//显示的行数,这个要和上面写的一样
	    
	    getObj.put("aaData", result.getResult());//要以JSON格式返回
	    
		return getObj.toString();
	}
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String add(Model model) throws Exception {
		
		model.addAttribute("maintainRecord", new MaintainRecord());
		return "maintain-record/add";
	}
	
	@RequestMapping(value="/add-by-equip",method = RequestMethod.GET)
	public String addByEquip(@RequestParam(required=true) long equipId,Model model) throws Exception {
		Ci ci = ciService.getById(equipId);
		MaintainRecord mr = new MaintainRecord();
		mr.setEquipId(ci.getId().toString());
		mr.setEquipName(ci.getName());
		mr.setEquipNum(ci.getNum());
		model.addAttribute("maintainRecord", mr);
		return "maintain-record/add";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("maintainRecord") MaintainRecord maintainRecord, HttpServletRequest request, Authentication authentication) throws Exception {
		//根据角色
		String username = userUtil.getUsernameByAuth(authentication);
		if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
			String attachIds = request.getParameter("fileids");
			
			Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
			
			maintainRecord.setAttachs(attachSet);
		}
		maintainRecord.setExecutor(username);
		maintainRecord.setRole(1);
		mrService.save(maintainRecord,getRemortIP(request));
		
		return "redirect:/maintain-record";
	}
	
	@RequestMapping(value="/search",method = RequestMethod.POST)
	public String search(HttpServletRequest request,Model model) throws Exception {
		String equipName = request.getParameter("equipName");
		String maintainTimeStart = request.getParameter("maintainTimeStart");
		String maintainTimeEnd = request.getParameter("maintainTimeEnd");
		String type = request.getParameter("type");
		
		List<MaintainRecord> list = mrService.search(equipName, maintainTimeStart, maintainTimeEnd, type);
		
		model.addAttribute("list", list);
		return "maintain-record/list";
	}
	
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String search(@RequestParam(required=true) int id, HttpServletRequest request, Authentication authentication) throws Exception {
		String username = userUtil.getUsernameByAuth(authentication);
		mrService.delById(id,username,getRemortIP(request));
		return "redirect:/maintain-record";
	}
	
}
