package com.cngc.pm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="operation_records")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class Records implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private RecordsType type;					//操作类型
	private String desc;
	private Date inTime;
	
	@Column(name="user_name")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="operation_type")
	public RecordsType getType() {
		return type;
	}
	public void setType(RecordsType type) {
		this.type = type;
	}
	
	@Column(name="desc_")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Column(name="in_while")
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
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
	
}
