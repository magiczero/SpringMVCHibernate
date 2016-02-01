package com.cngc.pm.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	//private Set<Resources> setResources = new LinkedHashSet<>();
	private Set<AuthReso> authResos = new LinkedHashSet<>();
	private Set<RoleAuth> roleAuths = new LinkedHashSet<>();
//	private Set<Role> roleSet = new LinkedHashSet<>();
	
	@OneToMany(targetEntity=AuthReso.class, mappedBy="auth")
	public Set<AuthReso> getAuthResos() {
		return authResos;
	}

	public void setAuthResos(Set<AuthReso> authResos) {
		this.authResos = authResos;
	}

//	
//	@ManyToMany(targetEntity=Role.class)  
//	@JoinTable(name = "sys_roles_authorities", joinColumns = { @JoinColumn(name = "AUTHORITY_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })  
//	@OrderBy("id")  
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
//	public Set<Role> getRoleSet() {
//		return roleSet;
//	}
//
//	public void setRoleSet(Set<Role> roleSet) {
//		this.roleSet = roleSet;
//	}

	@OneToMany(targetEntity=RoleAuth.class, mappedBy="auth")
	public Set<RoleAuth> getRoleAuths() {
		return roleAuths;
	}

	public void setRoleAuths(Set<RoleAuth> roleAuths) {
		this.roleAuths = roleAuths;
	}

//	@ManyToMany(targetEntity=Resources.class)  
//	@JoinTable(name = "sys_authorities_resources", joinColumns = { @JoinColumn(name = "AUTHORITY_ID") }, inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID") })  
//	@OrderBy("id")  
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
//	public Set<Resources> getSetResources() {
//		return setResources;
//	}
//
//	public void setSetResources(Set<Resources> setResources) {
//		this.setResources = setResources;
//	}

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

	@Override
	public String toString() {
		
		return "权限id：" + this.getId() + "，权限名：" + this.getAuthorityName() +"，权限标识：" + this.getAuthorityMake() + "， 权限说明：" + this.getAuthorityDesc() ;
	}
}
