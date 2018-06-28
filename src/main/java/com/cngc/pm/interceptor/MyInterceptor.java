package com.cngc.pm.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cngc.pm.model.Resources;
import com.cngc.pm.service.ResourcesService;

public class MyInterceptor extends HandlerInterceptorAdapter {

//	@Autowired
//	private ResourcesDAO resDao;
	private static final Logger logger = Logger.getLogger(MyInterceptor.class);  
	
	@Resource
	private ResourcesService resourcesService;
	
	public void postHandle(HttpServletRequest request,HttpServletResponse response,  
	        Object handler,ModelAndView modelAndView)throws Exception{  
	        //Resources resources = resourcesService.getByURL(request.getServletPath());
		logger.info("拦截的url是："+request.getServletPath());
		Resources resources = resourcesService.getByPath(request.getServletPath());
	    if(resources != null)
	       	if("URL".equals(resources.getType()))
	       		modelAndView.addObject("moduleId", resources.getModule().getParent().getId());
	    } 
}
