package com.cngc.pm.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.Records;
import com.cngc.pm.service.RecordsService;
import com.googlecode.genericdao.search.SearchResult;

@Controller
@RequestMapping("/records")
public class RecordsController {

	@Resource
	private RecordsService recordsService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	//@ResponseStatus(HttpStatus.)
	public String list(Model model, Integer offset, Integer maxResults, HttpServletRequest request) {
		boolean isPremiss = false;
		String roleName = "";
		//查看用户的权限
		for(GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
			if(ga.getAuthority().equals("security_secrecy_admin")) {
				isPremiss = true;
				roleName = "security_secrecy_admin";
				break;
			}
			if(ga.getAuthority().equals("security_auditor")) {
				isPremiss = true;
				roleName = "security_auditor";
				break;
			}
		}
		if(isPremiss) {
			SearchResult<Records> result = recordsService.getAllWithPage(roleName,offset, maxResults);
			
			model.addAttribute("records", result.getResult());
			model.addAttribute("count", result.getTotalCount());
			model.addAttribute("offset", offset);
			model.addAttribute("url", request.getRequestURI());
			
			//根据角色获取用户
			model.addAttribute("users", recordsService.getUsersByRole("sys_admin","security_secrecy_admin","ROLE_ADMIN"));
			
			return "records/list";
		} else {
			return "403";
		}
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Model model, HttpServletRequest request) throws ParseException {
		boolean isPremiss = false;
		String roleName = "";
		//查看用户的权限
		for(GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
			if(ga.getAuthority().equals("security_secrecy_admin")) {
				isPremiss = true;
				roleName = "security_secrecy_admin";
				break;
			}
			if(ga.getAuthority().equals("security_auditor")) {
				isPremiss = true;
				roleName = "security_auditor";
				break;
			}
		}
		if(isPremiss) {
			String username = request.getParameter("username");
			String[] types = null;
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			//System.out.println("用户："+username + "，类型：" + types + "，开始时间："+start + "，结束时间："+end);
			List<Integer> typeList = new ArrayList<>();
			if(request.getParameterValues("type") == null) {
				types = new String[2];
				if(roleName.equals("security_secrecy_admin")){
					types[0] = "2";
					types[1] = "3";
				} else if(roleName.equals("security_auditor")) {
					types[0] = "1";
					types[1] = "7";
				}
				
			} else {
				types = request.getParameterValues("type");
			}
			
			if(types != null && types.length>0)
				for(int i = 0; i < types.length; i++) {
					typeList.add(Integer.valueOf(types[i]));
				}
			
			model.addAttribute("records", recordsService.searchList(username, typeList, start, end));
			//根据角色获取用户
			model.addAttribute("users", recordsService.getUsersByRole("sys_admin","security_secrecy_admin","ROLE_ADMIN"));
			
			return "records/search-list";
		} else {
			return "403";
		}
	}
}
