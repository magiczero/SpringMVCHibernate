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


@Entity
@Table(name = "wk_change_item")
public class ChangeItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1056788695740478875L;
	private Long id;
	private Long changeId;
	private Long ciId;
	private String properties;
	private String propertiesName;
	private String oldValue;
	private String newValue;
	private String creator;				//创建者
	private Date createdTime;
	private String modifier;					//修改人
	private Date updatedTime;
	private ChangeitemType type;
	private ChangeitemActionType actionType;
	private boolean isPass;					//改变是否通过  20180703修改概念为是否确认
	
	@Column(name="passed_")
	public boolean isPass() {
		return isPass;
	}
	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}
	@Enumerated(EnumType.ORDINAL)
	@Column(name="action_")
	public ChangeitemActionType getActionType() {
		return actionType;
	}
	public void setActionType(ChangeitemActionType actionType) {
		this.actionType = actionType;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="type_")
	public ChangeitemType getType() {
		return type;
	}
	public void setType(ChangeitemType type) {
		this.type = type;
	}
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="change_id")
	public Long getChangeId() {
		return changeId;
	}
	public void setChangeId(Long changeId) {
		this.changeId = changeId;
	}
	@Column(name="ci_id")
	public Long getCiId() {
		return ciId;
	}
	public void setCiId(Long ciId) {
		this.ciId = ciId;
	}
	@Column(name="properties_name")
	public String getPropertiesName() {
		return propertiesName;
	}
	public void setPropertiesName(String propertiesName) {
		this.propertiesName = propertiesName;
	}
	@Column(name="properties")
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	@Column(name="old_value")
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	@Column(name="new_value")
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	@Column(name="created_time")
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Column(name="updated_time")
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@Column(name="create_by")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Column(name="update_by")
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
}
