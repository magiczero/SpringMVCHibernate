package com.cngc.pm.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="sys_user")  
public class SysUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2141848542645323697L;

	private Long id; 
	private String username;
	private String name;
	private transient String password;
	private Date createWhile;						//创建时间
	private Timestamp lastWhile;							//最后访问时间
	private Date deadline;							//截止日期
	private String loginIP;							//最后登录截止IP
	private Integer mechId;							//所属机构id -- 优先级
	private String mechName;					//所属机构名称
	private Integer depId;								//地区id--排序
	private String depName;						//地区名称--电话
	private boolean enabled;
	private boolean accountNonExpired;				//是否过期
	private boolean accountNonLocked;				//是否锁定
	private boolean creadentialsNonExpired;		//证书是否有效
	
	private Group group;			//所属机构，公司，部门等
	
//	private Set<Role> roles = new LinkedHashSet<Role>();						//角色列表
//
//	@ManyToMany(targetEntity=Role.class)
//	@JoinTable(name="sys_users_roles", joinColumns={@JoinColumn(name="user_id", referencedColumnName="user_id")}, inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")})
//	@OrderBy("id")
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	@JsonManagedReference
//	public Set<Role> getRoles() {
//		return roles;
//	}
//	@JsonManagedReference
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}
	
	@ManyToOne(targetEntity=Group.class)
	@JoinColumn(name="group_id", referencedColumnName="id")
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	private Set<UserRole> userRoles = new LinkedHashSet<>();

	@OneToMany(targetEntity=UserRole.class,mappedBy="user")
//	@JoinColumn(name="user_id", referencedColumnName="user_id")
//	@Fetch(value = FetchMode.SELECT)
	//@Filter(name = "user")
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
//	@Id  
//    @GeneratedValue(generator="system-uuid")  
//    @GenericGenerator(name="system-uuid",strategy="uuid")  
//    public String getId() {  
//        return id;  
//    }  
//    public void setId(String id) {  
//        this.id = id;  
//    }  

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
	public Integer getMechId() {
		return mechId;
	}

	public void setMechId(Integer mechId) {
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
	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
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
