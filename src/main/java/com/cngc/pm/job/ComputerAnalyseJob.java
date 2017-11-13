package com.cngc.pm.job;

import java.util.List;

import javax.annotation.Resource;

import com.cngc.pm.model.computer.ComputerTask;
import com.cngc.pm.service.computer.AnalyseService;
import com.cngc.pm.service.computer.InspectionTaskService;

public class ComputerAnalyseJob {

	@Resource
	private AnalyseService analyseService;
	@Resource
	private InspectionTaskService inspectionTaskService;
	
	/*
	 *启动检查结果信息分析 
	 */
	public void startAnalyse(){
		System.out.println("解析数据");
		List<ComputerTask> list =  inspectionTaskService.getAllRecieved();
		for(int i=0;i<list.size();i++)
		{
			ComputerTask t = list.get(i);
			analyseService.analyse(t.getComputer().getId(), t.getTask().getId());
		}
	}
}
