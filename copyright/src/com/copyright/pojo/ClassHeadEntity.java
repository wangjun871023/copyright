package com.copyright.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * ClassHeadEntity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "class_head_table", catalog = "copyright")
public class ClassHeadEntity extends com.copyright.common.base.BaseForm
		implements java.io.Serializable{

	// Fields

	private String id;
	private String classHeadName;
	private String teacherId;
	private String startDate;
	private String endDate;
	private String createTime;
	private String updateTime;
	private String createUserId;
	private String updateUserId;
	private String classHeadStatus;
	
	private String text;
	private String parentId;
	private boolean checked;
	private boolean leaf;
	
	private String teacherName;
	
	
	// Constructors

	/** default constructor */
	public ClassHeadEntity() {
	}

	/** full constructor */
	public ClassHeadEntity(String classHeadName, String teacherId,
			String startDate, String endDate, String createTime,
			String updateTime, String createUserId, String updateUserId) {
		this.classHeadName = classHeadName;
		this.teacherId = teacherId;
		this.startDate = startDate;
		this.endDate = endDate;
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

	@Column(name = "class_head_name", length = 100)
	public String getClassHeadName() {
		return this.classHeadName;
	}

	public void setClassHeadName(String classHeadName) {
		this.classHeadName = classHeadName;
	}

	@Column(name = "teacher_id", length = 100)
	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
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
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
	@Transient
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Transient
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	@Column(name = "class_head_status", length = 100)
	public String getClassHeadStatus() {
		return classHeadStatus;
	}

	public void setClassHeadStatus(String classHeadStatus) {
		this.classHeadStatus = classHeadStatus;
	}
	
	
}