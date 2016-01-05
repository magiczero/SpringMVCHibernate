package com.cngc.pm.controller.activiti;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/workflow/processinstance")
public class ProcessInstanceController {

	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private HistoryService historyService;

	/**
	 * 运行的流程实例
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/running", method = RequestMethod.GET)
	public String running(Model model) {
		ProcessInstanceQuery processInstanceQuery = runtimeService
				.createProcessInstanceQuery();
		List<ProcessInstance> list = processInstanceQuery.list();

		model.addAttribute("list", list);
		model.addAttribute("res", repositoryService);

		return "workflow/processinstance-running";
	}

	@RequestMapping(value = "/finished", method = RequestMethod.GET)
	public String finished(Model model) {

		HistoricProcessInstanceQuery dynamicQuery = historyService
				.createHistoricProcessInstanceQuery().finished()
				.orderByProcessInstanceEndTime().desc();
		List<HistoricProcessInstance> list = dynamicQuery.list();

		// ProcessDefinitionCache.setRepositoryService(repositoryService);

		model.addAttribute("list", list);
		model.addAttribute("res",repositoryService);

		return "workflow/processinstance-finished";
	}

	@RequestMapping(value = "/update/{state}/{processInstanceId}")
	public String updateState(@PathVariable("state") String state,
			@PathVariable("processInstanceId") String processInstanceId,
			RedirectAttributes redirectAttributes) {
		if (state.equals("active")) {
			redirectAttributes.addFlashAttribute("message", "已激活ID为["
					+ processInstanceId + "]的流程实例。");
			runtimeService.activateProcessInstanceById(processInstanceId);
		} else if (state.equals("suspend")) {
			runtimeService.suspendProcessInstanceById(processInstanceId);
			redirectAttributes.addFlashAttribute("message", "已挂起ID为["
					+ processInstanceId + "]的流程实例。");
		}

		return "redirect:/workflow/processinstance/running";
	}
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request)
	{
		String pid = request.getParameter("fm_processInstanceId");
		String reason = request.getParameter("fm_reason");
		runtimeService.deleteProcessInstance(pid, reason);
		
		return "redirect:/workflow/processinstance/running";
	}
}
