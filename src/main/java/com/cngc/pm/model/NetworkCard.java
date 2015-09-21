package com.cngc.pm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
	private String desc;
	
	private Set<InternetProtocol> ips = new HashSet<InternetProtocol>();
	
	@OneToMany(targetEntity=InternetProtocol.class)
	@JoinColumn(name="network_id", referencedColumnName="id")
	public Set<InternetProtocol> getIps() {
		return ips;
	}
	public void setIps(Set<InternetProtocol> ips) {
		this.ips = ips;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	@Cascade(CascadeType.ALL)
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
	
	
}
