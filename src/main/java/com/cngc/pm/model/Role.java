package com.cngc.pm.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
//	private Set<SysUser> users = new LinkedHashSet<>();
//	
//	@ManyToMany(targetEntity=SysUser.class)
//	@JoinTable(name="sys_users_roles", joinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")}, inverseJoinColumns={@JoinColumn(name="user_id",referencedColumnName="user_id")})
//	@OrderBy("id")
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	@JsonBackReference
//	public Set<SysUser> getUsers() {
//		return users;
//	}
//	@JsonBackReference
//	public void setUsers(Set<SysUser> users) {
//		this.users = users;
//	}
	
	private Set<UserRole> userRoles = new LinkedHashSet<>();
	private Set<RoleAuth> roleAuths = new LinkedHashSet<>();
	
	@OneToMany(targetEntity=RoleAuth.class, mappedBy="role")
	public Set<RoleAuth> getRoleAuths() {
		return roleAuths;
	}

	public void setRoleAuths(Set<RoleAuth> roleAuths) {
		this.roleAuths = roleAuths;
	}

	@OneToMany(targetEntity=UserRole.class, mappedBy="role")
//	@JoinColumn(name="role_id", referencedColumnName="role_id")
//	@Fetch(value = FetchMode.SUBSELECT)
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	private Set<Moudle> modules = new LinkedHashSet<>();

//	private Set<Authority> auths = new LinkedHashSet<Authority>();
//
//	@ManyToMany(targetEntity=Authority.class)  
//	@JoinTable(name = "sys_roles_authorities", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })  
//	@OrderBy("id")  
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
//	public Set<Authority> getAuths() {
//		return auths;
//	}
//
//	public void setAuths(Set<Authority> auths) {
//		this.auths = auths;
//	}
	
	@ManyToMany(targetEntity=Moudle.class)
	@JoinTable(name = "sys_roles_moudles", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "MODULE_ID") })  
	@OrderBy("priority")  
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
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
	
	@Override
	public String toString() {
		return "角色名：["+this.getRoleName()+"]，角色id：["+this.getId()+"]";
	}

}
