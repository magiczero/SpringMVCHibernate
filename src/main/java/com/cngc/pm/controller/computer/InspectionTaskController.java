package com.cngc.pm.controller.computer;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.model.computer.InspectionData;
import com.cngc.pm.model.computer.InspectionItem;
import com.cngc.pm.model.computer.InspectionTarget;
import com.cngc.pm.model.computer.InspectionTask;
import com.cngc.pm.service.computer.AnalyseService;
import com.cngc.pm.service.computer.ComputerService;
import com.cngc.pm.service.computer.InspectionItemService;
import com.cngc.pm.service.computer.InspectionTargetService;
import com.cngc.pm.service.computer.InspectionTaskService;

@Controller
@RequestMapping(value="/computer/task")
public class InspectionTaskController {

	@Resource
	private InspectionTaskService inspectionTaskService;
	@Resource
	private InspectionItemService itemService;
	@Resource
	private ComputerService computerService;
	@Resource 
	private InspectionTargetService targetService;
	@Resource
	private AnalyseService analyseService;
	
	@RequestMapping(value="/add")
	public String add(Model model){
		model.addAttribute("task",new InspectionTask());
		model.addAttribute("items", itemService.getAll());
		model.addAttribute("computers", computerService.getRegistedAll());
		model.addAttribute("targets", targetService.getAll());
		return "computer/task-add";
	}
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("task") InspectionTask inspectionTask, HttpServletRequest request){
		String items = request.getParameter("form_items");
		String computers = request.getParameter("form_computers");
		String chkItem = request.getParameter("chkItem");
	
		Long targetId = (long) 0;
		if(request.getParameter("targetId")!=null && !request.getParameter("targetId").isEmpty())
			targetId = Long.parseLong(request.getParameter("targetId"));
		
		if(!StringUtils.equals(computers, "0"))
		{
			Set<Computer> setComputer = null;
			if(!StringUtils.isEmpty(computers))
				setComputer = computerService.getByIds(computers) ;
			inspectionTask.setComputers(setComputer);
		}
		if(StringUtils.equals(chkItem,"true"))
		{
			//自定义
			if(!StringUtils.equals(items, "0"))
			{
				Set<InspectionItem> set = null;
				if(!StringUtils.isEmpty(items))
					set = itemService.getItemByIds(items) ;
				inspectionTask.setItems(set);
			}
		}
		else
		{
			if(targetId!=0)
			{
				InspectionTarget target = targetService.getById(targetId);
				inspectionTask.setTarget(target);
			}
		}
		
		inspectionTask.setCreateDate(new Timestamp(System.currentTimeMillis()));
		inspectionTask.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		inspectionTaskService.save(inspectionTask);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("list", inspectionTaskService.getAll());
		return "computer/task-list";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") long id,Model model){
		if(id!=0)
			inspectionTaskService.delById(id);
		
		return "redirect:/computer/task/list";
	}
	@RequestMapping(value="/detail/{id}",method = RequestMethod.GET)
	public String detail(@PathVariable("id") long id,Model model){
		InspectionTask task = inspectionTaskService.getById(id);
		model.addAttribute("list", inspectionTaskService.getByTask(task));
		model.addAttribute("taskId",id);
		return "computer/task-detail";
	}
	/*
	 * 报表详细
	 */
	@RequestMapping(value="/report/detail/{id}",method = RequestMethod.GET)
	public String reportDetail(@PathVariable("id") long taskId,Model model){
		InspectionTask task = inspectionTaskService.getById(taskId);
		model.addAttribute("list", inspectionTaskService.getByTask(task));
		//获取数据
		List<InspectionData> lsdiscompliance = inspectionTaskService.getInspectionDatas(taskId, null, false, null, true);
		List<InspectionData> lscompliance = inspectionTaskService.getInspectionDatas(taskId, null, true, null, true);
		lscompliance.addAll(lsdiscompliance);
		Map<Long,Map<String,Boolean>> map = new HashMap<Long,Map<String,Boolean>>();
		for(int i=0;i<lscompliance.size();i++)
		{
			InspectionData data = lscompliance.get(i);
			Map<String,Boolean> vmap = null;
			if(map.containsKey(data.getComputerTaskId()))
				vmap = map.get(data.getComputerTaskId());
			else
				vmap = new HashMap<String,Boolean>();
			vmap.put(data.getItemName(), data.isCompliance());
			
			map.put(data.getComputerTaskId(), vmap);
		}
		model.addAttribute("reportmap", map);
		return "computer/task-rdetail";
	}
	/*
	 * 检查结果统计
	 */
	@RequestMapping(value="/report/{id}",method = RequestMethod.GET)
	public String report(@PathVariable("id") long id,Model model){
		InspectionTask task = inspectionTaskService.getById(id);
		model.addAttribute("task",task);
		
		//所有已结束终端关联ID
		List<Long> lsCompliance = inspectionTaskService.getEndedRIDByTask(task.getId());
		model.addAttribute("ComputerCount",lsCompliance.size());
		//隐藏分区
		List<InspectionData> lsDiscomplianceDisk = analyseService.getStats(lsCompliance, "DISKPARTITIONINFO", false);
		model.addAttribute("ls_hiddendisk", lsDiscomplianceDisk);
		//List<Long> lsDiscomplianceDisk = inspectionTaskService.getStat(id, "DISKPARTITIONINFO", false, null);
		//model.addAttribute("s_hiddendisk", lsDiscomplianceDisk.size());
		//compliance(lsCompliance,lsDiscompliance,lsDiscomplianceDisk,false);
		//无线
		List<InspectionData> lsDiscomplianceWireless = analyseService.getStats(lsCompliance, "WIRELESS", false);
		model.addAttribute("ls_wireless", lsDiscomplianceWireless);
		//音视频设备
		List<InspectionData> lsDiscomplianceVideo = analyseService.getStats(lsCompliance,"VIDEO",false);
		model.addAttribute("ls_video", lsDiscomplianceVideo);
		//互联网
		List<InspectionData> lsDiscomplianceInternet = analyseService.getStats(lsCompliance,"INTERNET",false);
		model.addAttribute("ls_internet", lsDiscomplianceInternet);
		//三合一
		List<InspectionData> lsDiscomplianceSHY = analyseService.getStats(lsCompliance,"SHY",false);
		model.addAttribute("ls_shy", lsDiscomplianceSHY);
		//防病毒
		List<InspectionData> lsDiscomplianceAntivirus = analyseService.getStats(lsCompliance,"ANTIVIRUS",false);
		model.addAttribute("ls_antivirus", lsDiscomplianceAntivirus);
		//端口
		List<InspectionData> lsDiscompliancePorts = analyseService.getStats(lsCompliance,"PORTSINFO",false);
		model.addAttribute("ls_ports", lsDiscompliancePorts);
		//屏保
		List<InspectionData> lsDiscomplianceSreensave = analyseService.getStats(lsCompliance,"SCREENSAVE",false);
		model.addAttribute("ls_screensave", lsDiscomplianceSreensave);
		//系统共享
		List<InspectionData> lsDiscomplianceNetshare = analyseService.getStats(lsCompliance,"NETSHAREINFO",false);
		model.addAttribute("ls_netshare", lsDiscomplianceNetshare);
		//USB设备
		List<InspectionData> lsDiscomplianceUSB = analyseService.getStats(lsCompliance,"USBDEVICE",false);
		model.addAttribute("ls_usbdevice", lsDiscomplianceUSB);
		//USB存储
		List<InspectionData> lsDiscomplianceUSBStorage = analyseService.getStats(lsCompliance,"USBSTORAGE",false);
		model.addAttribute("ls_usbstorage", lsDiscomplianceUSBStorage);
		//软件安装
		List<InspectionData> lsDiscomplianceSoftware = analyseService.getStats(lsCompliance,"INSTALLEDSOFTWARE",false);
		model.addAttribute("ls_software", lsDiscomplianceSoftware);
		//已结束终端关联ID
		model.addAttribute("EndedComputerCount", lsCompliance.size());
		//不合规数
		model.addAttribute("s_compliance", inspectionTaskService.getCompliancedByTask(id, true).size());
		model.addAttribute("s_discompliance", inspectionTaskService.getCompliancedByTask(id, false).size());
		return "computer/task-report";
	}
}
