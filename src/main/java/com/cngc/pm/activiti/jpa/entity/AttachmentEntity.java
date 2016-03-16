package com.cngc.pm.activiti.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.cngc.pm.model.AttachType;

@Entity(name = "attachment")
public class AttachmentEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7313893974105561586L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;								//原始名，会重名
    private String thumbnailFilename;
    private String newFilename;					//需重新命名
    private String contentType;					//网络文件的类型和网页的编码，如“application/octet-stream”
    @Column(name = "path_")
    private String path;								//保存到服务器中的路径
    @Column(name = "size_")
    private Long size;									//文件大小
    @Enumerated(EnumType.ORDINAL)
	@Column(name="type_")
    private AttachType type;								//文件是哪个类的附件
    private Long thumbnailSize;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;									//上传日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
    @Transient
    private String url;
    @Transient
    private String thumbnailUrl;
    @Transient
    private String deleteUrl;
    @Transient
    private String deleteType;
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the thumbnailFilename
     */
    public String getThumbnailFilename() {
        return thumbnailFilename;
    }

    /**
     * @param thumbnailFilename the thumbnailFilename to set
     */
    public void setThumbnailFilename(String thumbnailFilename) {
        this.thumbnailFilename = thumbnailFilename;
    }

    /**
     * @return the newFilename
     */
    public String getNewFilename() {
        return newFilename;
    }

    /**
     * @param newFilename the newFilename to set
     */
    public void setNewFilename(String newFilename) {
        this.newFilename = newFilename;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the size
     */
    public Long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * @return the thumbnailSize
     */
    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    /**
     * @param thumbnailSize the thumbnailSize to set
     */
    public void setThumbnailSize(Long thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the thumbnailUrl
     */
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * @param thumbnailUrl the thumbnailUrl to set
     */
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    /**
     * @return the deleteUrl
     */
    public String getDeleteUrl() {
        return deleteUrl;
    }

    /**
     * @param deleteUrl the deleteUrl to set
     */
    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    /**
     * @return the deleteType
     */
    public String getDeleteType() {
        return deleteType;
    }

    /**
     * @param deleteType the deleteType to set
     */
    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }
    
    public AttachType getType() {
		return type;
	}

	public void setType(AttachType type) {
		this.type = type;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
    public String toString() {
        return "File{" + "name=" + name + ", thumbnailFilename=" + thumbnailFilename + ", newFilename=" + newFilename + ", contentType=" + contentType + ", url=" + url + ", thumbnailUrl=" + thumbnailUrl + ", deleteUrl=" + deleteUrl + ", deleteType=" + deleteType + '}';
    }
}
