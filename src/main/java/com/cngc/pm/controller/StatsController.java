package com.cngc.pm.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.service.ChangeService;
import com.cngc.pm.service.IncidentService;
import com.cngc.pm.service.InspectionService;
import com.cngc.pm.service.KnowledgeService;
import com.cngc.pm.service.LeaderTaskService;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.UpdateService;
import com.cngc.pm.service.SecJobService;
import com.cngc.pm.service.cms.CiService;
import com.cngc.pm.service.cms.PropertyService;
import com.cngc.utils.PropertyFileUtil;

@Controller
@RequestMapping(value = "/stats")
public class StatsController {
	@Resource
	private IncidentService incidentService;
	@Resource
	private SysCodeService syscodeService;
	@Resource
	private ChangeService changeService;
	@Resource
	private CiService ciService;
	@Resource
	private KnowledgeService knowledgeService;
	@Resource
	private LeaderTaskService leadertaskService;
	@Resource
	private InspectionService inspectionService;
	@Resource
	private UpdateService updateService;
	@Resource
	private SecJobService secjobService;
	@Resource
	private PropertyService propertyService;

	@RequestMapping(value = "/incident", method = RequestMethod.GET)
	public String incident(Model model, HttpServletRequest request) {
		String columnCategory = request.getParameter("columnCategory");
		String rowCategory = request.getParameter("rowCategory");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}

		if (columnCategory != null)
			model.addAttribute("stat",
					incidentService.getStats(columnCategory, rowCategory, startTime, endTime, status));

		model.addAttribute("status", syscodeService.getAllByType("INCIDENT_STATUS").getResult());

		if (columnCategory == null)
			return "stats/incident-stats";
		else {
			if (rowCategory != null)
				return "stats/incident-stats";
			else
				return "stats/incident-stats1";
		}
	}

	@RequestMapping(value = "/incident1", method = RequestMethod.GET)
	public String incident1(Model model, HttpServletRequest request) {

		model.addAttribute("status", syscodeService.getAllByType("INCIDENT_STATUS").getResult());

		return "stats/incident-stats1";
	}

	/**
	 * 事件本年度按月统计
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/incident/count", method = RequestMethod.GET)
	public String incidentCount(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				incidentService.getStats("DATE_MONTH", null, startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.incident.status.finished")));

		return "stats/incident-count";
	}

	@RequestMapping(value = "/incident/category", method = RequestMethod.GET)
	public String incidentCategory(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				incidentService.getStats("DATE_MONTH", "CODE_INCIDENT_CATEGORY", startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.incident.status.finished")));

		return "stats/incident-category";
	}

	@RequestMapping(value = "/incident/performance", method = RequestMethod.GET)
	public String incidentPerformance(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				incidentService.getStats("DATE_MONTH", "USER_INCIDENT_ENGINEER", startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.incident.status.finished")));

		return "stats/incident-performance";
	}
	
	//根据支持类型统计
	@RequestMapping(value = "/incident/support", method = RequestMethod.GET)
	public String incidentSupportType(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				incidentService.getStats("DATE_MONTH", "CODE_SUPPORT_TYPE", startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.incident.status.finished")));

		return "stats/incident-support";
	}

	@RequestMapping(value = "/incident/satisfaction", method = RequestMethod.GET)
	public String incidentSatisfaction(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				incidentService.getStats("DATE_MONTH", "CODE_SATISFACTION", startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.incident.status.finished")));

		return "stats/incident-satisfaction";
	}

	@RequestMapping(value = "/incident/ci", method = RequestMethod.GET)
	public String incidentCi(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				incidentService.getStats("DATE_MONTH", "CI_INCIDENT_ASSET", startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.incident.status.finished")));

		return "stats/incident-ci";
	}

	@RequestMapping(value = "/change1", method = RequestMethod.GET)
	public String change1(Model model, HttpServletRequest request) {

		model.addAttribute("status", syscodeService.getAllByType("CHANGE_STATUS").getResult());

		return "stats/change-stats1";
	}

	@RequestMapping(value = "/change", method = RequestMethod.GET)
	public String change(Model model, HttpServletRequest request) {

		String columnCategory = request.getParameter("columnCategory");
		String rowCategory = request.getParameter("rowCategory");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}

		if (columnCategory != null)
			model.addAttribute("stat", changeService.getStats(columnCategory, rowCategory, startTime, endTime, status));

		model.addAttribute("status", syscodeService.getAllByType("CHANGE_STATUS").getResult());

		if (columnCategory == null)
			return "stats/change-stats";
		else {
			if (rowCategory != null)
				return "stats/change-stats";
			else
				return "stats/change-stats1";
		}
	}

	@RequestMapping(value = "/change/count", method = RequestMethod.GET)
	public String changeCount(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				changeService.getStats("DATE_MONTH", null, startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.change.status.finished")));

		return "stats/change-count";
	}

	@RequestMapping(value = "/change/risk", method = RequestMethod.GET)
	public String changeRisk(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				changeService.getStats("DATE_MONTH", "CODE_RISK", startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.change.status.finished")));

		return "stats/change-risk";
	}

	@RequestMapping(value = "/change/performance", method = RequestMethod.GET)
	public String changePerformance(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				changeService.getStats("DATE_MONTH", "USER_CHANGE_ENGINEER", startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.change.status.finished")));

		return "stats/change-performance";
	}
	
	@RequestMapping(value = "/change/ci", method = RequestMethod.GET)
	public String changeCi(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				changeService.getStats("DATE_MONTH", "CI_CHANGE_ASSET", startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.change.status.finished")));

		return "stats/change-ci";
	}

	@RequestMapping(value = "/cms", method = RequestMethod.GET)
	public String cms(Model model, HttpServletRequest request) {
		String columnCategory = request.getParameter("columnCategory");
		String rowCategory = request.getParameter("rowCategory");
		String status = request.getParameter("status");

		if (columnCategory != null)
			model.addAttribute("stat", ciService.getStats(columnCategory, rowCategory, status));

		model.addAttribute("status", syscodeService.getAllByType("CI_STATUS").getResult());

		if (columnCategory == null)
			return "stats/cms-stats";
		else {
			if (rowCategory != null)
				return "stats/cms-stats";
			else
				return "stats/cms-stats1";
		}
	}

	@RequestMapping(value = "/cms1", method = RequestMethod.GET)
	public String cms1(Model model, HttpServletRequest request) {

		model.addAttribute("status", syscodeService.getAllByType("CI_STATUS").getResult());

		return "stats/cms-stats1";
	}

	@RequestMapping(value = "/cms/count", method = RequestMethod.GET)
	public String cmsCount(Model model, HttpServletRequest request) {

		model.addAttribute("stat", ciService.getStats("CODE_CI_STATUS", null, null));

		return "stats/cms-count";
	}

	@RequestMapping(value = "/cms/category", method = RequestMethod.GET)
	public String cmsCategory(Model model, HttpServletRequest request) {

		model.addAttribute("stat", ciService.getStats("CODE_CI_STATUS", "CODE_CI_CATEGORY", null));

		return "stats/cms-category";
	}

	@RequestMapping(value = "/cms/performance", method = RequestMethod.GET)
	public String cmsPerformance(Model model, HttpServletRequest request) {

		model.addAttribute("stat", ciService.getStats("CODE_CI_STATUS", "USER_CI_ENGINEER", null));

		return "stats/cms-performance";
	}

	@RequestMapping(value = "/knowledge", method = RequestMethod.GET)
	public String knowledge(Model model, HttpServletRequest request) {
		String columnCategory = request.getParameter("columnCategory");
		String rowCategory = request.getParameter("rowCategory");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String status = request.getParameter("status");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		if (startTime == null || startTime.isEmpty())
			startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		if (endTime == null || endTime.isEmpty()) {
			endTime = formatter.format(now.getTime());
		}

		if (columnCategory != null)
			model.addAttribute("stat",
					knowledgeService.getStats(columnCategory, rowCategory, startTime, endTime, status));

		model.addAttribute("status", syscodeService.getAllByType("KNOWLEDGE_STATUS").getResult());

		if (columnCategory == null)
			return "stats/knowledge-stats";
		else {
			if (rowCategory != null)
				return "stats/knowledge-stats";
			else
				return "stats/knowledge-stats1";
		}
	}

	@RequestMapping(value = "/knowledge1", method = RequestMethod.GET)
	public String knowledge1(Model model, HttpServletRequest request) {

		model.addAttribute("status", syscodeService.getAllByType("KNOWLEDGE_STATUS").getResult());

		return "stats/knowledge-stats1";
	}

	@RequestMapping(value = "/knowledge/count", method = RequestMethod.GET)
	public String knowledgeCount(Model model, HttpServletRequest request) {

		model.addAttribute("stat", knowledgeService.getStats("CODE_KNOWLEDGE_STATUS", null, null, null, null));

		return "stats/knowledge-count";
	}

	@RequestMapping(value = "/knowledge/category", method = RequestMethod.GET)
	public String knowledgeCategory(Model model, HttpServletRequest request) {

		model.addAttribute("stat", knowledgeService.getStats("CODE_KNOWLEDGE_STATUS", "CODE_KNOWLEDGE_CATEGORY", null, null, null));

		return "stats/knowledge-category";
	}

	@RequestMapping(value = "/knowledge/performance", method = RequestMethod.GET)
	public String knowledgePerformance(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute(
				"stat",
				knowledgeService.getStats("DATE_MONTH", "USER_KNOWLEDGE_ENGINEER", startTime, endTime,
						PropertyFileUtil.getStringValue("syscode.knowledge.status.published")));

		return "stats/knowledge-performance";
	}

	@RequestMapping(value = "/leadertask", method = RequestMethod.GET)
	public String leadertask(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute("stat", leadertaskService.getStats("DATE_MONTH", "USER_LEADERTASK_ENGINEER", startTime, endTime));

		return "stats/leadertask";
	}

	@RequestMapping(value = "/inspection", method = RequestMethod.GET)
	public String inspection(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute("stat", inspectionService.getStats("DATE_INSPECTION_MONTH", "USER_INSPECTION_ENGINEER", startTime, endTime));

		return "stats/inspection";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute("stat", updateService.getStats("DATE_UPDATE_MONTH", "USER_UPDATE_ENGINEER", startTime, endTime));

		return "stats/update";
	}

	@RequestMapping(value = "/secjob", method = RequestMethod.GET)
	public String secjob(Model model, HttpServletRequest request) {

		String startTime, endTime;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar now = Calendar.getInstance();
		startTime = String.valueOf(now.get(Calendar.YEAR)) + "-01-01";
		endTime = formatter.format(now.getTime());

		model.addAttribute("stat", secjobService.getStats("DATE_SECJOB_MONTH", "USER_SECJOB_ENGINEER", startTime, endTime));

		return "stats/secjob";
	}
}
