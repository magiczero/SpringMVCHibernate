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

/**
 * 登录及权限验证，已过时
 * @author HP
 *
 */
public class LoginFilter implements Filter {
	
	private FilterConfig config;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		
		String initlogin = config.getInitParameter("initLogin");
		String login = config.getInitParameter("login");
		String requestPath = req.getServletPath();
//		System.out.println("url : " + requestPath);
		if(requestPath.startsWith("/resources") || requestPath.endsWith("403.jsp") || requestPath.endsWith(initlogin) || requestPath.endsWith(login) || requestPath.endsWith("loginout") ) {
			chain.doFilter(request, response);
			return;
		}
		if (req.getSession().getAttribute("user") == null ) {
			HttpServletResponse res = (HttpServletResponse)response;
			//String ctp=req.getSession().getServletContext().getContextPath();//.getServletContext().getContextPath();
			res.sendRedirect(req.getSession().getServletContext().getContextPath()+"/initLogin");
			//request.getRequestDispatcher(loginPage).forward(request, response);
			return;
		}
		//chain.doFilter(request, response);
//		String path = req.getRequestURI();
//		SysUser user = (SysUser)req.getSession().getAttribute("user");
		HttpServletResponse res = (HttpServletResponse)response;
		String ctp=req.getSession().getServletContext().getContextPath();
//		String requestPath = req.getServletPath();
//		if(user == null && !requestPath.endsWith("initLogin") && !requestPath.endsWith("css") && !requestPath.endsWith("js") ) {
//			res.sendRedirect(ctp+"/initLogin");	
//			return;
//		}
//		for(Role role : user.getRoles()) {
//			for(Authority auth : role.getAuths()) {
//				for(Resources resource: auth.getSetResources()) {
//					if(path.endsWith(resource.getPath()) || path.replaceAll("/[^/]+$","").endsWith(resource.getPath()) ) {
//						//System.out.println(resource.getId());
//						chain.doFilter(request, response);	
//						return;
//					}
//				}
//			}
//		}
		
		
		//.getServletContext().getContextPath();
		res.sendRedirect(ctp+"/403.jsp");		//无权限
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
