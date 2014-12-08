package com.copyright.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * StudentEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "student_table")
public class StudentEntity extends com.copyright.common.base.BaseForm implements
		java.io.Serializable {

	// Fields

	private String id;
	private String studentCode; // 用户名
	private String studentNo;
	private String studentName;
	private String studentGender;
	private String studentSpecialty;
	private String studentClass;
	private String stateSelectedClass;
	private String studentGrade;
	private String studentAgain;
	private String studentStatus;
	private String classHeadId;
	private String createTime;
	private String updateTime;
	private String createUserId;
	private String updateUserId;
	private String courseId;

	// 瞬时
	private String classHeadName;
	private String studentActiveCode;
	private String studentActiveTime;
	private String activeStatus;

	// Constructors

	/** default constructor */
	public StudentEntity() {
	}

	/** full constructor */
	public StudentEntity(String studentCode, String studentNo,
			String studentName, String studentGender, String studentSpecialty,
			String stateSelectedClass, String studentGrade,
			String studentAgain, String classHeadId, String createTime,
			String updateTime, String createUserId, String updateUserId) {
		this.studentCode = studentCode;
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentGender = studentGender;
		this.studentSpecialty = studentSpecialty;
		this.stateSelectedClass = stateSelectedClass;
		this.studentGrade = studentGrade;
		this.studentAgain = studentAgain;
		this.classHeadId = classHeadId;
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

	@Column(name = "student_code", length = 100)
	public String getStudentCode() {
		return this.studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	@Column(name = "student_no", length = 100)
	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	@Column(name = "student_name", length = 100)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(name = "student_gender", length = 100)
	public String getStudentGender() {
		return this.studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	@Column(name = "student_specialty", length = 100)
	public String getStudentSpecialty() {
		return this.studentSpecialty;
	}

	public void setStudentSpecialty(String studentSpecialty) {
		this.studentSpecialty = studentSpecialty;
	}

	@Column(name = "state_selected_class", length = 100)
	public String getStateSelectedClass() {
		return this.stateSelectedClass;
	}

	public void setStateSelectedClass(String stateSelectedClass) {
		this.stateSelectedClass = stateSelectedClass;
	}

	@Column(name = "student_grade", length = 100)
	public String getStudentGrade() {
		return this.studentGrade;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}

	@Column(name = "student_again", length = 100)
	public String getStudentAgain() {
		return this.studentAgain;
	}

	public void setStudentAgain(String studentAgain) {
		this.studentAgain = studentAgain;
	}

	@Column(name = "class_head_id", length = 100)
	public String getClassHeadId() {
		return this.classHeadId;
	}

	public void setClassHeadId(String classHeadId) {
		this.classHeadId = classHeadId;
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

	@Column(name = "student_class", length = 100)
	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	@Transient
	public String getClassHeadName() {
		return classHeadName;
	}

	public void setClassHeadName(String classHeadName) {
		this.classHeadName = classHeadName;
	}

	@Column(name = "student_status", length = 100)
	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	@Column(name = "course_id", length = 100)
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	@Column(name = "student_active_code", length = 100)
	public String getStudentActiveCode() {
		return studentActiveCode;
	}

	public void setStudentActiveCode(String studentActiveCode) {
		this.studentActiveCode = studentActiveCode;
	}
	@Column(name = "student_active_time", length = 100)
	public String getStudentActiveTime() {
		return studentActiveTime;
	}

	public void setStudentActiveTime(String studentActiveTime) {
		this.studentActiveTime = studentActiveTime;
	}
	@Transient
	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
}