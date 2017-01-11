package com.cngc.pm.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.service.RecordsService;

public class LogoutSuccessHandlerImpl extends  
	SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	@Resource
	private RecordsService recordsService;
	
	@Override  
    public void onLogoutSuccess  
      (HttpServletRequest request, HttpServletResponse response, Authentication authentication)   
      throws IOException, ServletException {  
		
		if(authentication != null) {
			String username = authentication.getName();
			 //记录到日志表
			Records record = new Records();
			record.setUsername(username);
			record.setType(RecordsType.login);
			record.setDesc(username + " 退出了系统");
			recordsService.save(record);
			
		}
		
		for(Cookie cookie :request.getCookies()) {
			cookie.setMaxAge(0);

			cookie.setPath(request.getSession().getServletContext().getContextPath());

			response.addCookie(cookie);
		}
		
		request.getRequestDispatcher("/initLogin.html").forward(request, response);
	}
}
