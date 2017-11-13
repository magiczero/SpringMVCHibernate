package com.cngc.pm.model.cms;

public enum AccountType {
	infoSys("信息系统",1),infoEquipment("信息设备",2),storage("存储设备",3),security("安全保密产品",4),appSys("应用系统",5);
	
	private String name;
	private int num;
	
	private AccountType (String name, int num) {
		this.name = name;
		this.num = num;
	}
	
	public String getName() {
		return name;
	}
	
	public int getNum() {
		return num;
	}
	
	public static AccountType get(int value) {
		for(AccountType type : values()) {
			if(type.getNum() == value)
				return type;
		}
		return null;
	}
}
