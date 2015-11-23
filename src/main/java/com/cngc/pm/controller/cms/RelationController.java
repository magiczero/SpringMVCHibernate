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
import com.cngc.pm.model.cms.Relation;
import com.cngc.pm.service.cms.RelationService;

@Controller
@RequestMapping(value="/cms/relation")
public class RelationController {

	@Resource
	private RelationService relationService;
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("relation",new Relation());
		return "cms/relation-add";
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("relation") Relation relation, HttpServletRequest request){
		
		relationService.save(relation);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", relationService.getAll());
		model.addAttribute("relation",new Relation());
		return "cms/relation-list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") String id,Model model){
			
		relationService.delById(id);
		
		return "redirect:/cms/relation/list";
	}
}
