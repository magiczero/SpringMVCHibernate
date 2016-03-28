package com.cngc.pm.controller.cms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.cms.Category;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.cms.CategoryService;
import com.cngc.pm.service.cms.PropertyService;


@Controller
@RequestMapping(value="/cms/category")
public class CategoryController {
	
	@Resource
	private CategoryService categoryService;
	@Resource
	private PropertyService propertyService;
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("category",new Category());
		return "cms/category-add";
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("category") Category category, HttpServletRequest request){
		
		categoryService.save(category);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", categoryService.getAll());
		model.addAttribute("category",new Category());
		return "cms/category-list";
	}
	
	@RequestMapping(value="/delete/{code}", method = RequestMethod.GET)
	public String delete(@PathVariable("code") String code,Model model){
			
		categoryService.delByCode(code);
		
		return "redirect:/cms/category/list";
	}
	@RequestMapping(value="/updateproperty", method = RequestMethod.POST)
	public String updateProperty(Model model,HttpServletRequest request){
		String code = request.getParameter("form_code");
		String strproperties = request.getParameter("form_properties");
		if(!StringUtils.isEmpty(code))
		{
			Category category = categoryService.getByCode(code);
			if(!StringUtils.equals(strproperties, "0"))
			{
				Set<Property> set = null;
				if(StringUtils.isEmpty(strproperties))
				{
					//删除所有属性
					category.setProperties(set);
				}
				else
					set = propertyService.getPropertyByIds(strproperties);
				category.setProperties(set);
				categoryService.save(category);
			}
		}
		return "redirect:list";
	}
	@RequestMapping(value="/getproperty/{code}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getProperty(@PathVariable("code") String code,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		Category category = categoryService.getByCode(code);
		map.put("properties", category.getProperties());
		return map;
	}
	@RequestMapping(value="/getpropertyforselect/{code}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getPropertyForSelect(@PathVariable("code") String code,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Long> ids = new LinkedList<Long>();
		code = code.substring(0,code.length()-2);
		while(code.length()>=2)
		{
			//根据code分级判断
			Category category = categoryService.getByCode(code);
			
			for(Property property:category.getProperties())
			{
				ids.add(property.getId());
			}
			code = code.substring(0,code.length()-2);
		}

		map.put("properties", propertyService.getPropertyByNIds(ids));
		return map;
	}
	@RequestMapping(value="/getlayeredproperty/{code}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getLayeredProperty(@PathVariable("code") String code,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		while(code.length()>=2)
		{
			//根据code分级判断
			Category category = categoryService.getByCode(code);
			map.put(code, category);
			map.put(code+"properties", category.getProperties());
			code = code.substring(0,code.length()-2);
		}
		return map;
	}
	@RequestMapping(value="/getjson", method = RequestMethod.GET)
	@ResponseBody
	public String getJson(Model model){
//		Map<String,Object> map = new HashMap<String,Object>();
//
//		map.put("json", categoryService.getJSON());
//		
//		return map;
		return categoryService.getJSON();
	}
}
