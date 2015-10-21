package com.cngc.pm.model;

public enum DocAuth {
	open("公开",1),			//公开的 
	own("私人",2);				//私人的
	
	private String name;
	private int num;
	
	private DocAuth(String name, int num) {
		this.name = name;
		this.num = num;
	}

	public String getName() {
		return name;
	}
	
	public int getNum() {
		return num;
	}
	
	public static DocAuth get(String value) {
		for(DocAuth auth : values()) {
			if(auth.getNum() == Integer.valueOf(value))
				return auth;
		}
		return null;
	}
	
}
