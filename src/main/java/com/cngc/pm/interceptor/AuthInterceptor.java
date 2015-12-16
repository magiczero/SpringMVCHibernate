package com.cngc.pm.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cngc.pm.model.Resources;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Resource
	private UserService userService;
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String[] notFilter = new String[]{"initLogin", "login", "loginout","403"};
		
		String url = request.getRequestURI();
		
		
		if(url.indexOf("resources") == -1) {
			 // 是否过滤
            boolean doFilter = true;
            
            for (String s : notFilter) {
                if (url.indexOf(s) != -1) {
                    // 如果uri中包含不过滤的uri，则不进行过滤
                    doFilter = false;
                    break;
                }
            }
            if(doFilter) {
            	SysUser user = (SysUser)request.getSession().getAttribute("user");
            	String cxt = request.getContextPath();
            	//System.out.println("context：" + cxt);
				PathMatcher  urlMatcher = new AntPathMatcher();
				
				//System.out.println("访问路径：" + url);
				for(Resources re : userService.getResourcesByUser(user)) {
					
					//System.out.println("数据库：" + re.getPath());
					if(urlMatcher.match( cxt+re.getPath(),url)) {
						return true;
					}
				}
				
				//没有权限
		        //response.sendRedirect("/WEB-INF/403.jsp");
				request.getRequestDispatcher("/403").forward(request,response);
				return false;
            }
		}
		return true;
	}
}
