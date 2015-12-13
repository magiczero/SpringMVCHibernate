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
@Table(name = "sys_authorities_resources")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class AuthReso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5089179461353091013L;

	private Long id;
	private Authority auth;
	private Resources resources;
	
	@Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(targetEntity=Authority.class)
	@JoinColumn(name="authority_id", referencedColumnName="authority_id")
	public Authority getAuth() {
		return auth;
	}
	public void setAuth(Authority auth) {
		this.auth = auth;
	}
	
	@ManyToOne(targetEntity=Resources.class)
	@JoinColumn(name="resource_id", referencedColumnName="resource_id")
	public Resources getResources() {
		return resources;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}
	
}
