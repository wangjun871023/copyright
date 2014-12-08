package com.copyright.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * HomeworkStudentEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "homework_student_table", catalog = "copyright")
public class HomeworkStudentEntity extends com.copyright.common.base.BaseForm
		implements java.io.Serializable {

	// Fields

	private String id;
	private String homeworkId;
	private String homeworkName;
	private String homeworkStatus;
	private String homeworkFileId;
	private String homeworkContent;
	private String startDate;
	private String endDate;
	private String classHeadName;
	private String teacherName;
	private String homeworkFileName;
	private String homeworkFilePath;
	
	private String studentHomeworkId;
	
	
	private String studentId;
	private String studentHomeworkStatus;
	private String studentHomeworkContent;
	private String studentFileId;
	private String studentHomeworkFileName;
	private String studentHomeworkFilePath;
	private String studentScore;
	
	private String studentNo;
	private String studentName;
	private String studentGender;
	private String studentSpecialty;
	private String studentClass;
	private String studentGrade;
	
	
	
	
	
	private String createTime;
	private String updateTime;
	private String createUserId;
	private String updateUserId;

	// Constructors

	/** default constructor */
	public HomeworkStudentEntity() {
	}

	/** full constructor */
	public HomeworkStudentEntity(String homeworkId, String homeworkName,
			String homeworkStatus, String homeworkFileId,
			String homeworkContent, String startTime, String endTime,
			String classHeadName, String teacherName, String studentId,
			String studentHomeworkStatus, String studentHomeworkContent,
			String studentFileId, String createTime, String updateTime,
			String createUserId, String updateUserId) {
		this.homeworkId = homeworkId;
		this.homeworkName = homeworkName;
		this.homeworkStatus = homeworkStatus;
		this.homeworkFileId = homeworkFileId;
		this.homeworkContent = homeworkContent;
		this.classHeadName = classHeadName;
		this.teacherName = teacherName;
		this.studentId = studentId;
		this.studentHomeworkStatus = studentHomeworkStatus;
		this.studentHomeworkContent = studentHomeworkContent;
		this.studentFileId = studentFileId;
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

	@Column(name = "homework_id", length = 100)
	public String getHomeworkId() {
		return this.homeworkId;
	}

	public void setHomeworkId(String homeworkId) {
		this.homeworkId = homeworkId;
	}

	@Transient
	public String getHomeworkName() {
		return this.homeworkName;
	}

	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}

	@Transient
	public String getHomeworkStatus() {
		return this.homeworkStatus;
	}

	public void setHomeworkStatus(String homeworkStatus) {
		this.homeworkStatus = homeworkStatus;
	}

	@Transient
	public String getHomeworkFileId() {
		return this.homeworkFileId;
	}

	public void setHomeworkFileId(String homeworkFileId) {
		this.homeworkFileId = homeworkFileId;
	}

	@Transient
	public String getHomeworkContent() {
		return this.homeworkContent;
	}

	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}
	@Transient
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@Transient
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Transient
	public String getClassHeadName() {
		return this.classHeadName;
	}

	public void setClassHeadName(String classHeadName) {
		this.classHeadName = classHeadName;
	}

	@Transient
	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Column(name = "student_id", length = 100)
	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Column(name = "student_homework_status")
	public String getStudentHomeworkStatus() {
		return this.studentHomeworkStatus;
	}

	public void setStudentHomeworkStatus(String studentHomeworkStatus) {
		this.studentHomeworkStatus = studentHomeworkStatus;
	}

	@Column(name = "student_homework_content")
	public String getStudentHomeworkContent() {
		return this.studentHomeworkContent;
	}

	public void setStudentHomeworkContent(String studentHomeworkContent) {
		this.studentHomeworkContent = studentHomeworkContent;
	}

	@Column(name = "student_file_id")
	public String getStudentFileId() {
		return this.studentFileId;
	}

	public void setStudentFileId(String studentFileId) {
		this.studentFileId = studentFileId;
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
	public String getStudentHomeworkFileName() {
		return studentHomeworkFileName;
	}

	public void setStudentHomeworkFileName(String studentHomeworkFileName) {
		this.studentHomeworkFileName = studentHomeworkFileName;
	}
	@Transient
	public String getStudentHomeworkFilePath() {
		return studentHomeworkFilePath;
	}

	public void setStudentHomeworkFilePath(String studentHomeworkFilePath) {
		this.studentHomeworkFilePath = studentHomeworkFilePath;
	}
	@Column(name = "student_score", length = 100)
	public String getStudentScore() {
		return studentScore;
	}

	public void setStudentScore(String studentScore) {
		this.studentScore = studentScore;
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
	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}
	@Transient
	public String getStudentSpecialty() {
		return studentSpecialty;
	}

	public void setStudentSpecialty(String studentSpecialty) {
		this.studentSpecialty = studentSpecialty;
	}
	@Transient
	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	@Transient
	public String getStudentGrade() {
		return studentGrade;
	}

	public void setStudentGrade(String studentGrade) {
		this.studentGrade = studentGrade;
	}
	@Transient
	public String getStudentHomeworkId() {
		return studentHomeworkId;
	}

	public void setStudentHomeworkId(String studentHomeworkId) {
		this.studentHomeworkId = studentHomeworkId;
	}
}