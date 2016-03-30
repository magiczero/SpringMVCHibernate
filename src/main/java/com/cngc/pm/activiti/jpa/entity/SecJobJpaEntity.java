package com.cngc.pm.activiti.jpa.entity;

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

import org.hibernate.annotations.Where;

@Entity(name = "wk_sec_job")
public class SecJobJpaEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8565436008055663566L;
	private Long id;
	private String type;
	private String userId;
	private String attachment;
	private Date applyTime;
	private Date executionTime;
	private String processInstanceId;
	private boolean endbyuser;
	
	private Set<AttachmentEntity> attachs = new HashSet<>();							//附件
	
	@OneToMany(targetEntity = AttachmentEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name="type_id", referencedColumnName="id")
	@Where(clause="type_ = 5")
	public Set<AttachmentEntity> getAttachs() {
		return attachs;
	}
	public void setAttachs(Set<AttachmentEntity> attachs) {
		this.attachs = attachs;
	}
	
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="type_")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="attachment")
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	@Column(name="apply_time")
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	@Column(name="execution_time")
	public Date getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
	@Column(name="process_instance_id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	@Column(name="endbyuser")
	public boolean isEndbyuser() {
		return endbyuser;
	}
	public void setEndbyuser(boolean endbyuser) {
		this.endbyuser = endbyuser;
	}
	
}
