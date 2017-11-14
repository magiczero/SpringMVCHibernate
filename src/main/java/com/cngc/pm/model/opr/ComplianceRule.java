package com.cngc.pm.model.opr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "opr_compliance_rule")
public class ComplianceRule {

	private Long id;
	private String name;
	private String item;
	private String subitem;
	private int indexOfData;
	private String value;
	private boolean compliance;
	private String mark;
	private boolean equal;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="item")
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	@Column(name="subitem")
	public String getSubitem() {
		return subitem;
	}
	public void setSubitem(String subitem) {
		this.subitem = subitem;
	}
	@Column(name="index_of_data")
	public int getIndexOfData() {
		return indexOfData;
	}
	public void setIndexOfData(int indexOfData) {
		this.indexOfData = indexOfData;
	}
	@Column(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name="compliance")
	public boolean isCompliance() {
		return compliance;
	}
	public void setCompliance(boolean compliance) {
		this.compliance = compliance;
	}
	@Column(name="mark")
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	@Column(name="equal")
	public boolean isEqual() {
		return equal;
	}
	public void setEqual(boolean equal) {
		this.equal = equal;
	}
	
}
