package com.cngc.pm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.cxf.common.util.StringUtils;
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
import com.cngc.pm.model.Attachment;
import com.cngc.pm.model.Knowledge;
import com.cngc.pm.service.AttachService;
import com.cngc.pm.service.KnowledgeService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.utils.PropertyFileUtil;
import com.googlecode.genericdao.search.SearchResult;

@Controller
@RequestMapping(value = "/knowledge")
public class KnowledgeController {

	@Resource
	private KnowledgeService knowledgeService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private FormService formService;
	@Resource
	private TaskService taskService;
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private UserUtil userUtil;
	@Resource
	private AttachService attachService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("knowledge", new Knowledge());
		return "knowledge/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("knowledge") Knowledge knowledge, BindingResult result,
			HttpServletRequest request, Authentication authentication) throws Exception {

		if(knowledge.getId()==null)
		{
			if(!StringUtils.isEmpty(request.getParameter("fileids"))) {
				String attachIds = request.getParameter("fileids");
				
				Set<Attachment> attachSet = attachService.getSetByIds(attachIds);
				
				knowledge.setAttachs(attachSet);
			}
			
			knowledge.setApplyUser(userUtil.getUserId(authentication));
			knowledge.setApplyTime(new Date());
			knowledge.setStatus(PropertyFileUtil.getStringValue("syscode.knowledge.status.new"));
			
	
			knowledgeService.save(knowledge);
		}
		else
		{
			//更新
			Knowledge newknowledge = knowledgeService.getById(knowledge.getId());
			newknowledge.setTitle(knowledge.getTitle());
			newknowledge.setKeyword(knowledge.getKeyword());
			newknowledge.setCategory(knowledge.getCategory());
			newknowledge.setDesc(knowledge.getDesc());
			newknowledge.setSolution(knowledge.getSolution());
			
			knowledgeService.save(newknowledge);
		}
		return "redirect:list";
	}

	/**
	 * 知识控制台
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Authentication authentication) throws Exception {
		List<Task> tasks = null;
		List<Task> mytasks = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.knowledge"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.knowledge")).active().list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", knowledgeService.getNotFinished().getResult());
		model.addAttribute("count", knowledgeService.getCountByStatus());
		model.addAttribute("group",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.knowledge.status")).getResult());

		return "knowledge/list";
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
		model.addAttribute("count", knowledgeService.getCountByCategory(startTime, endTime));
		model.addAttribute("group",
				syscodeService.getParentCodeByType(PropertyFileUtil.getStringValue("syscode.incident.category"))
						.getResult());
		return "knowledge/stats";
	}

	@RequestMapping(value = "/search")
	public String search(Model model, String keyword, Integer offset, Integer maxResults, HttpServletRequest request) {
		SearchResult<Knowledge> result = knowledgeService.getSearchResult(keyword, offset, maxResults);

		model.addAttribute("list", result.getResult());
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("count", result.getTotalCount());
		model.addAttribute("offset", offset);
		model.addAttribute("lastread", knowledgeService.getLastRead());
		return "knowledge/search";
	}

	@RequestMapping(value = "/searchdialog")
	public String searchDialog(Model model, String keyword, Integer offset, Integer maxResults,
			HttpServletRequest request) {
		SearchResult<Knowledge> result = knowledgeService.getSearchResult(keyword, offset, maxResults);
		model.addAttribute("list", result.getResult());
		model.addAttribute("url", request.getRequestURI());
		model.addAttribute("count", result.getTotalCount());
		model.addAttribute("offset", offset);
		return "knowledge/search-dialog";
	}

	/**
	 * 按状态获取知识信息
	 * 
	 * @param status
	 * @param model
	 * @param authentication
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/list/{status}", method = RequestMethod.GET)
	public String list(@PathVariable("status") String status, Model model, Authentication authentication) throws Exception {
		List<Task> tasks = null;
		List<Task> mytasks = null;
		Map<String, Task> taskmap = null;
		Map<String, Task> mytaskmap = new HashMap<String, Task>();

		// 我的任务
		mytasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.knowledge"))
				.taskCandidateOrAssigned(userUtil.getUserId(authentication)).active().list();
		for (Task task : mytasks)
			mytaskmap.put(task.getProcessInstanceId(), task);

		// 所有任务
		tasks = taskService.createTaskQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.knowledge")).active().list();
		taskmap = new HashMap<String, Task>();
		for (Task task : tasks)
			taskmap.put(task.getProcessInstanceId(), task);

		model.addAttribute("tasks", taskmap);
		model.addAttribute("mytasks", mytaskmap);
		model.addAttribute("list", knowledgeService.getByStatus(status).getResult());
		model.addAttribute("count", knowledgeService.getCountByStatus());
		model.addAttribute("status",
				syscodeService.getCode(status, PropertyFileUtil.getStringValue("syscode.knowledge.status")));
		model.addAttribute("group",
				syscodeService.getAllByType(PropertyFileUtil.getStringValue("syscode.knowledge.status")).getResult());
		return "knowledge/list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id, Model model) {
		if (id != 0)
			knowledgeService.delById(id);

		return "redirect:/knowledge/list";
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") long id, Model model) {

		Knowledge knowledge = knowledgeService.getById(id);
		//阅读已发布的阅读数加1
		if(knowledge.getStatus().equals(PropertyFileUtil.getStringValue("syscode.knowledge.status.published")))
			knowledgeService.addHits(knowledge);

		model.addAttribute("knowledge", knowledge);

		return "/knowledge/detail";
	}

	@RequestMapping(value = "/detail/getjson/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> detailJson(@PathVariable("id") long id, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();

		Knowledge knowledge = knowledgeService.getById(id);
		knowledgeService.addHits(knowledge);

		map.put("knowledge", knowledge);

		return map;
	}

	@RequestMapping(value = "/manage/{id}/{type}", method = RequestMethod.GET)
	public String publish(@PathVariable("id") long id, @PathVariable("type") String type, Model model) {

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(PropertyFileUtil.getStringValue("workflow.processkey.knowledge")).latestVersion()
				.singleResult();
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("id", String.valueOf(id));
		variables.put("type", type.toUpperCase());
		formService.submitStartFormData(processDefinition.getId(), variables);

		return "redirect:/knowledge/list";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") long id, Model model) {
		Knowledge knowledge = knowledgeService.getById(id);
		model.addAttribute("knowledge", knowledge);

		return "knowledge/add";
	}

	@RequestMapping(value = "/getjson/{ids}")
	@ResponseBody
	public Map<String, Object> getCi(@PathVariable("ids") String ids, HttpServletRequest request, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> list = new ArrayList<Long>();

		String sids[] = ids.split(",");
		for (String s : sids)
			list.add(Long.valueOf(s));

		List<Knowledge> klist = knowledgeService.getByIds(list).getResult();
		map.put("knowledges", klist);

		return map;
	}
}
