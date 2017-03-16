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

/**
 * 执行的动作
 * @author HP
 *
 */
@Entity
@DynamicUpdate(true)  
@DynamicInsert(true)
@Table(name = "manage_action",uniqueConstraints = {@UniqueConstraint(columnNames="action_name")})  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="manage_action")  
public class Actions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3992188268978317613L;

	private Long id;
	private String name;
	
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
	@Column(name="action_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
