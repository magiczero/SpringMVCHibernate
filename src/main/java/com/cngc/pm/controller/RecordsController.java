package com.cngc.pm.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
		SearchResult<Records> result = recordsService.getAllWithPage(offset, maxResults);
		
		model.addAttribute("records", result.getResult());
		model.addAttribute("count", result.getTotalCount());
		model.addAttribute("offset", offset);
		model.addAttribute("url", request.getRequestURI());
		
		//根据角色获取用户
		model.addAttribute("users", recordsService.getUsersByRole("sys_admin","security_secrecy_admin","ROLE_ADMIN"));
		
		return "records/list";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchList(Model model, HttpServletRequest request) throws ParseException {
		String username = request.getParameter("username");
		String[] types = request.getParameterValues("type");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		//System.out.println("用户："+username + "，类型：" + types + "，开始时间："+start + "，结束时间："+end);
		
		int[] type = new int[types.length];
		
		for(int i = 0; i < types.length; i++) {
			type[i] = Integer.valueOf(types[i]);
		}
		
		model.addAttribute("records", recordsService.searchList(username, type, start, end));
		//根据角色获取用户
		model.addAttribute("users", recordsService.getUsersByRole("sys_admin","security_secrecy_admin","ROLE_ADMIN"));
		
		return "records/search-list";
	}
}
