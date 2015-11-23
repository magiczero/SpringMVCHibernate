package com.cngc.pm.model.cms;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cngc.pm.model.Role;

/**
 * 分类实体
 * 
 * @author andy
 *
 */
@Entity
@Table(name = "cms_category")
public class Category {

	private String categoryCode;
	private String categoryName;

	// 分类的属性列表
	private Set<Property> properties = new LinkedHashSet<Property>(); 

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "cms_category_property", joinColumns = { @JoinColumn(name = "category_code") }, inverseJoinColumns = { @JoinColumn(name = "property_id") })
	@OrderBy("property_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

	@Id
	@Column(name = "category_code")
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	@Column(name = "category_name")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
