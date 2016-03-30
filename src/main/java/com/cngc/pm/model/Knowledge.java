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
@Table(name = "wk_knowledge")
public class Knowledge implements Serializable {
	
	private static final long serialVersionUID = 5466613587566562481L;

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
	private Long hits;
	private Date lastReadTime;
	private boolean endbyuser;
	
	@Transient
	private String statusName;
	@Transient
	private String categoryName;
	@Transient
	private String applyUserName;
	
private Set<Attachment> attachs = new HashSet<>();							//附件
	
	@OneToMany(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name="type_id", referencedColumnName="id")
	@Where(clause="type_ = 4")
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
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='KNOWLEDGE_STATUS' AND a.code_= status_)")
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='INCIDENT_CATEGORY' AND a.code_= category)")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Column(name="hits")
	public Long getHits() {
		return hits;
	}

	public void setHits(Long hits) {
		this.hits = hits;
	}
	@Column(name="last_read_time")
	public Date getLastReadTime() {
		return lastReadTime;
	}

	public void setLastReadTime(Date lastReadTime) {
		this.lastReadTime = lastReadTime;
	}
	@Formula(value="(SELECT a.NAME FROM sys_users a WHERE a.USERNAME=apply_user)")
	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	@Column(name="endbyuser")
	public boolean isEndbyuser() {
		return endbyuser;
	}
	public void setEndbyuser(boolean endbyuser) {
		this.endbyuser = endbyuser;
	}
	
}
