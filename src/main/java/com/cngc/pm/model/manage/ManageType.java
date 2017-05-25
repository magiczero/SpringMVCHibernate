package com.cngc.pm.model.manage;

/**
 * 三员角色分类
 * @author young
 *
 */
public enum ManageType {
	SystemManager(1,"系统管理员"),
	SecurityManager(2,"安全管理员"),
	SecurityComptroller(3, "安全审计员");
	
	private final int value;
	private final String desc;
	
	private ManageType(int v, String desc) {
		this.value = v;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
	public static ManageType get(int v) {
		for(ManageType mt : values()) {
			if(mt.getValue() == v) {
				return mt;
			}
		}
		
		return null;
	}
	
}
