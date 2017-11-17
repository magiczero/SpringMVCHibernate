package com.cngc.pm.model.computer;

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

@Entity
@Table(name = "cmp_usb_alarm")
public class UsbAlarm {

	private Long id;
	private Computer computer;
	private String itemName;
	private String data;
	private Timestamp reportDate;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(targetEntity=Computer.class)
	@JoinColumn(name="computer_id", referencedColumnName="id")
	public Computer getComputer() {
		return computer;
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	@Column(name="item_name")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Column(name="data_")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss") 
	@Column(name="report_date")
	public Timestamp getReportDate() {
		return reportDate;
	}
	public void setReportDate(Timestamp reportDate) {
		this.reportDate = reportDate;
	}
	
	
	
}
