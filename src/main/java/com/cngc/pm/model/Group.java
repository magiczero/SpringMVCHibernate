package com.cngc.pm.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="sys_group")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5247384872979524619L;

	private Long id;
	private String groupName;
	private Group parentGroup;
	private Set<Group> child = new HashSet<Group>();
	private String explain;
	private Set<SysUser> users = new HashSet<>();
	private Integer order;
	
	@Column(name="order_")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")  
    @NotFound(action = NotFoundAction.IGNORE)
	@Where(clause="enabled=1")
	@OrderBy(value="depId asc")
	public Set<SysUser> getUsers() {
		return users;
	}
	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}
	@Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="group_name")
	@NotBlank
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@ManyToOne(targetEntity=Group.class,optional=true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "parent_id")
	public Group getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
	}
	
	//@OneToMany(targetEntity=Group.class)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentGroup")  
    @NotFound(action = NotFoundAction.IGNORE)
	@OrderBy(value="order asc")
	public Set<Group> getChild() {
		return child;
	}
	public void setChild(Set<Group> child) {
		this.child = child;
	}
	
	@Column(name="remark_")
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
}
