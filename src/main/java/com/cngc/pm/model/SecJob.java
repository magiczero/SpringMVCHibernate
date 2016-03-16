package com.cngc.pm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "wk_sec_job")
public class SecJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7487913912125910417L;
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
	
	private Set<Attachment> attachs = new HashSet<>();							//附件
	
	@OneToMany(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name="type_id", referencedColumnName="id")
	@Where(clause="type_ = 5")
	public Set<Attachment> getAttachs() {
		return attachs;
	}
	public void setAttachs(Set<Attachment> attachs) {
		this.attachs = attachs;
	}
	
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
