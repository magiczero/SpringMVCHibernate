package com.cngc.pm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		 boolean isAjax = isAjaxRequest(request);
	        System.out.println("是否是ajax请求：" + isAjax);
	        if(isAjax){
	            response.setCharacterEncoding("UTF-8");
	            response.setContentType("text/plain");
	            response.getWriter().write("权限不足");
	            response.getWriter().close();
	        }
	}

	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
	}
}
