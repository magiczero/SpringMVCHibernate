package com.cngc.pm.common.web.common;

import javax.servlet.http.HttpSession;

import com.cngc.pm.model.SysUser;

public class UserUtil {

	/**
	 * 获取session中保存的用户id
	 * @param session
	 * @return
	 */
	public static String getUserId(HttpSession session)
	{
		SysUser user = (SysUser)session.getAttribute("user");

		if(user==null)
			return "admin";
		else
			return user.getUsername();
	}
	
	/**
	 * 获取session中保存的用户名
	 * @param session
	 * @return
	 */
	public static String getUserName(HttpSession session)
	{
		SysUser user = (SysUser)session.getAttribute("user");
		if(user==null)
			return "系统管理员";
		else
			return user.getName();
	}
}
