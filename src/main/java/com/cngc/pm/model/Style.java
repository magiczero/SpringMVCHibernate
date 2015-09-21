package com.cngc.pm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	private Style style;
	private Set<Style> child = new HashSet<Style>();
	
	@Column
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", nullable = false, insertable = false, updatable = false)
	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "style")
	public Set<Style> getChild() {
		return child;
	}

	public void setChild(Set<Style> child) {
		this.child = child;
	}

	@Column
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
}
