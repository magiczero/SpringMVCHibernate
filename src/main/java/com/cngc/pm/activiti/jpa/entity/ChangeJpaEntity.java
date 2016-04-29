package com.cngc.pm.activiti.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "wk_change")
public class ChangeJpaEntity {

	
	private Long id;
	private String description;
	private String applyUser;
	private Date applyTime;
	private String changeType;
	private String solution;
	private String fallback;
	private Date planStartTime;
	private Date planEndTime;
	private String template;
	private String templateData;
	private String influence;
	private String critical;
	private String priority;
	private String risk;
	private String status;
	private String attachment;
	private String delegateUser;
	private String approve;
	private String result;
	private String processInstanceId;
	private Date endTime;
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
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="apply_user")
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	@Column(name="apply_time")
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	@Column(name="change_type")
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}
	@Column(name="solution")
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	@Column(name="fallback")
	public String getFallback() {
		return fallback;
	}
	public void setFallback(String fallback) {
		this.fallback = fallback;
	}
	@Column(name="plan_start_time")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
	public Date getPlanStartTime() {
		return planStartTime;
	}
	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}
	@Column(name="plan_end_time")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
	public Date getPlanEndTime() {
		return planEndTime;
	}
	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}
	@Column(name="template")
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
	@Column(name="influence")
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	@Column(name="critical")
	public String getCritical() {
		return critical;
	}
	public void setCritical(String critical) {
		this.critical = critical;
	}
	@Column(name="priority")
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	@Column(name="risk")
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	@Column(name="status_")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="attachment")
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	@Column(name="delegate_user")
	public String getDelegateUser() {
		return delegateUser;
	}
	public void setDelegateUser(String delegateUser) {
		this.delegateUser = delegateUser;
	}
	@Column(name="approve")
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	@Column(name="result")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Column(name="process_instance_id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	@Column(name="end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
