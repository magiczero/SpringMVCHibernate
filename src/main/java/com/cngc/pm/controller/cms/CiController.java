package com.cngc.pm.controller.cms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.ItilRelationService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.UserService;
import com.cngc.pm.service.cms.CategoryRelationService;
import com.cngc.pm.service.cms.CategoryService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.pm.service.cms.PropertyService;
import com.cngc.pm.service.cms.RelationService;
import com.cngc.utils.PropertyFileUtil;

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
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("ci",new Ci());
		model.addAttribute("status", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.status")).getResult());
		model.addAttribute("securityLevel", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.securitylevel")).getResult());
		model.addAttribute("system", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.cms.ci.system")).getResult());
		model.addAttribute("users", userService.getAll());
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
			map.put(category.getCategoryName(), category.getProperties());
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
	public String save(@Valid @ModelAttribute("ci") Ci ci, HttpServletRequest request,Authentication authentication){
		String attachIds = request.getParameter("fileids");
		
		Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
		
		ci.setReviewStatus("02");
		ci.setDeleteStatus("01");
		ci.setCreatedTime(new Date());
		ci.setLastUpdateTime(new Date());
		ci.setLastUpdateUser(userUtil.getUserId(authentication));
		ci.setAttachs(attachSet);
		
		ciService.save(ci);
		
		return "redirect:list";
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
		ciService.save(ci);
		
		return "redirect:/cms/ci/list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("category", categoryService.getJSON());
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
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
		{
			//	ciService.delById(id);
			Ci ci = ciService.getById(id);
			ci.setDeleteStatus(PropertyFileUtil.getStringValue("syscode.cms.ci.delete"));
			ciService.save(ci);
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
}
