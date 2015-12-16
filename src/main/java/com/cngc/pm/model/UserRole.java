package com.cngc.pm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "sys_users_roles")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6726283593460113734L;

	private Long id;
	private SysUser user;
	private Role role;
	
	@Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(targetEntity=SysUser.class)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	
	@ManyToOne(targetEntity=Role.class)
	@JoinColumn(name="role_id", referencedColumnName="role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
