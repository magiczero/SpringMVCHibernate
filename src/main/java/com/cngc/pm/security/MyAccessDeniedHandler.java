package com.cngc.pm.security;

import static com.cngc.utils.Constants.STREAM_OPERATE_LOG;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger logger = Logger.getLogger(MyAccessDeniedHandler.class);
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		// TODO Auto-generated method stub
		logger.info("没有权限："+request.getRequestURI());
		STREAM_OPERATE_LOG.info(this.getClass().getName()+"：没有权限访问这个url--" + request.getRequestURI());
		boolean isAjax = isAjaxRequest(request);
	    if(isAjax){
	        	String msg = "{\"flag\" : false, \"msg\" : \"没有权限\"}";  
	        	
	            //response.setCharacterEncoding("UTF-8");
	            response.setContentType("json");  
	            OutputStream outputStream = response.getOutputStream();  
	            outputStream.write(new String(msg.getBytes("GBK"),"ISO-8859-1").getBytes());  
	            outputStream.flush();  
	    }else {
	        	 response.sendRedirect(request.getContextPath()+"/403");
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
