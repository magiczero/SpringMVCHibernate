package com.cngc.pm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author HP
 * 	权限表
 */
@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "sys_authorities")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class Authority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2666511810211844876L;

	private Long id;

	private String authorityMake;					//权限标识
	private String authorityName;					//权限名称
	private String authorityDesc;					//说明
	private String message;								//提示信息
	
	private boolean enable;							//
	private boolean sys;
	//private Long moduleId;			
	
	private Set<Resources> setResources = new HashSet<>();
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch=FetchType.EAGER)  
	@JoinTable(name = "sys_authorities_resources", joinColumns = { @JoinColumn(name = "AUTHORITY_ID") }, inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID") })  
	@OrderBy("id")  
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
	public Set<Resources> getSetResources() {
		return setResources;
	}

	public void setSetResources(Set<Resources> setResources) {
		this.setResources = setResources;
	}

	@Id 
    @Column(name="authority_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="authority_mark")
	public String getAuthorityMake() {
		return authorityMake;
	}

	public void setAuthorityMake(String authorityMake) {
		this.authorityMake = authorityMake;
	}

	@Column(name="authority_name")
	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	@Column(name="authority_desc")
	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name="enable",nullable = false, insertable = false, updatable = true)
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Column(name="issys", nullable=false, insertable=false, updatable=true)
	public boolean isSys() {
		return sys;
	}

	public void setSys(boolean sys) {
		this.sys = sys;
	}

}
