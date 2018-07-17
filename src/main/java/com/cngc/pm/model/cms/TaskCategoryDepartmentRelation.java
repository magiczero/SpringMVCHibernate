package com.cngc.pm.model.cms;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cngc.pm.model.Group;

/**
 * 不知道该怎么称呼这个类
 * @author young
 *
 */
@Embeddable
public class TaskCategoryDepartmentRelation {

	protected Category ciCategory;
	protected Group ciDepartment;
	//2018/07/03 修改状态的表示为提交修改 1，未提交修改 0
	protected int status;//过程状态，1-（已审核）关闭结束，2-未审核，5-审核中
	
	public TaskCategoryDepartmentRelation() {}
	
	protected TaskCategoryDepartmentRelation(Category ciCategory,Group ciDepartment, int status) {
		this.ciCategory = ciCategory;
		this.ciDepartment = ciDepartment;
		this.status = status;
	}
	
	@Column(name="status_")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name = "category_code", nullable = false)
	public Category getCiCategory() {
		return ciCategory;
	}
	public void setCiCategory(Category ciCategory) {
		this.ciCategory = ciCategory;
	}
	
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	public Group getCiDepartment() {
		return ciDepartment;
	}
	public void setCiDepartment(Group ciDepartment) {
		this.ciDepartment = ciDepartment;
	}
	
	
}
