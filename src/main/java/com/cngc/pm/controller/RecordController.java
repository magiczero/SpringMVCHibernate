package com.cngc.pm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Income;
import com.cngc.pm.model.Inspection;
import com.cngc.pm.model.Training;
import com.cngc.pm.service.IncidentService;
import com.cngc.pm.service.IncomeService;
import com.cngc.pm.service.InspectionService;
import com.cngc.pm.service.SecJobService;
import com.cngc.pm.service.TrainingService;
import com.cngc.pm.service.UpdateService;
import com.cngc.pm.service.UserService;
import com.cngc.utils.PropertyFileUtil;

@Controller
@RequestMapping(value = "/record")
public class RecordController {
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private FormService formService;
	@Resource
	private UpdateService updateService;
	@Resource
	private InspectionService inspectionService;
	@Resource
	private TaskService taskService;
	@Resource
	private IncomeService incomeService;
	@Resource
	private TrainingService trainingService;
	@Resource
	private IncidentService incidentService;
	@Resource
	private SecJobService secjobService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private UserService userService;

	/**
	 * 获取升级记录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest request, Authentication authentication) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}

		List<Task> tasks = null;
		List<Task> mytasks = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.update"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);
		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.update")).active().list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", updateService.search(startTime, endTime).getResult());

		return "record/update-list";
	}

	/**
	 * 获取日常巡检信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/inspection", method = RequestMethod.GET)
	public String inspection(Model model, HttpServletRequest request, Authentication authentication) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}

		List<Task> tasks = null;
		List<Task> mytasks = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.inspection"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);
		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.inspection")).active()
				.list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", inspectionService.search(startTime, endTime).getResult());

		return "record/inspection-list";
	}

	/**
	 * 处理日常巡检
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/inspection/deal/{id}/{taskid}", method = RequestMethod.GET)
	public String dealInspection(@PathVariable("id") long id, @PathVariable("taskid") String taskId, Model model) {
		Inspection inspection = null;
		Task task = null;
		if (id != 0)
			inspection = inspectionService.getById(id);
		if (taskId != null)
			// 获取当前事件的task信息
			task = taskService.createTaskQuery().taskId(taskId).singleResult();

		model.addAttribute("inspection", inspection);
		model.addAttribute("task", task);

		return "record/inspection-deal";
	}

	@RequestMapping(value = "/income", method = RequestMethod.GET)
	public String income(Model model) {
		model.addAttribute("list", incomeService.getAll());
		model.addAttribute("income", new Income());
		model.addAttribute("users", userService.getEngineer());
		return "record/income-list";
	}

	@RequestMapping(value = "/income/delete/{id}", method = RequestMethod.GET)
	public String incomeDelete(@PathVariable("id") long id, Model model) {
		if (id != 0) {
			incomeService.delById(id);
		}

		return "redirect:/record/income";
	}

	@RequestMapping(value = "/income/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("income") Income income, BindingResult result, HttpServletRequest request,
			Authentication authentication) {

		income.setCreatedTime(new Date());
		income.setCreatedUser(userUtil.getUserId(authentication));
		incomeService.save(income);

		return "redirect:/record/income";
	}

	@RequestMapping(value = "/inspection/{id}/template/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveTemplate(@PathVariable("id") long id, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();

		ObjectMapper mapper = new ObjectMapper();

		Inspection inspection = inspectionService.getById(id);
		try {
			inspection.setTemplateData(mapper.writeValueAsString(request.getParameterMap()));
		} catch (JsonGenerationException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}
		inspectionService.save(inspection);

		result.put("result", "true");

		return result;
	}

	@RequestMapping(value = "/inspection/{id}/template/get")
	@ResponseBody
	public Map<String, Object> getTemplate(@PathVariable("id") long id) {
		Map<String, Object> result = new HashMap<String, Object>();

		Inspection inspection = inspectionService.getById(id);

		ObjectMapper mapper = new ObjectMapper();
		try {
			if(inspection.getTemplateData()!=null && inspection.getTemplateData()!="")
				result.put("result", mapper.readValue(inspection.getTemplateData(), Map.class));
			else
				result.put("result", "");
		} catch (JsonParseException e) {

		} catch (JsonMappingException e) {

		} catch (IOException e) {

		}

		return result;
	}

	@RequestMapping(value = "/training", method = RequestMethod.GET)
	public String training(Model model) {
		model.addAttribute("list", trainingService.getAll());
		model.addAttribute("training", new Training());
		model.addAttribute("users", userService.getEngineer());
		return "record/training-list";
	}

	@RequestMapping(value = "/training/delete/{id}", method = RequestMethod.GET)
	public String trainingDelete(@PathVariable("id") long id, Model model) {
		if (id != 0) {
			trainingService.delById(id);
		}

		return "redirect:/record/training";
	}

	@RequestMapping(value = "/training/save", method = RequestMethod.POST)
	public String trainingSave(@Valid @ModelAttribute("training") Training training, HttpServletRequest request,
			Authentication authentication) {

		training.setCreatedTime(new Date());
		training.setCreatedUser(userUtil.getUserId(authentication));
		trainingService.save(training);

		return "redirect:/record/training";
	}

	/**
	 * 三员工作管理
	 * 
	 * @param model
	 * @param request
	 * @param authentication
	 * @return
	 */
	@RequestMapping(value = "/secjob", method = RequestMethod.GET)
	public String secjob(Model model, HttpServletRequest request, Authentication authentication) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}
		List<Task> tasks = null;
		List<Task> mytasks = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.secjob"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);
		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.secjob")).active().list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", secjobService.search(startTime, endTime).getResult());

		return "record/secjob-list";
	}
}
