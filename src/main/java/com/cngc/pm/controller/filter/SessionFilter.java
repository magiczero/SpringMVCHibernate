package com.cngc.pm.controller.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//不过滤的url
		String[] notFilter = new String[]{"initLogin", "login", "loginout","403"};
		//请求的url
		String uri = request.getRequestURI();
		//System.out.println(uri);
		
		if(uri.indexOf("resources") == -1) {
			 // 是否过滤
            boolean doFilter = true;
            
            for (String s : notFilter) {
                if (uri.indexOf(s) != -1) {
                    // 如果uri中包含不过滤的uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }
            
            if(doFilter) {
            	Object obj = request.getSession().getAttribute("user");
            	
            	if(null == obj) {
            		request.setCharacterEncoding("UTF-8");
                    response.setCharacterEncoding("UTF-8");
                   
                    request.getRequestDispatcher("/initLogin").forward(request,response);
            	} else {
                    // 如果session中存在登录者实体，则继续
                    filterChain.doFilter(request, response);
                }
            } else {
                // 如果不执行过滤，则继续
                filterChain.doFilter(request, response);
            }
		}  else {
            // 如果是resources，则继续
            filterChain.doFilter(request, response);
        }
	}

}
