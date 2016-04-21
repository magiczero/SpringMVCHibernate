package com.cngc.pm.activiti.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "WK_TASK")
public class TaskJpaEntity {

    private Long id;
    private String fromUser;
    private String toUser;
    private String taskTitle;
    private String taskContent;
    private String taskResult;
    private String processInstanceId;
    private Date applyTime;
    private Date dueTime;
    private String userId;
    private Date executionTime;
    private boolean endbyuser;
    
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="FROM_USER")
    public String getFromUser(){
    	return fromUser;
    }
    public void setFromUser(String fromUser){
    	this.fromUser = fromUser;
    }
    
    @Column(name = "TO_USER")
    public String getToUser(){
    	return toUser;
    }
    public void setToUser(String toUser){
    	this.toUser = toUser;
    }
    
    @Column(name = "TASK_TITLE")
    public String getTaskTitle(){
    	return taskTitle;
    }
    public void setTaskTitle(String taskTitle){
    	this.taskTitle = taskTitle;
    }
    @Column(name = "TASK_CONTENT")
    public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	@Column(name = "TASK_RESULT")
    public String getTaskResult(){
    	return taskResult;
    }
    public void setTaskResult(String taskResult){
    	this.taskResult = taskResult;
    }
    
    @Column(name = "PROCESS_INSTANCE_ID")
    public String getProcessInstanceId() {
        return processInstanceId;
    }
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }
    
    @Column(name = "APPLY_TIME")
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name="due_time")
    public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	@Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Column(name="execution_time")
    public Date getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
	@Column(name="endbyuser")
	public boolean isEndbyuser() {
		return endbyuser;
	}
	public void setEndbyuser(boolean endbyuser) {
		this.endbyuser = endbyuser;
	}
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
