package com.cngc.pm.controller.computer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.computer.InspectionItem;
import com.cngc.pm.service.computer.InspectionItemService;

@Controller
@RequestMapping(value="/computer/inspectionitem")
public class InspectionItemController {

	@Resource
	private InspectionItemService itemService;
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("inspectionitem",new InspectionItem());
		return "computer/inspectionitem-add";
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("inspectionitem") InspectionItem item, HttpServletRequest request){
		
		itemService.save(item);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", itemService.getAll());
		model.addAttribute("inspectionitem",new InspectionItem());
		return "computer/inspectionitem-list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			itemService.delById(id);
		
		return "redirect:/computer/inspectionitem/list";
	}
	
}
