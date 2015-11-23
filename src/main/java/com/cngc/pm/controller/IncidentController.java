package com.cngc.pm.controller;

import java.io.IOException;
import java.util.Collection;
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
import org.activiti.engine.task.Task;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Incident;
import com.cngc.pm.model.Knowledge;
import com.cngc.pm.service.IncidentService;

@Controller
@RequestMapping(value="/incident")
public class IncidentController {

	@Resource
	private IncidentService incidentService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private TaskService taskService;
	@Resource
	private FormService formService;
	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("incident", new Incident());
		return "incident/add";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("incident") Incident incident, HttpServletRequest request){
		incident.setStatus("01");
		incident.setApplyTime(new Date());	
		incidentService.save(incident);
		
		//启动流程
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("INCIDENT").latestVersion().singleResult();
		Map<String,String> variables = new HashMap<String,String>();
		variables.put("id", String.valueOf(incident.getId()));
		formService.submitStartFormData(processDefinition.getId(), variables);
		
		return "redirect:/incident/list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", incidentService.getAll());
		model.addAttribute("runtime",runtimeService);
		model.addAttribute("res", repositoryService);
		return "incident/list";
	}
	
	@RequestMapping(value="/deal/{id}", method = RequestMethod.GET)
	public String deal(@PathVariable("id") long id,Model model){
		Incident incident = null;
		Task task = null;
		if(id!=0)
		{
			incident = incidentService.getById(id);
			// 获取当前事件的task信息
			task = taskService.createTaskQuery().processInstanceId(incident.getProcessInstanceId()).singleResult();
		}
		
		model.addAttribute("incident", incident);
		model.addAttribute("task",task);
		
		return "incident/deal";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			incidentService.delById(id);
		
		return "redirect:/incident/list";
	}
	
	@RequestMapping(value="/{id}/template/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveTemplate(@PathVariable("id") long id,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		Incident incident = incidentService.getById(id);
		try
		{
			incident.setTemplateData(mapper.writeValueAsString(request.getParameterMap()));
		}catch(JsonGenerationException e){
			
		}catch(JsonMappingException e){
			
		}catch(IOException e){
			
		}
		incidentService.save(incident);
		
		result.put("result", "true");
		
		return result;
	}
	@RequestMapping(value="/{id}/template/get")
	@ResponseBody
	public Map<String,Object> getTemplate(@PathVariable("id") long id){
		Map<String, Object> result = new HashMap<String, Object>();
			
		Incident incident = incidentService.getById(id);
		
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			result.put("result", mapper.readValue(incident.getTemplateData(),Map.class));
		}catch(JsonParseException e){
			
		}catch(JsonMappingException e){
			
		}catch(IOException e){
			
		}
		
		return result;
	}
}
