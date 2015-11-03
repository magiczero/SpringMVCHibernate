package com.cngc.pm.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "sys_users")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2141848542645323697L;

	private Long id; 
	private String username;
	private String name;
	private String password;
	private Date createWhile;						//创建时间
	private Timestamp lastWhile;							//最后访问时间
	private Date deadline;							//截止日期
	private String loginIP;							//最后登录截止IP
	private String mechId;							//所属机构id
	private String mechName;					//所属机构名称
	private String depId;								//地区id
	private String depName;						//地区名称
	private boolean enabled;
	private boolean accountNonExpired;				//是否过期
	private boolean accountNonLocked;				//是否锁定
	private boolean creadentialsNonExpired;		//整数是否有效
	
	private Set<Role> roles = new LinkedHashSet<Role>();						//角色列表

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch=FetchType.EAGER)
	@JoinTable(name="sys_users_roles", joinColumns={@JoinColumn(name="user_id")}, inverseJoinColumns={@JoinColumn(name="role_id")})
	@OrderBy("id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Id 
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    public Long getId() {  
        return id;  
    }  
  
    public void setId(Long id) {  
        this.id = id;  
    }  
	  
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="dt_create",insertable=false)
	public Date getCreateWhile() {
		return createWhile;
	}

	public void setCreateWhile(Date createWhile) {
		this.createWhile = createWhile;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") 
	@Column(name="last_login")
	public Timestamp getLastWhile() {
		return lastWhile;
	}

	public void setLastWhile(Timestamp lastWhile) {
		this.lastWhile = lastWhile;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	@Column(name="login_ip")
	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	@Column(name="mech_id")
	public String getMechId() {
		return mechId;
	}

	public void setMechId(String mechId) {
		this.mechId = mechId;
	}

	@Column(name="mech_name")
	public String getMechName() {
		return mechName;
	}

	public void setMechName(String mechName) {
		this.mechName = mechName;
	}

	@Column(name="dep_id")
	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	@Column(name="dep_name")
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name="account_non_expired")
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Column(name="account_non_locked")
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	@Column(name="credentials_non_expired")
	public boolean isCreadentialsNonExpired() {
		return creadentialsNonExpired;
	}

	public void setCreadentialsNonExpired(boolean creadentialsNonExpired) {
		this.creadentialsNonExpired = creadentialsNonExpired;
	}
}
