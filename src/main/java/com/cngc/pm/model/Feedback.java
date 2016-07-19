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
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "wk_feedback")
public class Feedback implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8673413558924389111L;
	
	private Long id;
	private String applyUser;							//事件请求人
	private String detail;									//反馈意见
	private String priority;								//优先级
	private String state;									//状态
	private String type	;									//分类
	private String completion;						//完成情况
	private Date doneTime;							//完成时间
	private Date createTime;
	
	private String processInstanceId;
	private String status;									//步骤
	private boolean endbyuser;
	private String currentDelegateUser;
	
	@Transient
	private String currentDelegateUserName;
	
	private String statusName;
	
	private Set<Attachment> attachs = new HashSet<>();							//附件
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "apply_user")
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	@Column(name = "detail_")
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Column(name = "priority_")
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	@Column(name = "state_")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "type_")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCompletion() {
		return completion;
	}
	public void setCompletion(String completion) {
		this.completion = completion;
	}
	@Column(name = "execution_time")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
	public Date getDoneTime() {
		return doneTime;
	}
	public void setDoneTime(Date doneTime) {
		this.doneTime = doneTime;
	}
	@Column(name = "create_time")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@OneToMany(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name="type_id", referencedColumnName="id")
	@Where(clause="type_ = 7")
	public Set<Attachment> getAttachs() {
		return attachs;
	}
	
	public void setAttachs(Set<Attachment> attachs) {
		this.attachs = attachs;
	}
	@Column(name="process_instance_id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	@Column(name="status_")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="endbyuser")
	public boolean isEndbyuser() {
		return endbyuser;
	}
	public void setEndbyuser(boolean endbyuser) {
		this.endbyuser = endbyuser;
	}
	
	@Column(name = "current_delegate_user")
	public String getCurrentDelegateUser() {
		return currentDelegateUser;
	}
	public void setCurrentDelegateUser(String currentDelegateUser) {
		this.currentDelegateUser = currentDelegateUser;
	}
	@Formula(value="(SELECT a.NAME FROM sys_users a WHERE a.USERNAME=current_delegate_user)")
	public String getCurrentDelegateUserName() {
		return currentDelegateUserName;
	}
	public void setCurrentDelegateUserName(String currentDelegateUserName) {
		this.currentDelegateUserName = currentDelegateUserName;
	}
	@Transient
	public String getStatusName() {
		if(("03").equals(this.status))
			this.statusName="结束";
		else
			this.statusName="工程师反馈";
		
		return this.statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
