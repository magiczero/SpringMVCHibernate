package com.cngc.pm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wk_task")
public class LeaderTask implements Serializable{

	private Long id;
	private String fromUser;
	private String toUser;
	private String taskTitle;
	private String taskDesc;
	private String taskResult;
	private String processInstanceId;
	private String currentActivityId;
	private String currentActivityName;
	private Date applyTime;
	private String userId;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="from_user")
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	@Column(name="to_user")
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	@Column(name="task_title")
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	@Column(name="task_desc")
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	@Column(name="task_result")
	public String getTaskResult() {
		return taskResult;
	}
	public void setTaskResult(String taskResult) {
		this.taskResult = taskResult;
	}
	@Column(name="process_instance_id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	@Column(name="current_activity_id")
	public String getCurrentActivityId() {
		return currentActivityId;
	}
	public void setCurrentActivityId(String currentActivityId) {
		this.currentActivityId = currentActivityId;
	}
	@Column(name="current_activity_name")
	public String getCurrentActivityName() {
		return currentActivityName;
	}
	public void setCurrentActivityName(String currentActivityName) {
		this.currentActivityName = currentActivityName;
	}
	@Column(name="apply_time")
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
