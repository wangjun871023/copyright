package com.copyright.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * TeacherEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "teacher_table", catalog = "copyright")
public class TeacherEntity extends com.copyright.common.base.BaseForm implements
		java.io.Serializable {

	// Fields

	private String id;
	private String teacherCode;
	private String teacherName;
	private String teacherGender;
	private String teacherAcademy;
	private String teacherStatus;
	private String teacherSchool;
	private String courseId;
	private String createTime;
	private String updateTime;
	private String createUserId;
	private String updateUserId;
	//树
	private String text;
	private String parentId;
	private boolean checked;
	private boolean leaf;
	
	
	// Constructors

	/** default constructor */
	public TeacherEntity() {
	}

	/** full constructor */
	public TeacherEntity(String teacherCode, String teacherName,
			String teacherGender, String teacherAcademy, String teacherSchool,
			String courseId, String createTime, String updateTime,
			String createUserId, String updateUserId) {
		this.teacherCode = teacherCode;
		this.teacherName = teacherName;
		this.teacherGender = teacherGender;
		this.teacherAcademy = teacherAcademy;
		this.teacherSchool = teacherSchool;
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

	@Column(name = "teacher_code", length = 100)
	public String getTeacherCode() {
		return this.teacherCode;
	}

	public void setTeacherCode(String teacherCode) {
		this.teacherCode = teacherCode;
	}

	@Column(name = "teacher_name", length = 100)
	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Column(name = "teacher_gender", length = 100)
	public String getTeacherGender() {
		return this.teacherGender;
	}

	public void setTeacherGender(String teacherGender) {
		this.teacherGender = teacherGender;
	}

	@Column(name = "teacher_academy", length = 100)
	public String getTeacherAcademy() {
		return this.teacherAcademy;
	}

	public void setTeacherAcademy(String teacherAcademy) {
		this.teacherAcademy = teacherAcademy;
	}

	@Column(name = "teacher_school", length = 100)
	public String getTeacherSchool() {
		return this.teacherSchool;
	}

	public void setTeacherSchool(String teacherSchool) {
		this.teacherSchool = teacherSchool;
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
	@Column(name = "teacher_status", length = 100)
	public String getTeacherStatus() {
		return teacherStatus;
	}

	public void setTeacherStatus(String teacherStatus) {
		this.teacherStatus = teacherStatus;
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
	@Transient
	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
}