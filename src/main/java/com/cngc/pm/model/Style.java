package com.cngc.pm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author HP
 *	通用类型类
 */
@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "styles")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class Style implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8766149347352771277L;

	private Long id;
	private String name;
	private String desc;
	private String code;
	private Integer order;
	
	@Column(name="order_")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	private Style style;
	private Set<Style> child = new HashSet<Style>();
	private Set<CheckItems> items = new HashSet<>();
	
	@OneToMany(targetEntity=CheckItems.class, mappedBy="item")
	public Set<CheckItems> getItems() {
		return items;
	}

	public void setItems(Set<CheckItems> items) {
		this.items = items;
	}

	@Column(name="desc_")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@ManyToOne(cascade = { CascadeType.PERSIST },fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name = "parent_id")
	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE},fetch=FetchType.EAGER, mappedBy = "style")
	@OrderBy(value="order")
	public Set<Style> getChild() {
		return child;
	}

	public void setChild(Set<Style> child) {
		this.child = child;
	}

	@Column(name="name_")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Column(name="code_")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
