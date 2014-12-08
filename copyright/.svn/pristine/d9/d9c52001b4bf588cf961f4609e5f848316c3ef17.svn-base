package com.copyright.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * HomeworkEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "homework_table", catalog = "copyright")
public class HomeworkEntity extends com.copyright.common.base.BaseForm
		implements java.io.Serializable {

	// Fields

	private String id;
	private String homeworkCode;
	private String homeworkContent;
	private String homeworkName;
	private String startDate;
	private String endDate;
	private String studentNumFinish;
	private String studentNumUndo;
	private String studentNum;
	private String classHeadId;
	private String fileId;
	private String createTime;
	private String updateTime;
	private String createUserId;
	private String updateUserId;
	
	//Transition
	private String classHeadName;
	private String homeworkId;
	private String homeworkFileName;
	private String homeworkFilePath;
	private String homeworkStatus;
	
	
	private boolean leaf;
	private String text;
	private String parentId;
	private boolean checked;
	
	
	
	
	// Constructors

	/** default constructor */
	public HomeworkEntity() {
	}

	/** full constructor */
	public HomeworkEntity(String homeworkCode, String homeworkName,
			String startDate, String endDate, String studentNumFinish,
			String studentNumUndo, String classHeadId, String fileId,
			String createTime, String updateTime, String createUserId,
			String updateUserId) {
		this.homeworkCode = homeworkCode;
		this.homeworkName = homeworkName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.studentNumFinish = studentNumFinish;
		this.studentNumUndo = studentNumUndo;
		this.classHeadId = classHeadId;
		this.fileId = fileId;
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

	@Column(name = "start_date", length = 100)
	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", length = 100)
	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "student_num_finish", length = 100)
	public String getStudentNumFinish() {
		return this.studentNumFinish;
	}

	public void setStudentNumFinish(String studentNumFinish) {
		this.studentNumFinish = studentNumFinish;
	}

	@Column(name = "student_num_undo", length = 100)
	public String getStudentNumUndo() {
		return this.studentNumUndo;
	}

	public void setStudentNumUndo(String studentNumUndo) {
		this.studentNumUndo = studentNumUndo;
	}

	@Column(name = "class_head_id", length = 100)
	public String getClassHeadId() {
		return this.classHeadId;
	}

	public void setClassHeadId(String classHeadId) {
		this.classHeadId = classHeadId;
	}

	@Column(name = "file_id", length = 100)
	public String getFileId() {
		return this.fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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
	@Column(name = "student_num", length = 100)
	public String getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	@Column(name = "homework_content")
	public String getHomeworkContent() {
		return homeworkContent;
	}

	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}
	@Transient
	public String getClassHeadName() {
		return classHeadName;
	}

	public void setClassHeadName(String classHeadName) {
		this.classHeadName = classHeadName;
	}
	@Transient
	public String getHomeworkId() {
		return homeworkId;
	}

	public void setHomeworkId(String homeworkId) {
		this.homeworkId = homeworkId;
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
	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	@Transient
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	@Transient
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Transient
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Column(name = "homework_status")
	public String getHomeworkStatus() {
		return homeworkStatus;
	}

	public void setHomeworkStatus(String homeworkStatus) {
		this.homeworkStatus = homeworkStatus;
	}
}