package com.cngc.pm.common.web.common;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.cngc.pm.service.UserService;
import com.cngc.utils.PropertyFileUtil;

@Service
public class UserUtil {
	private static final Logger logger = Logger.getLogger(UserUtil.class);

	@Resource
	private UserService userService;
	
	/**
	 * 获取项目路径
	 * @param httpSession
	 * @return
	 */
	public String getContextPath(HttpSession httpSession) {
		return httpSession.getServletContext().getContextPath();
	}
	
	/**
	 * 获取用户真实姓名
	 * @param username
	 * @return
	 */
	public String getNameByUsername(String username) {
		return userService.getUserName(username);
	}

	/**
	 * 获取session中保存的用户id
	 * 
	 * @param auth
	 * @return
	 * @throws Exception 
	 */
	public String getUsernameByAuth(Authentication auth) throws Exception {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null) {
			logger.error("当前没有用户");
			throw new Exception("注意，当前没有用户，或者用户超时");
		}

		UserDetails user = (UserDetails) auth.getPrincipal();
		if (user == null)
			return null;
		else
			return user.getUsername();
	}

	/**
	 * 获取session中保存的用户名
	 * 
	 * @param session
	 * @return
	 */
	public SysUser getUserByAuth(Authentication auth) {
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null)
			return null;

		UserDetails user = (UserDetails) auth.getPrincipal();
		if (user == null)
			return null;
		else
		{
			return userService.getByUsername(user.getUsername());
		}
		
	}

	/**
	 * 当前用户是否为流程管理者
	 * 
	 * @param authentication
	 * @return
	 */
	public Boolean IsWorkflowManager(Authentication authentication) {

		return QueryUser(authentication,PropertyFileUtil.getStringValue("system.user.role.workflowmanager"));
	}
	
	/**
	 * 判断用户是否为领导
	 * @param authentication
	 * @return
	 */
	public Boolean IsLeader(Authentication authentication) {
		return QueryUser(authentication,PropertyFileUtil.getStringValue("system.user.leader"));
	}
	
	/**
	 * 判断用户是否为经理
	 * @param authentication
	 * @return
	 */
	public Boolean IsManager(Authentication authentication) {
		return QueryUser(authentication,PropertyFileUtil.getStringValue("system.user.manager"));
	}

	/**
	 * 当前用户是否为服务台
	 * 
	 * @param authentication
	 * @return
	 */
	public Boolean IsServiceDesk(Authentication authentication) {

		return QueryUser(authentication,PropertyFileUtil.getStringValue("system.user.servicedesk"));
	}

	/**
	 * 当前用户是否为普通用户
	 * 
	 * @param authentication
	 * @return
	 */
	public Boolean IsCommonUser(Authentication authentication) {

		return QueryUser(authentication,PropertyFileUtil.getStringValue("system.user.role.user"));
	}
	
	/**
	 * 当前用户是否为工程师
	 * @param authentication
	 * @return
	 */
	public Boolean IsEngineer(Authentication authentication) {
	
		return QueryUser(authentication,PropertyFileUtil.getStringValue("system.user.engineer"));
	}
	
	public Boolean QueryUser(Authentication auth,String userrole) {
		Boolean bresult = false;
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth == null)
			return bresult;
		
		UserDetails user = (UserDetails) auth.getPrincipal();
		if (user == null)
			return bresult;
		else {
			SysUser sysuser = userService.getByUsername(user.getUsername());
			Set<UserRole> roles = sysuser.getUserRoles();
			for (UserRole role : roles) {
				if ( userrole.indexOf(role.getRole().getRoleName())>=0 ) {
					bresult = true;
					break;
				}
			}
			return bresult;
		}
	}

	public boolean IsAdmin(Authentication authentication) {
		// TODO Auto-generated method stub
		return QueryUser(authentication,PropertyFileUtil.getStringValue("system.user.admin"));
	}
}
