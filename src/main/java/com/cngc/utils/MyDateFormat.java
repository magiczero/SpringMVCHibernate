package com.cngc.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateFormat extends SimpleDateFormat {
	private static final long serialVersionUID = 1L;
	public MyDateFormat(String s) {  
        super(s);  
    }  
    public Date parse(String text) throws ParseException {  
        DateFormat df = null;  
        if(text.length()<=10){  
            df = new SimpleDateFormat("yyyy-MM-dd");  
            return new java.sql.Date(df.parse(text).getTime());  
        }  
        java.util.Date date = super.parse(text);  
        return new java.sql.Date(date.getTime());  
    }  
}
