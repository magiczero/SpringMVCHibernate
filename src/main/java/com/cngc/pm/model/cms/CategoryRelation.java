package com.cngc.pm.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 分类之间的关系
 * @author andy
 *
 */
@Entity
@Table(name = "cms_category_relation")
public class CategoryRelation {

	private Long id;
	private String categoryCodePrimary;
	private String categoryCodeSecondary;
	private String relationId;

	private Category categoryPrimary;
	private Category categorySecondary;
	private Relation relation;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "category_code_primary")
	public String getCategoryCodePrimary() {
		return categoryCodePrimary;
	}

	public void setCategoryCodePrimary(String categoryCodePrimary) {
		this.categoryCodePrimary = categoryCodePrimary;
	}

	@Column(name = "category_code_secondary")
	public String getCategoryCodeSecondary() {
		return categoryCodeSecondary;
	}

	public void setCategoryCodeSecondary(String categoryCodeSecondary) {
		this.categoryCodeSecondary = categoryCodeSecondary;
	}

	@Column(name = "relation_id")
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_code_primary", referencedColumnName = "category_code", insertable = false, updatable = false)
	public Category getCategoryPrimary() {
		return categoryPrimary;
	}

	public void setCategoryPrimary(Category categoryPrimary) {
		this.categoryPrimary = categoryPrimary;
	}

	@ManyToOne(targetEntity = Category.class)
	@JoinColumn(name = "category_code_secondary", insertable = false, updatable = false)
	public Category getCategorySecondary() {
		return categorySecondary;
	}

	public void setCategorySecondary(Category categorySecondary) {
		this.categorySecondary = categorySecondary;
	}

	@ManyToOne(targetEntity = Relation.class)
	@JoinColumn(name = "relation_id", insertable = false, updatable = false)
	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

}
