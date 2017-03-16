package com.cngc.pm.model.manage;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "manage_item",uniqueConstraints = {@UniqueConstraint(columnNames="name_")})  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="manage_item")  
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6170969221044543937L;
	
	private Long id;
	private String name;
	private int order;

	@Id 
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotBlank
	@Column(name="name_")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="order_")
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}
