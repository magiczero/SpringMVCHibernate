package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.exception.BusinessException;
import com.cngc.pm.common.web.BaseController;
import com.cngc.pm.model.Resources;
import com.cngc.pm.service.ResourcesService;

@Controller
@RequestMapping("/resource")
public class ResourcesController extends BaseController {

	@Resource
	private ResourcesService resourcesService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("listResources", resourcesService.getAll());
		model.addAttribute("resources", new Resources());
		model.addAttribute("modules", resourcesService.getModules());
		
		return "resources/list";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("resources") Resources resources, BindingResult result) throws BindException {
		if(result.hasErrors()) {
			throw new BindException(result);
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(resources.getId() == null)
			resourcesService.save(resources, username);
		else
			resourcesService.update(resources, username);
		
		return "redirect:/resource/list";
	}
	
	@RequestMapping(value = "/enable/{id}")
	@ResponseBody  
	public Map<String,Object> enableStatus(@PathVariable("id") long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		Resources r = resourcesService.getById(id);
		
		if(r == null) throw new BusinessException("无法删除，没有这个资源");
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(resourcesService.enableOrNot(r, username)) {
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}
		
		return map;
	}
	
	@RequestMapping(value = "/init-update/{id}")
	public String initUpdate(@PathVariable long id, Model model) {
		model.addAttribute("resources", resourcesService.getById(id));
		model.addAttribute("modules", resourcesService.getModules());
		
		return "resources/update";
	}
}
