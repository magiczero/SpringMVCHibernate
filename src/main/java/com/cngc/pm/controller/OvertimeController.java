package com.cngc.pm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/overtime")
public class OvertimeController {

	/**
	 * 进入加班申请页
	 * @param model
	 * @param request
	 * @param authentication
	 * @return
	 */
	@RequestMapping
	public String list(Model model, HttpServletRequest request, Authentication authentication) {
		
		return "overtime/index";
	}
}
