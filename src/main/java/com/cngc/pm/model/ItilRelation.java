package com.cngc.pm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wk_itil_relation")
public class ItilRelation {
	private Long id;
	private Long primaryId;
	private Long secondaryId;
	private String relationType;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="primary_id")
	public Long getPrimaryId() {
		return primaryId;
	}
	public void setPrimaryId(Long primaryId) {
		this.primaryId = primaryId;
	}
	@Column(name="secondary_id")
	public Long getSecondaryId() {
		return secondaryId;
	}
	public void setSecondaryId(Long secondaryId) {
		this.secondaryId = secondaryId;
	}
	@Column(name="relation_type")
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	

}
