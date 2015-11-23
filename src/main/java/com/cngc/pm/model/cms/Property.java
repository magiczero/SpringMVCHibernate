package com.cngc.pm.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 属性实体类
 * 
 * @author andy
 *
 */
@Entity
@Table(name = "cms_property")
public class Property {

	private Long id;
	private String propertyId;
	private String propertyName;
	private String propertyType;
	private String propertyConstraint;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "property_id")
	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	@Column(name = "property_name")
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Column(name = "property_type")
	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	@Column(name = "property_constraint")
	public String getPropertyConstraint() {
		return propertyConstraint;
	}

	public void setPropertyConstraint(String propertyConstraint) {
		this.propertyConstraint = propertyConstraint;
	}
}
