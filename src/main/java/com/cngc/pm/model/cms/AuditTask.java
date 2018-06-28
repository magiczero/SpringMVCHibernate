package com.cngc.pm.model.cms;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cngc.pm.model.SysUser;


@Entity
@Table(name = "audit_task")
public class AuditTask  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8667717885130444448L;
	private Long id;
	private String name;
	private String reason;
	private SysUser assessor;
	private Set<TaskCategoryDepartmentRelation> relationSet = new HashSet<>();
	private Date startTime;
	private Date endTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") 
	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") 
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	
	@Column(name="name_")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="reason_")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "assessor", nullable = false, updatable = false)
	public SysUser getAssessor() {
		return assessor;
	}
	public void setAssessor(SysUser assessor) {
		this.assessor = assessor;
	}
	
	@ElementCollection
	@CollectionTable(
			name="audit_task_category_department",
			joinColumns = @JoinColumn(name = "task_id"))
	@OrderColumn
	public Set<TaskCategoryDepartmentRelation> getRelationSet() {
		return relationSet;
	}
	public void setRelationSet(Set<TaskCategoryDepartmentRelation> relationSet) {
		this.relationSet = relationSet;
	}
		
	
}
