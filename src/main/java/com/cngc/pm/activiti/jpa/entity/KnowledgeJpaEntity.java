package com.cngc.pm.activiti.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(name = "kg_knowledge")
public class KnowledgeJpaEntity {
	
	private Long id;
	private String title;
	private String desc;
	private String solution;
	private String keyword;
	private String applyUser;
	private Date applyTime;
	private boolean status;
	private boolean locked;
	private String processInstanceId;
	private String currentActivityId;
	private String currentActivityName;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "desc_")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "solution")
	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	@Column(name = "keyword")
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword){
		this.keyword = keyword;
	}
	
	@Column(name="apply_user")
	public String getApplyUser(){
		return applyUser;
	}
	public void setApplyUser(String applyUser){
		this.applyUser = applyUser;
	}
	
	@Column(name="apply_time")
	public Date getApplyTime(){
		return applyTime;
	}
	public void setApplyTime(Date applyTime){
		this.applyTime = applyTime;
	}
	
	@Column(name="is_locked")
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	@Column(name="status_")
	public boolean getStatus(){
		return status;
	}
	public void setStatus(boolean status){
		this.status = status;
	}
	
	@Column(name="process_instance_id")
	public String getProcessInstanceId(){
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId){
		this.processInstanceId = processInstanceId;
	}
	
	@Column(name="current_activity_id")
	public String getCurrentAcitityId(){
		return currentActivityId;
	}
	public void setCurrentAcitityId(String currentActivityId){
		this.currentActivityId = currentActivityId;
	}
	
	@Column(name="current_activity_name")
	public String getCurrentAcitivityName(){
		return currentActivityName;
	}
	public void setCurrentAcitivityName(String currentActivityName){
		this.currentActivityName = currentActivityName;
	}
	
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
