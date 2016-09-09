package com.cngc.pm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.cngc.pm.model.cms.Ci;

@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "check_item")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class CheckItems implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8202503663146196767L;

	private Long id;
	private String name;
	private Style item;
	//private Style style;
	private String demand;			//评测要求			对应条目
	private String technique;		//评测方法			页数
	private Boolean base;
	private String record;				//相关记录
	
	private Set<Ci> docSet = new HashSet<>();

	@Column(name="name_")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="record_")
	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity=Style.class)
	@JoinColumn(name="item_id", referencedColumnName="id")
	@Cascade(CascadeType.REFRESH)
	public Style getItem() {
		return item;
	}

	public void setItem(Style item) {
		this.item = item;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getTechnique() {
		return technique;
	}

	public void setTechnique(String technique) {
		this.technique = technique;
	}

	@Column(name="base_")
	public Boolean getBase() {
		return base;
	}

	public void setBase(Boolean base) {
		this.base = base;
	}

	@ManyToMany(targetEntity=Ci.class)
	@JoinTable(name = "doc_style", joinColumns = { @JoinColumn(name = "style_id") }, inverseJoinColumns = { @JoinColumn(name = "doc_id") })  
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id")  
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Ci> getDocSet() {
		return docSet;
	}

	public void setDocSet(Set<Ci> docSet) {
		this.docSet = docSet;
	}
	
}
