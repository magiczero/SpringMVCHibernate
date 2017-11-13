package com.cngc.pm.controller.computer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cngc.pm.model.computer.InspectionData;
import com.cngc.pm.service.SysCodeService;
import com.cngc.pm.service.computer.AnalyseService;
import com.cngc.pm.service.computer.InspectionTaskService;

@Controller
@RequestMapping(value="/analyse")
public class AnalyseController {
	
	@Resource
	private AnalyseService analyseService;
	@Resource
	private InspectionTaskService inspectionTaskService;
	@Resource
	private SysCodeService syscodeService;
	
	/*
	 * 检查结果数据分析
	 */
	@RequestMapping(value="/{computerid}/{taskid}", method = RequestMethod.GET)
	public String analyse(@PathVariable("computerid") long computerId,
			@PathVariable("taskid") long taskId,Model model){
		
		analyseService.analyse(computerId, taskId);
		
		return "redirect:/computer/task/detail/"+String.valueOf(taskId);
	}
	
	@RequestMapping(value="/view/{computerid}/{taskid}/{itemname}", method = RequestMethod.GET)
	public String view(@PathVariable("computerid") long computerId,
			@PathVariable("taskid") long taskId,@PathVariable("itemname") String itemName,Model model){
		
		model.addAttribute("datas",inspectionTaskService.getInspectionDatas(computerId, taskId, itemName,false));
		model.addAttribute("fields", syscodeService.getAllByType(itemName).getResult());
		model.addAttribute("computerId",computerId);
		model.addAttribute("taskId", taskId);
		
		return "/computer/task-view";
	}
	/*
	 * 获取不合规的记录信息
	 */
	@RequestMapping(value="/compliance/{taskid}/{compliance}/{itemname}", method = RequestMethod.GET)
	public String compliance(@PathVariable("taskid") long taskId,@PathVariable("compliance") boolean bcompliance,
			@PathVariable("itemname") String itemName,Model model){
		switch(itemName)
		{
			case "VIDEO"://音视频设备
				model.addAttribute("datas",inspectionTaskService.getInspectionDatas(taskId, "DEVICEINFO",bcompliance,itemName,false));
				model.addAttribute("fields", syscodeService.getAllByType("DEVICEINFO").getResult());
				break;
			case "INTERNET": 			//互联网
			case "DISKPARTITIONINFO": 	//隐藏分区
			case "PORTSINFO": 			//端口
			case "NETSHAREINFO":		//系统共享
			case "WIRELESS":			//无线
			case "USBDEVICE":			//usb设备
			case "USBSTORAGE":			//usb存储
			case "INSTALLEDSOFTWARE":	//软件安装
				model.addAttribute("datas",inspectionTaskService.getInspectionDatas(taskId, itemName,bcompliance,null,false));
				model.addAttribute("fields", syscodeService.getAllByType(itemName).getResult());
				break;
			case "ANTIVIRUS": 		//防病毒软件
			case "SHY": 			//三合一
				model.addAttribute("datas",inspectionTaskService.getInspectionDatas(taskId, "INSTALLEDSOFTWARE",bcompliance,itemName,false));
				model.addAttribute("fields", syscodeService.getAllByType("INSTALLEDSOFTWARE").getResult());
				break;
			case "SCREENSAVE": //屏保
				model.addAttribute("datas",inspectionTaskService.getInspectionDatas(taskId, "USERINFO",bcompliance,itemName,false));
				model.addAttribute("fields", syscodeService.getAllByType("USERINFO").getResult());
				break;
		}
		model.addAttribute("taskId", taskId);
		return "/computer/compliance-view";
	}
	@RequestMapping(value="/compliancebyid/{id}/{compliance}/{itemname}", method = RequestMethod.GET)
	public String complianceById(@PathVariable("id") long computerTaskId,@PathVariable("compliance") boolean bcompliance,
			@PathVariable("itemname") String itemName,Model model){
		switch(itemName)
		{
			case "VIDEO"://音视频设备
				model.addAttribute("datas",inspectionTaskService.getInspectionDatasById(computerTaskId, "DEVICEINFO",bcompliance,itemName,false));
				model.addAttribute("fields", syscodeService.getAllByType("DEVICEINFO").getResult());
				break;
			case "INTERNET": 			//互联网
			case "DISKPARTITIONINFO": 	//隐藏分区
			case "PORTSINFO": 			//端口
			case "NETSHAREINFO":		//系统共享
			case "WIRELESS":			//无线
			case "USBDEVICE":			//usb设备
			case "USBSTORAGE":			//usb存储
			case "INSTALLEDSOFTWARE":	//软件安装
				model.addAttribute("datas",inspectionTaskService.getInspectionDatasById(computerTaskId, itemName,bcompliance,null,false));
				model.addAttribute("fields", syscodeService.getAllByType(itemName).getResult());
				break;
			case "ANTIVIRUS": 		//防病毒软件
			case "SHY": 			//三合一
				model.addAttribute("datas",inspectionTaskService.getInspectionDatasById(computerTaskId, "INSTALLEDSOFTWARE",bcompliance,itemName,false));
				model.addAttribute("fields", syscodeService.getAllByType("INSTALLEDSOFTWARE").getResult());
				break;
			case "SCREENSAVE": //屏保
				model.addAttribute("datas",inspectionTaskService.getInspectionDatasById(computerTaskId, "USERINFO",bcompliance,itemName,false));
				model.addAttribute("fields", syscodeService.getAllByType("USERINFO").getResult());
				break;
		}
		model.addAttribute("taskId", computerTaskId);
		return "/computer/compliance-view";
	}
	/*
	 * 获取不合规的终端
	 */
	@RequestMapping(value="/compliance-computer/{taskid}/{compliance}/{itemname}", method = RequestMethod.GET)
	public String complianceComputer(@PathVariable("taskid") long taskId,@PathVariable("compliance") boolean bcompliance,
			@PathVariable("itemname") String itemName,Model model){
		/*switch(itemName)
		{
			case "ANTIVIRUS": //防病毒软件
			case "SHY": //三合一
			{
				List<Long> ids = inspectionTaskService.getStatByNotExist(taskId, "INSTALLEDSOFTWARE", bcompliance, itemName);
				model.addAttribute("list", inspectionTaskService.getByIds(ids));
				break;
			}
			case "WIRELESS":			//无线
			case "DISKPARTITIONINFO":	//隐藏分区
			case "NETSHAREINFO":		//系统共享	
			case "PORTSINFO":			//端口
			case "INTERNET":			//互联网
			case "USBDEVICE":			//USB设备
			case "USBSTORAGE":			//USB存储
			case "INSTALLEDSOFTWARE":	//软件安装
			{
				List<Long> ids = inspectionTaskService.getStatByNotExist(taskId, itemName, bcompliance, null);
				model.addAttribute("list", inspectionTaskService.getByIds(ids));
				break;
			}
			case "VIDEO":	//音视频
			{
				List<Long> ids = inspectionTaskService.getStatByNotExist(taskId, "DEVICEINFO", bcompliance, itemName);
				model.addAttribute("list", inspectionTaskService.getByIds(ids));
				break;
			}
			case "SCREENSAVE":		//屏保
			{
				List<Long> ids = inspectionTaskService.getStatByNotExist(taskId, "USERINFO", bcompliance, itemName);
				model.addAttribute("list", inspectionTaskService.getByIds(ids));
				break;
			}
			
		}*/
		List<Long> lsCompliance = inspectionTaskService.getEndedRIDByTask(taskId);
		//隐藏分区
		List<InspectionData> list = analyseService.getStats(lsCompliance, itemName, bcompliance);
		List<Long> ids = new ArrayList<Long>();
		for(int i=0;i<list.size();i++)
			ids.add(list.get(i).getComputerTaskId());
		model.addAttribute("list", inspectionTaskService.getByIds(ids));
		return "/computer/compliance-computer";
	}
}
