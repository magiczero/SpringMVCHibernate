package com.cngc.pm.controller.computer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cngc.exception.BusinessException;
import com.cngc.pm.model.Group;
import com.cngc.pm.model.computer.Computer;
import com.cngc.pm.model.computer.ComputerTask;
import com.cngc.pm.model.computer.InspectionItem;
import com.cngc.pm.model.computer.Parameter;
import com.cngc.pm.service.GroupService;
import com.cngc.pm.service.computer.AnalyseUSBService;
import com.cngc.pm.service.computer.ComputerService;
import com.cngc.pm.service.computer.InspectionTaskService;
import com.cngc.pm.service.computer.ParameterService;

/**
 * @author andy
 * 终端合规性管理接口
 */
@Controller
@RequestMapping(value="/interface")
public class InterfaceController {

	@Resource
	private ComputerService computerService;
	@Resource
	private GroupService groupService;
	@Resource
	private InspectionTaskService inspectionTaskService;
	@Resource
	private ParameterService parameterService;
	@Resource
	private AnalyseUSBService analyseUSBService;
	
	private String RESPONSE_SUCCESS = "100";
	private String RESPONSE_ERROR = "200";
	private String RESPONSE_NULL = "224";
	private String RESPONSE_SPLIT_CHAR = "|";
	private String RESPONSE_CONNECT_CHAR = "&";
	private String storageDirectory = "/Users/andy/Documents/workspace/attachment";
	
	/*
	 * 终端访问接口
	 */
	@RequestMapping(value="/action",method={RequestMethod.GET,RequestMethod.POST})
	public String action(Model model,HttpServletRequest request,HttpServletResponse response){
		String action = request.getParameter("action");
		switch(action)
		{
		case "RegisterClient":	//注册
			return RegisterClient(request,response);
		case "SaveClient":		//保存
			return SaveClient(model,request);
		case "GetTask":			//获取任务信息
			return GetTask(request,response);
		case "GetPara":			//获取参数
			return GetPara(request,response);
		case "Upload":			//升级
			return "computer/upload";
		default:
			break;
		}
		return GetTask(request,response);
		//return "computer/register";
	}
	/*
	 * 客户端注册页面
	 */
	@RequestMapping(value="/register")
	public String register(Model model){
		model.addAttribute("groupList", groupService.getAllTop());
		return "computer/register";
	}
	/*
	 * 回传检查结果文件
	 */
	@RequestMapping(value="/getfile",produces="text/html;charset=UTF-8",method = RequestMethod.POST)
	public String upload(Model model,MultipartHttpServletRequest request,HttpServletResponse response){
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			String origFilename =  mpf.getOriginalFilename();
			String originalFileExtension = StringUtils.substring(origFilename,  StringUtils.lastIndexOf(origFilename, "."));
			
			if(StringUtils.isEmpty(originalFileExtension)) {
				throw new BusinessException("非法文件");
			}

			File folder = new File(storageDirectory);
			if (!folder.exists())
				folder.mkdirs();

			File newFile = new File(storageDirectory + "/" + origFilename);
			try {
				mpf.transferTo(newFile);
				if(origFilename.indexOf("AU_")==0)
				{	//USB报警
					analyseUSBService.analyse(origFilename);
				}
				else
					inspectionTaskService.SetTaskReback(origFilename);
				
				PrintWriter out = response.getWriter();
				out.print(RESPONSE_SUCCESS + RESPONSE_SPLIT_CHAR);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "computer/upload";
	}
	/*
	 * 客户端注册--静默
	 */
	public String RegisterClient(HttpServletRequest request,HttpServletResponse response){
		String mac = request.getParameter("MAC");
		Computer computer = computerService.getComputerByMac(mac);
		if(computer==null)
		{
			//新建
			computer = new Computer();
			computer.setCreateDate(new Timestamp(System.currentTimeMillis()));
		}
		if(request.getParameter("group")!=null)
		{
			Long groupId = Long.parseLong(request.getParameter("group"));
			Group group = groupService.getById(groupId);
			computer.setGroup(group);
		}
		computer.setUserName(request.getParameter("USERNAME"));
		computer.setDiskSn(request.getParameter("DISKSN"));
		computer.setMac(request.getParameter("MAC"));
		computer.setIp(request.getParameter("IP"));
		computer.setVersion(request.getParameter("VERSION"));
		computer.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		
		computerService.save(computer);
		
		try {
			PrintWriter out = response.getWriter();
			if(computer.getId()>0)
				out.print(RESPONSE_SUCCESS + RESPONSE_SPLIT_CHAR + String.valueOf(computer.getId()));
			else
				out.print(RESPONSE_ERROR + RESPONSE_SPLIT_CHAR);
			out.flush();
			out.close();
			return "computer/register-result";
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return "computer/register-result";
	}
	/*
	 * 保存客户端--从注册页面
	 */
	public String SaveClient(Model model,HttpServletRequest request){
		String message = "";
		String mac = request.getParameter("txtMAC");
		Computer computer = computerService.getComputerByMac(mac);
		if(computer==null)
		{
			//新建
			computer = new Computer();
			computer.setCreateDate(new Timestamp(System.currentTimeMillis()));
			message = "终端注册成功";
		}
		else
		{
			message= "终端修改成功";
		}
		Long groupId = Long.parseLong(request.getParameter("group"));
		Group group = groupService.getById(groupId);
		computer.setGroup(group);
		computer.setUserName(request.getParameter("USER_NAME1"));
		computer.setDiskSn(request.getParameter("txtDiskNo"));
		computer.setMac(request.getParameter("txtMAC"));
		computer.setIp(request.getParameter("txtIP"));
		computer.setVersion(request.getParameter("txtVersion"));
		computer.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		
		computerService.save(computer);
		
		model.addAttribute("message", message);

		return "computer/register-result";
	}
	/*
	 * 获取任务
	 */
	public String GetTask(HttpServletRequest request,HttpServletResponse response){
		
		try {
			request.getSession();
			PrintWriter out = response.getWriter();
			String userid = request.getParameter("userSN");
			if(userid==null||userid.isEmpty())	//未获取终端标识id
			{
				out.print(RESPONSE_ERROR + RESPONSE_SPLIT_CHAR +"userid is null");
				out.flush();  
			    out.close();
				return "computer/register-result";
			}
			Computer computer = computerService.getById(Long.parseLong(userid));
			if(computer==null)	//数据库中未保存终端标识id
			{
				out.print(RESPONSE_ERROR + RESPONSE_SPLIT_CHAR +"computer is null");
				out.flush();
				out.close();
				return "computer/register-result";
			}
			//保存终端访问时间
			computer.setLastRebackDate(new Timestamp(System.currentTimeMillis()));
			computerService.save(computer);
			List<ComputerTask> tasks = inspectionTaskService.getNewTaskByComputer(computer);
			boolean btask = false;
			for(ComputerTask t:tasks)
			{
				if(t.getTask().isPublish())
				{
					btask = true;
					break;
				}
			}
			if(tasks.size()==0||!btask)	//未查询到检查任务
			{
				out.print(RESPONSE_NULL + RESPONSE_SPLIT_CHAR);
				out.flush();
				out.close();
				return "computer/register-result";
			}
			out.print(RESPONSE_SUCCESS);
			out.print(RESPONSE_SPLIT_CHAR);
			for(ComputerTask t:tasks)
			{
				//id|publishtime|content|isnotify|info
				out.print(t.getTask().getId());
				out.print(RESPONSE_SPLIT_CHAR);
				out.print(t.getTask().getCreateDate());
				out.print(RESPONSE_SPLIT_CHAR);
				//生成任务内容
				Set<InspectionItem> items = null;
				if(t.getTask().getTarget()==null)  //自定义检查
					items = t.getTask().getItems();
				else
					 items = t.getTask().getTarget().getItems();
				
				for(int i=1;i<10;i++)
				{
					String pfe = "0" + String.valueOf(i) + "_";
					String stmp = "";
					int[] nflag = new int[15];
					for(int j=0;j<15;j++)
					{
						String s = pfe + stmp + "1";
						nflag[j] = 0;
						for(InspectionItem item:items)
						{
							if(item.getItemNo().equals(s))
							{
								nflag[j] = 1;
								//items.remove(item);
								break;
							}
						}
						stmp += "0";
					}
					out.print(pfe);
					for(int j=0;j<15;j++)
						out.print(nflag[j]);
					out.print(RESPONSE_CONNECT_CHAR);
				}
				out.print(RESPONSE_SPLIT_CHAR);
				//out.print("1");	//提醒
				out.print("0");		//不提醒
				out.print(RESPONSE_SPLIT_CHAR);
				out.print(t.getTask().getTaskInfo());
				//设置任务已接收
				inspectionTaskService.SetTaskRecieved(t);
				break;	//只发一个任务
			} 
		    out.flush();  
		    out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "computer/register-result";
	}
	/*
	 * 查询参数
	 */
	public String GetPara(HttpServletRequest request,HttpServletResponse response){
		try {
			request.getSession();
			PrintWriter out = response.getWriter();
			//更新终端最后访问时间
			String userid = request.getParameter("userSN");
			if(userid==null||userid.isEmpty())	//未获取终端标识id
			{
				Computer computer = computerService.getById(Long.parseLong(userid));
				computer.setLastRebackDate(new Timestamp(System.currentTimeMillis()));
				computerService.save(computer);
			}
			
			Parameter para = parameterService.getParamter();
			out.print(RESPONSE_SUCCESS + RESPONSE_SPLIT_CHAR + String.valueOf(para.getInterval()) 
					+ RESPONSE_SPLIT_CHAR + "500*300");
			
			out.flush();
			out.close();
			return "computer/register-result";
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return "computer/register-result";
	}
}
