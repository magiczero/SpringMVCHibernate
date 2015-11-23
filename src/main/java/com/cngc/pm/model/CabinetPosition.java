package com.cngc.pm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 位置
 * @author HP
 *
 */
@Entity
@Table(name="cabinet_position")
public class CabinetPosition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4042898554363784903L;

	private Long id;
	private String num;					//位置编号
	private Cabinet cabinet;			//所属机柜
	private String desc;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	@ManyToOne(targetEntity=Cabinet.class)
	@JoinColumn(name="cabinet_id", referencedColumnName="id")
	@Cascade(CascadeType.ALL)
	public Cabinet getCabinet() {
		return cabinet;
	}
	public void setCabinet(Cabinet cabinet) {
		this.cabinet = cabinet;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
