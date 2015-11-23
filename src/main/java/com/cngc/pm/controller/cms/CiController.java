package com.cngc.pm.controller.cms;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.cxf.common.util.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.service.cms.CategoryRelationService;
import com.cngc.pm.service.cms.CategoryService;
import com.cngc.pm.service.cms.CiService;
import com.ctc.wstx.util.StringUtil;

@Controller
@RequestMapping(value="/cms/ci")
public class CiController {

	@Resource
	private CiService ciService;
	@Resource
	private CategoryService categoryService;
	@Resource
	private CategoryRelationService categoryRelationService;
	

	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("ci",new Ci());
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

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("ci") Ci ci, HttpServletRequest request){
		
		ci.setStatus("01");
		ci.setCreatedTime(new Date());
		ci.setLastUpdateTime(new Date());
		ci.setLastUpdateUser(UserUtil.getUserId(request.getSession()));
		ciService.save(ci);
		
		return "redirect:list";
	}
	@RequestMapping(value="/saveproperty/{id}",method=RequestMethod.POST)
	public String saveProperty(@PathVariable("id") long id,HttpServletRequest request){	
		ObjectMapper mapper = new ObjectMapper();
		Ci ci = ciService.getById(id);
		try
		{
			ci.setPropertiesData(mapper.writeValueAsString(request.getParameterMap()));
			
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
			ciService.delById(id);
		
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
		model.addAttribute("relations", categoryRelationService.getByPrimaryCode(ci.getCategoryCode()));
		
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
	@RequestMapping(value="/deleterelation")
	public String deleteRelation(HttpServletRequest request,Model model)
	{
		Long primaryId = Long.valueOf(request.getParameter("primary_id").toString());
		Long secondaryId = Long.valueOf(request.getParameter("secondary_id").toString());
		String relationId = request.getParameter("relation_id").toString();
		
		ciService.deleteRelation(primaryId, secondaryId, relationId);
		
		return "redirect:/cms/ci/detail/" + primaryId;
	}
}
