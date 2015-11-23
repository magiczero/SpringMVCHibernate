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
import com.cngc.pm.model.cms.CategoryRelation;
import com.cngc.pm.service.cms.CategoryRelationService;

@Controller
@RequestMapping(value="/cms/categoryrelation")
public class CategoryRelationController {

	@Resource
	private CategoryRelationService categoryRelationService;
	

	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("categoryRelation",new CategoryRelation());
		return "cms/categoryrelation-add";
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("categoryRelation") CategoryRelation categoryRelation, HttpServletRequest request){
		
		categoryRelationService.save(categoryRelation);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", categoryRelationService.getAll());
		model.addAttribute("categoryRelation",new CategoryRelation());
		return "cms/categoryrelation-list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,Model model){
			
		categoryRelationService.delById(id);
		
		return "redirect:/cms/categoryrelation/list";
	}
}
