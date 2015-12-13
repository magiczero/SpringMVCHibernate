package com.cngc.pm.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AttachmentFilter  implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
//		String path = req.getRequestURI();
//		SysUser user = (SysUser)req.getSession().getAttribute("user");
		HttpServletResponse res = (HttpServletResponse)response;
		String ctp=req.getSession().getServletContext().getContextPath();
		
//		for(Role role : user.getRoles()) {
//			for(Authority auth : role.getAuths()) {
//				for(Resources resource: auth.getSetResources()) {
//					if(path.endsWith(resource.getPath()) || path.replaceAll("/[^/]+$","").endsWith(resource.getPath()) ) {
//						chain.doFilter(request, response);	
//						return;
//					}
//				}
//			}
//		}
		
		
		res.sendRedirect(ctp+"/403.jsp");		//无权限
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
