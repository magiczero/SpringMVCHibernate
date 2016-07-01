package com.cngc.pm.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="document")
public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7659574962437647754L;
	
	private Long id;
	
	private String docId;							//用于版本标识某一文档
	private String name;
	private String keywords;
	private Integer versions;					//版本号，自动递增
	private Date createDate;
	private DocAuth auth;
	private Date issueTime;						//发布时间
	private boolean last;						//是否是最终版，用于搜索
	private String deposit;						//存放位置
	private String docNum;						//文档编号
	private SecretLevel secretLevel;		//密级
	private boolean enabled;					//删除标识位
	private String link;
	private SysUser user;
	private Style style;
	
	private Set<Attachment> attachs = new HashSet<>();							//附件
	private Set<CheckItems> itemSet = new HashSet<>();							//保密检查项关联关系
	
	@ManyToMany(targetEntity = CheckItems.class, mappedBy="docSet")
	public Set<CheckItems> getItemSet() {
		return itemSet;
	}

	public void setItemSet(Set<CheckItems> itemSet) {
		this.itemSet = itemSet;
	}

	//	@JoinColumnsOrFormulas(value = {
//	@JoinColumnOrFormula(column = @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)),
//	@JoinColumnOrFormula(formula = @JoinFormula(value = "0", referencedColumnName = "type_")) })
	@OneToMany(targetEntity = Attachment.class, fetch = FetchType.EAGER)
	@JoinColumn(name="type_id", referencedColumnName="id")
	@Where(clause="type_ = 0")
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
	@Size(min = 2, max = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotNull
	@Size(min = 2, max = 30)
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	@NotNull
	public Integer getVersions() {
		return versions;
	}
	public void setVersions(Integer versions) {
		this.versions = versions;
	}
	
	@Column(name="doc_id")
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@Column(name="issue_time")
	public Date getIssueTime() {
		return issueTime;
	}
	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	
	@Column(name="last_version")
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
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
	
	//private Set<Style> checkItems = new HashSet<>();								//检查项
	
//	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },fetch=FetchType.EAGER)  
//	@JoinTable(name = "doc_style", joinColumns = { @JoinColumn(name = "doc_id") }, inverseJoinColumns = { @JoinColumn(name = "style_id") })  
//	@OrderBy("id")  
//	public Set<Style> getCheckItems() {
//		return checkItems;
//	}
//	public void setCheckItems(Set<Style> checkItems) {
//		this.checkItems = checkItems;
//	}
	
	@ManyToOne(targetEntity=SysUser.class)
	@JoinColumn(name="user_id", referencedColumnName="user_id")
	public SysUser getUser() {
		return user;
	}
	
	public void setUser(SysUser user) {
		this.user = user;
	}
	
	@Column(name="doc_num")
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="secret_level")
	public SecretLevel getSecretLevel() {
		return secretLevel;
	}
	public void setSecretLevel(SecretLevel secretLevel) {
		this.secretLevel = secretLevel;
	}
	
	@ManyToOne(targetEntity=Style.class)
	@JoinColumn(name="style_id", referencedColumnName="id", nullable = false)
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	
	@Column(name="link_")
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
