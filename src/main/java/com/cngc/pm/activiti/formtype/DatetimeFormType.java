package com.cngc.pm.activiti.formtype;

import org.activiti.engine.impl.form.DateFormType;

public class DatetimeFormType  extends DateFormType {

	public DatetimeFormType(String datePattern) {
		super(datePattern);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
	    return "datetime";
	  }

}
