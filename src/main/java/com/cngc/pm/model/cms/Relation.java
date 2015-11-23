package com.cngc.pm.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 关系
 * 
 * @author andy
 *
 */
@Entity
@Table(name = "cms_relation")
public class Relation {

	private String relationId;
	private String relationName;
	private String relationDescription;

	@Id
	@Column(name = "relation_id")
	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	@Column(name = "relation_name")
	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	@Column(name = "relation_description")
	public String getRelationDescription() {
		return relationDescription;
	}

	public void setRelationDescription(String relationDescription) {
		this.relationDescription = relationDescription;
	}

}
