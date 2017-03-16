package com.cngc.pm.model.manage;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "manage_form")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="manage_form")  
public class ManagerForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4944425553584236094L;

	private Long id;
	private Date inWhile;
	private String username;
	private String value;
	private String processInstanceId;
	
	@Id 
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="in_time")
	public Date getInWhile() {
		return inWhile;
	}
	public void setInWhile(Date inWhile) {
		this.inWhile = inWhile;
	}
	
	@Column
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@NotBlank
	@Column(name="value_")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(name="process_instance_id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
}
