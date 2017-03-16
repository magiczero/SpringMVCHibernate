package com.cngc.pm.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.cxf.common.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cngc.pm.common.web.common.UserUtil;
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.Feedback;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.FeedbackService;
import com.cngc.utils.PropertyFileUtil;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

	@Resource
	private FeedbackService feedbackService;
	@Resource
	private AttachService attachService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private TaskService taskService;
	@Resource
	private FormService formService;
	@Resource
	private UserUtil userUtil;
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("feedback", new Feedback());
		
		return "feedback/add";
	}
	
	/**
	 * 新建&修改
	 * @param feedback
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("feedback") Feedback feedback,HttpServletRequest request) {
		if(feedback.getId()==null) {				//新建
			if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
				String attachIds = request.getParameter("fileids");
				
				Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
				
				feedback.setAttachs(attachSet);
			}
			
			feedback.setState(PropertyFileUtil.getStringValue("syscode.incident.status.new"));
			feedback.setStatus(PropertyFileUtil.getStringValue("syscode.incident.status.new"));
			feedback.setPriority("03");
			feedback.setCreateTime(new Date());
			
			feedbackService.save(feedback);
			
			// 启动流程
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
								.processDefinitionKey("feedback").active()
								.latestVersion().singleResult();
			if (processDefinition != null) {
				Map<String, String> variables = new HashMap<String, String>();
				variables.put("id", String.valueOf(feedback.getId()));
				formService.submitStartFormData(processDefinition.getId(), variables);
			}
		} else {			//修改
			Feedback newFeedback = feedbackService.getById(feedback.getId());
			newFeedback.setDetail(feedback.getDetail());
			if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
				String attachIds = request.getParameter("fileids");
				
				Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
				
				attachSet.addAll(newFeedback.getAttachs());
				
				newFeedback.setAttachs(attachSet);
			}
			feedbackService.save(newFeedback);
		}
		
		return "redirect:/feedback/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Authentication authentication) throws Exception {

		List<Task> tasks = null;
		List<Task> mytasks = null;
		List<Feedback> feedbackList = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey("feedback")
				.taskCandidateOrAssigned(userUtil.getUsernameByAuth(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有为未关闭的反馈信息
		feedbackList = feedbackService.getNotFinished();
		if (userUtil.IsWorkflowManager(authentication)) // 流程管理者可查看所有事件任务信息
		{
			// 所有任务
			tasks = taskService.createTaskQuery()
					.processDefinitionKey("feedback").active()
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
		model.addAttribute("list", feedbackList);
		//model.addAttribute("count", feedbackService.getCountByStatus());
		//model.addAttribute("group",
		//		syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.incident.status")).getResult());
		return "feedback/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("feedback", feedbackService.getById(id));
		
		return "feedback/add";
	}
	
	@RequestMapping(value="/delAttach/{attachid}")
	@ResponseBody
	public boolean delAttach(@PathVariable("attachid") long attachid){
		return attachService.delById(attachid);
	}
}
