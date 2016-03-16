package com.cngc.pm.activiti.formtype;

import org.activiti.engine.impl.form.StringFormType;

public class FileFormType extends StringFormType{
	
	@Override
	public String getName(){
		return "file";
	}
}
