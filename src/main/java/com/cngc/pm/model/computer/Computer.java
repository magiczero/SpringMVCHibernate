package com.cngc.pm.model.computer;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cngc.pm.model.Group;

@Entity
@Table(name = "cmp_computer")
public class Computer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6231808744259003568L;
	private Long id;
	private String userName;
	private String diskSn;
	private String mac;
	private String ip;
	private String version;
	private Group group;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Timestamp lastCheckDate;
	private Long lastTaskId;
	private Timestamp lastRebackDate;
	private boolean	compliance;
	
	@ManyToOne(targetEntity=Group.class)
	@JoinColumn(name="group_id", referencedColumnName="id")
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="disk_sn")
	public String getDiskSn() {
		return diskSn;
	}
	public void setDiskSn(String diskSn) {
		this.diskSn = diskSn;
	}
	@Column(name="mac")
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	@Column(name="ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	@Column(name="version")
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") 
	@Column(name="create_date")
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") 
	@Column(name="update_date")
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") 
	@Column(name="last_check_date")
	public Timestamp getLastCheckDate() {
		return lastCheckDate;
	}
	public void setLastCheckDate(Timestamp lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") 
	@Column(name="last_reback_date")
	public Timestamp getLastRebackDate() {
		return lastRebackDate;
	}
	public void setLastRebackDate(Timestamp lastRebackDate) {
		this.lastRebackDate = lastRebackDate;
	}
	@Column(name="compliance")
	public boolean isCompliance() {
		return compliance;
	}
	public void setCompliance(boolean compliance) {
		this.compliance = compliance;
	}
	@Column(name="last_task_id")
	public Long getLastTaskId() {
		return lastTaskId;
	}
	public void setLastTaskId(Long lastTaskId) {
		this.lastTaskId = lastTaskId;
	}
	
}
