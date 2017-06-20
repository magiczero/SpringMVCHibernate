package com.cngc.pm.common.web.common;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.service.UserService;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Resource
	private UserService userService;
	@Resource
	private UserUtil userUtil;
	
	@RequestMapping(value = "/initLogin.html")
	public String initLoginPage() {
//	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	    if(auth instanceof AnonymousAuthenticationToken){
	      return "login";
//	    }else{
//	    	if(userUtil.IsLeader(auth)) {
//				return "redirect:/workflow/task/board";
//			} else
//				return "redirect:/workflow/task/mytask";
//	    }
		//	return "login";
	}
	
	@RequestMapping(value = "/welcome.html", method = RequestMethod.GET)
	public String welcomePage(Authentication authentication) {
			if(authentication == null) {
				return "redirect:/initLogin.html";
			}
			if(RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass()))
				logger.info(authentication.getName()+" 用户是从Remember Me Cookie自动登录");
			//美丽的错误，其实不用这样的
			if(userUtil.IsLeader(authentication)) {
				return "redirect:/workflow/task/board";
			} else
				return "redirect:/workflow/task/mytask";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String noAuth() {
		return "403";
	}
}
