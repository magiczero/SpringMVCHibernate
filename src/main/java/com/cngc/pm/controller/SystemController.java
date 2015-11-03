package com.cngc.pm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		
		model.addAttribute("user", user);
		
		return "public/menu";
	}
	
	@RequestMapping(value = "/contentbuttons", method = RequestMethod.GET)
	public String contentButtons() {
		
		return "public/content_buttons";
	}
}
