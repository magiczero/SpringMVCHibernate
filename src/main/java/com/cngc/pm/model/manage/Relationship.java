package com.cngc.pm.model.manage;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cngc.pm.model.Style;
import com.cngc.pm.model.SysUser;

/**
 * 人员、系统、角色关系
 * @author young
 *
 */
@Entity
@Table(name="manage_position")
public class Relationship implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6042020083497773540L;

	private Long id;
	private Style style;		//系统
	private ManageType role;		//角色
	private SysUser user;			//人员
	private boolean isDel;			//是否删除
	private Date inTime;			//建立时间
	private Date delTime;			//删除时间
	
	@Column(name="is_del")
	public boolean isDel() {
		return isDel;
	}
	public void setDel(boolean isDel) {
		this.isDel = isDel;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="in_time")
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="del_time")
	public Date getDelTime() {
		return delTime;
	}
	public void setDelTime(Date delTime) {
		this.delTime = delTime;
	}
	
	@Id 
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "system_id")
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="character_")
	public ManageType getRole() {
		return role;
	}
	public void setRole(ManageType role) {
		this.role = role;
	}
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
}
