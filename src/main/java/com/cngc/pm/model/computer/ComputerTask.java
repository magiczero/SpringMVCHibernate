package com.cngc.pm.model.computer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "cmp_task_computer")  
public class ComputerTask implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7911214288128983128L;
	private Long id;
	private Computer computer;
	private InspectionTask task;
	private String	status;
	private String statusName;
	private String file;
	private boolean compliance;
	
	@Id 
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne(targetEntity=InspectionTask.class)
	@JoinColumn(name="task_id", referencedColumnName="id")
	public InspectionTask getTask() {
		return task;
	}
	public void setTask(InspectionTask task) {
		this.task = task;
	}
	@ManyToOne(targetEntity=Computer.class)
	@JoinColumn(name="computer_id", referencedColumnName="id")
	public Computer getComputer() {
		return computer;
	}
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	@Column(name="status_")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Formula(value="(SELECT a.code_name FROM sys_code a WHERE a.type_='INSPECTIONTASK_STATUS' AND a.code_= status_)")
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Column(name="file_")
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	@Column(name="compliance")
	public boolean isCompliance() {
		return compliance;
	}
	public void setCompliance(boolean compliance) {
		this.compliance = compliance;
	}
	
	
}
