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

public class LoginFilter implements Filter {
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		
		if (req.getSession().getAttribute("user") == null ) {
			HttpServletResponse res = (HttpServletResponse)response;
			//String ctp=req.getSession().getServletContext().getContextPath();//.getServletContext().getContextPath();
			res.sendRedirect(req.getSession().getServletContext().getContextPath()+"/initLogin");
			//request.getRequestDispatcher(loginPage).forward(request, response);
			return;
		}
		chain.doFilter(request, response);	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
