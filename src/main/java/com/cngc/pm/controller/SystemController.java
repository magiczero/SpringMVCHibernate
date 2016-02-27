package com.cngc.pm.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.UserService;

@Controller
public class SystemController {

	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header() {
		
		return "public/header";
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(Model model) {
		
		UserDetails user1 = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SysUser user = userService.getByUsername(user1.getUsername());

		//List<Moudle> menu1 = new ArrayList<>();
		Set<Moudle> menu1 = new LinkedHashSet<>();
		//List<Moudle> menu2 = new ArrayList<>();
		Set<Moudle> menu2 =  new LinkedHashSet<>();
		//for(Role role : user.getRoles()) {
		for(Role role : userService.getRolesByUser(user.getId())) {
			for(Moudle mod : role.getModules()) {
				if(mod.isEnable()) {
					//int level = mod.getLevel();
					int level = mod.reaches();
					if(level == 0) {
						menu1.add(mod);
					} else if(level == 1) {
						menu2.add(mod);
					}
				}
			}
		}
		
		model.addAttribute("menu1", menu1);
		model.addAttribute("menu2", menu2);

		//model.addAttribute("lastLogin", user.getLastWhile());
		
		return "public/menu";
	}
	
	@RequestMapping(value = "/contentbuttons", method = RequestMethod.GET)
	public String contentButtons() {
		
		return "public/content_buttons";
	}
}
