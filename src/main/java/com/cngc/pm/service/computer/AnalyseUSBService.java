package com.cngc.pm.service.computer;

import java.io.BufferedReader;
import java.io.IOException;

public interface AnalyseUSBService {

	//分析文件
	void analyse(String file);
	//解压文件
	String DecompressFile(String file);
	//解析文件
	void parseFile(String filename,Long computerId);
	//获取数据
	void getData(BufferedReader reader,Long computerId,String itemName)throws IOException;
}
