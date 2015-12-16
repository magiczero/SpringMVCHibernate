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
@Table(name = "sys_roles_authorities")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class RoleAuth implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8319926707378063084L;

	private Long id;
	private Role role;
	private Authority auth;
	
	@Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(targetEntity=Role.class)
	@JoinColumn(name="role_id", referencedColumnName="role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne(targetEntity=Authority.class)
	@JoinColumn(name="authority_id", referencedColumnName="authority_id")
	public Authority getAuth() {
		return auth;
	}
	public void setAuth(Authority auth) {
		this.auth = auth;
	}
}
