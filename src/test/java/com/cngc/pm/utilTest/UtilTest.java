package com.cngc.pm.utilTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import com.cngc.utils.Common;

public class UtilTest {

	@Test
	@Ignore
	public void test() throws FileNotFoundException {
		Map<String, String[]> headers = new HashMap<>();  
        Map<String, List<Object[]>> dataset = new HashMap<>();
		String[] headers1 =  { "学号", "姓名", "年龄", "性别", "出生日期" };
		String[] headers2 = { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN", "图书出版社" };  
		headers.put("学生", headers1);
		headers.put("图书", headers2);
	    List<Object[]> dataset1 = new ArrayList<Object[]>();		//学生
	    Object[] objs1 = new Object[5],objs2 = new Object[5],objs3 = new Object[5];
	    objs1[0] = 10000001;
	    objs1[1] = "张三";
	    objs1[2] = 20;
	    objs1[3] = true;
	    objs1[4] = new Date();
	    objs2[0] = 20000002;
	    objs2[1] = "李四";
	    objs2[2] = 24;
	    objs2[3] = false;
	    objs2[4] = new Date();
	    objs3[0] = 30000003;
	    objs3[1] = "王五";
	    objs3[2] = 22;
	    objs3[3] = true;
	    objs3[4] = new Date();
	        dataset1.add(objs1); dataset1.add(objs2); dataset1.add(objs3);
	    dataset.put("学生", dataset1);
	    
	    List<Object[]> dataset10 = new ArrayList<Object[]>();		//图书
	    
	    Object[] objs101 = new Object[6];
	    objs101[0] = 1;
	    objs101[1] = "jsp";
	    objs101[2] = "leno";
	    objs101[3] = 300.33f;
	    objs101[4] = "1234567";
	    objs101[5] = "清华出版社";
	    
	    dataset10.add(objs101);
	    
	    dataset.put("图书", dataset10);
	    
	    Common.exportExcel( headers, dataset, new FileOutputStream("E://a.xls"));
	        
	}

}
