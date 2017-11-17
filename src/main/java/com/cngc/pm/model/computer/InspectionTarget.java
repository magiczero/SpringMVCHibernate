package com.cngc.pm.model.computer;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author andy
 *
 */
@Entity
@Table(name = "cmp_inspection_target")
public class InspectionTarget implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8004604057607947427L;
	private Long targetId;
	private String targetName;
	
	// 分类的属性列表
	private Set<InspectionItem> items = new LinkedHashSet<InspectionItem>(); 

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "cmp_inspection_relation", joinColumns = { @JoinColumn(name = "target_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
	//@OrderBy("item_id")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<InspectionItem> getItems() {
		return items;
	}

	public void setItems(Set<InspectionItem> items) {
		this.items = items;
	}
	
	@Id
	@Column(name = "target_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	@Column(name="target_name")
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
}
