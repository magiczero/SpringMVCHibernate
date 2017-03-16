package com.cngc.pm.threemember.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocumentHandler {
	private Configuration configuration = null;
	
	public DocumentHandler() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
	}
	
	public void createDoc() throws IOException {
		//要填入模本的数据文件
		Map<String , Object> dataMap=new HashMap<>();
		getData(dataMap);
		//设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		//这里我们的模板是放在com.havenliu.document.template包下面
		//configuration.setClassForTemplateLoading(this.getClass(), "/com/cngc/pm/threemember/template");
		configuration.setDirectoryForTemplateLoading(new File("E:\\SpringMVCHibernate\\src\\main\\java\\com\\cngc\\pm\\threemember\\template"));
		Template t=null;
		try {
			//test.ftl为要装载的模板
			t = configuration.getTemplate("first.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//输出文档路径及名称
		File outFile = new File("D:/temp/outFile.doc");
		Writer out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"utf-8"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			t.process(dataMap, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	* 注意dataMap里存放的数据Key值要与模板中的参数相对应
	* @param dataMap
	*/
	private void getData(Map<String, Object> dataMap)	{
		dataMap.put("typeName", "系統管理員");
		dataMap.put("executor", "郭長勝");
		dataMap.put("time", "2017-02-25");

		List<Table2>  list=new ArrayList<>();
		for(int i=0;i<5;i++)	{
			Table2 _t2=new Table2();
			_t2.setDetail("测试开发计划"+i);
			_t2.setItemName("张三——"+i);
			_t2.setActionName("2010-10-1");
			_t2.setBasis("备注信息");
			list.add(_t2);
		}
		dataMap.put("list", list);
	}
	

}

