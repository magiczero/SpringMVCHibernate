package com.cngc.pm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="server_software")
public class ServerSoftware {

	private Long id;
	private Servers server;
	private Software software;
	private Date installDate;
	private String remark;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(targetEntity=Servers.class)
	@JoinColumn(name = "server_id", nullable = false)
	public Servers getServer() {
		return server;
	}
	public void setServer(Servers server) {
		this.server = server;
	}
	
	@ManyToOne(targetEntity=Software.class)
	@JoinColumn(name = "software_id", nullable = false)
	public Software getSoftware() {
		return software;
	}
	public void setSoftware(Software software) {
		this.software = software;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name="install_date")
	public Date getInstallDate() {
		return installDate;
	}
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
