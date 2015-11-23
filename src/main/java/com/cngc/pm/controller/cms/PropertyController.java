package com.cngc.pm.controller.cms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.cms.PropertyService;

@Controller
@RequestMapping(value="/cms/property")
public class PropertyController {

	@Resource
	private PropertyService propertyService;
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("property",new Property());
		return "cms/property-add";
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("property") Property property, HttpServletRequest request){
		
		propertyService.save(property);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", propertyService.getAll());
		model.addAttribute("property",new Property());
		return "cms/property-list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			propertyService.delById(id);
		
		return "redirect:/cms/property/list";
	}
}
