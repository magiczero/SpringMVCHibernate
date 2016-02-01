package com.cngc.pm.security;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.cngc.pm.model.Records;
import com.cngc.pm.model.RecordsType;
import com.cngc.pm.service.RecordsService;

public class LogoutHandlerImpl implements LogoutHandler {

	@Resource
	private RecordsService recordsService;
	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		if(authentication != null) {
			String username = authentication.getName();
			 //记录到日志表
			Records record = new Records();
			record.setUsername(username);
			record.setType(RecordsType.login);
			record.setDesc(username + " 退出了系统");
			recordsService.save(record);
		}
	}

}
