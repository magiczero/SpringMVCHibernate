package com.cngc.pm.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.Knowledge;
import com.cngc.pm.service.KnowledgeService;

@Controller
@RequestMapping(value="/knowledge")
public class KnowledgeController {

	@Resource
	private KnowledgeService knowledgeService;
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("knowledge", new Knowledge());
		
		return "knowledge/add";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("knowledge") Knowledge knowledge,BindingResult result, HttpServletRequest request){
		knowledge.setApplyTime(new Date());
		knowledgeService.save(knowledge);
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", knowledgeService.getAll());
		return "knowledge/list";
	}
}
