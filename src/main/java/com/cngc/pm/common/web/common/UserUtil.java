package com.cngc.pm.common.web.common;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.UserRole;
import com.cngc.pm.service.UserService;
import com.cngc.utils.PropertyFileUtil;

@Service
public class UserUtil {

	@Resource
	private UserService userService;

	/**
	 * 获取session中保存的用户id
	 * 
	 * @param session
	 * @return
	 */
	public String getUserId(Authentication authentication) {
		if (authentication == null)
			return null;

		User user = (User) authentication.getPrincipal();
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
	public String getUserName(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		if (user == null)
			return "";
		else {
			SysUser sysuser = userService.getByUsername(user.getUsername());
			return sysuser.getUsername();
		}
	}

	/**
	 * 当前用户是否为流程管理者
	 * 
	 * @param authentication
	 * @return
	 */
	public Boolean IsWorkflowManager(Authentication authentication) {
		Boolean bresult = false;
		User user = (User) authentication.getPrincipal();
		if (user == null)
			return bresult;
		else {
			SysUser sysuser = userService.getByUsername(user.getUsername());
			Set<UserRole> roles = sysuser.getUserRoles();
			for (UserRole role : roles) {
				if (role.getRole().getRoleName()
						.equals(PropertyFileUtil.getStringValue("system.user.role.workflowmanager"))) {
					bresult = true;
					break;
				}
			}
			return bresult;
		}
	}

	/**
	 * 当前用户是否为服务台
	 * 
	 * @param authentication
	 * @return
	 */
	public Boolean IsServiceDesk(Authentication authentication) {
		Boolean bresult = false;
		User user = (User) authentication.getPrincipal();
		if (user == null)
			return bresult;
		else {
			SysUser sysuser = userService.getByUsername(user.getUsername());
			Set<UserRole> roles = sysuser.getUserRoles();
			for (UserRole role : roles) {
				if (role.getRole().getRoleName().equals(PropertyFileUtil.getStringValue("system.user.servicedesk"))) {
					bresult = true;
					break;
				}
			}
			return bresult;
		}
	}

	/**
	 * 当前用户是否为普通用户
	 * 
	 * @param authentication
	 * @return
	 */
	public Boolean IsCommonUser(Authentication authentication) {
		Boolean bresult = false;
		User user = (User) authentication.getPrincipal();
		if (user == null)
			return bresult;
		else {
			SysUser sysuser = userService.getByUsername(user.getUsername());
			Set<UserRole> roles = sysuser.getUserRoles();
			for (UserRole role : roles) {
				if (role.getRole().getRoleName().equals(PropertyFileUtil.getStringValue("system.user.role.user"))) {
					bresult = true;
					break;
				}
			}
			return bresult;
		}
	}
}
