package com.cngc.pm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "wk_income")
public class Income  implements Serializable{

	private Long id;
	private int personCount;
	private String personName;
	private String personOfCompany;
	private String accompany;
	private Date inTime;
	private Date outTime;
	private Date createdTime;
	private String createdUser;
	@Transient
	private String createdUserName;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="person_count")
	public int getPersonCount() {
		return personCount;
	}
	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}
	@Column(name="person_name")
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	@Column(name="person_of_company")
	public String getPersonOfCompany() {
		return personOfCompany;
	}
	public void setPersonOfCompany(String personOfCompany) {
		this.personOfCompany = personOfCompany;
	}
	@Column(name="accompany")
	public String getAccompany() {
		return accompany;
	}
	public void setAccompany(String accompany) {
		this.accompany = accompany;
	}
	@Column(name="in_time")
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	@Column(name="out_time")
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	@Column(name="created_time")
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name="created_user")
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	@Formula(value="(SELECT a.NAME FROM sys_users a WHERE a.USERNAME=created_user)")
	public String getCreatedUserName() {
		return createdUserName;
	}
	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}
	
}
