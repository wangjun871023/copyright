package com.copyright.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * HomeworkTemplateEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "homework_template_table")
public class HomeworkTemplateEntity extends com.copyright.common.base.BaseForm
		implements java.io.Serializable {

	// Fields

	private String id;
	private String homeworkCode;
	private String homeworkName;
	private String homeworkContent;
	private String fileId;
	private String courseId;
	private String createTime;
	private String updateTime;
	private String createUserId;
	private String updateUserId;
	private String homeworkStatus;
	
	
	//Transient
	private String homeworkFileName;
	private String homeworkFilePath;
	private String courseName;
	
	
	
	

	// Constructors

	/** default constructor */
	public HomeworkTemplateEntity() {
	}

	/** full constructor */
	public HomeworkTemplateEntity(String homeworkCode, String homeworkName,
			String homeworkContent, String fileId, String courseId,
			String createTime, String updateTime, String createUserId,
			String updateUserId) {
		this.homeworkCode = homeworkCode;
		this.homeworkName = homeworkName;
		this.homeworkContent = homeworkContent;
		this.fileId = fileId;
		this.courseId = courseId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createUserId = createUserId;
		this.updateUserId = updateUserId;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid2")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "homework_code", length = 100)
	public String getHomeworkCode() {
		return this.homeworkCode;
	}

	public void setHomeworkCode(String homeworkCode) {
		this.homeworkCode = homeworkCode;
	}

	@Column(name = "homework_name", length = 100)
	public String getHomeworkName() {
		return this.homeworkName;
	}

	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}

	@Column(name = "homework_content", length = 65535)
	public String getHomeworkContent() {
		return this.homeworkContent;
	}

	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}

	@Column(name = "file_id", length = 100)
	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Column(name = "course_id", length = 100)
	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Column(name = "create_time", length = 100)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 100)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "create_user_id", length = 100)
	public String getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@Column(name = "update_user_id", length = 100)
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	@Transient
	public String getHomeworkFileName() {
		return homeworkFileName;
	}

	public void setHomeworkFileName(String homeworkFileName) {
		this.homeworkFileName = homeworkFileName;
	}
	
	@Transient
	public String getHomeworkFilePath() {
		return homeworkFilePath;
	}

	public void setHomeworkFilePath(String homeworkFilePath) {
		this.homeworkFilePath = homeworkFilePath;
	}
	@Transient
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	@Column(name = "homework_status", length = 100)
	public String getHomeworkStatus() {
		return homeworkStatus;
	}

	public void setHomeworkStatus(String homeworkStatus) {
		this.homeworkStatus = homeworkStatus;
	}
	
}