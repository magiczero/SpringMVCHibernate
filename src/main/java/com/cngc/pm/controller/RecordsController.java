package com.cngc.pm.controller;

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
		
		return "records/list";
	}
}
