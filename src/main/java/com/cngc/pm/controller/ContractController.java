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
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Contract;
import com.cngc.pm.service.ContractService;
import com.cngc.utils.PropertyFileUtil;

@Controller
@RequestMapping(value="/contract")
public class ContractController {
	
	@Resource
	private ContractService contractService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private FormService formService;
	@Resource
	private TaskService taskService;

	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("contract",new Contract());
		return "contract/add";
	}

	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("contract") Contract contract, HttpServletRequest request){
		
		contract.setCreatedUser(UserUtil.getUserId(request.getSession()));
		contract.setCreatedTime(new Date());
		contractService.save(contract);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", contractService.getAll());
		model.addAttribute("runtime",runtimeService);
		model.addAttribute("res", repositoryService);
		model.addAttribute("task", taskService);
		return "contract/list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			contractService.delById(id);
		
		return "redirect:/contract/list";
	}
	
	@RequestMapping(value="/manage/{id}/{type}", method = RequestMethod.GET)
	public String publish(@PathVariable("id") long id,@PathVariable("type") String type,Model model){
		
		String processKey = PropertyFileUtil.getStringValue("workflow.processkey.contract");
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey( processKey ).latestVersion().singleResult();
		Map<String,String> variables = new HashMap<String,String>();
		variables.put("id", String.valueOf(id));
		variables.put("type", type.toUpperCase());
		formService.submitStartFormData(processDefinition.getId(), variables);
		
		return "redirect:/contract/list";
	}
	
}
