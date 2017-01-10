package com.cngc.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.Group;
import com.cngc.pm.service.GroupService;
import com.cngc.utils.GroupSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

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
	
	@RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
	@ResponseBody  
	public Map<String, Object> getGroupJson(@PathVariable("id") long id) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper(); 
		SimpleModule module = new SimpleModule();  
		module.addSerializer(Group.class, new GroupSerializer());
		mapper.registerModule(module);
		map.put("group", mapper.writeValueAsString(groupService.getById(id)));
		
		return map;
	}
	
	@RequestMapping(value = "/all-top-groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody  
	public ResponseEntity<List<Map<String, String>>> getTopGroups(HttpServletRequest request) {
		List<Group> listTopGroups = groupService.getAllTop();
		
		
		List<Map<String, String>> groupList = new ArrayList<>();
		for(Group group : listTopGroups) {
			Map<String, String> map = new HashMap<>();
			map.put("groupid", group.getId().toString());
			map.put("groupname", group.getGroupName());
			
			groupList.add(map);
		}
		
		return new ResponseEntity<List<Map<String, String>>>(groupList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all-json", method = RequestMethod.GET)
	@ResponseBody  
	public String getAllJson(HttpServletRequest request) {
		String root = request.getParameter("root");
//		Map<String, Object> map = new HashMap<>();
//		
//		map.put("json", groupService.getAllWithJson());
		
		//return "[	{\"text\": \"<a href='' onclick=''>1. Pre Lunch (120 min)</a>\",\"expanded\": true,\"classes\": \"important\",\"children\":	[{\"text\": \"1.1 The State of the Powerdome (30 min)\"	},	{\"text\": \"1.2 The Future of jQuery (30 min)\"},	{\"text\": \"1.2 jQuery UI - A step to richnessy (60 min)\"}]	},	{\"text\": \"2. Lunch  (60 min)\"},{\"text\": \"3. After Lunch  (120+ min)\",\"children\":	[{\"text\": \"3.1 jQuery Calendar Success Story (20 min)\"},	{\"text\": \"3.2 jQuery and Ruby Web Frameworks (20 min)\"},	{\"text\": \"3.3 Hey, I Can Do That! (20 min)\"},{\"text\": \"3.4 Taconite and Form (20 min)\"}, {\"text\": \"3.5 Server-side JavaScript with jQuery and AOLserver (20 min)\"}, 	{\"text\": \"3.6 The Onion: How to add features without adding features (20 min)\",\"id\": \"36\",	\"hasChildren\": true}	]}]";
		return groupService.getChildByGroup(root);
	}
	
	@RequestMapping(value = "/all-tree", method = RequestMethod.GET)
	@ResponseBody  
	public Map<String, Object> getAllTree() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("json", groupService.getAllWithJson());
		
		return map;
	}
}
