package com.cngc.pm.controller.cms;

import static com.cngc.utils.Common.getRemortIP;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
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
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.ItilRelationService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.UserService;
import com.cngc.pm.service.cms.CategoryRelationService;
import com.cngc.pm.service.cms.CategoryService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.pm.service.cms.PropertyService;
import com.cngc.pm.service.cms.RelationService;
import com.cngc.utils.MyDateFormat;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.SearchResult;

@Controller
@RequestMapping(value="/cms/ci")
public class CiController {

	@Resource
	private CiService ciService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private CategoryRelationService categoryRelationService;
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private ItilRelationService itilrelationService;
	@Resource
	private PropertyService propertyService;
	@Resource
	private RelationService relationService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private UserService userService;
	@Resource
	private AttachService attachService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(java.sql.Date.class,  new CustomDateEditor(new MyDateFormat("yyyy-MM-dd HH:mm:ss"), true));
   
	}
	
	@RequestMapping(value="/edit")
	public String initEditCi(@RequestParam(required=true) long ciid,Model model) {
		Ci ci = ciService.getById(ciid);
		
		model.addAttribute("ci",ci);
		model.addAttribute("status", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.status")).getResult());
		model.addAttribute("securityLevel", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.securitylevel")).getResult());
		model.addAttribute("system", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.system")).getResult());
		model.addAttribute("users", userService.getAllByCondition(true, true));
		
		return "cms/ci-add";
	}
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("ci",new Ci());
		model.addAttribute("status", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.status")).getResult());
		model.addAttribute("securityLevel", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.securitylevel")).getResult());
		model.addAttribute("system", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.system")).getResult());
		model.addAttribute("users", userService.getAllByCondition(true, true));
		return "cms/ci-add";
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
		return "cms/ci-addproperty";
	}
	@RequestMapping(value="/getproperty/{id}")
	@ResponseBody
	public Map<String,Object> getProperty(@PathVariable("id") long id,Model model){
		Map<String,Object> result = new HashMap<String,Object>();
		Ci ci = ciService.getById(id);
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
		result.put("properties", map);
		result.put("fields", propertyService.getFields());

		return result;
	}
	@RequestMapping(value="/getpropertybycode/{code}")
	@ResponseBody
	public Map<String,Object> getProperty(@PathVariable("code") String code,Model model){
		Map<String,Object> result = new HashMap<String,Object>();
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
		result.put("properties", map);

		return result;
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("ci") Ci ci, HttpServletRequest request,Authentication authentication) throws Exception{
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
		}
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/importData",method=RequestMethod.POST)
	public String importExcelData(HttpServletRequest request) {
		if(StringUtils.isEmpty(request.getParameter("fileids"))) {
			return "redirect:/cms/ci/list";
		}
		String attachIds = request.getParameter("fileids");
		
		Set<Attachment> setAttach = attachService.getSetByIds(attachIds);
		try {
			ciService.importData(setAttach);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/cms/ci/list";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/saveproperty/{id}",method=RequestMethod.POST)
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
		
		return "redirect:/cms/ci/list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		//model.addAttribute("category", categoryService.getJSON());
		return "cms/ci-list";
	}
	
	@RequestMapping(value="/list/{code}/",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> geListByCategoryCode(@PathVariable("code") String code,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		if(code.equals("0"))
	 		map.put("list", ciService.getAll());
	 	else
	 		map.put("list", ciService.getByCategoryCode(code).getResult());
	 	
	 	return map;
	 }
	
	@RequestMapping(value="/list-by-category",method = RequestMethod.GET)
	@ResponseBody
	public String geListByCategoryCode(@RequestParam(required=true) String aoData){
		JSONArray jsonarray = new JSONArray(aoData);
		
		String sEcho = null;  
	    int iDisplayStart = 0; // 起始索引  
	    int iDisplayLength = 0; // 每页显示的行数  
	    String code = "0";
	    
	    for (int i = 0; i < jsonarray.length(); i++) {  
	        JSONObject obj = (JSONObject) jsonarray.get(i);  
	        if (obj.get("name").equals("sEcho"))  {
	            sEcho = obj.get("value").toString();  
	        } else if (obj.get("name").equals("iDisplayStart")) {  
	            iDisplayStart = obj.getInt("value");  
	        } else if (obj.get("name").equals("iDisplayLength")) {  
	            iDisplayLength = obj.getInt("value");  
	        } else if (obj.get("name").equals("code")) {  
	            code = obj.getString("value");  
	        } 
	    } 
	    
	    SearchResult<Ci> sr  = ciService.getAllWithPage(code,iDisplayStart,iDisplayLength);
		
		JSONObject getObj = new JSONObject();
		
		getObj.put("sEcho", sEcho);// 不知道这个值有什么用,有知道的请告知一下
	    getObj.put("iTotalRecords", sr.getTotalCount());//实际的行数
	    getObj.put("iTotalDisplayRecords",  sr.getTotalCount());//显示的行数,这个要和上面写的一样
	    
	    getObj.put("aaData", sr.getResult());//要以JSON格式返回
	    
		return getObj.toString();
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model,HttpServletRequest request){
		if(id!=0)
		{
			//	ciService.delById(id);
			Ci ci = ciService.getById(id);
			ci.setDeleteStatus(PropertyFileUtil.getStringValue("syscode.cms.ci.delete"));
			ciService.save(ci, getRemortIP(request));
		}
		
		return "redirect:/cms/ci/list";
	}
	
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") long id,Model model){
		
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
		model.addAttribute("properties", map);
		model.addAttribute("relations", relationService.getByCode(ci.getCategoryCode()));
		
		return "cms/ci-detail";
	}
	@RequestMapping(value="/getrelation/{id}/{relation}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getRelation(@PathVariable("id") long id,@PathVariable("relation") String relation,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("cis", ciService.getByRelation(relation, id));
		
		return map;
	}
	@RequestMapping(value="/saverelation", method = RequestMethod.POST)
	public String saveRelation(HttpServletRequest request,Model model)
	{
		Long primaryId = Long.valueOf(request.getParameter("primary_id").toString());
		Long secondaryId = Long.valueOf(request.getParameter("secondary_id").toString());
		String relationId = request.getParameter("relation_id").toString();
		
		ciService.saveRelation(primaryId, secondaryId, relationId);
		
		return "redirect:/cms/ci/detail/" + primaryId;
	}
	@RequestMapping(value="/saverelations")
	@ResponseBody
	public Map<String,Object> saveRelations(HttpServletRequest request,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		Long primaryId = Long.valueOf(request.getParameter("primary_id").toString());
		String secondaryId = request.getParameter("secondary_ids").toString();
		String relationId = request.getParameter("relation_id").toString();
		
		String ids[] = secondaryId.split(",");
		for(String id:ids)
			ciService.saveRelation(primaryId, Long.valueOf(id), relationId);
		
		map.put("result","true");
		
		return map;
	}
	@RequestMapping(value="/deleterelation")
	public String deleteRelation(HttpServletRequest request,Model model)
	{
		Long primaryId = Long.valueOf(request.getParameter("primary_id").toString());
		Long secondaryId = Long.valueOf(request.getParameter("secondary_id").toString());
		String relationId = request.getParameter("relation_id").toString();
		
		ciService.deleteRelation(primaryId, secondaryId, relationId);
		
		return "redirect:/cms/ci/detail/" + primaryId;
	}
	@RequestMapping(value="/getjson/{ids}")
	@ResponseBody
	public Map<String,Object> getCi(@PathVariable("ids") String ids,HttpServletRequest request,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		List<Long> list = new ArrayList<Long>();
		
		String sids[] = ids.split(",");
		for(String s:sids)
			list.add(Long.valueOf(s));
		
		List<Ci> cilist = ciService.getByIds(list).getResult();
		map.put("cis", cilist);
		
		return map;
	}
	
//	class MyDateFormat extends SimpleDateFormat {  
//	    /**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;
//		public MyDateFormat(String s) {  
//	        super(s);  
//	    }  
//	    public Date parse(String text) throws ParseException {  
//	        DateFormat df = null;  
//	        if(text.length()<=10){  
//	            df = new SimpleDateFormat("yyyy-MM-dd");  
//	            return new java.sql.Date(df.parse(text).getTime());  
//	        }  
//	        java.util.Date date = super.parse(text);  
//	        return new java.sql.Date(date.getTime());  
//	    }  
//	}   
}
