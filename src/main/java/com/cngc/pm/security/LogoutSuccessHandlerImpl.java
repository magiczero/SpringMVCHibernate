package com.cngc.pm.security;

import static com.cngc.utils.Common.getRemortIP;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
//		
		if(authentication != null) {
			boolean isSysAdmin = false;
			for(GrantedAuthority ga : authentication.getAuthorities()){
				String gaName = ga.getAuthority();
				if(gaName.equals("sys_admin")||gaName.equals("security_secrecy_admin")||gaName.equals("security_auditor")) {
					isSysAdmin = true;
					break;
				} 
			}
//			System.out.println("adf:"+authentication.getAuthorities());
			String username = authentication.getName();
			 //记录到日志表
			Records record = new Records();
			record.setUsername(username);
			if(isSysAdmin)
				record.setType(RecordsType.memberlogin);
			else
				record.setType(RecordsType.login);
			record.setDesc(username + " 退出了系统");
			record.setIpAddress(getRemortIP(request));
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
