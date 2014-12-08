package com.copyright.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * ActiveCodeEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "active_code_table", catalog = "copyright")
public class ActiveCodeEntity extends com.copyright.common.base.BaseForm
		implements java.io.Serializable {

	// Fields

	private String id;
	private String activeCode;
	private String courseId;
	private String studentId;
	private String activeStatus;
	private String createTime;
	private String updateTime;
	private String createUserId;
	private String updateUserId;
	
	//transient
	private String courseName;
	private String studentNo;
	private String studentName;
	private String studentActiveCode;
	private String studentActiveTime;
	
	

	// Constructors

	/** default constructor */
	public ActiveCodeEntity() {
	}

	/** full constructor */
	public ActiveCodeEntity(String activeCode, String courseId,
			String studentId, String activeStatus, String studentActiveCode,
			String studentActiveTime, String createTime, String updateTime,
			String createUserId, String updateUserId) {
		this.activeCode = activeCode;
		this.courseId = courseId;
		this.studentId = studentId;
		this.activeStatus = activeStatus;
		this.studentActiveCode = studentActiveCode;
		this.studentActiveTime = studentActiveTime;
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

	@Column(name = "active_code", length = 100)
	public String getActiveCode() {
		return this.activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	@Column(name = "course_id", length = 100)
	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@Column(name = "student_id", length = 100)
	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(name = "active_status", length = 100)
	public String getActiveStatus() {
		return this.activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Transient
	public String getStudentActiveCode() {
		return this.studentActiveCode;
	}

	public void setStudentActiveCode(String studentActiveCode) {
		this.studentActiveCode = studentActiveCode;
	}

	@Transient
	public String getStudentActiveTime() {
		return this.studentActiveTime;
	}

	public void setStudentActiveTime(String studentActiveTime) {
		this.studentActiveTime = studentActiveTime;
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
	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	@Transient
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	@Transient
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}