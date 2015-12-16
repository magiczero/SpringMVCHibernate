package com.cngc.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.cngc.pm.service.RoleService;
import com.cngc.pm.service.UserService;
import com.cngc.utils.Common;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private IdentityService identityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", userService.getAll());
		model.addAttribute("rolelist",roleService.getAll());
		
		return "sysmanage/user-list";
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Model model,HttpServletRequest request)	{
		SysUser user;
		Long id = Long.parseLong(request.getParameter("userform_id"));
		
		if(id!=0)
			user = userService.getById(id);
		else
			user = new SysUser();
		if(id==0)
		{
			user.setUsername(request.getParameter("username"));
		}
		if(!StringUtils.isEmpty(request.getParameter("password")) )
		{
			//md5 password
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			user.setPassword(md5.encodePassword(
					request.getParameter("password"),user.getUsername())
			);
		}
		user.setName(request.getParameter("name"));
		userService.save(user);
		
		//同步到activiti的user
		org.activiti.engine.identity.User actuser = identityService.newUser(user.getUsername());
		actuser.setFirstName(user.getName());
		identityService.saveUser(actuser);
		
		return "redirect:/user/list";
	}
	@RequestMapping(value="/updaterole", method = RequestMethod.POST)
	public String updateRole(Model model,HttpServletRequest request){
		String sid = request.getParameter("roleform_id");
		String roleIds = request.getParameter("rolefrom_userrole");
		Long id = Long.valueOf(sid);
		if(id!=0)
		{
			SysUser user = userService.getById(id);
			
			if(Common.isEmpty(roleIds)) {
				//清空用户的所有角色
				user.setUserRoles(null);
				
				userService.save(user);
			} else {
				userService.setRole(user,roleIds);
			}
			
//			if(!StringUtils.equals(roles, "0"))
//			{
//				Set<Role> set = null;
//				if(StringUtils.isEmpty(roles))
//				{
//					//删除所有权限设置
//					user.setRoles(null);
//				}
//				else
//					set = roleService.getRoleByIds(roles);
//				user.setRoles(set);
//				userService.save(user);
				
				//同步到activity的user-group，先删除再新增
				List<org.activiti.engine.identity.Group> groups = identityService.createGroupQuery().groupMember(user.getUsername()).list();
				if(groups!=null)
				{
					for(org.activiti.engine.identity.Group group:groups)
						identityService.deleteMembership(user.getUsername(),group.getId());
				}
				if(user.getUserRoles() !=null)
				{
					for(UserRole ur : user.getUserRoles())
						identityService.createMembership(user.getUsername(), ur.getRole().getRoleName());
				}
//			}
		}
		return "redirect:/user/list";
	}
	@RequestMapping(value="/getrole/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getRole(@PathVariable("id") long id,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		//SysUser user = userService.getById(id);
		//map.put("role", user.getRoles());
		List<Map<String, String>> list = new ArrayList<>();
		for(Role role : userService.getRolesByUser(id)) {
			Map<String, String> map1 = new HashMap<>();
			map1.put("id", role.getId().toString());
			map1.put("name", role.getRoleName());
			list.add(map1);
		}
		
		map.put("role", list);
		
		return map;
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
		{
			SysUser user = userService.getById(id);
			//同步到activiti的user
			identityService.deleteUser(user.getUsername());
			userService.delById(id);
		}
		
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/get/{id}")
	@ResponseBody
	public Map<String,Object> getUser(@PathVariable("id") long id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		SysUser user = userService.getById(id);
		
		Map<String, String> map1 = new HashMap<>();
		map1.put("id", user.getId().toString());
		map1.put("name", user.getUsername());
		
		map.put("user",map1);
		
		return map;
	}
}
