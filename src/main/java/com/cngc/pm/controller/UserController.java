package com.cngc.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.IdentityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.exception.BusinessException;
import com.cngc.pm.common.web.BaseController;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.DocumentService;
import com.cngc.pm.service.GroupService;
import com.cngc.pm.service.RoleService;
import com.cngc.pm.service.UserService;
import com.cngc.utils.UserSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController  {

	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private IdentityService identityService;
	@Resource
	private GroupService groupService;
	@Resource
	private DocumentService docService;
	
	@RequestMapping(value = "/name-check")
	@ResponseBody  
	public Map<String,Object> validateAssetNum(@RequestParam String fieldId,@RequestParam String fieldValue ) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		String msg = "";
		boolean status = true;
		
		if("username".equals(fieldId) && (fieldValue!=null || !"".equals(fieldValue))) {
			if(userService.getByUsername(fieldValue.trim()) == null) {
				msg = "通过";
			} else {
				status = false;
				msg = "用户名已经存在，请重新填写";
				
			}
		}
		// field, status, message不可更改，和前台ajax紧耦合
		map.put("fieldId", fieldId);
		map.put("status", status);
		map.put("message", msg);
		return map;
	}
	
	@RequestMapping(value="/get-user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUserByGroup(@PathVariable("id") long id) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper(); 
		SimpleModule module = new SimpleModule();  
		module.addSerializer(SysUser.class, new UserSerializer());
		mapper.registerModule(module);
		map.put("user", mapper.writeValueAsString(userService.getById(id)));
		return map;
	}
	
	@RequestMapping(value="/get-users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getUsersByGroup(@PathVariable("id") long id) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		Group group = groupService.getById(id);
		ObjectMapper mapper = new ObjectMapper(); 
		SimpleModule module = new SimpleModule();  
		module.addSerializer(SysUser.class, new UserSerializer());
		mapper.registerModule(module);
		String value = "[";
		int count=0;
		for(SysUser user : group.getUsers()) {
			value += mapper.writeValueAsString(user)+",";
			count++;
		}
			
		
		for(Group group1 : group.getChild()) {
				for(SysUser user : group1.getUsers()) {
					value += mapper.writeValueAsString(user)+",";
					count++;
				}
			
			for(Group group2 : group1.getChild()) {
				for(SysUser user : group2.getUsers()) {
					value += mapper.writeValueAsString(user)+",";
					count++;
				}
				
			}
		}
		if(count ==0) {
			value = "";
		} else {
			value = value.substring(0, value.length()-1)+"]";
		}
		map.put("users", value);
		return map;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", userService.getAll());
		model.addAttribute("rolelist",roleService.getAll());
		model.addAttribute("groupList", groupService.getAllTop());
		
		return "sysmanage/user-list";
	}
	
	@RequestMapping(value="/update-pwd", method = RequestMethod.POST)
	public String updatePassword(Model model, HttpServletRequest request) {
		UserDetails user1 = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = user1.getUsername();
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		String repeatPwd = request.getParameter("repeatPwd");
		
		SysUser user = userService.getByUsername(username);
		
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		
		if(md5.encodePassword(oldPwd, username).equals(user.getPassword())) {		//如果旧密码输入正确，则可以修改
			if(newPwd.equals(repeatPwd)) {
				user.setPassword(md5.encodePassword(newPwd, username));
				//user.setEnabled(true);
				
				userService.save(user, username, user.isEnabled());
			}
		} else {
			return "redirect:/500";
		}
		
		//成功后重新登陆
		return "redirect:/logout";
	}
	
	@RequestMapping(value="/save-user", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveUser(Model model, HttpServletRequest request) {
		Map<String , Object> map = new HashMap<>();
		map.put("flag", false);
		if(StringUtils.isEmpty(request.getParameter("group"))) {
			
			return map;
		}
		SysUser user = new SysUser();
		
		String username = request.getParameter("username");
		//判断唯一性
		if(userService.getByUsername(username) != null) {
			return map;
		}
		user.setUsername(username);
		user.setName(request.getParameter("name"));
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		user.setPassword(md5.encodePassword(	"123456",username));
		
		//部门
		Long groupId = Long.parseLong(request.getParameter("group"));
		Group group = groupService.getById(groupId);
		user.setGroup(group);
		String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
		//user.setEnabled(false);		//因为三员管理的关系，所以保存时设置为未启用
		user.setDepId(Integer.parseInt(request.getParameter("sort")));
		user.setDepName(request.getParameter("tel"));
		user.setMechId(Integer.parseInt(request.getParameter("priority")));
		user.setMechName(request.getParameter("mechName"));
				
		userService.save(user, currentName, true);
				
		map.put("flag", true);
		
		return map;
	}

	/**
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String save(Model model,HttpServletRequest request)	{
		SysUser user = new SysUser();
		Long id = Long.parseLong(request.getParameter("userform_id"));
		
		if(id!=0)
			user = userService.getById(id);
//		else
//			user = new SysUser();
		if(id==0)
		{
			user.setUsername(request.getParameter("username"));
		}
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		if(!StringUtils.isEmpty(request.getParameter("password")) )
		{
			//md5 password
			user.setPassword(md5.encodePassword(
					request.getParameter("password"),user.getUsername())
			);
		} else {
			user.setPassword(md5.encodePassword(	"123456",user.getUsername()));
		}
		user.setName(request.getParameter("name"));
		//部门
		Long groupId = Long.parseLong(request.getParameter("group"));
		Group group = groupService.getById(groupId);
		user.setGroup(group);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		//user.setEnabled(false);		//因为三员管理的关系，所以保存时设置为未启用
		user.setDepId(Integer.parseInt(request.getParameter("sort")));
		user.setDepName(request.getParameter("tel"));
		
		userService.save(user, username, false);
		
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/edit-user", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> editUser(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		try {
			Long userid = Long.parseLong(request.getParameter("euserid"));
			
			SysUser euser = userService.getById(userid);
			
			euser.setName(request.getParameter("ename"));
			euser.setDepId(Integer.parseInt(request.getParameter("esort")));
			euser.setDepName(request.getParameter("etel"));
			euser.setMechId(Integer.parseInt(request.getParameter("epriority")));
			euser.setMechName(request.getParameter("emechName"));
			String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
			
			userService.save(euser, currentUsername, true);
			
			map.put("flag", true);
		} catch(Exception e) {
			map.put("flag", false);
		}
		
		return map;
	}
	
	@RequestMapping(value="/enable/{id}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> enableUser(@PathVariable("id") long id) {
		Map<String, Object> map = new HashMap<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SysUser user = userService.getById(id);
		if(user == null) throw new BusinessException("无法启用用户，无此用户");
		
		if(userService.enableUser(username, user)) {
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}
		
		return map;
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.PUT)
	public @ResponseBody Map<String, Object> delUser(@PathVariable("id") long id) {
		Map<String, Object> map = new HashMap<>();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		SysUser user = userService.getById(id);
		if(user == null) throw new BusinessException("无法删除用户，无此用户");
		
		if(userService.disableUser(username, user)) {
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}
		
		return map;
	}
	
	@RequestMapping(value="/updaterole", method = RequestMethod.POST)
	public String updateRole(Model model,HttpServletRequest request){
		String sid = request.getParameter("roleform_id");
		String roleIds = request.getParameter("rolefrom_userrole");
		Long id = Long.valueOf(sid);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(id!=0)
		{
			SysUser user = userService.getById(id);
			
//			if(Common.isEmpty(roleIds)) {
//				//清空用户的所有角色
//				user.setUserRoles(null);
//				
//				userService.save(user,username, user.isEnabled());
//			} else {
				userService.setRole(username,user,roleIds);
//			}
			
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
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("id") long id,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		SysUser user = userService.getById(id);
		if(user == null) {
			map.put("flag", false);
		} else if(user.getUserRoles().size()==0 && docService.isEmptyDocsByUser(user)) {
			map.put("flag", true);
		} else {
			map.put("flag", false);
		}
		
		return map;
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
	
	@RequestMapping(value="/getengineer")
	@ResponseBody
	public Map<String,Object> getEngineer(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> usermap = new LinkedHashMap<String,String>();
		
		List<SysUser> users = userService.getEngineer();
		for(SysUser user:users)
		{
			usermap.put(user.getUsername(), user.getName());
		}
		map.put("users",usermap);
		
		return map;
	}
	@RequestMapping(value="/getleader")
	@ResponseBody
	public Map<String,Object> getLeader(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,String> usermap = new LinkedHashMap<String,String>();
		
		List<SysUser> users = userService.getLeader();
		for(SysUser user:users)
		{
			usermap.put(user.getUsername(), user.getName());
		}
		map.put("users",usermap);
		
		return map;
	}

}
