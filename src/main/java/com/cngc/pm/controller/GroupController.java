package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.Group;
import com.cngc.pm.service.GroupService;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Resource
	private GroupService groupService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("groupList", groupService.getAllTop());
		model.addAttribute("group", new Group());
		
		return "group/list";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute Group group, BindingResult result,Model model) {
		groupService.saveGroup(group);
		
		return "redirect:/group/list";
	}
	
	@RequestMapping(value = "/all-json", method = RequestMethod.GET)
	@ResponseBody  
	public Map<String, Object> getAllJson() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("json", groupService.getAllWithJson());
		
		return map;
	}
}
