package com.cngc.pm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Change;
import com.cngc.pm.model.ChangeItem;
import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;
import com.cngc.pm.model.cms.Property;
import com.cngc.pm.service.ChangeItemService;
import com.cngc.pm.service.ChangeService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.UserService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.pm.service.cms.PropertyService;
import com.cngc.utils.Common;
import com.cngc.utils.PropertyFileUtil;

@Controller
@RequestMapping(value = "/change")
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
	@Resource
	private HistoryService historyService;
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private ChangeItemService changeitemService;
	@Resource
	private CiService ciService;
	@Resource
	private PropertyService propertyService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private UserService userService;

	/**
	 * 创建新变更
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("change", new Change());
		model.addAttribute("category",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.change.category")).getResult());
		model.addAttribute("risk", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.change.risk"))
				.getResult());
		model.addAttribute("influence",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.influence")).getResult());
		model.addAttribute("critical", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.critical"))
				.getResult());
		model.addAttribute("priority", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.priority"))
				.getResult());
		return "change/add";
	}

	/**
	 * 修改变更信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("change", changeService.getById(id));
		model.addAttribute("category",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.change.category")).getResult());
		model.addAttribute("risk", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.change.risk"))
				.getResult());
		model.addAttribute("influence",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.influence")).getResult());
		model.addAttribute("critical", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.critical"))
				.getResult());
		model.addAttribute("priority", syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.priority"))
				.getResult());
		return "change/add";
	}

	/**
	 * 保存变更信息
	 * 
	 * @param change
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("change") Change change, Authentication authentication) {
		if(change.getId()==null)
		{
			change.setStatus("01");
			change.setApplyTime(new Date());
			change.setApplyUser(userUtil.getUserId(authentication));
			changeService.save(change);
	
			// 启动流程
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.change")).active()
					.latestVersion().singleResult();
			if (processDefinition != null) {
				Map<String, String> variables = new HashMap<String, String>();
				variables.put("id", String.valueOf(change.getId()));
				formService.submitStartFormData(processDefinition.getId(), variables);
			}
		}
		else
		{
			Change newchange =changeService.getById(change.getId());
			newchange.setDescription(change.getDescription());
			newchange.setChangeType(change.getChangeType());
			newchange.setInfluence(change.getInfluence());
			newchange.setCritical(change.getCritical());
			newchange.setPriority(change.getPriority());
			newchange.setRisk(change.getRisk());
			changeService.save(newchange);
		}

		return "redirect:/change/list";
	}

	/**
	 * 未关闭变更信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Authentication authentication) {
		List<Task> tasks = null;
		List<Task> mytasks = null;
		List<Change> changes = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.change"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有为未关闭的变更信息
		changes = changeService.getNotFinished().getResult();
		if (userUtil.IsWorkflowManager(authentication)) // 流程管理者可查看所有变更任务信息
		{
			// 所有任务
			tasks = taskService.createTaskQuery()
					.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.change")).active()
					.list();
			taskmap = new HashMap<String, Task>();
			for (Task task : tasks)
				taskmap.put(task.getProcessInstanceId(), task);
		}
		if (userUtil.IsServiceDesk(authentication)) {
			// 服务台用户拥有修改权限
			model.addAttribute("ROLE_MODIFY", true);
		}
		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", changes);
		model.addAttribute("count", changeService.getCountByStatus());
		model.addAttribute("group",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.change.status")).getResult());

		return "change/list";
	}
	
	@RequestMapping(value = "/list/{status}", method = RequestMethod.GET)
	public String list(@PathVariable("status") String status, Model model, Authentication authentication) {
		List<Task> tasks = null;
		List<Task> mytasks = null;
		List<Change> changes = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.change"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有指定状态的变更信息
		changes = changeService.getByStatus(status).getResult();
		if (userUtil.IsWorkflowManager(authentication)) // 流程管理者可查看所有变更任务信息
		{
			// 所有任务
			tasks = taskService.createTaskQuery()
					.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.change")).active()
					.list();
			taskmap = new HashMap<String, Task>();
			for (Task task : tasks)
				taskmap.put(task.getProcessInstanceId(), task);
		}
		if (userUtil.IsServiceDesk(authentication)) {
			// 服务台用户拥有修改权限
			model.addAttribute("ROLE_MODIFY", true);
		}
		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", changes);
		model.addAttribute("status",
				syscodeService.getCode(status, PropertyFileUtil.getStringValue("syscode.change.status")));
		model.addAttribute("count", changeService.getCountByStatus());
		model.addAttribute("group",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.change.status")).getResult());

		return "change/list";
	}

	@RequestMapping(value = "/mydealedlist", method = RequestMethod.GET)
	public String myDealedList(Model model, Authentication authentication) {

		List<Task> tasks = null;
		Map<String, Task> taskmap = null;

		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.change")).active().list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		// 我参与的任务
		List<String> processInstanceIds = new ArrayList<String>();
		List<HistoricTaskInstance> mytasks = historyService.createHistoricTaskInstanceQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.change"))
				.taskAssignee(userUtil.getUserId(authentication)).list();
		for (HistoricTaskInstance task : mytasks)
			processInstanceIds.add(task.getProcessInstanceId());

		model.addAttribute("list", changeService.getByProcessInstance(processInstanceIds).getResult());
		model.addAttribute("task", taskmap);

		return "change/mydealedlist";
	}
	
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String stats(Model model, HttpServletRequest request) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}
		model.addAttribute("count", changeService.getCountByCategory(startTime, endTime));
		model.addAttribute("group",
				syscodeService.getParentCodeByType(PropertyFileUtil.getStringValue("syscode.change.category"))
						.getResult());
		return "change/stats";
	}

	/**
	 * 处理变更信息
	 * 
	 * @param id
	 * @param taskId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deal/{id}/{taskid}", method = RequestMethod.GET)
	public String deal(@PathVariable("id") long id, @PathVariable("taskid") String taskId, Model model) {
		Change change = null;
		Task task = null;

		if (id != 0)
			change = changeService.getById(id);
		if (taskId != null)
			// 获取当前事件的task信息
			task = taskService.createTaskQuery().taskId(taskId).singleResult();

		model.addAttribute("change", change);
		model.addAttribute("task", task);

		return "change/deal";
	}
	@RequestMapping(value = "/dealbyprocess/{pid}/{taskid}", method = RequestMethod.GET)
	public String dealByProcessInstanceId(@PathVariable("pid") String pid,@PathVariable("taskid") String taskid){
		
		return "redirect:/change/deal/" + changeService.getIdByProcessInstance(pid) + "/"+taskid;
	}

	/**
	 * 查看变更信息
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") long id, Model model) {
		Change change = null;
		if (id != 0)
			change = changeService.getById(id);

		model.addAttribute("change", change);

		return "change/view";
	}
	@RequestMapping(value = "/viewbyprocess/{pid}", method = RequestMethod.GET)
	public String viewByProcessInstanceId(@PathVariable("pid") String pid){
		
		return "redirect:/change/view/" + changeService.getIdByProcessInstance(pid);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model) {
		if (id != 0)
			changeService.delById(id);

		return "redirect:/change/list";
	}

	@RequestMapping(value = "/{id}/template/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveTemplate(@PathVariable("id") long id, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();

		// JSONObject json = JSONObject.fromObject(request.getParameterMap());
		ObjectMapper mapper = new ObjectMapper();

		Change change = changeService.getById(id);
		// event.setTemplateData(json.toString());
		try {
			change.setTemplateData(mapper.writeValueAsString(request.getParameterMap()));
		} catch (JsonGenerationException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}
		changeService.save(change);

		result.put("result", "true");

		return result;
	}

	@RequestMapping(value = "/{id}/template/get")
	@ResponseBody
	public Map<String, Object> getTemplate(@PathVariable("id") long id) {
		Map<String, Object> result = new HashMap<String, Object>();

		Change change = changeService.getById(id);

		// JSONObject json = JSONObject.fromObject(event.getTemplateData());
		ObjectMapper mapper = new ObjectMapper();
		try {
			result.put("result", mapper.readValue(change.getTemplateData(), Map.class));
		} catch (JsonParseException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}

		return result;
	}

	/**
	 * 查询历史变更信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}
		Date startDate = null, endDate = null;

		try {
			startDate = formatter.parse(startTime);

			endDate = formatter.parse(endTime);
			now.setTime(endDate);
			now.add(Calendar.DATE, 1);
			endDate = now.getTime();

		} catch (ParseException e) {
			e.printStackTrace();
		}

		String description = request.getParameter("description");
		String applyUser = request.getParameter("applyUser");
		String engineer = request.getParameter("engineer");
		String risk = request.getParameter("risk");
		if (risk != null) {
			if (risk.equals("00"))
				risk = null;
		}
		if (applyUser != null) {
			if (applyUser.equals("00")) // 查看全部
				applyUser = null;
		}
		if (engineer != null) {
			if (engineer.equals("00")) // 查看全部
				engineer = null;
		}
		model.addAttribute("risk", syscodeService.getAllByType("CHANGE_RISK").getResult());
		model.addAttribute("list", changeService.search(description, applyUser, engineer, risk, startDate, endDate)
				.getResult());
		List<SysUser> engineers = userService.getEngineer();
		model.addAttribute("engineers", engineers);
		model.addAttribute("users", engineers);

		return "change/search";
	}

	@RequestMapping(value = "/saveitems")
	@ResponseBody
	public Map<String, Object> saveItems(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long changeId = Long.valueOf(request.getParameter("change_id").toString());
		String ciIds = request.getParameter("ci_ids").toString();

		String ids[] = ciIds.split(",");
		for (String id : ids) {
			ChangeItem item = new ChangeItem();
			item.setChangeId(changeId);
			item.setCiId(Long.valueOf(id));
			changeitemService.save(item);
		}

		map.put("result", "true");

		return map;
	}

	@RequestMapping(value = "/deleteitem/{id}")
	@ResponseBody
	public Map<String, Object> deleteItems(HttpServletRequest request, Model model, @PathVariable("id") long id) {
		Map<String, Object> map = new HashMap<String, Object>();

		// Change change = changeService.getById(id);
		changeitemService.delById(id);

		map.put("result", "true");

		return map;
	}

	@RequestMapping(value = "/getitems/{id}")
	@ResponseBody
	public Map<String, Object> getItems(HttpServletRequest request, Model model, @PathVariable("id") long id) {
		Map<String, Object> map = new HashMap<String, Object>();

		Change change = changeService.getById(id);

		map.put("items", change.getItems());

		return map;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateitem")
	@ResponseBody
	public Map<String, Object> updateItem(HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		String id = request.getParameter("fm_id");
		String properties = request.getParameter("fm_properties");
		String propertiesName = request.getParameter("fm_propertiesName");

		ChangeItem item = changeitemService.getById(Long.valueOf(id));
		item.setProperties(properties);
		item.setPropertiesName(propertiesName);

		// 把值存入oldvalue中
		Ci ci = ciService.getById(item.getCiId());
		ObjectMapper mapper = new ObjectMapper();
		List<Property> propertylist = propertyService.getFields();
		Map<String, Property> fieldmap = new HashMap<String, Property>();
		for (Property p : propertylist)
			fieldmap.put(p.getPropertyId(), p);
		try {
			Map<String, String> propertymap = mapper.readValue(ci.getPropertiesData(), Map.class);
			Map<String, String> oldvalue = new HashMap<String, String>();
			String ps[] = properties.split(",");
			for (String s : ps) {
				if (s.indexOf("CMS_FIELD_") == 0)
					oldvalue.put(s, Common.getFieldValueByName(ci, fieldmap.get(s).getPropertyConstraint()).toString());
				else
					oldvalue.put(s, propertymap.get(s));
			}

			item.setOldValue(mapper.writeValueAsString(oldvalue));

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		changeitemService.save(item);

		map.put("result", "true");

		return map;
	}

	@RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
	public String items(@PathVariable("id") long id, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		Change change = changeService.getById(id);
		model.addAttribute("items", change.getItems());
		for (ChangeItem item : change.getItems()) {
			map.put(item.getCiId().toString(), ciService.getById(item.getCiId()));
		}
		model.addAttribute("cis", map);

		return "change/items";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{id}/saveitems", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveItems(@PathVariable("id") long id, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();

		Change change = changeService.getById(id);
		Set<ChangeItem> items = change.getItems();

		Map<String, Map<String, String>> values = new HashMap<String, Map<String, String>>();
		for (ChangeItem item : items) {
			values.put(item.getCiId().toString(), new HashMap<String, String>());
		}
		// 从request中读取参数然后转换
		Set<Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String key = entry.getKey();
			String cid = key.substring(0, key.indexOf('_'));
			String name = key.substring(key.indexOf('_') + 1);
			values.get(cid).put(name, entry.getValue()[0]);
		}

		try {
			for (ChangeItem item : items) {
				item.setNewValue(mapper.writeValueAsString(values.get(item.getCiId().toString())));
				changeitemService.save(item);
			}

		} catch (JsonGenerationException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}

		result.put("result", "true");

		return result;
	}

	@RequestMapping(value = "/getbyci/{ciid}")
	@ResponseBody
	public Map<String, Object> getCi(@PathVariable("ciid") Long ciId, HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> list = new ArrayList<Long>();

		List<ChangeItem> items = changeitemService.getByCi(ciId).getResult();
		for (ChangeItem item : items)
			list.add(item.getChangeId());

		List<Change> changelist = changeService.getByIds(list).getResult();
		map.put("changes", changelist);

		return map;
	}
}
