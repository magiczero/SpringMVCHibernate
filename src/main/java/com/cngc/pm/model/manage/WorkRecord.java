package com.cngc.pm.model.manage;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="manage_records")
public class WorkRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1903910636040520893L;

	private Long id;
	private Date inTime;
	private Relations relation;				//关系信息
	private String details;					//详细信息
	private String basis;					//依据
//	private SysUser user;					//填写人
//	private Style system;					//系统
//	private ManageType type;				//管理员类别
	private Relationship auth;				//权限信息
	private Date recordTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="record_time")
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	@ManyToOne
	@JoinColumn(name = "auth_id")
	public Relationship getAuth() {
		return auth;
	}
	public void setAuth(Relationship auth) {
		this.auth = auth;
	}
	@Id 
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="in_time")
	@org.hibernate.annotations.CreationTimestamp
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	
	@ManyToOne
	@JoinColumn(name = "relation_id")
	public Relations getRelation() {
		return relation;
	}
	public void setRelation(Relations relation) {
		this.relation = relation;
	}
	
	@Column(name="details_")
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Column(name="basis_")
	public String getBasis() {
		return basis;
	}
	public void setBasis(String basis) {
		this.basis = basis;
	}
	
//	@ManyToOne
//	@JoinColumn(name = "written_by")
//	public SysUser getUser() {
//		return user;
//	}
//	public void setUser(SysUser user) {
//		this.user = user;
//	}
//	
//	@ManyToOne
//	@JoinColumn(name = "system_id")
//	public Style getSystem() {
//		return system;
//	}
//	public void setSystem(Style system) {
//		this.system = system;
//	}
//	
//	@Enumerated(EnumType.STRING)
//	@Column(name="character_")
//	public ManageType getType() {
//		return type;
//	}
//	public void setType(ManageType type) {
//		this.type = type;
//	}
	
}
