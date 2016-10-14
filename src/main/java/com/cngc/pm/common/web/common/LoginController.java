package com.cngc.pm.common.web.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.service.UserService;

@Controller
public class LoginController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/initLogin.html", method = RequestMethod.GET)
	public String init() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return "login";
		}
				 
		if(RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass()))
			return "redirect:/workflow/task/mytask";
		else
			return "login";
	}
//	
//	@RequestMapping(value = "/login-after", method = RequestMethod.POST)
//	public String welcome(HttpServletRequest request) {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		SysUser user = userService.getByUsername(username);
//		if(user == null) {
//			return "login";
//		}
//		
//		//验证password
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();   
//		
//		if(user.getPassword().equals(md5.encodePassword(password, username))) {
//			//model.addAttribute("lastLogin", user.getLastWhile().toString());
//			request.getSession().setAttribute("lastLogin", user.getLastWhile());
//			request.getSession().setAttribute("user", user);
//			//更新IP
//			user.setLoginIP(request.getRemoteAddr());
//			//更新登录时间
//			user.setLastWhile(new java.sql.Timestamp(new java.util.Date().getTime()));
//			userService.update(user);
//			return "redirect:/workflow/task/list";
//		}
//		
//		return "login";
//	}
//	
//	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
//	public String loginOut(HttpSession session) {
//		session.invalidate();
//		
//		return "redirect:/initLogin";
//	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String noAuth(HttpSession session) {
		return "403";
	}
}
