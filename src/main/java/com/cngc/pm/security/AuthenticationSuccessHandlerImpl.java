package com.cngc.pm.security;

import static com.cngc.utils.Common.getRemortIP;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.RecordsService;
import com.cngc.pm.service.UserService;

public class AuthenticationSuccessHandlerImpl implements
		AuthenticationSuccessHandler, InitializingBean {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandlerImpl.class);
	
	private boolean forwardToDestination = false; 
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();  

	private String defaultTargetUrl; 
	
	private static final String boardUrl = "/workflow/task/board";
//	private static final String initialPassword = "123456";
	private static final String modifyInitialPasswordUrl = "/modify-initial-password";
	@Resource
	private UserService userService;
	@Resource
	private RecordsService recordsService;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(defaultTargetUrl)) {
			logger.debug("未设置defaultTargetUrl属性");
			throw new Exception("必须设置defaultTargetUrl属性");
		}
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		boolean isSysAdmin = false;
		
		String username = authentication.getName();
		boolean isLeader = false;
		String roleName = "";
		for(GrantedAuthority ga :authentication.getAuthorities()) {
			String gaName = ga.getAuthority();
			if(gaName.equals("sys_admin")) {
				roleName = "sys_admin";
				isSysAdmin = true;
			} else if(gaName.equals("security_secrecy_admin")) {
				roleName = "security_secrecy_admin";
				isSysAdmin = true;
			} else if(gaName.equals("security_auditor")) {
				roleName = "security_auditor";
				isSysAdmin = true;
			}
			
			if(gaName.equals("WK_LEADER")) {
				isLeader = true;
			}
			
			
		}
		
		SysUser user = userService.getByUsername(username);
		request.getSession().setAttribute("lastLogin", user.getLastWhile());
		String ip = getRemortIP(request); 
		Date date = new Date();  
		logger.info("登录IP："+ip +"， 时间："+ date);
		
		//更新登录时间
		user.setLastWhile(new java.sql.Timestamp(date.getTime()));
		user.setLoginIP(ip);
		userService.update(user, "系统");

        //记录到日志表
		Records record = new Records();
		record.setUsername(username);
		if(isSysAdmin)
			record.setType(RecordsType.memberlogin);
		else
			record.setType(RecordsType.login);
		record.setDesc(username + " 登入了系统");
		record.setIpAddress(ip);
		recordsService.save(record);
		
		//判断是否是管理员
		
		//判断是否更改了初始密码
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//		if(md5.isPasswordValid(user.getPassword(), initialPassword, user.getUsername())) {
		if(roleName.equals("sys_admin") || roleName.equals("security_secrecy_admin")) {
			this.redirectStrategy.sendRedirect(request, response, "/user/list");
		} else if(roleName.equals("security_auditor")){
			this.redirectStrategy.sendRedirect(request, response, "/records/list");
		} else if(user.isInitPwd()){
			this.redirectStrategy.sendRedirect(request, response, modifyInitialPasswordUrl); 
//			request.getRequestDispatcher(modifyInitialPasswordUrl).forward(request, response);  
		} else {
	        if(this.forwardToDestination){  
	            logger.info("Login success,Forwarding to "+this.defaultTargetUrl);  
	              
	            request.getRequestDispatcher(this.defaultTargetUrl).forward(request, response);  
	        }else{  
	        	if(isLeader) {
	        		logger.info("Login success,Redirecting to board");
	        		
	        		 this.redirectStrategy.sendRedirect(request, response, boardUrl); 
	        	} else {
		            logger.info("Login success,Redirecting to "+this.defaultTargetUrl);  
		              
		            this.redirectStrategy.sendRedirect(request, response, this.defaultTargetUrl);
	        	}
	        }  
		}
        
	}

	public void setForwardToDestination(boolean forwardToDestination) {
		this.forwardToDestination = forwardToDestination;
	}

	public void setDefaultTargetUrl(String defaultTargetUrl) {
		this.defaultTargetUrl = defaultTargetUrl;
	}
}
