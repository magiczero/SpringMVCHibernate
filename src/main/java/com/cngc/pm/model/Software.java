package com.cngc.pm.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="software")
public class Software extends Asset {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2654463190224691909L;

	private String num;						//软件编号
	private String name;						//软件名称
	private String versions;					//版本
	private String remark;					//备注
	
	private Set<ServerSoftware> serverSofts = new HashSet<ServerSoftware>();
	
	@NotNull
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	@NotNull
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull
	public String getVersions() {
		return versions;
	}
	public void setVersions(String versions) {
		this.versions = versions;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@OneToMany(targetEntity=ServerSoftware.class)
	@JoinColumn(name="software_id", referencedColumnName="id")
	public Set<ServerSoftware> getServerSofts() {
		return serverSofts;
	}
	public void setServerSofts(Set<ServerSoftware> serverSofts) {
		this.serverSofts = serverSofts;
	}
}
