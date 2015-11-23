package com.cngc.pm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.Role;
import com.cngc.pm.service.RoleService;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	@Resource
	private RoleService roleService;
	@Resource
	private IdentityService identityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", roleService.getAll());
		return "sysmanage/role-list";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Model model,HttpServletRequest request)	{
		Role role;
		Long id = Long.parseLong(request.getParameter("id"));
		
		if(id!=0)
			role = roleService.getById(id);
		else
			role = new Role();

		role.setRoleName(request.getParameter("rolename"));
		role.setRoleDesc(request.getParameter("roledesc"));
		roleService.save(role);
		
		// 同步到activiti的group
		Group group = identityService.newGroup(role.getRoleName());
		group.setName(role.getRoleDesc());
		identityService.saveGroup(group);
		
		return "redirect:/role/list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
		{
			Role role = roleService.getById(id);
			//同步到activiti的group
			identityService.deleteGroup(role.getRoleName());
			roleService.delById(id);
		}
		
		return "redirect:/role/list";
	}
	
	@RequestMapping(value="/get/{id}")
	@ResponseBody
	public Map<String,Object> getUser(@PathVariable("id") long id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		Role role = roleService.getById(id);
		map.put("role",role);
		
		return map;
	}
}

