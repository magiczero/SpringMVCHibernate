package com.cngc.pm.controller;

import java.io.IOException;
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

import com.cngc.pm.model.Change;
import com.cngc.pm.service.ChangeService;

@Controller
@RequestMapping(value="/change")
public class ChangeController {

	@Resource
	private ChangeService changeService;
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
		model.addAttribute("change", new Change());
		return "change/add";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("change") Change change, HttpServletRequest request){
		change.setStatus("01");
		change.setApplyTime(new Date());	
		changeService.save(change);
		
		//启动流程
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("CHANGE").latestVersion().singleResult();
		Map<String,String> variables = new HashMap<String,String>();
		variables.put("id", String.valueOf(change.getId()));
		formService.submitStartFormData(processDefinition.getId(), variables);
		
		return "redirect:/change/list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", changeService.getAll());
		model.addAttribute("runtime",runtimeService);
		model.addAttribute("res", repositoryService);
		return "change/list";
	}
	
	@RequestMapping(value="/deal/{id}", method = RequestMethod.GET)
	public String deal(@PathVariable("id") long id,Model model){
		Change change = null;
		Task task = null;
		if(id!=0)
		{
			change = changeService.getById(id);
			// 获取当前事件的task信息
			task = taskService.createTaskQuery().processInstanceId(change.getProcessInstanceId()).singleResult();
		}
		
		model.addAttribute("change", change);
		model.addAttribute("task",task);
		
		return "change/deal";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			changeService.delById(id);
		
		return "redirect:/change/list";
	}
	
	@RequestMapping(value="/{id}/template/save",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveTemplate(@PathVariable("id") long id,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		
		//JSONObject json = JSONObject.fromObject(request.getParameterMap());
		ObjectMapper mapper = new ObjectMapper();
		
		Change change = changeService.getById(id);
		//event.setTemplateData(json.toString());
		try
		{
			change.setTemplateData(mapper.writeValueAsString(request.getParameterMap()));
		}catch(JsonGenerationException e){
			
		}catch(JsonMappingException e){
			
		}catch(IOException e){
			
		}
		changeService.save(change);
		
		result.put("result", "true");
		
		return result;
	}
	@RequestMapping(value="/{id}/template/get")
	@ResponseBody
	public Map<String,Object> getTemplate(@PathVariable("id") long id){
		Map<String, Object> result = new HashMap<String, Object>();
			
		Change change = changeService.getById(id);
		
		//JSONObject json = JSONObject.fromObject(event.getTemplateData());
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			result.put("result", mapper.readValue(change.getTemplateData(),Map.class));
		}catch(JsonParseException e){
			
		}catch(JsonMappingException e){
			
		}catch(IOException e){
			
		}
		
		return result;
	}
}
