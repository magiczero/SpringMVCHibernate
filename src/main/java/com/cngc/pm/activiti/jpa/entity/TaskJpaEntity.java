package com.cngc.pm.activiti.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(name = "WK_TASK")
public class TaskJpaEntity {

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
    
    @Column(name = "TASK_DESC")
    public String getTaskDesc(){
    	return taskDesc;
    }
    public void setTaskDesc(String taskDesc){
    	this.taskDesc = taskDesc;
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
    
    @Column(name = "CURRENT_ACTIVITY_ID")
    public String getCurrentActivityId(){
    	return currentActivityId;
    }
    public void setCurrentActivityId(String currentActivityId){
    	this.currentActivityId = currentActivityId;
    }
    
    @Column(name = "CURRENT_ACTIVITY_NAME")
    public String getCurrentActivityName(){
    	return currentActivityName;
    }
    public void setCurrentActivityName(String currentActivityName){
    	this.currentActivityName = currentActivityName;
    }
    
    @Column(name = "APPLY_TIME")
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    @Column(name = "USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
