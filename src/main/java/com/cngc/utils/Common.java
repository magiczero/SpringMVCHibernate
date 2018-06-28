package com.cngc.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class Common {
	public static final String _fileUploadPath = "D:\\plupload\\files\\";
	public static final String _strKey = "LmMGStGtOpF4xNyvYt54EQ==";
	private static final int BUFFER_SIZE = 100 * 1024;

	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {  
		String ipAddress = request.getHeader("x-forwarded-for");  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
            ipAddress = request.getRemoteAddr();  
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                //根据网卡取本机配置的IP  
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                }  
                ipAddress= inet.getHostAddress();  
            }  
        }  
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
            if(ipAddress.indexOf(",")>0){  
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
            }  
        }  
        return ipAddress;   
//	    if (request.getHeader("x-forwarded-for") == null) {  
//	        return request.getRemoteAddr();  
//	    }  
//	    return request.getHeader("x-forwarded-for");  
	}  
	/**
	 * 判断是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 上传文件
	 * 
	 * @param in
	 * @param destFile
	 * @param log
	 */
	public static void appendFile(InputStream in, File destFile, Logger log) {
		OutputStream out = null;
		try {
			// plupload 配置了chunk的时候新上传的文件append到文件末尾
			if (destFile.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(destFile, true), BUFFER_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(destFile), BUFFER_SIZE);
			}
			in = new BufferedInputStream(in, BUFFER_SIZE);

			int len = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}

	public static Object getFieldValueByName( Object o, String fieldName) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			return null;
		}
	}
	public static void setFieldValueByName( Object o, String fieldName,Object value) throws Exception {
		try {
//			String firstLetter = fieldName.substring(0, 1).toUpperCase();
//			String setter = "set" + firstLetter + fieldName.substring(1);
			String setter = "set"+fieldName;
			Method method = o.getClass().getMethod(setter, value.getClass());
			method.invoke(o,value);
		} catch (Exception e) {
			throw new Exception("给方法赋值时出现问题");
		}
	}
	
	 /**
	  * 输出excel文件
	 * @param headers		分类 -- 此类下要显示的属性
	 * @param dataset		数据集合
	 * @param out			 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中 
	 */
	public static void exportExcel(Map<String, String[]> headers,  
	            Map<String, List<Object[]>> dataset, OutputStream out	)  {
		 
		Workbook wb = new SXSSFWorkbook(500);
		
		if(headers.isEmpty())
			return ;
		
		//设置样式
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
		
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(titleFont);
		styles.put("title", style);

		style = wb.createCellStyle();
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		Font dataFont = wb.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 10);
		style.setFont(dataFont);
		styles.put("data", style);
		
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_LEFT);
		styles.put("data1", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_CENTER);
		styles.put("data2", style);

		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		styles.put("data3", style);
		
		style = wb.createCellStyle();
		style.cloneStyleFrom(styles.get("data"));
//		style.setWrapText(true);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font headerFont = wb.createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		styles.put("header", style);
		//-------------------- 设置样式End -----------------------------//
		
		//遍历标题集合，根据分类建立多个sheet
		for(Iterator<String> it = headers.keySet().iterator(); it.hasNext();) {
			String typeName = (String) it.next();
			// 生成一个表格  sheet
	        Sheet sheet = wb.createSheet(typeName); 		//	表格的名字是分类名
	        int rownum = 0;
	        //create title
	        Row titleRow = sheet.createRow(rownum++);
	        titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styles.get("title"));
			titleCell.setCellValue(typeName);
			String[] headerStrs = headers.get(typeName);
			sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(),
					titleRow.getRowNum(), titleRow.getRowNum(), headerStrs.length-1));
	        
			//create header
			Row headerRow = sheet.createRow(rownum++);
			headerRow.setHeightInPoints(16);
	        
			for (int i = 0; i < headerStrs.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellStyle(styles.get("header"));
				cell.setCellValue(headerStrs[i]);
				sheet.autoSizeColumn(i);
			}
			
			for (int i = 0; i < headerStrs.length; i++) {  
				int colWidth = sheet.getColumnWidth(i)*2;
		        sheet.setColumnWidth(i, colWidth < 3000 ? 3000 : colWidth);  
			}
			
			List<Object[]> list = dataset.get(typeName);
			
			for(Object[] objs : list) {
				
				Row row = sheet.createRow(rownum++);
				int column = 0;
				for(Object val : objs) {
					
					Cell cell = row.createCell(column++);
					
					if (val == null){
						cell.setCellValue("");
					} else if (val instanceof String) {
						cell.setCellValue((String) val);
					} else if (val instanceof Integer) {
						cell.setCellValue((Integer) val);
					} else if (val instanceof Long) {
						cell.setCellValue((Long) val);
					} else if (val instanceof Double) {
						cell.setCellValue((Double) val);
					} else if (val instanceof Float) {
						cell.setCellValue((Float) val);
					} else if (val instanceof Date) {
						DataFormat format = wb.createDataFormat();
			            style.setDataFormat(format.getFormat("yyyy-MM-dd"));
						cell.setCellValue((Date) val);
					}
					
					CellStyle cellStyle = styles.get("data2");
					cell.setCellStyle(cellStyle);
				}
			}
		}
		
		try  
        {  
            wb.write(out);
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
		
	 }

}
