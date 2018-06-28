package com.cngc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(getNumbers("pmsys/user/15"));
	}
	
	
	//截取数字    
	public static String getNumbers(String content) {    
	    Pattern pattern = Pattern.compile("\\d+");    
	    Matcher matcher = pattern.matcher(content);    
	    while (matcher.find()) {
	    	System.out.println(content.substring(0,matcher.start())+"*");
	    	
	       return matcher.group(0);    
	    }    
	    return "";    
	} 

}
