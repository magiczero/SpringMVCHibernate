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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 网卡
 * @author HP
 *
 */
@Entity
@Table(name="network_card")
public class NetworkCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8052696590408833265L;

	private Long id;
	private Style cardType;					//网卡类型
	private String macAdd;					//mac地址
	private String internetProtocol;	//IP地址
	private String subnetMask;			//子网掩码
	
	private String remark;
	
	private Servers server;						//所属服务器
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	@JoinColumn(name = "card_type", nullable = false)
	//@Cascade(CascadeType.ALL)
	public Style getCardType() {
		return cardType;
	}
	public void setCardType(Style cardType) {
		this.cardType = cardType;
	}
	
	@Column(name="mac_add")
	public String getMacAdd() {
		return macAdd;
	}
	public void setMacAdd(String macAdd) {
		this.macAdd = macAdd;
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
	
	@Column(name="internet_protocol")
	public String getInternetProtocol() {
		return internetProtocol;
	}
	public void setInternetProtocol(String internetProtocol) {
		this.internetProtocol = internetProtocol;
	}
	
	@Column(name="subnet_mask")
	public String getSubnetMask() {
		return subnetMask;
	}
	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}
}
