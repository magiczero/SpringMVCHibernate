package com.cngc.pm.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="servicer")
public class Servers extends Asset {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6386133773885377851L;
	//private String num;				//服务器编号
	private String cpu;					
	private String memory;			//内存
	private String cdrom;				//光驱
	private String raidCard;			//raid卡
	private String raidModel;			//raid模式
	private int powerNum;				//电源数量
	
	private Set<CabinetPosition> position = new HashSet<CabinetPosition>();		//机柜位置
	private Set<HardDisk> disks = new HashSet<HardDisk>();									//硬盘
	private Set<NetworkCard> networkCards = new HashSet<NetworkCard>();		//网卡
	
	
//	public String getNum() {
//		return num;
//	}
//	public void setNum(String num) {
//		this.num = num;
//	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getCdrom() {
		return cdrom;
	}
	public void setCdrom(String cdrom) {
		this.cdrom = cdrom;
	}
	
	@Column(name="raid_card")
	public String getRaidCard() {
		return raidCard;
	}
	public void setRaidCard(String raidCard) {
		this.raidCard = raidCard;
	}
	
	@Column(name="raid_model")
	public String getRaidModel() {
		return raidModel;
	}
	public void setRaidModel(String raidModel) {
		this.raidModel = raidModel;
	}
	
	@Column(name="power_num")
	public int getPowerNum() {
		return powerNum;
	}
	public void setPowerNum(int powerNum) {
		this.powerNum = powerNum;
	}
	
	@OneToMany(targetEntity=CabinetPosition.class)
	@JoinColumn(name="server_id", referencedColumnName="id")
	public Set<CabinetPosition> getPosition() {
		return position;
	}
	public void setPosition(Set<CabinetPosition> position) {
		this.position = position;
	}
	
	@OneToMany(targetEntity=HardDisk.class, mappedBy="server")
	//@JoinColumn(name="server_id", referencedColumnName="id")
	public Set<HardDisk> getDisks() {
		return disks;
	}
	public void setDisks(Set<HardDisk> disks) {
		this.disks = disks;
	}
	
	@OneToMany(targetEntity=NetworkCard.class)
	@JoinColumn(name="server_id", referencedColumnName="id")
	public Set<NetworkCard> getNetworkCards() {
		return networkCards;
	}
	public void setNetworkCards(Set<NetworkCard> networkCards) {
		this.networkCards = networkCards;
	}
	
}
