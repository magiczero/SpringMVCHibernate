package com.cngc.pm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.Moudle;
import com.cngc.pm.model.Role;
import com.cngc.pm.model.SysUser;

@Controller
public class SystemController {

	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header() {
		
		return "public/header";
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu(Model model, HttpSession session) {
		SysUser user = (SysUser)session.getAttribute("user");
		List<Moudle> menu1 = new ArrayList<>();
		List<Moudle> menu2 = new ArrayList<>();
		for(Role role : user.getRoles()) {
			for(Moudle mod : role.getModules()) {
				if(mod.isEnable()) {
					int level = mod.getLevel();
					if(level == 1) {
						menu1.add(mod);
					} else if(level == 2) {
						menu2.add(mod);
					}
				}
			}
		}
		
		model.addAttribute("menu1", menu1);
		model.addAttribute("menu2", menu2);
		model.addAttribute("user", user);
		
		return "public/menu";
	}
	
	@RequestMapping(value = "/contentbuttons", method = RequestMethod.GET)
	public String contentButtons() {
		
		return "public/content_buttons";
	}
}
