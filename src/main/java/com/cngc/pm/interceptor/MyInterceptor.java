package com.cngc.pm.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cngc.pm.model.Resources;
import com.cngc.pm.service.ResourcesService;

public class MyInterceptor extends HandlerInterceptorAdapter {

//	@Autowired
//	private ResourcesDAO resDao;
	
	@Resource
	private ResourcesService resourcesService;
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response,  
	        Object handler,ModelAndView modelAndView)throws Exception{  
	        Resources resources = resourcesService.getByURL(request.getServletPath());
	        if(resources != null)
	        	if("URL".equals(resources.getType()))
	        		modelAndView.addObject("moduleId", resources.getModule().getParent().getId());
	    } 
}
