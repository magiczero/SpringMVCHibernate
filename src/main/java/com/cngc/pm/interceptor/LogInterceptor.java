package com.cngc.pm.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cngc.pm.annotation.ControllerLog;
import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.service.RecordsService;

public class LogInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private RecordsService recordsService;
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response,  
	        Object handler,ModelAndView modelAndView)throws Exception{  
		String reqMethod = request.getMethod();		//请求的使用的方法
		
		System.out.println("请求的方法："+reqMethod);
		HandlerMethod hm = (HandlerMethod) handler;
		ControllerLog log = hm.getMethodAnnotation(ControllerLog.class);
		if(log != null) {		//是否需要记录
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			String url = request.getRequestURI();
			
			if(url.indexOf("authority") != -1) {
				Records record = new Records();
				record.setUsername(username);
				record.setType(RecordsType.auth);
				//拦截权限
//				Method method = hm.getMethod();
				
//				if(method.getName().equals("save")) {
					//需要学习反射知识
//					Object[] objs = method.getParameterTypes();
//					Authority authority = (Authority)objs[0];
					//record.setDesc("添加了权限，权限标识："+ authority.getAuthorityMake());
//				}else {
					record.setDesc(username+log.description());
//				}
				
				recordsService.save(record);
			}
		}
	}
}
