package com.cngc.pm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 资产类
 * @author Haipeng
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="asset")
public class Asset implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5823184090010152364L;

	private Long id;
	
	private String assetNum;		//资产编号
	private String secretNum;		//涉密编号
	private String brand;				//品牌
	private String model;				//设备型号
	private String snNum;			//sn号
	
	private Style equipType;		//设备类型
	private SecretLevel secretLevel;		//密级
	
	private String purpose;			//用途
	private Date productionDate;			//生产日期
	private Date purchaseTime;				//购置时间
	
	private Manufacturer manufa;				//厂商
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	@Column(name="asset_num", nullable=false)
	public String getAssetNum() {
		return assetNum;
	}
	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}
	
	@Size(min = 2, max = 30)
	@Column(name="secret_num")
	public String getSecretNum() {
		return secretNum;
	}
	public void setSecretNum(String secretNum) {
		this.secretNum = secretNum;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(name="sn_num")
	public String getSnNum() {
		return snNum;
	}
	public void setSnNum(String snNum) {
		this.snNum = snNum;
	}
	
	@NotNull
	@ManyToOne(targetEntity=Style.class)
	@JoinColumn(name = "equip_type", nullable = false)
//	@Cascade(CascadeType.ALL)
	public Style getEquipType() {
		return equipType;
	}
	public void setEquipType(Style equipType) {
		this.equipType = equipType;
	}
	
//	@ManyToOne(targetEntity=Style.class)
//	@JoinColumn(name = "secret_type", nullable = false)
//	@Cascade(CascadeType.ALL)
	@Enumerated(EnumType.ORDINAL)
	@Column(name="secret_level")
	public SecretLevel getSecretLevel() {
		return secretLevel;
	}
	public void setSecretLevel(SecretLevel secretLevel) {
		this.secretLevel = secretLevel;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name="production_date")
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	
	@NotNull
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name="purchase_time")
	public Date getPurchaseTime() {
		return purchaseTime;
	}
	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}
	
	@ManyToOne
	@JoinColumn(name = "manufa_id", nullable = false)
	public Manufacturer getManufa() {
		return manufa;
	}
	public void setManufa(Manufacturer manufa) {
		this.manufa = manufa;
	}
	
}
