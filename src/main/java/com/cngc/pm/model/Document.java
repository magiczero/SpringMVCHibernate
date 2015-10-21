package com.cngc.pm.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="document")
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7659574962437647754L;
	
	private Long id;
	
	private String name;
	private String keywords;
	private String versions;
	private Date createDate;
	private DocAuth auth;
	private Set<Style> styles = new HashSet<>();
	private Set<Attachment> attachs = new HashSet<>();
	
	@OneToMany(targetEntity=Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name="type_id", referencedColumnName="id")
	public Set<Attachment> getAttachs() {
		return attachs;
	}
	public void setAttachs(Set<Attachment> attachs) {
		this.attachs = attachs;
	}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@NotNull
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getVersions() {
		return versions;
	}
	public void setVersions(String versions) {
		this.versions = versions;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name="in_while")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="auth_")
	public DocAuth getAuth() {
		return auth;
	}
	public void setAuth(DocAuth auth) {
		this.auth = auth;
	}
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch=FetchType.EAGER)  
	@JoinTable(name = "doc_style", joinColumns = { @JoinColumn(name = "doc_id") }, inverseJoinColumns = { @JoinColumn(name = "style_id") })  
	@OrderBy("id")  
	public Set<Style> getStyles() {
		return styles;
	}
	public void setStyles(Set<Style> styles) {
		this.styles = styles;
	}
}
