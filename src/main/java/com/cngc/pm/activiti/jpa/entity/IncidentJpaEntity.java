package com.cngc.pm.activiti.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(name = "wk_incident")
public class IncidentJpaEntity {
	
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
	@Column(name="endbyuser")
	public boolean isEndbyuser() {
		return endbyuser;
	}
	public void setEndbyuser(boolean endbyuser) {
		this.endbyuser = endbyuser;
	}
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
