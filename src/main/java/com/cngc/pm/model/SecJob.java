package com.cngc.pm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "wk_sec_job")
public class SecJob implements Serializable{

	private Long id;
	private String type;
	private String userId;
	private String attachment;
	private Date applyTime;
	private Date executionTime;
	private String processInstanceId;
	
	@Transient
	private String userName;
	@Transient
	private String typeName;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "type_")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "attachment")
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	@Column(name = "apply_time")
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	@Column(name = "execution_time")
	public Date getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
	@Column(name = "process_instance_id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	@Formula(value="(SELECT a.NAME FROM sys_users a WHERE a.USERNAME=user_id)")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='SECJOB_TYPE' AND a.code_= type_)")
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
