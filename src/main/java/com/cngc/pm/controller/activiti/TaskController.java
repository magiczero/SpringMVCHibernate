package com.cngc.pm.controller.activiti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/workflow/task")
public class TaskController {

	@Resource
	private FormService formService;
	@Resource
	private IdentityService identityService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private TaskService taskService;

	/**
	 * 待办任务
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String taskList(Model model) {
		// User user = UserUtil.getUserFromSession(request.getSession());

		List<Task> tasks = new ArrayList<Task>();

		tasks = taskService.createTaskQuery().taskCandidateOrAssigned("andyhe")
				.active().orderByTaskId().desc().list();

		model.addAttribute("tasks", tasks);
		model.addAttribute("res", repositoryService);

		return "/workflow/task-list";
	}

	/**
	 * 任务签收
	 * 
	 * @param taskId
	 * @param session
	 * @param request
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/claim/{id}")
	public String claim(@PathVariable("id") String taskId, HttpSession session,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// String userId = UserUtil.getUserFromSession(session).getId();
		taskService.claim(taskId, "andyhe");
		redirectAttributes.addFlashAttribute("message", "任务已签收");
		
		return "redirect:/workflow/task/list";
	}

	/**
	 * 任务处理
	 * 
	 * @param taskId
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/complete/{taskId}")
	@SuppressWarnings("unchecked")
	public String completeTask(@PathVariable("taskId") String taskId,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
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

		// 用户未登录不能操作，实际应用使用权限框架实现，例如Spring Security、Shiro等
		// if (user == null || StringUtils.isBlank(user.getId())) {
		// return "redirect:/login?timeout=true";
		// }
		try {
			identityService.setAuthenticatedUserId("andyhe");
			formService.submitTaskFormData(taskId, formProperties);
		} finally {
			identityService.setAuthenticatedUserId(null);
		}

		redirectAttributes
				.addFlashAttribute("message", "任务完成：taskId=" + taskId);
		return "redirect:/workflow/task/list";
	}
}
