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
import javax.validation.constraints.NotNull;

import com.cngc.pm.model.SysUser;
import com.cngc.pm.model.cms.Ci;

/**
 * 人员、系统或产品（CMDB）、角色关系
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
	private Ci cmdb;		//系统或产品（CMDB）
	private ManageType role;		//角色
	private SysUser user;			//人员
	private boolean del = false;			//是否删除
	private Date inTime;			//建立时间
	private Date delTime;			//删除时间
	
	@Column(name="is_del")
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="in_time",insertable=false)
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
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "system_id")
	public Ci getCmdb() {
		return cmdb;
	}
	public void setCmdb(Ci cmdb) {
		this.cmdb = cmdb;
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
