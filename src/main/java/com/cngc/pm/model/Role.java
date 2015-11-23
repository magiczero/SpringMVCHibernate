package com.cngc.pm.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
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
 *	角色类
 */
@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "sys_roles")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 615551004857247980L;

	private Long id;
	private String roleName;
	private String roleDesc;
	private boolean enable;
	private boolean sys;
	//private Long moduleId;
	
	private Set<Moudle> modules = new LinkedHashSet<>();

	private Set<Authority> auths = new LinkedHashSet<Authority>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch=FetchType.EAGER)  
	@JoinTable(name = "sys_roles_authorities", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })  
	@OrderBy("id")  
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
	public Set<Authority> getAuths() {
		return auths;
	}

	public void setAuths(Set<Authority> auths) {
		this.auths = auths;
	}
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch=FetchType.EAGER)  
	@JoinTable(name = "sys_roles_moudles", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "MODULE_ID") })  
	@OrderBy("id")  
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
	public Set<Moudle> getModules() {
		return modules;
	}

	public void setModules(Set<Moudle> modules) {
		this.modules = modules;
	}

	@Id 
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="role_name")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name="role_desc")
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name="isenable")
	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Column(name="issys")
	public boolean isSys() {
		return sys;
	}

	public void setSys(boolean sys) {
		this.sys = sys;
	}

}
