package com.cngc.pm.model.manage;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 三员角色所能操作的项目和动作
 * @author young
 *
 */
@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "manage_relation")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="manage_relation")  
public class Relations implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1543707670621633165L;

	private Long id;
	private Item item;
	private Actions action;
	private ManageType type;
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
	
	@NotNull
	@ManyToOne(targetEntity=Item.class)
	@JoinColumn(name="item_id", referencedColumnName="id")
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	@NotNull
	@ManyToOne(targetEntity=Actions.class)
	@JoinColumn(name="action_id", referencedColumnName="id")
	public Actions getAction() {
		return action;
	}
	public void setAction(Actions action) {
		this.action = action;
	}
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(name="type_")
	public ManageType getType() {
		return type;
	}
	public void setType(ManageType type) {
		this.type = type;
	}
	
	@NotNull
	@Column(name="order_")
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
}
