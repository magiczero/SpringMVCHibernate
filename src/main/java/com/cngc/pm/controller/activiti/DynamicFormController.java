package com.cngc.pm.controller.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.form.StartFormDataImpl;
import org.activiti.engine.impl.form.TaskFormDataImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cngc.pm.common.web.common.UserUtil;

/**
 * 表单控制器
 * 
 * @author andy
 *
 */
@Controller
@RequestMapping("/workflow/dynamicform")
public class DynamicFormController {

	@Resource
	private FormService formService;
	@Resource
	private IdentityService identityService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private UserUtil userUtil;

	/**
	 * 初始化启动流程，读取启动流程的表单字段来渲染start form
	 * 
	 * @param processDefinitionId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "get-form/start/{processDefinitionId}")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, Object> findStartForm(@PathVariable("processDefinitionId") String processDefinitionId)
			throws Exception {

		Map<String, Object> result = new HashMap<String, Object>();
		StartFormDataImpl startFormData = (StartFormDataImpl) formService.getStartFormData(processDefinitionId);
		startFormData.setProcessDefinition(null);

		// 读取enum类型数据，用于下拉框
		List<FormProperty> formProperties = startFormData.getFormProperties();
		for (FormProperty formProperty : formProperties) {
			Map<String, String> values = (Map<String, String>) formProperty.getType().getInformation("values");
			if (values != null) {
				result.put("enum_" + formProperty.getId(), values);
			}
		}
		result.put("form", startFormData);

		return result;
	}

	@RequestMapping(value = "getformbykey/start/{processDefinitionKey}")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public Map<String, Object> findStartFormByKey(@PathVariable("processDefinitionKey") String processDefinitionKey)
			throws Exception {

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).latestVersion().singleResult();

		Map<String, Object> result = new HashMap<String, Object>();
		StartFormDataImpl startFormData = (StartFormDataImpl) formService.getStartFormData(processDefinition.getId());
		startFormData.setProcessDefinition(null);

		// 读取enum类型数据，用于下拉框
		List<FormProperty> formProperties = startFormData.getFormProperties();
		for (FormProperty formProperty : formProperties) {
			Map<String, String> values = (Map<String, String>) formProperty.getType().getInformation("values");
			if (values != null) {
				result.put("enum_" + formProperty.getId(), values);
			}
		}
		result.put("form", startFormData);

		return result;
	}

	/**
	 * 获取任务表单信息
	 * 
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "get-form/task/{taskId}")
	@ResponseBody
	public Map<String, Object> findTaskForm(@PathVariable("taskId") String taskId) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		TaskFormDataImpl taskFormData = (TaskFormDataImpl) formService.getTaskFormData(taskId);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		// 2.获取流程实例
		//ProcessInstance pi =runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		//HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		
		List<HistoricTaskInstance> tasks = historyService.createHistoricTaskInstanceQuery().processInstanceId(task.getProcessInstanceId()).list();
		
		//List<Task> tasks = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list(); 
		
		String preAssignee = "";
		
		for(HistoricTaskInstance t : tasks) {
			if(t.getId().equals(task.getId()))
				break;
			preAssignee = t.getAssignee();
		}
		
		/**
		 * 
		String excId = task.getExecutionId();
		ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(excId).singleResult();
		String activitiId = execution.getActivityId();
		
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(task.getProcessDefinitionId());
		
		for(ActivityImpl activityImpl : def.getActivities()){
			String currentId = activityImpl.getId();
			if(activitiId.equals(currentId)){
				
				System.out.println("当前任务："+activityImpl.getProperty("name")); //输出某个节点的某种属性
				List<PvmTransition> inTransitions = activityImpl.getIncomingTransitions();
				
				for(PvmTransition tr:inTransitions){
					
					PvmActivity ac = tr.getSource(); //获取线路的终点节点
					System.out.println("shang一步任务任务："+ac.getProperty("name"));
				}
				
				List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();//获取从某个节点出来的所有线路
			
				for(PvmTransition tr:outTransitions){
					
					PvmActivity ac = tr.getDestination(); //获取线路的终点节点
					System.out.println("下一步任务任务："+ac.getProperty("name"));
				}
			}
			
		}
		*/
		// 设置task为null，否则输出json的时候会报错
		taskFormData.setTask(null);

		result.put("form", taskFormData);
		result.put("preassignee", userUtil.getNameByUsername(preAssignee));
		/*
		 * 读取enum类型数据，用于下拉框
		 */
		List<FormProperty> formProperties = taskFormData.getFormProperties();
		for (FormProperty formProperty : formProperties) {
			Map<String, String> values = (Map<String, String>) formProperty.getType().getInformation("values");
			if (values != null) 
				result.put("enum_" + formProperty.getId(), values);
		}

		return result;
	}
	/**
	 * 提交启动流程
	 * 
	 * @param processDefinitionId
	 * @param processType
	 * @param redirectAttributes
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "start-process/{processDefinitionId}")
	@SuppressWarnings("unchecked")
	public String submitStartFormAndStartProcessInstance(
			@PathVariable("processDefinitionId") String processDefinitionId, RedirectAttributes redirectAttributes,
			HttpServletRequest request,Authentication authentication) throws Exception {
		Map<String, String> formProperties = new HashMap<String, String>();

		// 从request中读取参数然后转换
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String key = entry.getKey();

			// fp_的意思是form paremeter
			if (StringUtils.defaultString(key).startsWith("fp_")) {
				formProperties.put(key.split("_")[1], entry.getValue()[0]);
			}
		}

		// logger.debug("start form parameters: {}", formProperties);

		// User user = UserUtil.getUserFromSession(request.getSession());
		// // 用户未登录不能操作，实际应用使用权限框架实现，例如Spring Security、Shiro等
		// if (user == null || StringUtils.isBlank(user.getId())) {
		// return "redirect:/login?timeout=true";
		// }
		ProcessInstance processInstance = null;
		try {
			identityService.setAuthenticatedUserId( userUtil.getUsernameByAuth(authentication) );
			processInstance = formService.submitStartFormData(processDefinitionId, formProperties);
			// logger.debug("start a processinstance: {}", processInstance);
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		redirectAttributes.addFlashAttribute("message", "启动成功，流程ID：" + processInstance.getId());

		// return "redirect:/workflow/processinstance/running";
		return "redirect:" + request.getParameter("backhref");
	}

	@RequestMapping(value = "startprocessbykey/{processDefinitionKey}")
	@SuppressWarnings("unchecked")
	public String submitStartFormAndStartProcessInstanceByKey(
			@PathVariable("processDefinitionKey") String processDefinitionKey, RedirectAttributes redirectAttributes,
			HttpServletRequest request,Authentication authentication) throws Exception {
		Map<String, String> formProperties = new HashMap<String, String>();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).latestVersion().singleResult();
		// 从request中读取参数然后转换
		Map<String, String[]> parameterMap = request.getParameterMap();
		Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
		for (Entry<String, String[]> entry : entrySet) {
			String key = entry.getKey();

			// fp_的意思是form paremeter
			if (StringUtils.defaultString(key).startsWith("fp_")) {
				formProperties.put(key.split("_")[1], entry.getValue()[0]);
			}
		}
		ProcessInstance processInstance = null;
		try {
			identityService.setAuthenticatedUserId(userUtil.getUsernameByAuth(authentication));
			processInstance = formService.submitStartFormData(processDefinition.getId(), formProperties);
		} finally {
			//identityService.setAuthenticatedUserId(null);
		}
		redirectAttributes.addFlashAttribute("message", "启动成功，流程ID：" + processInstance.getId());

		if(StringUtils.isEmpty(request.getParameter("redirectAddress")))
			return "redirect:/workflow/task/list";
		else
			return "redirect:" + request.getParameter("redirectAddress");
		
	}
}
