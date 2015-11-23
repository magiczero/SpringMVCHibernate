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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY) 
@Table(name="hard_disk")
public class HardDisk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6992494249726404082L;

	private Long id;
	private String serialNum;					//硬盘序列号
	private String interf;							//接口
	private String type;							//硬盘类型
	private String capacity;						//容量
	
//	private String severName;					//所属服务器名称
	
	private Servers server;						//所属服务器

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="serial_num")
	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	@Column(name="disk_interface")
	public String getInterf() {
		return interf;
	}

	public void setInterf(String interf) {
		this.interf = interf;
	}

	@Column(name="disk_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	@ManyToOne(targetEntity=Servers.class)
	@JoinColumn(name="server_id", referencedColumnName="id")
	@NotFound(action=NotFoundAction.IGNORE)  
	public Servers getServer() {
		return server;
	}

	public void setServer(Servers server) {
		this.server = server;
	}

//	@Transient
//	public String getSeverName() {
//		
//		return this.severName;
//	}
//
//	public void setSeverName(String severName) {
//		this.severName = severName;
//	}

}
