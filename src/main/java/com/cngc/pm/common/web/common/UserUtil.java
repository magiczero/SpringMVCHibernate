package com.cngc.pm.common.web.common;

import javax.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.service.UserService;

public class UserUtil {

	@Resource
	private static UserService userService;
	/**
	 * 获取session中保存的用户id
	 * @param session
	 * @return
	 */
	public static String getUserId(Authentication authentication)
	{	
		User user = (User)authentication.getPrincipal();
		if(user==null)
			return "";
		else
			return user.getUsername();
		//SysUser user = userService.getByUsername(user1.getUsername());
	}
	
	
	/**
	 * 获取session中保存的用户名
	 * @param session
	 * @return
	 */
	public static String getUserName(Authentication authentication)
	{
		User user = (User)authentication.getPrincipal();
		if(user==null)
			return "";
		else
		{
			SysUser sysuser = userService.getByUsername(user.getUsername());
			return sysuser.getUsername();
		}
	}
}
