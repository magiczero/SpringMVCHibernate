package com.cngc.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5Tools {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(getMd5("user","user"));/workflow/task/list
//		PathMatcher  urlMatcher = new AntPathMatcher();
//		
//		System.out.println(urlMatcher.matchStart( "/PmSys/workflow/task/list","/PmSys/workflow/task/list"));
		
		String ext = StringUtils.substring("abc.txt",  
                StringUtils.lastIndexOf("abc.txt	", "."));
		
		System.out.println(isExtension(ext, "doc", "xls",".txt"));
	}
	
	private static boolean isExtension(String ext, String... exts) {
		boolean flag = false;
		for(String s : exts) {
			if(StringUtils.equalsIgnoreCase(ext, s)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static String getMd5(String password, String salt){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();   
		String result = "d:/123/456/789.txt".replaceAll("/[^/]+$","");
		System.out.println(result);
		
		return md5.encodePassword(password, salt);
	}
}
