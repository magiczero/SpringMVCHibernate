package com.cngc.pm.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.UserService;

@Controller
public class LoginController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/initLogin", method = RequestMethod.GET)
	public String init() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String welcome(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		SysUser user = userService.getByUsername(username);
		if(user == null) {
			return "login";
		}
		
		//验证password
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();   
		
		if(user.getPassword().equals(md5.encodePassword(password, username))) {
			//model.addAttribute("lastLogin", user.getLastWhile().toString());
			request.getSession().setAttribute("lastLogin", user.getLastWhile());
			request.getSession().setAttribute("user", user);
			//更新IP
			user.setLoginIP(request.getRemoteAddr());
			//更新登录时间
			user.setLastWhile(new java.sql.Timestamp(new java.util.Date().getTime()));
			userService.update(user);
			return "redirect:/document";
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public String loginOut(HttpSession session) {
		session.invalidate();
		
		return "redirect:/initLogin";
	}
}
