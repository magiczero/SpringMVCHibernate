package com.cngc.pm.model.computer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cmp_rs_computerinfo")
public class RsComputerInfo {

	private Long id;
	private Long computerTaskId;
	private String model;
	private String mainboardManufacture;
	private String mainboardName;
	private String mainboardNo;
	private String cdrom;
	private String memory;
	private String cpu;
	private String computerName;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="computer_task_id")
	public Long getComputerTaskId() {
		return computerTaskId;
	}
	public void setComputerTaskId(Long computerTaskId) {
		this.computerTaskId = computerTaskId;
	}
	@Column(name="model_")
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	@Column(name="mainboard_manufacture")
	public String getMainboardManufacture() {
		return mainboardManufacture;
	}
	public void setMainboardManufacture(String mainboardManufacture) {
		this.mainboardManufacture = mainboardManufacture;
	}
	@Column(name="mainboard_name")
	public String getMainboardName() {
		return mainboardName;
	}
	public void setMainboardName(String mainboardName) {
		this.mainboardName = mainboardName;
	}
	@Column(name="mainboard_no")
	public String getMainboardNo() {
		return mainboardNo;
	}
	public void setMainboardNo(String mainboardNo) {
		this.mainboardNo = mainboardNo;
	}
	@Column(name="cdrom_")
	public String getCdrom() {
		return cdrom;
	}
	public void setCdrom(String cdrom) {
		this.cdrom = cdrom;
	}
	@Column(name="memory_")
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	@Column(name="cpu_")
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	@Column(name="computer_name")
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
}
