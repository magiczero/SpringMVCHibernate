package com.cngc.pm.activiti.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(name = "wk_knowledge")
public class KnowledgeJpaEntity {
	
	private Long id;
	private String title;
	private String desc;
	private String solution;
	private String keyword;
	private String applyUser;
	private Date applyTime;
	private String category;
	private String status;
	private boolean locked;
	private String processInstanceId;
	private boolean endbyuser;

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
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	@Column(name="process_instance_id")
	public String getProcessInstanceId(){
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId){
		this.processInstanceId = processInstanceId;
	}
	@Column(name="category")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
