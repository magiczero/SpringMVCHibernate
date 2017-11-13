package com.cngc.pm.service.computer;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.cngc.pm.model.computer.InspectionData;

public interface AnalyseService {
	//分析结果
	void analyse(Long computerId,Long taskId);
	//解压文件
	String DecompressFile(String file);
	//解析文件
	void parseFile(String filename,Long computerTaskId);
	//获取数据
	void getData(BufferedReader reader,Long computerTaskId,String itemName) throws IOException;
	//合规性判断
	void dataCompliance(InspectionData data,Map<String,String> parameters);
	void dataComplianceDefault(InspectionData data,Map<String,String> parameters);
	void dataCompliancePorts(InspectionData data,Map<String,String> parameters);
	//统计
	void dataStat(Long computerTaskId);
	//保存统计信息
	void saveStat(Long computerTaskId,String itemName,int sum);
	//无线设备
	void saveStatWireless(Long computerTaskId);
	void saveStatVideo(Long computerTaskId);
	void saveStatInternet(Long computerTaskId);
	void saveStatShy(Long computerTaskId);
	void saveStatAntivirus(Long computerTaskId);
	void saveStatDisk(Long computerTaskId);
	void saveStatNetshare(Long computerTaskId);
	void saveStatPorts(Long computerTaskId);
	void saveStatSreensave(Long computerTaskId);
	void saveStatUSBDevice(Long computerTaskId);
	void saveStatUSBStorage(Long computerTaskId);
	void saveStatInstalledSoftware(Long computerTaskId);
	/*
	 * 获取统计信息
	 */
	List<InspectionData> getStats(List<Long> ids,String itemName,boolean bCompliance);
}
