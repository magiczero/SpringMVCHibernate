package com.cngc.pm.model.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private String propertyType;		//属性类型
	private String propertyConstraint;
	
	private Date createTime;

	//约束条件
	@org.hibernate.annotations.Type(type="byte")
	private boolean requiredField = true;		//是否必填
	private String defaultValue;	//预设值
	private String htmlCode;			//生成的html代码
	
	private Integer maxLength;					//最大字数
	private Integer minLength;				//最小字数
	
	private Double maxValue;				//数字最大值
	private Double minValue;				//数字最小值

	private String defaultDateFormat;		//默认的时间格式
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="in_time")
	@org.hibernate.annotations.CreationTimestamp
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "max_length")
	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	@Column(name = "min_length")
	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	@Column(name = "max_value")
	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	@Column(name = "min_value")
	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	@Column(name = "date_format")
	public String getDefaultDateFormat() {
		return defaultDateFormat;
	}

	public void setDefaultDateFormat(String defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}
	@Column(name = "is_required")
	public boolean isRequiredField() {
		return requiredField;
	}

	public void setRequiredField(boolean requiredField) {
		this.requiredField = requiredField;
	}
	@Column(name = "default_value")
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	@Column(name = "html_code")
	public String getHtmlCode() {
		return htmlCode;
	}

	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
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
