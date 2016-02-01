package com.cngc.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.exception.BusinessException;
import com.cngc.exception.ParameterException;
import com.cngc.pm.model.Authority;
import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.Role;
import com.cngc.pm.service.AuthorityService;
import com.cngc.pm.service.MoudleService;
import com.cngc.pm.service.RoleService;
import com.cngc.pm.service.UserService;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private IdentityService identityService;
	@Resource
	private AuthorityService authService;
	@Resource
	private MoudleService moudleService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", roleService.getAll());
		model.addAttribute("authList", authService.getAll());
		List<Moudle> menuAll = moudleService.getAllMenu();
		List<Moudle> menuAllLevelTop = new ArrayList<>();
		for(Moudle m : menuAll) {
			if(m.reaches() == 0)
				menuAllLevelTop.add(m);
		}
		model.addAttribute("menuList", menuAllLevelTop);
		
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
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		roleService.save(role, username);
		
		// 同步到activiti的group
		Group group = identityService.newGroup(role.getRoleName());
		group.setName(role.getRoleDesc());
		identityService.saveGroup(group);
		
		return "redirect:/role/list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model) {
		Role role = roleService.getById(id);
		
		if (role == null)
			throw new BusinessException("没有这个角色，无法删除");
		if(role.getRoleName().equals("ROLE_ADMIN"))
			throw new BusinessException("系统不允许删除ROLE_ADMIN，请联系管理员");
		
		String username = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		if(	roleService.del(role, username)) {
			// 同步到activiti的group
			identityService.deleteGroup(role.getRoleName());
		} else {
			throw new BusinessException("无法删除角色，请先清空角色与用户及权限的关系");
		}

		return "redirect:/role/list";
	}
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getUser(@PathVariable("id") long id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		Role role = roleService.getById(id);
		map.put("role",role);
		
		return map;
	}
	
	@RequestMapping(value="/getauth/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getAuth(@PathVariable("id") long id,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		//SysUser user = userService.getById(id);
		//map.put("role", user.getRoles());
		List<Map<String, String>> list = new ArrayList<>();
		for(Authority auth : roleService.getAuthsByRole(id)) {
			Map<String, String> map1 = new HashMap<>();
			map1.put("id", auth.getId().toString());
			map1.put("name", auth.getAuthorityName());
			list.add(map1);
		}
		
		map.put("auth", list);
		
		return map;
	}
	
	@RequestMapping(value="/getmenu/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMenu(@PathVariable("id") long id,Model model) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map<String, String>> list = new ArrayList<>();
		Role role = roleService.getById(id);
		if(role == null)
			throw new ParameterException("获取角色的菜单出错，无法根据id获得角色");
		
		for(Moudle moudle : role.getModules()) {
			Map<String, String> map1 = new HashMap<>();
			map1.put("id", moudle.getId().toString());
			map1.put("name", moudle.getName());
			list.add(map1);
		}
		
		map.put("menu", list);
		
		return map;
	}
	
	@RequestMapping(value="/role-update-auth", method = RequestMethod.POST)
	public String updateAuth(HttpServletRequest request) {
		
		String[] authIds = request.getParameterValues("auths");
		if(authIds == null)
			throw new ParameterException("无法更新角色的权限，因为参数为空");
		
		String roleId = request.getParameter("role_id");
		Role role = roleService.getById(Long.valueOf(roleId));
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		roleService.setAuths(username, role, authIds);
		
		return "redirect:/role/list";
	}
	
	@RequestMapping(value="/role-set-menu", method = RequestMethod.POST)
	public String setMenu(HttpServletRequest request) {
		String[] menuIds = request.getParameterValues("menus");
		if(menuIds == null)
			throw new ParameterException("无法设置角色的菜单，因为参数为空");
		
		String roleId = request.getParameter("role_id");
		Role role = roleService.getById(Long.valueOf(roleId));
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		roleService.setMenuByRole(username, role, menuIds);
		
		return "redirect:/role/list";
	}
}

