package com.cngc.pm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SystemController {

	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header() {
		
		return "public/header";
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menu() {
		
		return "public/menu";
	}
	
	@RequestMapping(value = "/contentbuttons", method = RequestMethod.GET)
	public String contentButtons() {
		
		return "public/content_buttons";
	}
}
