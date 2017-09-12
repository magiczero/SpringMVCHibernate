package com.cngc.pm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;
//import org.hibernate.envers.Audited;
//import org.hibernate.envers.NotAudited;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="maintain_record")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class MaintainRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String equipName;
	private String equipNum;
	private String equipId;
	@Column(name="equip_id")
	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	private Date maintainTime;
	private String addIn;			//外接设备
	private String circs;			//执行情况
	private String executor;		//执行者
	private String escort;			//陪同人
	private String remark;
	private int type;				//类型
	private int role;
	
	@Column(name="role_")
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Transient
	private String executorName;
	
	private Set<Attachment> attachs = new HashSet<>();	
	//增加一个文档
	@OneToMany(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name="type_id", referencedColumnName="id")
	@Where(clause="type_ = 9")
	public Set<Attachment> getAttachs() {
		return attachs;
	}

	public void setAttachs(Set<Attachment> attachs) {
		this.attachs = attachs;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="equip_name")
	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	@Column(name="equip_num")
	public String getEquipNum() {
		return equipNum;
	}

	public void setEquipNum(String equipNum) {
		this.equipNum = equipNum;
	}
	
	@Column(name="maintain_time")
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
	public Date getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(Date maintainTime) {
		this.maintainTime = maintainTime;
	}

	@Column(name="add_in")
	public String getAddIn() {
		return addIn;
	}

	public void setAddIn(String addIn) {
		this.addIn = addIn;
	}

	@Column(name="performance_circs")
	public String getCircs() {
		return circs;
	}

	public void setCircs(String circs) {
		this.circs = circs;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getEscort() {
		return escort;
	}

	public void setEscort(String escort) {
		this.escort = escort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="type_")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Formula(value="(SELECT a.NAME FROM sys_users a WHERE a.USERNAME=executor)")
	public String getExecutorName() {
		return executorName;
	}

	public void setExecutorName(String executorName) {
		this.executorName = executorName;
	}
	
}
