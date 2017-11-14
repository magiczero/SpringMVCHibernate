package com.cngc.pm.controller.computer;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.Group;
import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.model.computer.Parameter;
import com.cngc.pm.service.GroupService;
import com.cngc.pm.service.computer.ComputerService;
import com.cngc.pm.service.computer.ParameterService;

@Controller
@RequestMapping(value="/computer")
public class ComputerController {

	@Resource
	private ComputerService computerService;
	@Resource
	private ParameterService parameterService;
	@Resource
	private GroupService groupService;
	
	@RequestMapping(value="/parameter")
	public String parameter(Model model){
		model.addAttribute("parameter", parameterService.getParamter());
		return "computer/parameter";
	}
	@RequestMapping(value="/parameter/save")
	public String saveParameter(@Valid @ModelAttribute("parameter") Parameter parameter, HttpServletRequest request){

		parameterService.save(parameter.getInterval());
		return "redirect:/computer/parameter";
	}
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("computer",new Computer());
		return "register";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("computer") Computer computer, HttpServletRequest request){
		
		computerService.save(computer);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list/{id}",method = RequestMethod.GET)
	public String list(Model model,@PathVariable("id") Long id){
		if(id==0)
			model.addAttribute("list", computerService.getRegistedAll());
		else
			model.addAttribute("list", computerService.getByGroup(id));
		model.addAttribute("groupList", groupService.getAllTop());
		model.addAttribute("group", groupService.getById(id));
		return "computer/computer-list";
	}
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", computerService.getRegistedAll());
		List<Group> groups =  groupService.getAllTop();
		model.addAttribute("groupList", groups);
		if(groups.size()>0)
			model.addAttribute("group", groups.get(0));
		return "computer/computer-list";
	}
	
	@RequestMapping(value="/notregisted",method = RequestMethod.GET)
	public String notRegisted(Model model){
		model.addAttribute("list", computerService.getNotRegistedAll());
		model.addAttribute("groupList", groupService.getAllTop());
		return "computer/computer-list";
	}
	
	@RequestMapping(value="/delete/{ids}", method = RequestMethod.GET)
	public String delete(@PathVariable("ids") String ids,Model model){
		if(ids!="")
			computerService.delByIds(ids);
		
		return "redirect:/computer/list";
	}
	@RequestMapping(value="/setdepartment", method = RequestMethod.POST)
	public String setDepartment(Model model,HttpServletRequest request){
		String ids = request.getParameter("ids");
		String group = request.getParameter("group");
		
		if(ids!=null&&group!=null)
		{
			computerService.setDepartment(ids, Long.valueOf(group));
		}
		
		return "redirect:list";
	}
}
