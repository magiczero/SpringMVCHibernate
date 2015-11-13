package com.cngc.pm.model;

public enum ModuleType {
	menu("菜单",1),
	other("其他",2);
	
	private String name;
	private int num;
	
	private ModuleType(String name, int num) {
		this.name = name;
		this.num = num;
	}

	public String getName() {
		return name;
	}
	
	public int getNum() {
		return num;
	}
	
	public static ModuleType get(String value) {
		for(ModuleType type : values()) {
			if(type.getNum() == Integer.valueOf(value))
				return type;
		}
		return null;
	}
}
