package com.cngc.pm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 资源表
 * @author HP
 *
 */
@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "sys_resources")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class Resources implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1603214274304695061L;

	private Long id;
	private String type;
	private String name;
	private String desc;
	private String path;
	
	@Id 
    @Column(name="resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="resource_type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name="resource_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="resource_desc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Column(name="resource_path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public boolean isIssys() {
		return issys;
	}
	public void setIssys(boolean issys) {
		this.issys = issys;
	}
	private String priority;				//优先级
	private boolean enable;
	private boolean issys;
	
	
}
