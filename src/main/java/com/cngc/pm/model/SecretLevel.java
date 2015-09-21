package com.cngc.pm.model;

public enum SecretLevel {
	levelone("机密",1), leveltwo("秘密",2), levelthree("内部",3), levelfour("非密",4);
	
	private String level;
	private int value;
	
	public String getLevel() {
		return level;
	}

	public int getValue() {
		return value;
	}

	private SecretLevel(String level, int value) {
		this.level = level;
		this.value = value;
	}
	
	public static SecretLevel get(String strValue) {
		for(SecretLevel sl : values()) {
			if(sl.getValue() == Integer.valueOf(strValue)) {
				return sl;
			}
		}
		return null;
	}
}
