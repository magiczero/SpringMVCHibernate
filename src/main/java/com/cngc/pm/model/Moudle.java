package com.cngc.pm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "sys_modules")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class Moudle implements Serializable {				//module故意写错，怕与关键字冲突

	/**
	 * 
	 */
	private static final long serialVersionUID = 3043524114367198102L;

	private Long id;
	private String name;
	private String desc;
	private ModuleType type;			//菜单type为menu
	private Moudle parent;
	private String url;
	private String styleClass;				//样式
	private int level;					//模块级别
	private int leaf;					//最下级？
	private boolean enable;
	private int priority;				//优先级
	
	private Set<Moudle> child = new HashSet<>();
	
	@Id 
    @Column(name="module_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="module_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="module_desc")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="module_type")
	public ModuleType getType() {
		return type;
	}
	public void setType(ModuleType type) {
		this.type = type;
	}
	
	@ManyToOne
	@JoinColumn(name = "parent")
	public Moudle getParent() {
		return parent;
	}
	public void setParent(Moudle parent) {
		this.parent = parent;
	}
	
	@Column(name="module_url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="i_level")
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLeaf() {
		return leaf;
	}
	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	@Column(name="controller")
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	public Set<Moudle> getChild() {
		return child;
	}
	public void setChild(Set<Moudle> child) {
		this.child = child;
	}
	
}
