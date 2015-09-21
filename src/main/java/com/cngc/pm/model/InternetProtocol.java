package com.cngc.pm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IP地址
 * @author HP
 *
 */
@Entity
@Table(name="ip_add")
public class InternetProtocol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4335538488761866839L;
	
	private Long id;
	private String address;						//IP地址
	private String macAdd;						//mac地址
	private String maskCode;					//掩码
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
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="mac_add")
	public String getMacAdd() {
		return macAdd;
	}
	public void setMacAdd(String macAdd) {
		this.macAdd = macAdd;
	}
	
	@Column(name="mask_code")
	public String getMaskCode() {
		return maskCode;
	}
	public void setMaskCode(String maskCode) {
		this.maskCode = maskCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}	
}
