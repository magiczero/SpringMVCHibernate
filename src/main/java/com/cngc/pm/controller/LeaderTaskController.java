package com.cngc.pm.controller;

import javax.annotation.Resource;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.service.LeaderTaskService;

@Controller
@RequestMapping(value="/leadertask")
public class LeaderTaskController {
	@Resource
	private LeaderTaskService leaderTaskService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private FormService formService;
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", leaderTaskService.getAll());
		model.addAttribute("runtime",runtimeService);
		model.addAttribute("res", repositoryService);
		return "leadertask/list";
	}
}
