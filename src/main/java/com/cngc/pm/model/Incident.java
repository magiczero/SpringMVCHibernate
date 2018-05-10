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
@Table(name = "wk_incident")
public class Incident implements Serializable{
	
	private static final long serialVersionUID = 5466613597666562481L;
	
	private Long id;
	private String applyUser;
	private String phoneNumber;
	private String abs;
	private String detail;
	private String template;
	private String templateData;
	private Date applyTime;
	private String source;
	private String influence;
	private String critical;
	private String priority;
	private String type;
	private String supportType;
	private Date finishTime;
	private String category;
	private String currentDelegateGroup;
	private String currentDelegateUser;
	private String status;
	private String solution;
	private Date recoverTime;
	private String attachment;
	private String processInstanceId;
	private String satisfaction;
	private String feedback;
	private boolean endbyuser;
	
	@Transient
	private String unitName;
	@Transient
	private String priorityName;
	@Transient
	private String categoryName;
	@Transient
	private String typeName;
	@Transient
	private String supportTypeName;
	
	@Transient
	private String influenceName;
	@Transient
	private String criticalName;
	@Transient
	private String sourceName;
	@Transient
	private String statusName;
	@Transient
	private String applyUserName;
	@Transient
	private String applyUserRoom;
	@Transient
	private String satisfactionName;
	@Transient
	private String currentDelegateUserName;
	
	private Set<Attachment> attachs = new HashSet<>();							//附件
	
	@OneToMany(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name="type_id", referencedColumnName="id")
	@Where(clause="type_ = 2")
	public Set<Attachment> getAttachs() {
		return attachs;
	}

	public void setAttachs(Set<Attachment> attachs) {
		this.attachs = attachs;
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Column(name="phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name = "abstract")
	public String getAbs() {
		return abs;
	}
	public void setAbs(String abs) {
		this.abs = abs;
	}
	@Column(name = "detail")
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Column(name = "template")
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	@Column(name="template_data")
	public String getTemplateData() {
		return templateData;
	}
	public void setTemplateData(String templateData) {
		this.templateData = templateData;
	}
	@Column(name = "apply_time")
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	@Column(name="source")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	@Column(name = "influence")
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	@Column(name = "critical")
	public String getCritical() {
		return critical;
	}
	public void setCritical(String critical) {
		this.critical = critical;
	}
	@Column(name = "priority")
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	@Column(name = "type_")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name = "current_delegate_group")
	public String getCurrentDelegateGroup() {
		return currentDelegateGroup;
	}
	public void setCurrentDelegateGroup(String currentDelegateGroup) {
		this.currentDelegateGroup = currentDelegateGroup;
	}
	@Column(name = "current_delegate_user")
	public String getCurrentDelegateUser() {
		return currentDelegateUser;
	}
	public void setCurrentDelegateUser(String currentDelegateUser) {
		this.currentDelegateUser = currentDelegateUser;
	}
	@Column(name = "status_")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "solution")
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	@Column(name = "recover_time")
	public Date getRecoverTime() {
		return recoverTime;
	}
	public void setRecoverTime(Date recoverTime) {
		this.recoverTime = recoverTime;
	}
	@Column(name = "attachment")
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	@Column(name="process_instance_id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='INCIDENT_CATEGORY' AND a.code_= category)")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='PRIORITY' AND a.code_= priority)")
	public String getPriorityName() {
		return priorityName;
	}
	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='INCIDENT_TYPE' AND a.code_= type_)")
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='INFLUENCE' AND a.code_= influence)")
	public String getInfluenceName() {
		return influenceName;
	}
	public void setInfluenceName(String influenceName) {
		this.influenceName = influenceName;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='CRITICAL' AND a.code_= critical)")
	public String getCriticalName() {
		return criticalName;
	}
	public void setCriticalName(String criticalName) {
		this.criticalName = criticalName;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='INCIDENT_SOURCE' AND a.code_= source)")
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='INCIDENT_STATUS' AND a.code_= status_)")
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Formula(value="(SELECT a.NAME FROM sys_users a WHERE a.USERNAME=apply_user)")
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	@Formula(value="(SELECT a.MECH_NAME FROM sys_users a WHERE a.USERNAME=apply_user)")
	public String getApplyUserRoom() {
		return applyUserRoom;
	}

	public void setApplyUserRoom(String applyUserRoom) {
		this.applyUserRoom = applyUserRoom;
	}
	@Column(name="satisfaction")
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	@Column(name="feedback")
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='INCIDENT_SATISFACTION' AND a.code_= satisfaction)")
	public String getSatisfactionName() {
		return satisfactionName;
	}
	public void setSatisfactionName(String satisfactionName) {
		this.satisfactionName = satisfactionName;
	}
	@Formula(value="(SELECT a.NAME FROM sys_users a WHERE a.USERNAME=current_delegate_user)")
	public String getCurrentDelegateUserName() {
		return currentDelegateUserName;
	}
	public void setCurrentDelegateUserName(String currentDelegateUserName) {
		this.currentDelegateUserName = currentDelegateUserName;
	}
	@Column(name="endbyuser")
	public boolean isEndbyuser() {
		return endbyuser;
	}
	public void setEndbyuser(boolean endbyuser) {
		this.endbyuser = endbyuser;
	}
	@Column(name="support_type")
	public String getSupportType() {
		return supportType;
	}

	public void setSupportType(String supportType) {
		this.supportType = supportType;
	}
	@Column(name="finish_time")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='INCIDENT_SUPPORT_TYPE' AND a.code_= support_type)")
	public String getSupportTypeName() {
		return supportTypeName;
	}

	public void setSupportTypeName(String supportTypeName) {
		this.supportTypeName = supportTypeName;
	}
	
	@Formula(value="(SELECT a.group_name FROM sys_group a WHERE a.id=LEFT((SELECT b.group_id FROM sys_users b WHERE b.username=apply_user),2))")
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
}
