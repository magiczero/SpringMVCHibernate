package com.cngc.pm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Knowledge;
import com.cngc.pm.service.KnowledgeService;

@Controller
@RequestMapping(value="/knowledge")
public class KnowledgeController {

	@Resource
	private KnowledgeService knowledgeService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private FormService formService;
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("knowledge", new Knowledge());
		return "knowledge/add";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("knowledge") Knowledge knowledge, BindingResult result, HttpServletRequest request){
		
		knowledge.setApplyUser(UserUtil.getUserId(request.getSession()));
		knowledge.setApplyTime(new Date());
		
		knowledgeService.save(knowledge);
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", knowledgeService.getAll());
		model.addAttribute("runtime",runtimeService);
		model.addAttribute("res", repositoryService);
		return "knowledge/list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			knowledgeService.delById(id);
		
		return "redirect:/knowledge/list";
	}
	
	@RequestMapping(value="/manage/{id}/{type}", method = RequestMethod.GET)
	public String publish(@PathVariable("id") long id,@PathVariable("type") String type,Model model){
		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("WK_KNOWLEDGE").latestVersion().singleResult();
		Map<String,String> variables = new HashMap<String,String>();
		variables.put("id", String.valueOf(id));
		variables.put("type", type.toUpperCase());
		formService.submitStartFormData(processDefinition.getId(), variables);
		
		return "redirect:/knowledge/list";
	}
}
