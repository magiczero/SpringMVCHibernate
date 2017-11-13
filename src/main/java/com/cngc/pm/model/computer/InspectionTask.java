package com.cngc.pm.model.computer;

import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cmp_task")
public class InspectionTask {

	private Long id;
	private String taskName;
	private String taskInfo;
	private InspectionTarget target;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Set<InspectionItem> items = new LinkedHashSet<InspectionItem>();
	private Set<Computer> computers = new LinkedHashSet<Computer>();
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="task_name")
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	@Column(name="task_info")
	public String getTaskInfo() {
		return taskInfo;
	}
	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}
	@ManyToOne(targetEntity=InspectionTarget.class)
	@JoinColumn(name="target_id", referencedColumnName="target_id")
	public InspectionTarget getTarget() {
		return target;
	}
	public void setTarget(InspectionTarget target) {
		this.target = target;
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
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "cmp_task_item", joinColumns = { @JoinColumn(name = "task_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<InspectionItem> getItems() {
		return items;
	}
	public void setItems(Set<InspectionItem> items) {
		this.items = items;
	}
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "cmp_task_computer", joinColumns = { @JoinColumn(name = "task_id") }, inverseJoinColumns = { @JoinColumn(name = "computer_id") })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Computer> getComputers() {
		return computers;
	}
	public void setComputers(Set<Computer> computers) {
		this.computers = computers;
	}
}
