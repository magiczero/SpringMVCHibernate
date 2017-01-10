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
		
		System.out.println(StringUtils.containsOnly("CMS_FIELD_SECURITYNO", "FIELD"));
		
		String[][] strs= new String[2][5];
		
		for(int i=0; i<2; i++) {
			for(int j = 0; j<5; j++) {
				strs[i][j] = "wo " + i + " " + j;
				System.out.println(strs[i][j]);
			}
		}
		
		
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
