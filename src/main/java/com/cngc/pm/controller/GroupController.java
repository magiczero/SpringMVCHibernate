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
import org.springframework.web.bind.annotation.RequestParam;
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
		if(group.getId()!=null && group.getId()>0) { //修改
			//group.setParentGroup(groupService.getById(group.getId()).getParentGroup());
			groupService.saveGroup(group);
		} else {
			Long parentId = group.getParentGroup().getId();
			long id = 0;
			//计算主键
			if(parentId == null) {
				id = groupService.getMaxTop().getId()+1;
			} else {
				//验证
				Group parentGroup = groupService.getById(parentId);
				
				if(parentGroup!= null && parentGroup.getId() > 0) {
					//if(parentId.toString().length()/2 > 1) {
					Group maxChildGroup = groupService.getMaxChild(parentGroup);
					if(maxChildGroup==null)
						id = parentGroup.getId()*100+1;
					else
						id = groupService.getMaxChild(parentGroup).getId()+1;
					//}
				}
			}
			if(id != 0) {
				group.setId(id);
				groupService.saveGroup(group);
			}
		}
		
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
	
	/**
	 * 返回所有顶级部门
	 * @param request
	 * @return
	 */
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
	
	/**
	 * 返回部门列表的json字符串
	 * @param root 部门id变量，
	 * @param user 是否显示部门下的人员
	 * @return 如果为source则返回全部所有部门，如果某一部门id，则返回这个部门下的所有子部门（及人员）
	 */
	@RequestMapping(value = "/all-json", method = RequestMethod.GET)
	@ResponseBody  
	public String getAllJson(@RequestParam String root, @RequestParam boolean haveuser) {

		return groupService.getChildByGroup(root,haveuser);
	}
	
	/**
	 * 返回部门及部门下的人员
	 * @return
	 */
	@RequestMapping(value = "/all-tree", method = RequestMethod.GET)
	@ResponseBody  
	public Map<String, Object> getAllTree() {
		Map<String, Object> map = new HashMap<>();
		
		map.put("json", groupService.getAllWithJson());
		
		return map;
	}
}
