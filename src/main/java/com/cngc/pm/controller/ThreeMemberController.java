package com.cngc.pm.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.apache.cxf.common.util.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import com.cngc.exception.ResourceNotFoundException;
import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.exception.NotDeleteAuthorityException;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.Style;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.manage.Item;
import com.cngc.pm.model.manage.ManageType;
import com.cngc.pm.model.manage.ManagerForm;
import com.cngc.pm.model.manage.Relations;
import com.cngc.pm.model.manage.Relationship;
import com.cngc.pm.model.manage.WorkRecord;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.GroupService;
import com.cngc.pm.service.ThreeMemberService;
import com.cngc.pm.service.UserService;
import com.cngc.pm.service.cms.CiService;
import com.googlecode.genericdao.search.SearchResult;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Controller
@RequestMapping("/three-member")
public class ThreeMemberController {

	private static final Logger log = LoggerFactory.getLogger(ThreeMemberController.class);
	@Resource
	ThreeMemberService tmService;
	@Resource
	private HistoryService historyService;
	@Resource
	private UserService userService;
	@Resource
	private TaskService taskService;
	@Resource
	private CiService ciService;
	@Resource
	private GroupService groupService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private AttachService attachService;
	
	/**
	 * 删除workrecord根据id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "workrecord/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deletePostById(@PathVariable("id") Long id) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  

	  	SysUser currentUser = userService.getByUsername(userDetails.getUsername());
	  	
        log.debug("delete workrecord by id @" + id);

        try {
			tmService.deleteWorkrecordById(id, currentUser);
		} catch (ResourceNotFoundException re) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("Don't have the resources", HttpStatus.NOT_FOUND);
		} catch(NotDeleteAuthorityException ne) {
			return new ResponseEntity<>("Didn't remove permissions", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		}
		
        return new ResponseEntity<>("Deleting completed", HttpStatus.NO_CONTENT);
    }
	
	/**
	 * 进入三员工作记录页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/workrecord-manage")
	public String workrecordManage(Model model) {
		//当前用户可以操作的CMDB
		//获取当前用户，分析权限
	  	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  

	  	SysUser currentUser = userService.getByUsername(userDetails.getUsername());
	  	
	  	List<Relationship> rsList = tmService.getRelationshipListByUser(currentUser);
	  	
	  	model.addAttribute("relationshipList", rsList);
	  	
		return "threemember/workrecord-manage";
	}
	
	@RequestMapping(value="/get-action-by-item-and-mt")
	@ResponseBody
	public List<Relations> getActionByItemAndMT(@RequestParam(required=true) int itemId,@RequestParam(required=true) int mt) {
		
		return tmService.getRelationListByItemAndMt(itemId,mt);
		
	}
	
	@RequestMapping(value="/get-mt-and-item-by-cmdb")
	@ResponseBody
	public String getMtByCmdb(@RequestParam(required=true) int cmdbId) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();  

	  	SysUser currentUser = userService.getByUsername(userDetails.getUsername());
		ManageType mt = tmService.getMtByCmdb(currentUser, cmdbId);
		
		List<Relations> list = tmService.getRelationItemListByType(mt);
		String jsonStr = "{\"id\":\""+mt.getValue()+"\",\"desc\":\""+mt.getDesc()+"\"";
		
		if(list!=null && list.size()>0) {
			jsonStr += ",\"items\":[";
			for(Relations r : list) {
				jsonStr += "{\"itemid\":\""+r.getItem().getId()+"\",\"itemname\":\""+r.getItem().getName()+"\"},";
			}
			jsonStr = jsonStr.substring(0, jsonStr.length()-1);
			jsonStr += "]";
		}
		
		jsonStr += "}";
		
		return jsonStr;
	}
	
	@RequestMapping(value="/save-workrecord",method=RequestMethod.POST)
	@ResponseBody
	public String saveWorkrecord(HttpServletRequest request) throws ParseException {
//		
//		@SuppressWarnings("unchecked")
//		Map<String,String[]> map=request.getParameterMap();  
//		
//		java.util.Iterator<Entry<String, String[]>> iter = map.entrySet().iterator();
//		
//		while(iter.hasNext()) {
//			Entry<String, String[]> entry = iter.next();
//			
//		  
//	    	System.out.println(entry.getKey() + " -- " +entry.getValue()[0]);  
//			
//		}
		String detail = HtmlUtils.htmlEscape(request.getParameter("detail"));	//详细信息
		
		String basis = HtmlUtils.htmlEscape(request.getParameter("basis"));	//详细信息
		
		String cmdbid = request.getParameter("cmdbin");					//产品id
		
		String typeid = request.getParameter("typeid");					//三员角色
		
		String itemid = request.getParameter("item_");					//操作项
		
		String actionid = request.getParameter("action_");				//操作动作
		
		String inTime = request.getParameter("inTime");					//时间
		
		ManageType mt = ManageType.get(Integer.parseInt(typeid));
		//获得relations
		Relations r = tmService.getRelationOne(mt, Integer.parseInt(actionid),Integer.parseInt(itemid) );
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();  

	  	SysUser currentUser = userService.getByUsername(userDetails.getUsername());
	  	
		Relationship rs = tmService.getRelationshipOne(mt, currentUser, Integer.parseInt(cmdbid));
		
		WorkRecord wr = new WorkRecord();
		
		if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
			String attachIds = request.getParameter("fileids");
			
			Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
			
			wr.setAttachs(attachSet);
		}
		
		wr.setAuth(rs);
		wr.setBasis(basis);
		wr.setDetails(detail);
		wr.setRelation(r);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		wr.setRecordTime(sdf.parse(inTime));
		
		tmService.saveWorkrecord(wr);
		
		return "{\"OK\":\"true\"}";
	}
	
	@RequestMapping(value="/relationship-index")
	public String relationshipListPage(Model model) {
		
		return "threemember/relationship-list";
	}
	
	@RequestMapping(value="/workrecord-with-table",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String workrecordListWithJqueryTable(@RequestParam(required=true) String aoData,HttpServletRequest request) throws ParseException {
		JSONArray jsonarray = new JSONArray(aoData);
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
	    String startTime = "";
	    String endTime = "";
	    String findUserId = "";			//userid
	    String cmdbid = "0";
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
	        } else if (obj.get("name").equals("userid")) {  
	        	findUserId = obj.getString("value");  
	        } else if (obj.get("name").equals("type_")) {  
	        	type_ = obj.getString("value");  
	        } else if (obj.get("name").equals("cmdbid")) {  
	        	cmdbid = obj.getString("value");  
	        } 
	    } 
	    
	    //获取当前用户，分析权限
	  	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();  

	  	SysUser currentUser = userService.getByUsername(userDetails.getUsername());
	  	//如果只是普通三员就只能查询自己的
	  	SysUser searchUser = null;		//如果最终计算为null，则表示查询所有
	  	if(userUtil.IsEngineer(currentUser)) {
	  		if(!"".equals(findUserId)) {
	  			searchUser = userService.getById(Long.parseLong(findUserId));
	  		}
	  	} else {
	  		searchUser = currentUser;
	  	}
	  	
	  	//获取查询的角色
	  	ManageType mt = null;		//三员角色
		
		switch(type_) {
		case "1" :
			mt = ManageType.SystemManager;
			break;
		case "2" :
			mt = ManageType.SecurityManager;
			break;
		case "3" :
			mt = ManageType.SecurityComptroller;
			break;
		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//确定时间
		Date startTime_ = null, endTime_ = null;
    	if(!("").equals(startTime)) {
  	    	//hpq.startedAfter(sdf.parse(startTime + " 00:00"));
  	    	startTime_ = sdf.parse(startTime + " 00:00");
  	    }
  	    if(!("").equals(endTime)) {
  	    	//hpq.startedBefore(sdf.parse(endTime + " 23:59"));
  	    	endTime_ = sdf.parse(endTime + " 23:59");
  	    }
	    
  	    Ci searchCmdb = null;
		if(!cmdbid.equals("0")){
			searchCmdb = ciService.getById(Long.valueOf(cmdbid));
		}
		
		SearchResult<WorkRecord> result = tmService.getWorkrecord(searchUser, mt, searchCmdb, startTime_, endTime_, iDisplayStart, iDisplayLength);
  	    
		JSONObject getObj = new JSONObject();
		
		getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", result.getTotalCount());//实际的行数
	    getObj.put("iTotalDisplayRecords",  result.getTotalCount());//显示的行数,这个要和上面写的一样
	    
	    List<Map<String, String>> mapList = new ArrayList<>();
	    SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
	    //String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
	    String path = request.getContextPath();
	    //System.out.println(path1);
	    for(WorkRecord wr : result.getResult()) {
	    	Map<String, String> map = new HashMap<String, String>();
	    	map.put("id", wr.getId().toString());
	    	map.put("cmdbname", wr.getAuth().getCmdb().getName());
	    	map.put("rolename", wr.getAuth().getRole().getDesc());
	    	map.put("username", wr.getAuth().getUser().getName());
	    	
	    	map.put("itemname", wr.getRelation().getItem().getName());
	    	map.put("actionname", wr.getRelation().getAction().getName());
	    	
	    	
	    	map.put("intime", sdf1.format(wr.getRecordTime()));
	    	
	    	map.put("detail", wr.getDetails());
	    	map.put("basis", wr.getBasis());
	    	//保存文档
	    	String doc= "";
	    	for(Attachment atta : wr.getAttachs()) {
	    		doc += "<a href=\""+path+"/attachment/download/"+atta.getId()+"\">"+atta.getName()+"</a><br/>";
	    	}
	    	map.put("doc", doc);
	    	
	    	mapList.add(map);
	    }
	    
	    getObj.put("aaData", mapList);//要以JSON格式返回
	    
		return getObj.toString();
	}
	
	@RequestMapping(value="/relationship",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String threememberRelationship(@RequestParam(required=true) String aoData) {
		JSONArray jsonarray = new JSONArray(aoData); 
		
		String sEcho = null;  
	    
	    String searchUserId = "";
	    String type = "";
	    String searchCmdbId = "";
	   
	    for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  {
	            sEcho = obj.get("value").toString();  
	        } else if (obj.get("name").equals("userid")) {  
	        	searchUserId = obj.getString("value");  
	        } else if (obj.get("name").equals("type_")) {  
	        	type = obj.getString("value");  
	        } else if (obj.get("name").equals("cmdb")) {  
	        	searchCmdbId = obj.getString("value");  
	        } 
	    } 
		//根据当前用户的权限来决定单位
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();  

		SysUser currentUser = userService.getByUsername(userDetails.getUsername());
		
		SysUser searchUser = null;
		if(searchUserId.length() >0) {
			searchUser = userService.getByUsername(searchUserId);
		}
		
		ManageType mt = null;		//默认是系统管理员
		
		switch(type) {
		case "1" :
			mt = ManageType.SystemManager;
			break;
		case "2" :
			mt = ManageType.SecurityManager;
			break;
		case "3" :
			mt = ManageType.SecurityComptroller;
			break;
		}
		
		Ci searchCmdb = null;
		if(!searchCmdbId.equals("0")){
			searchCmdb = ciService.getById(Long.valueOf(searchCmdbId));
		}
		
		Group unit = null;
		if(!userUtil.IsEngineer(currentUser)) {
			unit = userUtil.getTopGroup(currentUser.getGroup());
		}
		
		List<Relationship> list =  tmService.getRelationshipList(unit, searchCmdb, mt, searchUser);
		
		JSONObject getObj = new JSONObject();
	    
	    getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", list.size());//实际的行数
	    getObj.put("iTotalDisplayRecords",  list.size());//显示的行数,这个要和上面写的一样
	    
	    //结果加工
	    List<Map<String, String>> resultList = new ArrayList<>();
	    
	    for(Relationship r : list) {
	    	Map<String, String> map = new HashMap<>();
	    	
	    	map.put("cmdbname", r.getCmdb().getName());
	    	map.put("cmdbid", r.getCmdb().getId().toString());
	    	map.put("rolename", r.getRole().getDesc());
	    	map.put("createtime", r.getInTime().toString());
	    	map.put("userrealname", r.getUser().getName());
	    	map.put("op", "这里是操作");
	 
	    	resultList.add(map);
	    }
	    
	    getObj.put("aaData", resultList);//要以JSON格式返回
	    
		return getObj.toString();
		
//		if(userUtil.IsEngineer(currentUser))	//如果时系统级用户
//			return tmService.getAllRelationshipList();
//		else {		//按照单位显示
//			return tmService.getRelationshipListByUnit(userUtil.getTopGroup(currentUser.getGroup()));
//		} 
		
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value="/get-cmdb-list")
	@ResponseBody
	public List<Map<String,String>> getCMDBListByUsername(@RequestParam String username) {
		SysUser user = userService.getByUsername(username);
		
		List<Ci> list = ciService.getAll();
		List<Map<String, String>> list1 = new ArrayList<>();
		if(userUtil.IsEngineer(user)) {
			for(Ci ci : list) {
				Map<String, String> map = new HashMap<>();
				
				map.put("id", ci.getId().toString());
				map.put("name", ci.getName());
				
				list1.add(map);
			}
		} else{
			Group unit = userService.getTopGroupByUser(user);		//用户所属单位
			
			for(Ci ci : list) {
				String groupid = ci.getDepartmentInUse();	//cmdb所属部门
				
				Group topGroup = userUtil.getTopGroup(groupService.getById(Long.valueOf(groupid)));
				
				if(topGroup.getId() == unit.getId()) {
					Map<String, String> map = new HashMap<>();
					
					map.put("id", ci.getId().toString());
					map.put("name", ci.getName());
					
					list1.add(map);
				}
			}
		}
		
		return list1;
	}
	
	@RequestMapping(value="/create-threemember",method=RequestMethod.GET)
	public String initCreateThreemember(Model model) {
		
//		model.addAttribute("relationship", new Relationship());
		
		return "threemember/create-threemember";
	}
	
	@RequestMapping(value="/save-relationship",method=RequestMethod.POST)
	public ResponseEntity<String> saveRelationship(HttpServletRequest request) throws UnsupportedEncodingException {
		
		@SuppressWarnings("unchecked")
		Map<String,String[]> map=request.getParameterMap();  
		
		java.util.Iterator<Entry<String, String[]>> iter = map.entrySet().iterator();
		
		String userid = "", roleid = "";
		String[] cmdbids = {};
		while(iter.hasNext()) {
			Entry<String, String[]> entry = iter.next();
			
			if(entry.getKey().equals("userid")) {  
	            userid = entry.getValue()[0];  
			} else if(entry.getKey().equals("role_")) {
				roleid = entry.getValue()[0];
			} else if(entry.getKey().equals("cmdb")) {
				cmdbids = entry.getValue();
			}
			
		}
		
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text","html",Charset.forName("utf-8"));
		headers.setContentType(mediaType);
		
		
		if(cmdbids ==null || cmdbids.length==0) {
			return new ResponseEntity<String>("{\"OK\":false,\"message\":\"未选择任何CMDB\"}",headers,HttpStatus.OK);
		}
		
		ManageType mt = ManageType.SystemManager;		//默认是系统管理员
		
		switch(roleid) {
		case "1" :
			mt = ManageType.SystemManager;
			break;
		case "2" :
			mt = ManageType.SecurityManager;
			break;
		case "3" :
			mt = ManageType.SecurityComptroller;
			break;
		}
		
		List<Ci> cmdbList = new ArrayList<>();
		
		SysUser selectedUser = userService.getById(Long.valueOf(userid));
		//验证是否重复
		for(String cmdbid : cmdbids) {
			Ci cmdb = ciService.getById(Long.valueOf(cmdbid));
			
			//判断这个cmdb的这个三员角色已经被设置了
			if(tmService.haveRelationshipByCmdbAndRole(cmdb, mt)) {
//				String urlStr = URLEncoder.encode(cmdb.getName()+"的此三员角色已经被设置","UTF-8");
//				System.out.println(urlStr);
				return new ResponseEntity<String>("{\"OK\":false,\"message\":\"'"+cmdb.getName()+"' 的三员角色已经被设置\"}",headers,HttpStatus.OK);
			}
			
			//判断被选择人已经是这个设备的三员之一了
			if(tmService.haveRelationshipByCmdbAndUser(cmdb, selectedUser)) {
				
				return new ResponseEntity<String>("{\"OK\":false,\"message\":\"'"+selectedUser.getUsername()+"' 已经是 '"+cmdb.getName()+"' 的三员角色了\"}",headers,HttpStatus.OK);
			}
			
			cmdbList.add(cmdb);
		}
		
		//验证过后，保存
		tmService.saveRelationshipWithBatch(selectedUser, mt, cmdbList);
		
		return new ResponseEntity<String>("{\"OK\":true,\"message\":\"save completed\"}",headers,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/my",method=RequestMethod.GET)
	public String myList() {
		//根据自己的权限进入相应的页面
		
		return "threemember/my-list";
	}
	
	@RequestMapping(value="/get-system-tree")
	@ResponseBody
	public String getSystemTree()
	{
		Style styleRoot = tmService.getSystem();
		
		String strJson = "[{";
		
		strJson += "\"text\":\""+styleRoot.getName()+"\"";
		
		if(styleRoot.getChild().size()>0) {
			strJson+=", \"collapsed\":true,\"children\":[";
			
			for(Style style : styleRoot.getChild()) {
				strJson += "{\"text\":\""+style.getName()+"\"},";
			}
			
			strJson = strJson.substring(0, strJson.length()-1);
			strJson +="]";
		}
		
		strJson += "}]";
		
		return strJson;
	}
	
	@RequestMapping(value = "/list/{manageType}",method=RequestMethod.GET)
	public String list(@PathVariable("manageType") String mt, Model model) {
		model.addAttribute("engineers", userService.getThreemembers());
		
		int type = 0;
		switch(mt) { 
		case "system-manager":
			type = 1;
			break;
		case "security-manager" :
			type = 2;
			break;
		case "security-comptroller":
			type = 3;
			break;
		case "bm-system-manager":
			type = 4;
			break;
		case "bm-security-manager":
			type = 5;
			break;
		case "bm-security-comptroller":
			type = 6;
			break;
		case "oa-system-manager":
			type = 7;
			break;
		case "oa-security-manager":
			type = 8;
			break;
		case "oa-security-comptroller":
			type = 9;
			break;
		}
		
		model.addAttribute("type", type);
		
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
	        } else if (obj.get("name").equals("username")) {  
	        	findUsername = obj.getString("value");  
	        } else if (obj.get("name").equals("type_")) {  
	        	type_ = obj.getString("value");  
	        } else if (obj.get("name").equals("search")) {  
	        	isSearch = obj.getString("value");  
	        } 
	    } 
	    
	    String currentUsername = userUtil.getUsernameByAuth(authentication);
	    //判断权限
	    boolean isLeader = false;
	    
	    if(userUtil.IsLeader(authentication))
	    	isLeader = true;
	    
	    HistoricProcessInstanceQuery hpq = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("threeMember");
	    
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		
    	if(isSearch.equals("1") ) {//hpq = hpq.finished();  //如果是搜索
    		//需要查找的人员
        	if(isLeader) {
        		if(!findUsername.equals("00")) {
        	    	hpq = hpq.involvedUser(findUsername);
        	    }
        		if(!type_.equals("0")) {
        			hpq = hpq.variableValueEquals("type", type_);
        		}
        	} else {
        		hpq = hpq.involvedUser(currentUsername);
        		hpq = hpq.variableValueEquals("type", type);
        	}
        	
        	
    	} else {
    		hpq = hpq.involvedUser(currentUsername);			//设置用户
    		hpq = hpq.variableValueEquals("type", type);		//设置类别
    	}
    	
    	if(!("").equals(startTime)) {
  	    	//hpq.startedAfter(sdf.parse(startTime + " 00:00"));
  	    	hpq.variableValueGreaterThanOrEqual("inTime", sdf.parse(startTime + " 00:00"));
  	    }
  	    if(!("").equals(endTime)) {
  	    	//hpq.startedBefore(sdf.parse(endTime + " 23:59"));
  	    	hpq.variableValueLessThanOrEqual("inTime", sdf.parse(endTime + " 23:59"));
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
	    	String inTime = "";
	    	for(HistoricDetail historicDetail : formProperties) {
	   			 HistoricFormProperty	 field = (HistoricFormProperty) historicDetail;
	   			 if(field.getPropertyId().equals("threemember")) {
	   				username = field.getPropertyValue();
	   				
	   			 } else if(field.getPropertyId().equals("inTime")) {
	   				inTime = field.getPropertyValue(); 
	   			 }
	    	}
	    	
	    	map.put("username", userUtil.getNameByUsername(username));
	    	map.put("in_time", inTime);
	    	
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
