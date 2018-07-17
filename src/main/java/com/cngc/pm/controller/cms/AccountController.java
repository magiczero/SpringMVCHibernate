package com.cngc.pm.controller.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.ChangeitemType;
import com.cngc.pm.model.cms.Account;
import com.cngc.pm.model.cms.AccountType;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.ChangeItemService;
import com.cngc.pm.service.GroupService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.UserService;
import com.cngc.pm.service.cms.AccountService;
import com.cngc.pm.service.cms.CategoryService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.pm.service.cms.PropertyService;
import com.cngc.utils.Common;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.SearchResult;

@Controller
@RequestMapping(value="/stats/account")
public class AccountController {
	@Resource
	private CiService ciService;
	@Resource
	private PropertyService propertyService;
	@Resource
	private AccountService accountService;
	@Resource
	private GroupService groupService;
	@Resource
	private UserService userService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private ChangeItemService changeitemService;
	
	//四大类的属性
	//信息系统--编号、名称、型号、密级、用途、所属部门、放置地点、责任人、操作系统安装日期、硬盘序列号、IP地址、MAC地址、启用时间、使用情况
	private static final String _fieldone = "account.type.infosys.filed";
//	private static final String _propertiesone = "account.type.infosys.property";
	//信息设备--编号、名称、型号、密级、用途、所属部门、放置地点、责任人、操作系统安装日期、硬盘序列号、设备序列号、IP地址、启用时间、使用情况
	private static final String _fieldtwo = "account.type.infoequipment.filed";
//	private static final String _propertiestwo = "account.type.infoequipment.property";
	//存储设备--编号、名称、型号、密级、用途、所属部门、放置地点、责任人、序列号、启用时间、使用情况等
	private static final String _fieldthree = "account.type.storage.filed";
//	private static final String _propertiesthree = "account.type.storage.property";
	//安全保密产品--编号、名称、型号、密级、所属部门，生产厂家、检测证书名称和编号、责任人、购置时间、启用时间、使用情况等
	private static final String _fieldfour = "account.type.security.filed";
//	private static final String _propertiesfour = "account.type.security.property";
	//应用系统
	private static final String _fieldfive = "account.type.appsys.filed";
//	private static final String _propertiesfive = "account.type.appsys.property";
	/**添加台账信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String account(Model model,HttpServletRequest request){

		List<Account> accounts = accountService.getAll();
		
		model.addAttribute("accounts", accounts);
		model.addAttribute("fields", propertyService.getFields());
		
		return "stats/account-add";
	}
	
	@RequestMapping
	public String getAccountListByCategoryAndAuth(@RequestParam int category,@RequestParam int secretlevel, Model model,Authentication auth){
//		SysUser currentUser = userUtil.getUserByAuth(auth);
//		String authName = "";
//		List<String> codeList = new ArrayList<>();
			
//		List<Ci> ciList = ciService.getByAuthAndCategory(secretlevel,currentUser,codeList);
			
		List<Account> accounts = accountService.getAll();
		
		model.addAttribute("accounts", accounts);
		model.addAttribute("fields", propertyService.getFields());
		
		return "stats/account-category-list";
	}
	/**保存台账信息
	 * @param category
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(HttpServletRequest request,Authentication authentication) throws Exception{
		
		String code = request.getParameter("category");
		String fields = request.getParameter("fm_fields");
		String properties = request.getParameter("fm_properties");
		String name = request.getParameter("fm_name");
		String type = request.getParameter("type");
		
		Account account = new Account();
		account.setName(name);
		account.setCategory(code);
		account.setFields(fields);
		account.setProperties(properties);
		account.setType(AccountType.get(Integer.valueOf(type)));
		account.setCreatedTime(new Date());
		account.setCreatedUser(userUtil.getUsernameByAuth(authentication));
		
		accountService.save(account);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/detail/{id}",method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id,Model model) throws JsonParseException, JsonMappingException, IOException{
		Ci ci = ciService.getById(id);
		model.addAttribute("ci",ci);
		String code = ci.getCategoryCode();
		String tmpcode = code.substring(0,2);
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		while(tmpcode.length()<=code.length())
		{
			//根据code分级判断
			Category category = categoryService.getByCode(tmpcode);
			map.put(category.getCategoryName(), category.getProperties());
			if(tmpcode.length()+2>code.length())
				break;
			tmpcode = code.substring(0,tmpcode.length()+2);
		}
		
		//获取新建时信息
		List<ChangeItem> list = changeitemService.getByCiAndType(ci.getId(), ChangeitemType.create);
		if(list.size()>0) {
			ChangeItem changeItem = list.get(0);
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String, String> propertymap = mapper.readValue(changeItem.getNewValue(), Map.class);
			//责任人
			String username = propertymap.get("CMS_FIELD_USERINMAINTENANCE");
			propertymap.put("CMS_FIELD_USERINMAINTENANCE", userService.getUserName(username));
			//设备类型
			String categoryCode = propertymap.get("CMS_FIELD_CATEGORYCODE");
			propertymap.put("CMS_FIELD_CATEGORYNAME", categoryService.getByCode(categoryCode).getCategoryName());
			//密级
			String securityLevel = propertymap.get("CMS_FIELD_SECURITYLEVEL");
			propertymap.put("CMS_FIELD_SECURITYLEVELNAME", syscodeService.getCode(securityLevel, "CI_SECURITY_LEVEL").getCodeName());
			
			model.addAttribute("createDetail", propertymap);
		}
		
		model.addAttribute("properties", map);
		
		
		return "account/account-detail";
	}
	/**
	 * 列表信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", accountService.getAll());
		return "stats/account-list";
	}
	
	/**
	 * 按照类别进入台账页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="list-by-type", method = RequestMethod.GET)
	public String listByType(@RequestParam(required=true) int typeid,Model model) throws Exception {
		AccountType at = AccountType.get(typeid);
		if(at == null) throw new Exception("台账类型中没有这个类型");
		
		//根据主类型获取子类型
		List<Account> list = accountService.getListByType(at);
		
		String fields=""; //properties = "";
		//获取属性
		switch(at) {
			case infoSys:
				fields = PropertyFileUtil.getStringValue(_fieldone);
//				properties = PropertyFileUtil.getStringValue(_propertiesone);
				break;
			case infoEquipment:
				fields = PropertyFileUtil.getStringValue(_fieldtwo);
//				properties = PropertyFileUtil.getStringValue(_propertiestwo);
				break;
			case storage:
				fields = PropertyFileUtil.getStringValue(_fieldthree);
//				properties = PropertyFileUtil.getStringValue(_propertiesthree);
				break;
			case security:
				fields = PropertyFileUtil.getStringValue(_fieldfour);
//				properties = PropertyFileUtil.getStringValue(_propertiesfour);
				break;
			case appSys:
				fields = PropertyFileUtil.getStringValue(_fieldfive);
//				properties = PropertyFileUtil.getStringValue(_propertiesfive);
				break;
		}
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
//		List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		//生成column
		List<String> columns = new LinkedList<String>();
		List<String> mDatas = new LinkedList<>();
		//mDatas.add("index");	//序号
		for(Property p:fieldsSet) {
			columns.add(p.getPropertyName());
			mDatas.add(p.getPropertyId());
		}
//		for(Property p:propertiesSet) {
//			columns.add(p.getPropertyName());
//			mDatas.add(p.getPropertyId());
//		}
		
		model.addAttribute("columns", columns);
		model.addAttribute("mDatas", mDatas);
		
		//部门
		model.addAttribute("topGroups", groupService.getAllTop());
		
		model.addAttribute("at", at);
		model.addAttribute("list", list);
		//状态、密级
		model.addAttribute("statusList", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.status")).getResult());
		model.addAttribute("securityLevelList", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.securitylevel")).getResult());
		
		return "account/list-by-type";
	}
	
	@RequestMapping(value="/search-by-table",method = RequestMethod.GET)
	@ResponseBody
	public String geListByCategoryCode(@RequestParam(required=true) String aoData) throws Exception{
		JSONArray jsonarray = new JSONArray(aoData);
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
	    String type = "0";			//子类型
	    String department = "0";			//部门
	    String status = "0";			//子类型
	    String securityLevel = "0";			//子类型
	    String mainType = "";				//主类型
	    
	    
	    for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  {
	            sEcho = obj.get("value").toString();  
	        } else if (obj.get("name").equals("iDisplayStart")) {  
	            iDisplayStart = obj.getInt("value");  
	        } else if (obj.get("name").equals("iDisplayLength")) {  
	            iDisplayLength = obj.getInt("value");  
	        } else if (obj.get("name").equals("type")) {  
	            type = obj.getString("value");  
	        } else if (obj.get("name").equals("department")) {  
	        	department = obj.getString("value");  
	        } else if (obj.get("name").equals("status")) {  
	        	status = obj.getString("value");  
	        } else if (obj.get("name").equals("securityLevel")) {  
	        	securityLevel = obj.getString("value");  
	        } else if (obj.get("name").equals("mainType")) {  
	        	mainType = obj.getString("value");  
	        }
	    } 
	    
	    AccountType at = AccountType.get(Integer.parseInt(mainType));
	    List<Account> accountList = accountService.getListByType(at);
	    
	    List<String> codeList = new ArrayList<>();
	    for(Account account : accountList) {
	    	codeList.add(account.getCategory());
	    }
	    
	    SearchResult<Ci> result = ciService.getListByCondition(codeList, type, department, status, securityLevel, iDisplayStart, iDisplayLength);
	    
	    String fields=""; //properties = "";
		//获取属性
		switch(at) {
			case infoSys:
				fields = PropertyFileUtil.getStringValue(_fieldone);
				//properties = PropertyFileUtil.getStringValue(_propertiesone);
				break;
			case infoEquipment:
				fields = PropertyFileUtil.getStringValue(_fieldtwo);
				//properties = PropertyFileUtil.getStringValue(_propertiestwo);
				break;
			case storage:
				fields = PropertyFileUtil.getStringValue(_fieldthree);
				//properties = PropertyFileUtil.getStringValue(_propertiesthree);
				break;
			case security:
				fields = PropertyFileUtil.getStringValue(_fieldfour);
				//properties = PropertyFileUtil.getStringValue(_propertiesfour);
				break;
			case appSys:
				fields = PropertyFileUtil.getStringValue(_fieldfive);
				//properties = PropertyFileUtil.getStringValue(_propertiesfive);
				break;
		}
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
		//List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		//生成column
		List<String> mDatas = new LinkedList<>();
//		mDatas.add("index");	//序号
		for(Property p:fieldsSet) {
			mDatas.add(p.getPropertyConstraint());
		}
//		for(Property p:propertiesSet) {
//			mDatas.add(p.getPropertyConstraint());
//		}
		
//	    int indexNum = iDisplayStart * iDisplayLength;
	    
	    List<Map<String, String>> accountList1 = new ArrayList<>();
//	    ObjectMapper mapper = new ObjectMapper();
	    for(Ci ci : result.getResult()) {
	    	//需要判断是否为空ci.getPropertiesData()
	    	
	    	Map<String, String> accountMap = new HashMap<>();
	    	accountMap.put("id", String.valueOf(ci.getId()));
	    	for(Property p:fieldsSet) {
	    		Object obj;
	    		if(p.getPropertyConstraint().equals("DepartmentInUse")) {		//
					obj = Common.getFieldValueByName(ci, "departmentName");
				} else if(p.getPropertyConstraint().equals("UserInMaintenance")) {
					obj = Common.getFieldValueByName(ci, "userInMaintenanceName");
				} else if(p.getPropertyConstraint().equals("status")) {
					obj = Common.getFieldValueByName(ci, "statusName");
				} else if(p.getPropertyConstraint().equals("SecurityLevel")) {
					obj = Common.getFieldValueByName(ci, "SecurityLevelName");
				} else
					obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
	    		if(obj != null)
	    			accountMap.put(p.getPropertyId(), obj.toString());
			}
	    	//处理属性
//	    	if(ci.getPropertiesData()!=null) {
//	    		@SuppressWarnings("unchecked")
//		    	Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
//				for(Property p:propertiesSet) {
//					accountMap.put(p.getPropertyId(), propertymap.get(p.getPropertyId()));
//				}
//	    	}
			accountMap.put("ids", " ");
			
			accountList1.add(accountMap);
	    }
	    
	    JSONObject getObj = new JSONObject();
		
		getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", result.getTotalCount());//实际的行数
	    getObj.put("iTotalDisplayRecords", result.getTotalCount());//显示的行数,这个要和上面写的一样
	    
	    getObj.put("aaData", accountList1);//要以JSON格式返回
	    
		return getObj.toString();
	}
	/**
	 * 删除台账
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,Model model){
			
		accountService.delById(id);
		
		return "redirect:/stats/account/list";
	}
	/**定义台账信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/setting",method = RequestMethod.GET)
	public String setting(Model model,HttpServletRequest request){
		List<Account> accounts = accountService.getAll();
		
		model.addAttribute("accounts", accounts);
		model.addAttribute("fields", propertyService.getFields());
		
		return "stats/account-setting";
	}
	/**生成自定义台账
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/generate",method = RequestMethod.POST)
	public String accountList(Model model,HttpServletRequest request) throws Exception{

		String code = request.getParameter("category");
		String fields = request.getParameter("fm_fields");
		String properties = request.getParameter("fm_properties");
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
		List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		//生成column
		List<String> columns = new LinkedList<String>();
		for(Property p:fieldsSet)
			columns.add(p.getPropertyName());
		for(Property p:propertiesSet)
			columns.add(p.getPropertyName());
		model.addAttribute("columns", columns);
		//生成row
		ObjectMapper mapper = new ObjectMapper();
		List<Object> rows = new LinkedList<Object>();
		List<Ci> cilist = ciService.getByCategoryCode(code).getResult();
		for(Ci ci:cilist)
		{
			List<String> row = new LinkedList<String>();
			try {
				Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
				Object obj = null;
				for(Property p:fieldsSet)
				{
					obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
					if(obj==null)
						row.add("-");
					else
						row.add(obj.toString());
				}
				for(Property p:propertiesSet)
					row.add(propertymap.get(p.getPropertyId()));
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rows.add(row);
		}
		model.addAttribute("rows", rows);
		
		
		return "stats/account-generate";
	}
	
	@RequestMapping(value="/view/{ciid}/{accountid}",method = RequestMethod.GET)
	public String viewAccount(@PathVariable("ciid") Long ciId,@PathVariable("accountid") Long accountId,Model model) throws Exception {
		//根据id获取ci
		Ci ci = ciService.getById(ciId);
		Account account = accountService.getById(accountId);
		//获取要显示的列
		String fields = account.getFields();
		String properties = account.getProperties();
		
		Map<String, String> ciMap = new LinkedHashMap<String, String>();
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
		List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		for(Property p:fieldsSet) {
//			System.out.println(p.getPropertyName());
			Object obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
			//System.out.println(p.getPropertyName());
			ciMap.put(p.getPropertyName(), obj.toString());
		}
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
		for(Property p:propertiesSet) {
			ciMap.put(p.getPropertyName(), propertymap.get(p.getPropertyId()));
		}
		
//		int mapSize = ciMap.size();
//		
//		String body = "";
//
//		Object s[] = ciMap.keySet().toArray();
//		for(int i=0; i<mapSize; i=i+2) {
//			body += "<div class=\"row-form clearfix\">";
//			body += "<div class=\"col-md-2\">"+s[i]+"：</div><div class=\"col-md-4\">"+ciMap.get(s[i])+"</div>";
//			body += "<div class=\"col-md-2\">"+s[i+1]+"：</div><div class=\"col-md-4\">"+ciMap.get(s[i+1])+"</div>";
//			body += "</div>";
//		}
//		
//		if(mapSize%2 !=0) {
//			body += "<div class=\"row-form clearfix\">";
//			body += "<div class=\"col-md-2\">"+s[mapSize-1]+":</div><div class=\"col-md-4\">"+ciMap.get(s[mapSize-1])+"</div>";
//			body += "<div class=\"col-md-2\"></div><div class=\"col-md-4\"></div>";
//			body += "</div>";
//		}
		
		List<Account> accounts = accountService.getAll();
		
		model.addAttribute("accounts", accounts);
//		model.addAttribute("body", body);
		model.addAttribute("ciMap", ciMap);
		model.addAttribute("accountName", account.getName());
		
		return "stats/account-view";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/get/{id}",method = RequestMethod.GET)
	public String getAccount(@PathVariable("id") Long id,HttpServletRequest request,Model model) throws Exception{

		List<Account> accounts = accountService.getAll();
		Account account = accountService.getById(id);
		String code = account.getCategory();
		String fields = account.getFields();
		//编号、名称、型号、密级、用途、所属部门、放置地点、责任人、操作系统安装日期、硬盘序列号、IP地址、MAC地址、启用时间、使用情况
		//fields = "CMS_FIELD_SECURITYNO,CMS_FIELD_NAME,CMS_FIELD_MODEL,CMS_FIELD_SECURITYLEVEL,CMS_FIELD_PURPOSE,CMS_FIELD_DEPARTMENTINUSE,CMS_FIELD_LOCATION,CMS_FIELD_USERINMAINTENANCE,CMS_FIELD_SERVICESTARTTIME,CMS_FIELD_STATUS";
		String properties = account.getProperties();
		//properties = "CMS_PROPERTY_OSINSTALLTIME,CMS_PROPERTY_DISKNO,CMS_PROPERTY_IPADDRESS,CMS_PROPERTY_MACADDRESS";
//		String comma=",";
//		String views = account.getViews();
//		String[] viewproperties = views.split(comma);
		
//		for(String vp : viewproperties) {
//			if(StringUtils.contains(vp, "FIELD")) {
//				fields += vp+comma;
//			} else if(StringUtils.contains(vp, "PROPERTY")) {
//				properties += vp+comma;
//			}
//		}
		
//		fields = fields.substring(0, fields.length()-1);
//		properties = properties.substring(0, properties.length()-1);
		
		List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
		List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
		
		//生成column
		List<String> columns = new LinkedList<String>();
		for(Property p:fieldsSet)
			columns.add(p.getPropertyName());
		for(Property p:propertiesSet)
			columns.add(p.getPropertyName());
		model.addAttribute("columns", columns);
		//生成row
		ObjectMapper mapper = new ObjectMapper();
		List<Object> rows = new LinkedList<Object>();
		List<Ci> cilist = ciService.getByCategoryCode(code).getResult();
		for(Ci ci:cilist)
		{
			List<String> row = new LinkedList<String>();
			try {
				//需要判断是否为空ci.getPropertiesData()
				Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
				Object obj;
				//获取属性
				for(Property p:fieldsSet)
				{
					if(p.getPropertyConstraint().equals("DepartmentInUse")) {		//
						obj = Common.getFieldValueByName(ci, "departmentName");
					} else if(p.getPropertyConstraint().equals("UserInMaintenance")) {
						obj = Common.getFieldValueByName(ci, "userInMaintenanceName");
					} else if(p.getPropertyConstraint().equals("status")) {
						obj = Common.getFieldValueByName(ci, "statusName");
					} else if(p.getPropertyConstraint().equals("SecurityLevel")) {
						obj = Common.getFieldValueByName(ci, "SecurityLevelName");
					} else
						obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
					
					if(obj==null)
						row.add("-");
					else {
						row.add(obj.toString());
					}
				}
				//获取扩展属性
				for(Property p:propertiesSet)
					row.add(propertymap.get(p.getPropertyId()));
				
//				row.add("<a href=\""+request.getContextPath()+"/stats/account/view/"+ci.getId()+"/"+account.getId()+"\">查看</a>");
				
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rows.add(row);
		}
		model.addAttribute("rows", rows);
		model.addAttribute("accounts", accounts);
		model.addAttribute("id", id);
		
		return "stats/account-get";
	}
	

    /** 
        * 导出Excel 
        * @param model 
        * @param projectId 
        * @param request 
        * @return 
     * @throws Exception 
        */  
    @RequestMapping(value="/dcExcel",method=RequestMethod.GET)  
    public ModelAndView toDcExcel(ModelMap model, HttpServletRequest request) throws Exception{  
    	   String accountid = request.getParameter("id");
    	   
    	   Long id = Long.valueOf(accountid);
    	   Account account = accountService.getById(id);
    	   
    	   List<Ci> ciList = ciService.getByCategoryCode(account.getCategory()).getResult();
    	   int rows = ciList.size();
    	   
    	   
    	   //设置表头
    	   int columnsNum = 0;
    	   String fields = account.getFields();
    	   String properties = account.getProperties();
    	   
    	   List<Property> fieldsSet = propertyService.getByPropertyIds(fields).getResult();
    	   List<Property> propertiesSet = propertyService.getByPropertyIds(properties).getResult();
    	   
    	   String[][] datas = new String[rows+1][fieldsSet.size() + propertiesSet.size()];
    	   
    	   for(Property p:fieldsSet) {
   				datas[0][columnsNum] = p.getPropertyName();
   				columnsNum++;
    	   }
    	   for(Property p:propertiesSet) {
    		   datas[0][columnsNum] = p.getPropertyName();
    		   columnsNum++;
    	   }
    	   
    	   int rowsNum = 1;
    	   ObjectMapper mapper = new ObjectMapper();
    	   //数值行
    	   for(Ci ci : ciList) {
    		   columnsNum = 0;
    		   @SuppressWarnings("unchecked")
			Map<String,String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
    		   for(Property p:fieldsSet)
				{
					Object obj = Common.getFieldValueByName(ci, p.getPropertyConstraint());
					if(obj==null)
						datas[rowsNum][columnsNum] = "-";
					else
						datas[rowsNum][columnsNum] = obj.toString();
					columnsNum++;
				}
				for(Property p:propertiesSet) {
					datas[rowsNum][columnsNum] = propertymap.get(p.getPropertyId());
					columnsNum++;
				}
				rowsNum++;
    	   }
    	   
           ViewExcel viewExcel = new ViewExcel();    
           model.addAttribute("datas", datas);
           model.addAttribute("accountName", account.getName());
           return new ModelAndView(viewExcel, model);   
       }  
	
	class ViewExcel extends AbstractExcelView {

		@Override
		protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			HSSFSheet sheet = workbook.createSheet((String)model.get("accountName"));    
			
			String datas[][] = (String[][]) model.get("datas");
			
			for(int i = 0; i<datas.length; i++) {
				HSSFRow row = sheet.createRow(i);
				
				String datas1[] = datas[i];
				
				for(int j = 0; j < datas1.length; j++) {
					HSSFCell cell = row.createCell(j);
					
					cell.setCellValue(datas1[j]);
				}
			}
			
	        String filename = "台账.xls";//设置下载时客户端Excel的名称     
	        filename = encodeFilename(filename, request);//处理中文文件名  
	        response.setContentType("application/vnd.ms-excel");     
	        response.setHeader("Content-disposition", "attachment;filename=" + filename);     
	        OutputStream ouputStream = response.getOutputStream();     
	        workbook.write(ouputStream);     
	        ouputStream.flush();     
	        ouputStream.close();    
		}
		

	    /**  
	         * 设置下载文件中文件的名称  
	         *   
	         * @param filename  
	         * @param request  
	         * @return  
	         */    
	        public  String encodeFilename(String filename, HttpServletRequest request) {    
	          /**  
	           * 获取客户端浏览器和操作系统信息  
	           * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
	           * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
	           */    
	          String agent = request.getHeader("USER-AGENT");    
	          try {    
	            if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
	              String newFileName = URLEncoder.encode(filename, "UTF-8");    
	              newFileName = StringUtils.replace(newFileName, "+", "%20");    
	              if (newFileName.length() > 150) {    
	                newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
	                newFileName = StringUtils.replace(newFileName, " ", "%20");    
	              }    
	              return newFileName;    
	            }    
	            if ((agent != null) && (-1 != agent.indexOf("Mozilla")))    
	              return MimeUtility.encodeText(filename, "UTF-8", "B");    
	          
	            return filename;    
	          } catch (Exception ex) {    
	            return filename;    
	          }    
	        }   
		
	}
}
