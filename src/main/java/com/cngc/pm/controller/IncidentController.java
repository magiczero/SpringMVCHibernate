package com.cngc.pm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.Incident;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.service.ChangeItemService;
import com.cngc.pm.service.ChangeService;
import com.cngc.pm.service.IncidentService;
import com.cngc.pm.service.ItilRelationService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.utils.PropertyFileUtil;

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
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private ItilRelationService itilRelationService;
	@Resource
	private CiService ciService;
	@Resource
	private ChangeItemService changeitemService;
	@Resource
	private ChangeService changeService;

	
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("incident", new Incident());
		model.addAttribute("source", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.source")).getResult());
		model.addAttribute("category", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.category")).getResult());
		model.addAttribute("type", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.type")).getResult());
		model.addAttribute("influence", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.influence")).getResult());
		model.addAttribute("critical", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.critical")).getResult());
		model.addAttribute("priority", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.priority")).getResult());
		return "incident/add";
	}
	@RequestMapping(value="/addbyuser",method = RequestMethod.GET)
	public String addbyuser(Model model){
		return "incident/addbyuser";
	}
	
	@RequestMapping(value="/savebyuser",method = RequestMethod.POST)
	public String savebyuser(Model model,HttpServletRequest request){
		String abs = request.getParameter("fm_abs");
		String detail = request.getParameter("fm_detail");
		
		Incident incident = new Incident();
		incident.setAbs(abs);
		incident.setDetail(detail);
		incident.setSource("04");
		incident.setInfluence("04");
		incident.setCritical("04");
		incident.setPriority("04");
		incident.setType("01");
		incident.setStatus("01");
		incident.setApplyUser(UserUtil.getUserId(request.getSession()));
		incident.setApplyTime(new Date());	
		incidentService.save(incident);
		
		//启动流程
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey("INCIDENT").latestVersion().singleResult();
		Map<String,String> variables = new HashMap<String,String>();
		variables.put("id", String.valueOf(incident.getId()));
		formService.submitStartFormData(processDefinition.getId(), variables);
		
		return "incident/mylist";
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
		model.addAttribute("list", incidentService.getNotFinished().getResult());
		model.addAttribute("runtime",runtimeService);
		model.addAttribute("res", repositoryService);
		model.addAttribute("count", incidentService.getCountByStatus());
		model.addAttribute("group",syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.status")).getResult());
		return "incident/list";
	}
	@RequestMapping(value="/mylist",method = RequestMethod.GET)
	public String mylist(Model model){
		model.addAttribute("list", incidentService.getNotFinished().getResult());
		model.addAttribute("runtime",runtimeService);
		model.addAttribute("res", repositoryService);
		return "incident/mylist";
	}
	@RequestMapping(value="/stats",method = RequestMethod.GET)
	public String stats(Model model,HttpServletRequest request){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
		Calendar now = Calendar.getInstance();
		if(startTime==null || startTime.isEmpty())
			startTime = String.valueOf( now.get(Calendar.YEAR) ) + "-01-01";
		if(endTime==null || endTime.isEmpty())
		{
			endTime = formatter.format( now.getTime());
		}
		model.addAttribute("count", incidentService.getCountByCategory(startTime,endTime));
		model.addAttribute("group", syscodeService.getParentCodeByType(PropertyFileUtil.getStringValue("syscode.incident.category")).getResult());
		return "incident/stats";
	}
	@RequestMapping(value="/list/{status}",method = RequestMethod.GET)
	public String list(@PathVariable("status") String status,Model model){
		model.addAttribute("list", incidentService.getByStatus(status).getResult());
		model.addAttribute("runtime",runtimeService);
		model.addAttribute("res", repositoryService);
		model.addAttribute("count", incidentService.getCountByStatus());
		model.addAttribute("status", syscodeService.getCode(status, PropertyFileUtil.getStringValue("syscode.incident.status")));
		model.addAttribute("group",syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.status")).getResult());
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
		model.addAttribute("relIncidents", incidentService.getByApplyUser(incident.getApplyUser()).getResult());
		List<Ci> cilist = ciService.getByUser(incident.getApplyUser()).getResult();
		model.addAttribute("relCis", cilist);
		
		List<Long> ciids = new LinkedList<Long>();
		for(Ci ci:cilist)
			ciids.add(ci.getId());
		List<ChangeItem> items = changeitemService.getByCi(ciids).getResult();
		List<Long> changeids = new LinkedList<Long>();
		for(ChangeItem item:items)
			changeids.add(item.getChangeId());
		
		model.addAttribute("relchanges", changeService.getByIds(changeids).getResult());
		
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
	@RequestMapping(value="/mystats",method = RequestMethod.GET)
	public String mystats(Model model,HttpServletRequest request){
/*		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
		Calendar now = Calendar.getInstance();
		if(startTime==null || startTime.isEmpty())
			startTime = String.valueOf( now.get(Calendar.YEAR) ) + "-01-01";
		if(endTime==null || endTime.isEmpty())
		{
			endTime = formatter.format( now.getTime());
		}
		model.addAttribute("count", incidentService.getCountByCategory(startTime,endTime));
		model.addAttribute("group", syscodeService.getParentCodeByType(PropertyFileUtil.getStringValue("syscode.incident.category")).getResult());*/
		//String columnCategory = request.getParameter("columnCategory");
		//String rowCategory = request.getParameter("rowCategory");
		//if(rowCategory!=null)
		//	model.addAttribute("stat", incidentService.getStats(columnCategory, rowCategory));
		
		return "incident/mystats";
	}
	@RequestMapping(value="/search",method = RequestMethod.GET)
	public String search(Model model,HttpServletRequest request){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd"); 
		Calendar now = Calendar.getInstance();
		if(startTime==null || startTime.isEmpty())
			startTime = String.valueOf( now.get(Calendar.YEAR) ) + "-01-01";
		if(endTime==null || endTime.isEmpty())
		{
			endTime = formatter.format( now.getTime());
		}
		Date startDate=null,endDate=null;
		
		try {
			startDate = formatter.parse(startTime);
			endDate = formatter.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String abs = request.getParameter("abstract");
		String applyUser = request.getParameter("applyUser");
		String engineer = request.getParameter("engineer");
		String satisfaction = request.getParameter("satisfaction");
		if(satisfaction!=null)
		{
			if(satisfaction.equals("00"))
				satisfaction = null;
		}
		model.addAttribute("satisfaction", syscodeService.getAllByType("INCIDENT_SATISFACTION").getResult());
		model.addAttribute("list", incidentService.search(abs,applyUser, engineer, satisfaction, startDate, endDate).getResult());
		return "incident/search";
	}
	@RequestMapping(value="/getjson/{ids}")
	@ResponseBody
	public Map<String,Object> getCi(@PathVariable("ids") String ids,HttpServletRequest request,Model model)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		List<Long> list = new ArrayList<Long>();
		
		String sids[] = ids.split(",");
		for(String s:sids)
			list.add(Long.valueOf(s));
		
		List<Incident> incidentlist = incidentService.getByIds(list).getResult();
		map.put("incidents", incidentlist);
		
		return map;
	}
}
